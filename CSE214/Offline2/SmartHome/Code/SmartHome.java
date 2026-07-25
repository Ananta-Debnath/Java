
interface SmartComponent {
    void activate();
    void deactivate();
    String getStatus();
    double getPowerUsage();
}

abstract class SmartDevice implements SmartComponent {
    protected boolean isActive;

    @Override
    public void activate() {
        isActive = true;
    }

    @Override
    public void deactivate() {
        isActive = false;
    }

    @Override
    public String getStatus() {
        return isActive ? "ON" : "OFF";
    }
}

class SmartLight extends SmartDevice {
    private final double powerUsage = 10.0;

    @Override
    public double getPowerUsage() {
        return isActive ? powerUsage : 0.0;
    }
}

class SmartThermostat extends SmartDevice {
    private final double powerUsage = 150.0;

    @Override
    public double getPowerUsage() {
        return isActive ? powerUsage : 0.0;
    }
}

class SmartSpeaker extends SmartDevice {
    private final double powerUsage = 5.0;

    @Override
    public double getPowerUsage() {
        return isActive ? powerUsage : 0.0;
    }
}










public class SmartHome {
    
}
