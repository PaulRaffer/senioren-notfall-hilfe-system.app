package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TTNWristbandService {

	private static TTNWristbandService instance = null;

	public static final String BROKER = "tcp://eu.thethings.network:1883";
	//public static final String BROKER = "tcp://NetworkServer:1883";
	public static final String CLIENT_ID = "ApplicationServer";
	public static final String APP_ID = "senioren-notfall-hilfe-system";
	//public static final String APP_ID = "senioren-notfall-hilfe";
	public static final String API_KEY = "ttn-account-v2.xqhU5_c5SPEKLNDpg38Ah5er2XqEZI0Gxt_iobseDmQ";
	//public static final String API_KEY = "NNSXS.LZ6CC6BCP4PZXBTGFJISETZJVC7I7O76TFVSQ5Q.YNSPM6KJZ2EXEXQIHWZHI57XC5O733FNQS4XDC75FKNCBBD4BH6Q";
	public static final String TOPIC = "#";


	public static synchronized TTNWristbandService getInstance()
	{
		if (instance == null) {
			instance = new TTNWristbandService(BROKER, CLIENT_ID, APP_ID, API_KEY, TOPIC);
		}
		return instance;
	}


	private final List<TTNData<Wristband>> wristbands = new ArrayList<>();
	private final MqttClient mqttClient;

	public TTNWristbandService(
			final String broker, final String clientId,
			final String appId, final String apiKey,
			final String topic)
	{
		MqttClient mqttClientTemp;
		try {
			mqttClientTemp = new MqttClient(broker, clientId, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName(appId);
			options.setPassword(apiKey.toCharArray());
			options.setCleanSession(true);
			mqttClientTemp.connect(options);

			mqttClientTemp.subscribe(topic, (t, msg) ->
					readJson(new String(msg.getPayload()))
			);
		}
		catch (MqttException e) {
			mqttClientTemp = null;
			e.printStackTrace();
		}
		mqttClient = mqttClientTemp;
	}

	private void readJson(String dataJson)
	{
		System.out.println(dataJson);
		//dataJson = "{\"app_id\":\"senioren-notfall-hilfe-system\",\"dev_id\":\"armband_v01_4\",\"hardware_serial\":\"0000000000000004\",\"port\":15,\"counter\":10327,\"payload_raw\":\"AAAAAAAAAAAAAAAAAIMv\",\"payload_fields\":{\"altitude\":0,\"hdop\":0,\"latitude\":14,\"longitude\":16,\"status\":0,\"voltage\":3.3583},\"metadata\":{\"time\":\"2021-02-05T22:25:18.327075713Z\",\"frequency\":867.5,\"modulation\":\"LORA\",\"data_rate\":\"SF7BW125\",\"airtime\":66816000,\"coding_rate\":\"4/5\",\"gateways\":[{\"gtw_id\":\"eui-58a0cbfffe8021bb\",\"timestamp\":2635901532,\"time\":\"2021-02-05T22:25:18.314580917Z\",\"channel\":0,\"rssi\":-59,\"snr\":7.75,\"rf_chain\":0}]}}";
		try {
			ObjectMapper mapper = new ObjectMapper();
			// https://www.baeldung.com/jackson-linkedhashmap-cannot-be-cast
			final TTNData<Wristband> newWristband = mapper.readValue(dataJson, new TypeReference<TTNData<Wristband>>() {});
			TTNData<Wristband> wb = getByDev_id(newWristband.getDev_id());
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

	public List<TTNData<Wristband>> getAll()
	{
		return Collections.unmodifiableList(wristbands);
	}

	public TTNData<Wristband> getByDev_id(String dev_id)
	{
		for (TTNData<Wristband> wb : wristbands) {
			if (wb.getDev_id().equals(dev_id)) {
				return wb;
			}
		}
		return null;
	}

	public void add(TTNData<Wristband> wristband)
	{
		// protect concurrent access since MqttWristbandService is a singleton
		synchronized (wristbands) {
			wristbands.add(wristband);
		}
	}
}