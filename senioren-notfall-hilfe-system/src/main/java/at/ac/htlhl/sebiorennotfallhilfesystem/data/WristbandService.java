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

	public Wristband getWristbandByDev_id(String dev_id)
	{
		for (Wristband wb : wristbands) {
			if (wb.getDev_id().equals(dev_id)) {
				return wb;
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
