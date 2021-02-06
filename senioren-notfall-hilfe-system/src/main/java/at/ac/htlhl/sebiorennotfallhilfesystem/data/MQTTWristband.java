package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttWristband extends Wristband {

	public MqttWristband()
	{
		String topic = "#";
		try {
			Data.mqttClient.subscribe(topic, (t, msg) ->
				decode(new String(msg.getPayload()))
			);
		}
		catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void set(final MqttWristband wristband)
	{
		super.set(wristband);
	}

	private void decode(String dataJson)
	{
		System.out.println(dataJson);
		//dataJson = "{\"app_id\":\"senioren-notfall-hilfe-system\",\"dev_id\":\"armband_v01_4\",\"hardware_serial\":\"0000000000000004\",\"port\":15,\"counter\":10327,\"payload_raw\":\"AAAAAAAAAAAAAAAAAIMv\",\"payload_fields\":{\"altitude\":0,\"hdop\":0,\"latitude\":14,\"longitude\":16,\"status\":0,\"voltage\":3.3583},\"metadata\":{\"time\":\"2021-02-05T22:25:18.327075713Z\",\"frequency\":867.5,\"modulation\":\"LORA\",\"data_rate\":\"SF7BW125\",\"airtime\":66816000,\"coding_rate\":\"4/5\",\"gateways\":[{\"gtw_id\":\"eui-58a0cbfffe8021bb\",\"timestamp\":2635901532,\"time\":\"2021-02-05T22:25:18.314580917Z\",\"channel\":0,\"rssi\":-59,\"snr\":7.75,\"rf_chain\":0}]}}";
		try {
			ObjectMapper mapper = new ObjectMapper();
			final MqttWristband wristband = mapper.readValue(dataJson, MqttWristband.class);
			set(wristband);
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
