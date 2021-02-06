package at.ac.htlhl.sebiorennotfallhilfesystem.data;

/**
 * Simple data object representing a location on a map.
 */
public class Location<T> {

	private T latitude;
	private T longitude;
	private T altitude;

	public Location()
	{
	}

	public Location(T latitude, T longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location(T latitude, T longitude, T altitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	public T getLatitude()
	{
		return latitude;
	}

	public void setLatitude(T latitude)
	{
		this.latitude = latitude;
	}

	public T getLongitude()
	{
		return longitude;
	}

	public void setLongitude(T longitude)
	{
		this.longitude = longitude;
	}

	public T getAltitude()
	{
		return altitude;
	}

	public void setAltitude(T altitude)
	{
		this.altitude = altitude;
	}
}
