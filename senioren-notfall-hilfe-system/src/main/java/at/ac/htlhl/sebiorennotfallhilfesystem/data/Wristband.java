package at.ac.htlhl.sebiorennotfallhilfesystem.data;

public class Wristband
        extends Location<Double>
        implements HasEmergencyI {

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

    @Override
    public boolean isEmergency()
    {
        return emergency;
    }

    @Override
    public void setEmergency(boolean emergency)
    {
        this.emergency = emergency;
    }

    @Override
    public void updateFields()
    {
        // Uptate emergency-variable
        emergency = (getStatus() == Status.EMERGENCY);
    }

    @Override
    public void update(HasEmergencyI u) {

        this.emergency = u.isEmergency();
    }

    @Override
    public void endEmergency()
    {
        emergency = false;
    }

}
