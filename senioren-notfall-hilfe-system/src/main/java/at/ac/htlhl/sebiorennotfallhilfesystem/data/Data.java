package at.ac.htlhl.sebiorennotfallhilfesystem.data;

public class Data {

	private static TTNWristbandService wristbands = null;

	public static final String BROKER = "tcp://eu.thethings.network:1883";
	//public static final String BROKER = "tcp://NetworkServer:1883";
	public static final String CLIENT_ID = "ApplicationServer";
	public static final String APP_ID = "senioren-notfall-hilfe-system";
	//public static final String APP_ID = "senioren-notfall-hilfe";
	public static final String API_KEY = "ttn-account-v2.xqhU5_c5SPEKLNDpg38Ah5er2XqEZI0Gxt_iobseDmQ";
	//public static final String API_KEY = "NNSXS.LZ6CC6BCP4PZXBTGFJISETZJVC7I7O76TFVSQ5Q.YNSPM6KJZ2EXEXQIHWZHI57XC5O733FNQS4XDC75FKNCBBD4BH6Q";
	public static final String TOPIC = "#";


	public static synchronized TTNWristbandService getWristbandServiceInstance()
	{
		if (wristbands == null) {
			wristbands = new TTNWristbandService(BROKER, CLIENT_ID, APP_ID, API_KEY, TOPIC);
		}
		return wristbands;
	}

}
