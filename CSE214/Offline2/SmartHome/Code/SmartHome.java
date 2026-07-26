import java.util.ArrayList;
import java.util.List;

interface SmartDevice {
    void activate();
    void deactivate();
    String getStatus();
    double getPowerUsage();
}

abstract class PhysicalDevice implements SmartDevice {
    protected boolean isActive = false;

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }

    public String getStatus() {
        return isActive ? "ON" : "OFF";
    }
}

class SmartLight extends PhysicalDevice {
    private final double powerUsage = 10.0;

    @Override
    public double getPowerUsage() {
        return isActive ? powerUsage : 0.0;
    }
}

class SmartThermostat extends PhysicalDevice {
    private final double powerUsage = 150.0;

    @Override
    public double getPowerUsage() {
        return isActive ? powerUsage : 0.0;
    }
}

class SmartSpeaker extends PhysicalDevice {
    private final double powerUsage = 5.0;

    @Override
    public double getPowerUsage() {
        return isActive ? powerUsage : 0.0;
    }
}

abstract class Area<T extends SmartDevice> implements SmartDevice {
    protected String name;
    protected List<T> children;

    public Area(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    @Override
    public void activate() {
        for (T child : children) {
            child.activate();
        }
    }

    @Override
    public void deactivate() {
        for (T child : children) {
            child.deactivate();
        }
    }

    @Override
    public String getStatus() {
        StringBuilder status = new StringBuilder(name + " Status:\n");
        for (T child : children) {
            status.append(" - ").append(child.getStatus()).append("\n");
        }
        return status.toString();
    }

    @Override
    public double getPowerUsage() {
        double totalPowerUsage = 0.0;
        for (T child : children) {
            totalPowerUsage += child.getPowerUsage();
        }
        return totalPowerUsage;
    }

    public void addChild(T child) {
        children.add(child);
    }
}

class Room extends Area<PhysicalDevice> {
    public Room(String name) {
        super(name);
    }

    public void addDevice(PhysicalDevice device) {
        addChild(device);
    }
}

class Home extends Area<Room> {
    public Home(String name) {
        super(name);
    }

    public void addRoom(Room room) {
        addChild(room);
    }
}

// Decorators for SmartDevice
abstract class SmartDeviceDecorator implements SmartDevice {
    protected SmartDevice device;

    public SmartDeviceDecorator(SmartDevice device) {
        this.device = device;
    }

    @Override
    public void activate() {
        device.activate();
    }

    @Override
    public void deactivate() {
        device.deactivate();
    }

    @Override
    public double getPowerUsage() {
        return device.getPowerUsage();
    }
}

class AccessRestricted extends SmartDeviceDecorator {
    private int accessCode;
    private boolean isLocked = true;

    public AccessRestricted(SmartDevice device, int accessCode) {
        super(device);
        this.accessCode = accessCode;
    }

    @Override
    public void activate() {
        if (!isLocked) {
            device.activate();
        }
    }

    @Override
    public void deactivate() {
        if (!isLocked) {
            device.deactivate();
        }
    }

    public void unlock(int code) {
        if (code == accessCode) {
            isLocked = false;
        }
    }

    public void lock() {
        isLocked = true;
    }

    public String getStatus() {
        if (isLocked) {
            return device.getStatus() + " (LOCKED)";
        } else {
            return device.getStatus();
        }
    }
}

class TimerControlled extends SmartDeviceDecorator {
    private double duration; // in seconds

    public TimerControlled(SmartDevice device, double duration) {
        super(device);
        this.duration = duration;
    }

    public void simulateTimerExpiry() {
        device.deactivate();
    }

    @Override
    public String getStatus() {
        if (!device.getStatus().equals("OFF")) {
            return device.getStatus() + " (auto-off " + duration + "s)";
        }
        else {
            return device.getStatus();
        }
    }
}

class PowerThrottled extends SmartDeviceDecorator {
    private double maxPowerUsage;

    public PowerThrottled(SmartDevice device, double maxPowerUsage) {
        super(device);
        this.maxPowerUsage = maxPowerUsage;
    }

    @Override
    public double getPowerUsage() {
        double usage = device.getPowerUsage();
        return Math.min(usage, maxPowerUsage);
    }

    @Override
    public String getStatus() {
        double usage = device.getPowerUsage();
        if (usage > maxPowerUsage) {
            return device.getStatus() + " (throttled to " + maxPowerUsage + "W)";
        }
        else {
            return device.getStatus();
        }
    }
}
