package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
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
		String broker = "tcp://eu.thethings.network:1883";
		String clientId = "ApplicationServer";
		MemoryPersistence persistence = new MemoryPersistence();
		String appId = "senioren-notfall-hilfe-system";
		char[] apiKey = "ttn-account-v2.xqhU5_c5SPEKLNDpg38Ah5er2XqEZI0Gxt_iobseDmQ".toCharArray();

		/*String broker = "tcp://10.0.0.9:1883";
		String clientId = "ApplicationServer";
		MemoryPersistence persistence = new MemoryPersistence();
		String appId = "my-new-application";
		char[] apiKey = "NNSXS.75BJZKY5I6IVDGAOVXLXOXOW6DB2Y7A534P53KA.JTZIWSFG6LADZD2YBLF2YJUV5PL2572J5TFC7N3AJENYOKCU6D7A".toCharArray();
		*/
		try {
			Data.mqttClient = new MqttClient(broker, clientId, persistence);
		    MqttConnectOptions options = new MqttConnectOptions();
		    options.setUserName(appId);
		    options.setPassword(apiKey);
		    options.setCleanSession(true);
		    Data.mqttClient.connect(options);
		}
		catch (MqttException e) {
			e.printStackTrace();
		}
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
