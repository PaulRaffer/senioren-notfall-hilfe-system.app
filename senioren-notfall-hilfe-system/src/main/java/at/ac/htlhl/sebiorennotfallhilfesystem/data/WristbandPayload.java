package at.ac.htlhl.sebiorennotfallhilfesystem.data;

public class WristbandPayload extends Location<Double> {

	public enum Status {
		OK,
		LOW_BATTERY,
		EMERGENCY,
	}

	private Status status;
	private double voltage;
	private double hdop;


	public WristbandPayload()
	{
		super((double) 0, (double) 0, (double) 0);
	}

	public Location<Double> getLocation()
	{
		return new Location<>(getLatitude(), getLongitude(), getAltitude());
	}

	public Status getStatus()
	{
		return status;
	}

	public double getVoltage()
	{
		return voltage;
	}

	public double getHdop()
	{
		return hdop;
	}

}
