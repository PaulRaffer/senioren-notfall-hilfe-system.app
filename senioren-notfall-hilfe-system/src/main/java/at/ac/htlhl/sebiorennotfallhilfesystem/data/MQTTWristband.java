package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTWristband extends Wristband {

	public MQTTWristband(String name, Location location)
	{
		super(name, location);

		String topic = "#";
		String broker = "tcp://10.0.0.9:1883";
		String clientId = "NetworkServer";
		MemoryPersistence persistence = new MemoryPersistence();
		String appId = "my-new-application";
		char[] apiKey = "NNSXS.75BJZKY5I6IVDGAOVXLXOXOW6DB2Y7A534P53KA.JTZIWSFG6LADZD2YBLF2YJUV5PL2572J5TFC7N3AJENYOKCU6D7A".toCharArray();

		try {
			MqttClient client = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName(appId);
			options.setPassword(apiKey);
			options.setCleanSession(true);

			client.connect(options);
			client.subscribe(topic, (t, msg) -> {
				byte[] payload = msg.getPayload();
				setName(payload.toString());
			});

			//client.disconnect();
		}
		catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void updateLocation()
	{

	}

}
