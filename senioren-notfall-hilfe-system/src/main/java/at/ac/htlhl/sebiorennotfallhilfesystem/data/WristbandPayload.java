package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

public class WristbandPayload {

	public enum Status {
		OK,
		LOW_BATTERY,
		EMERGENCY,
	}

	//private String name;
	private Status status;
	private double voltage;
	private double hdop;
	private double latitude;
	private double longitude;
	private double altitude;

	public WristbandPayload()
	{
	}

	public Location<Double> getLocation()
	{
		return new Location<>(latitude, longitude, altitude);
	}

	public double getLatitude()
	{
		return latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public double getAltitude()
	{
		return altitude;
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
