package at.ac.htlhl.sebiorennotfallhilfesystem.data;

public class TTNData<T> {

	private String app_id;
	private String dev_id;
	private String hardware_serial;
	private int port;
	private int counter;
	private String payload_raw;
	private T payload_fields;
	private TTNWristbandMetadata metadata;


	public TTNData()
	{
	}

	public TTNData(
			String app_id,
			String dev_id,
			String hardware_serial,
			int port,
			int counter,
			String payload_raw,
			T payload_fields,
			TTNWristbandMetadata metadata)
	{
		this.app_id = app_id;
		this.dev_id = dev_id;
		this.hardware_serial = hardware_serial;
		this.port = port;
		this.counter = counter;
		this.payload_raw = payload_raw;
		this.payload_fields = payload_fields;
		this.metadata = metadata;
	}

	public void set(final TTNData<T> wristband)
	{
		this.app_id = wristband.getApp_id();
		this.dev_id = wristband.getDev_id();
		this.hardware_serial = wristband.getHardware_serial();
		this.port = wristband.getPort();
		this.counter = wristband.getCounter();
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

	public String getPayload_raw()
	{
		return payload_raw;
	}

	public T getPayload_fields()
	{
		return payload_fields;
	}

	public TTNWristbandMetadata getMetadata()
	{
		return metadata;
	}

}
