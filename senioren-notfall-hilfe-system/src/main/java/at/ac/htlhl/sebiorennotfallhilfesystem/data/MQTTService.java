package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class MQTTService<UplinkMessage extends UplinkMessageI<? extends HasEmergencyI>> {

    private static MQTTService<TTNUplinkMessage<Wristband>> instance = null;
    public static enum NetworkServer {
       TTN,
       TTS,
    }
    public static NetworkServer networkServer = NetworkServer.TTN;
    public static final String BROKER = networkServer == NetworkServer.TTN ? "tcp://eu.thethings.network:1883" : "tcp://NetworkServer:1883";
    public static final String CLIENT_ID = "ApplicationServer";
    public static final String APP_ID = networkServer == NetworkServer.TTN ? "senioren-notfall-hilfe-system" : "test-app";
    public static final String API_KEY =
            networkServer == NetworkServer.TTN ? "ttn-account-v2.xqhU5_c5SPEKLNDpg38Ah5er2XqEZI0Gxt_iobseDmQ" :
            "NNSXS.CPH7U3C3BN6CBFQQUSUF7KYCJGBQBXWOHNLEE3Q.R6NXALG5GXH2SHKZIYJQP36IH6M3YTSXY47522QNKJZRAYUD7LZA";
    public static final String TOPIC = "#";


    public static synchronized MQTTService<TTNUplinkMessage<Wristband>> getInstance()
    {
        if (instance == null) {
            ObjectMapper mapper = new ObjectMapper();
            instance = new MQTTService<TTNUplinkMessage<Wristband>>(
                    mapper.getTypeFactory().constructParametricType(TTNUplinkMessage.class, Wristband.class), //new TypeReference<TTNUplinkMessage<Wristband>>() {},
                    BROKER, CLIENT_ID, APP_ID, API_KEY, TOPIC);
        }
        return instance;
    }


    private final List<UplinkMessage> messages = new ArrayList<>();
    private final MqttClient mqttClient;

    public MQTTService(
            JavaType messageType,
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
                    readJson(new String(msg.getPayload()), messageType)
            );
        }
        catch (MqttException e) {
            mqttClientTemp = null;
            e.printStackTrace();
        }
        mqttClient = mqttClientTemp;
    }

    private void readJson(String dataJson, JavaType messageType)
    {
        System.out.println(dataJson);
        //dataJson = "{\"app_id\":\"senioren-notfall-hilfe-system\",\"dev_id\":\"armband_v01_4\",\"hardware_serial\":\"0000000000000004\",\"port\":15,\"counter\":10327,\"payload_raw\":\"AAAAAAAAAAAAAAAAAIMv\",\"payload_fields\":{\"altitude\":215.0,\"hdop\":0,\"latitude\":48.4758,\"longitude\":15.9778,\"status\":" + abs(new Random().nextInt()) % 3 + ",\"voltage\":3.3583},\"metadata\":{\"time\":\"2021-02-05T22:25:18.327075713Z\",\"frequency\":867.5,\"modulation\":\"LORA\",\"data_rate\":\"SF7BW125\",\"airtime\":66816000,\"coding_rate\":\"4/5\",\"gateways\":[{\"gtw_id\":\"eui-58a0cbfffe8021bb\",\"timestamp\":2635901532,\"time\":\"2021-02-05T22:25:18.314580917Z\",\"channel\":0,\"rssi\":-59,\"snr\":7.75,\"rf_chain\":0}]}}";
        try {
            ObjectMapper mapper = new ObjectMapper();
            // https://www.baeldung.com/jackson-linkedhashmap-cannot-be-cast
            final UplinkMessage newMessage = mapper.readValue(dataJson, messageType);
            UplinkMessage lastMessage = getByDev_id(newMessage.getDevice_id());
            messages.remove(lastMessage);
            add(newMessage);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<UplinkMessage> getAll()
    {
        return Collections.unmodifiableList(messages);
    }

    public UplinkMessage getByDev_id(String dev_id)
    {
        for (UplinkMessage wb : messages) {
            if (wb.getDevice_id().equals(dev_id)) {
                return wb;
            }
        }
        return null;
    }

    public void add(UplinkMessage message)
    {
        synchronized (messages) {
            message.getPayload().updateFields();
            messages.add(message);
        }
    }

}
