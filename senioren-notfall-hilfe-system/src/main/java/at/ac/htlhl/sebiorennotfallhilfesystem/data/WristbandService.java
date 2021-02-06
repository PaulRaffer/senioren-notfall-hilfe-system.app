package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WristbandService {

	private final List<Wristband> wristbands = new ArrayList<>();

	public WristbandService()
	{
	}

	public List<Wristband> getAll()
	{
		return Collections.unmodifiableList(wristbands);
	}

	public Wristband getWristbandByName(String name)
	{
		for (Wristband w : wristbands) {
			if (w.getName().equals(name)) {
				return w;
			}
		}
		return null;
	}

	public void add(Wristband wristband)
	{
		// protect concurrent access since MqttWristbandService is a singleton
		synchronized (wristbands) {
			wristbands.add(wristband);
		}
	}
}
