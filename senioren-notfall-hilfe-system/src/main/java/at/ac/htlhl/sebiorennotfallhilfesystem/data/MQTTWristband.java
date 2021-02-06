package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasSize;
import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonFactory;
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

	public MqttWristband()
	{
		//super(name, location);
		String topic = "#";

		try {
			Data.mqttClient.subscribe(topic, (t, msg) -> {
				decode(new String(msg.getPayload()));
			});
		}
		catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void set(final MqttWristband wristband)
	{
		this.setApp_id(wristband.getApp_id());
		this.setDev_id(wristband.getDev_id());
		this.setHardware_serial(wristband.getHardware_serial());
		this.setPort(wristband.getPort());
		this.setCounter(wristband.getCounter());
		this.setPayload_raw(wristband.getPayload_raw());
		this.setPayload_fields(wristband.getPayload_fields());
	}

	private void decode(String dataJson)
	{
		System.out.println(dataJson);
		dataJson = "{\"app_id\":\"senioren-notfall-hilfe-system\",\"dev_id\":\"armband_v01_4\",\"hardware_serial\":\"0000000000000004\",\"port\":15,\"counter\":10327,\"payload_raw\":\"AAAAAAAAAAAAAAAAAIMv\",\"payload_fields\":{\"altitude\":0,\"hdop\":0,\"latitude\":14,\"longitude\":16,\"status\":0,\"voltage\":3.3583},\"metadata\":{\"time\":\"2021-02-05T22:25:18.327075713Z\",\"frequency\":867.5,\"modulation\":\"LORA\",\"data_rate\":\"SF7BW125\",\"airtime\":66816000,\"coding_rate\":\"4/5\",\"gateways\":[{\"gtw_id\":\"eui-58a0cbfffe8021bb\",\"timestamp\":2635901532,\"time\":\"2021-02-05T22:25:18.314580917Z\",\"channel\":0,\"rssi\":-59,\"snr\":7.75,\"rf_chain\":0}]}}";
		try {
			ObjectMapper mapper = new ObjectMapper();
			final MqttWristband wristband = mapper.readValue(dataJson, MqttWristband.class);
			set(wristband);

			System.out.println(getApp_id());
			System.out.println(wristband.getPayload_fields().getLatitude());
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
