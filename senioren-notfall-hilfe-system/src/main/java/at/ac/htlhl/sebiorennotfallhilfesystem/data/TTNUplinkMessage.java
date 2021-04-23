package at.ac.htlhl.sebiorennotfallhilfesystem.data;

// https://www.thethingsnetwork.org/docs/applications/mqtt/api.html
public class TTNUplinkMessage<Payload>
        implements UplinkMessageI<Payload> {

    private String app_id;
    private String dev_id;
    private String hardware_serial;
    private int port;
    private int counter;
    private boolean is_retry;
    private boolean confirmed;
    private String payload_raw;
    private Payload payload_fields;
    private TTNMetadata metadata;


    public TTNUplinkMessage()
    {
    }

    public TTNUplinkMessage(
            String app_id,
            String dev_id,
            String hardware_serial,
            int port,
            int counter,
            boolean is_retry,
            boolean confirmed,
            String payload_raw,
            Payload payload_fields,
            TTNMetadata metadata)
    {
        this.app_id = app_id;
        this.dev_id = dev_id;
        this.hardware_serial = hardware_serial;
        this.port = port;
        this.counter = counter;
        this.is_retry = is_retry;
        this.confirmed = confirmed;
        this.payload_raw = payload_raw;
        this.payload_fields = payload_fields;
        this.metadata = metadata;
    }

    public void set(TTNUplinkMessage<Payload> wristband)
    {
        this.app_id = wristband.getApp_id();
        this.dev_id = wristband.getDev_id();
        this.hardware_serial = wristband.getHardware_serial();
        this.port = wristband.getPort();
        this.counter = wristband.getCounter();
        this.is_retry = wristband.isIs_retry();
        this.confirmed = wristband.isConfirmed();
        this.payload_raw = wristband.getPayload_raw();
        this.payload_fields = wristband.getPayload_fields();
        this.metadata = wristband.getMetadata();
    }

    public String getApp_id()
    {
        return app_id;
    }

    public String getDev_id()
    {
        return dev_id;
    }
    @Override
    public String getDevice_id()
    {
        return getDev_id();
    }

    public String getHardware_serial()
    {
        return hardware_serial;
    }

    public int getPort()
    {
        return port;
    }

    public int getCounter()
    {
        return counter;
    }

    public boolean isIs_retry()
    {
        return is_retry;
    }

    public boolean isConfirmed()
    {
        return confirmed;
    }

    public String getPayload_raw()
    {
        return payload_raw;
    }

    public Payload getPayload_fields()
    {
        return payload_fields;
    }
    @Override
    public Payload getPayload()
    {
        return getPayload_fields();
    }

    public TTNMetadata getMetadata()
    {
        return metadata;
    }

}
