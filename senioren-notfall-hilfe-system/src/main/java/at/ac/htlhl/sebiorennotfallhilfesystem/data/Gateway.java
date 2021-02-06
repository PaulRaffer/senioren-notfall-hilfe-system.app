package at.ac.htlhl.sebiorennotfallhilfesystem.data;

public class Gateway {

	private String gtw_id;
	private long timestamp;
	private String time;
	private int channel;
	private int rssi;
	private int snr;
	private int rf_chain;

	public String getGtw_id() {
		return gtw_id;
	}

	public void setGtw_id(String gtw_id) {
		this.gtw_id = gtw_id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public int getSnr() {
		return snr;
	}

	public void setSnr(int snr) {
		this.snr = snr;
	}

	public int getRf_chain() {
		return rf_chain;
	}

	public void setRf_chain(int rf_chain) {
		this.rf_chain = rf_chain;
	}

}
