package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import com.vaadin.flow.component.button.Button;

public class Wristband
        extends Location<Double>
        implements HasUpdateFieldsI {

    public enum Status {
        OK,
        LOW_BATTERY,
        EMERGENCY,
    }

    private Status status;
    private double voltage;
    private double hdop;

    private boolean emergency;


    public Wristband()
    {
        super((double) 0, (double) 0, (double) 0);
    }

    public Location<Double> getLocation()
    {
        return new Location<>(
                getLatitude(), getLongitude(), getAltitude());
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

    public boolean isEmergency()
    {
        return emergency;
    }

    @Override
    public void updateFields()
    {
        // Uptate emergency-variable
        emergency = (getStatus() == Status.EMERGENCY);
    }

    public void endEmergency()
    {
        emergency = false;
    }

}
