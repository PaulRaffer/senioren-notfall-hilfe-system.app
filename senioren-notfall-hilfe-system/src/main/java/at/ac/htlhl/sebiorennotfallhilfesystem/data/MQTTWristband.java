package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasSize;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class MqttWristband extends Wristband {

	public MqttWristband(MqttClient client, String name, Location location)
	{
		super(name, location);
		String topic = "#";

		try {
			client.subscribe(topic, (t, msg) -> {
				byte[] payload = msg.getPayload();
				decode(payload);
			});
		}
		catch (MqttException e) {
			e.printStackTrace();
		}
	}

	private void decode(byte[] payload)
	{
		//payload = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		// Decode an uplink message from a buffer
		// (array) of bytes to an object of fields.
		int i = 0;
		int latitude = 0;
		int longitude = 0;
		int voltage = 0;
		int n = 0;  // Um richtig shiften zu können!
		int sizelong = 4; // Um richtig shiften zu können! Ansonsten würde falscher Wert entstehen!


		if(payload[0] == 0x00) {
			setStatus(0);  // Normaler Grund (Einfach die Zeit abgekaufen)
		}
		else if(payload[0] == 0xAA) {
			setStatus(1);  // Akku wird schwach (5% oder weniger!)
		}
		else if(payload[0] == 0xFF) {
			setStatus(2);  // Notfall!!!
		}

		for (i = 1; i <= 1 + sizelong-1; i++) {
			latitude = latitude | (payload[i] << (sizelong-1-n)*8);  // Richtiges Byte an richtige Stelle schieben!
			n++;
		}
		getLocation().setLatitude(latitude / 1000000);

		n = 0;
		for (; i <= 1 + 2*sizelong-1; i++) {
			longitude = longitude | (payload[i] << (sizelong-1-n)*8);  // Richtiges Byte an richtige Stelle schieben!
			n++;
		}
		getLocation().setLongitude(longitude / 1000000);

		n = 0;
		for (; i <= 1 + 3*sizelong-1; i++) {
			voltage = voltage | (payload[i] << (sizelong-1-n)*8);  // Richtiges Byte an richtige Stelle schieben!
			n++;
		}
		setVoltage(voltage / 10000);
	}

}
