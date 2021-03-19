package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import javax.validation.Payload;

public interface UplinkMessageI<Payload> {
	String getDevice_id();
	Payload getPayload();

}
