package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WristbandService {

	private final List<Wristband> wristbands = new ArrayList<>();

	public WristbandService(
			final String broker, final String clientId,
			final String appId, final String apiKey,
			final String topic)
	{
		try {
			Data.mqttClient = new MqttClient(broker, clientId, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName(appId);
			options.setPassword(apiKey.toCharArray());
			options.setCleanSession(true);
			Data.mqttClient.connect(options);

			Data.mqttClient.subscribe(topic, (t, msg) ->
					readJson(new String(msg.getPayload()))
			);
		}
		catch (MqttException e) {
			e.printStackTrace();
		}
	}

	private void readJson(String dataJson)
	{
		System.out.println(dataJson);
		//dataJson = "{\"app_id\":\"senioren-notfall-hilfe-system\",\"dev_id\":\"armband_v01_4\",\"hardware_serial\":\"0000000000000004\",\"port\":15,\"counter\":10327,\"payload_raw\":\"AAAAAAAAAAAAAAAAAIMv\",\"payload_fields\":{\"altitude\":0,\"hdop\":0,\"latitude\":14,\"longitude\":16,\"status\":0,\"voltage\":3.3583},\"metadata\":{\"time\":\"2021-02-05T22:25:18.327075713Z\",\"frequency\":867.5,\"modulation\":\"LORA\",\"data_rate\":\"SF7BW125\",\"airtime\":66816000,\"coding_rate\":\"4/5\",\"gateways\":[{\"gtw_id\":\"eui-58a0cbfffe8021bb\",\"timestamp\":2635901532,\"time\":\"2021-02-05T22:25:18.314580917Z\",\"channel\":0,\"rssi\":-59,\"snr\":7.75,\"rf_chain\":0}]}}";
		try {
			ObjectMapper mapper = new ObjectMapper();
			final Wristband newWristband = mapper.readValue(dataJson, Wristband.class);
			Wristband wb = getWristbandByDev_id(newWristband.getDev_id());
			if (wb == null) {
				add(newWristband);
			}
			else {
				wb.set(newWristband);
			}
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
