package at.ac.htlhl.sebiorennotfallhilfesystem.data;

public interface HasEmergencyI {
	void updateFields();
	void update(HasEmergencyI u);
	boolean isEmergency();
	void setEmergency(boolean emergency);
	void endEmergency();
}
