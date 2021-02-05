package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

public class Wristband {
	private String name;
	private int status;
	private int battery;
	private int voltage;
	private Location location;

	public Wristband(String name, Location location)
	{
		this.name = name;
		this.location = location;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getBattery()
	{
		return battery;
	}

	public void setBattery(int battery)
	{
		this.battery = battery;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}
}
