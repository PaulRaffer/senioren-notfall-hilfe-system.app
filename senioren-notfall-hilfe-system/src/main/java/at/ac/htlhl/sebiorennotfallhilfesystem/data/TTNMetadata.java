package at.ac.htlhl.sebiorennotfallhilfesystem.data;

public class TTNMetadata {

	private int airtime;
	private String time;
	private double frequency;
	private String modulation;
	private String data_rate;
	private String coding_rate;
	Gateway[] gateways;


	public int getAirtime()
	{
		return airtime;
	}

	public void setAirtime(int airtime)
	{
		this.airtime = airtime;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public double getFrequency()
	{
		return frequency;
	}

	public void setFrequency(double frequency)
	{
		this.frequency = frequency;
	}

	public String getModulation()
	{
		return modulation;
	}

	public void setModulation(String modulation)
	{
		this.modulation = modulation;
	}

	public String getData_rate()
	{
		return data_rate;
	}

	public void setData_rate(String data_rate)
	{
		this.data_rate = data_rate;
	}

	public String getCoding_rate()
	{
		return coding_rate;
	}

	public void setCoding_rate(String coding_rate)
	{
		this.coding_rate = coding_rate;
	}

	public Gateway[] getGateways()
	{
		return gateways;
	}

	public void setGateways(Gateway[] gateways)
	{
		this.gateways = gateways;
	}

}
