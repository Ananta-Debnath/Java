import java.util.ArrayList;
import java.util.List;
import java.util.Set;

interface SmartDevice {
    void activate();
    void deactivate();
    String getStatus();
    double getPowerUsage();

    Class<?> getDeviceType();
}

abstract class PhysicalDevice implements SmartDevice {
    protected boolean isActive = false;

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

    @Override
    public Class<?> getDeviceType() {
        return this.getClass();
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

interface AreaInterface extends SmartDevice {
    void addChild(SmartDevice child);
    void removeChild(SmartDevice child);
    List<SmartDevice> getChildren();
}

abstract class Area implements AreaInterface {
    protected String name;
    protected List<SmartDevice> children;

    public Area(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    @Override
    public void activate() {
        for (SmartDevice child : children) {
            child.activate();
        }
    }

    @Override
    public void deactivate() {
        for (SmartDevice child : children) {
            child.deactivate();
        }
    }

    @Override
    public String getStatus() {
        StringBuilder status = new StringBuilder(name + " Status:\n");
        for (SmartDevice child : children) {
            status.append(" - ").append(child.getStatus()).append("\n");
        }
        return status.toString();
    }

    @Override
    public double getPowerUsage() {
        double totalPowerUsage = 0.0;
        for (SmartDevice child : children) {
            totalPowerUsage += child.getPowerUsage();
        }
        return totalPowerUsage;
    }

    @Override
    public Class<?> getDeviceType() {
        return this.getClass();
    }

    @Override
    public void addChild(SmartDevice child) {
        children.add(child);
    }

    @Override
    public void removeChild(SmartDevice child) {
        children.remove(child);
    }

    @Override
    public List<SmartDevice> getChildren() {
        return children;
    }
}

class Room extends Area {
    public Room(String name) {
        super(name);
    }

    public void addDevice(SmartDevice device) {
        Class <?> deviceType = device.getDeviceType();
        if (deviceType == Room.class || deviceType == Home.class) {
            throw new IllegalArgumentException("Cannot add a Room or Home as a child of a Room.");
        }
        addChild(device);
    }

    public void removeDevice(SmartDevice device) {
        Class<?> deviceType = device.getDeviceType();
        if (deviceType == Room.class || deviceType == Home.class) {
            throw new IllegalArgumentException("Cannot remove a Room or Home from a Room.");
        }
        removeChild(device);
    }
}

class Home extends Area {
    public Home(String name) {
        super(name);
    }

    public void addRoom(SmartDevice room) {
        Class<?> deviceType = room.getDeviceType();
        if (deviceType != Room.class) {
            throw new IllegalArgumentException("Only Room instances can be added to Home.");
        }
        addChild(room);
    }

    public void removeRoom(SmartDevice room) {
        Class<?> deviceType = room.getDeviceType();
        if (deviceType != Room.class) {
            throw new IllegalArgumentException("Only Room instances can be removed from Home.");
        }
        removeChild(room);
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

    @Override
    public Class<?> getDeviceType() {
        return device.getDeviceType();
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

    @Override
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

// AreaDecorator
class AreaDecorator implements AreaInterface {
    protected AreaInterface area;

    public AreaDecorator(AreaInterface area) {
        this.area = area;
    }

    @Override
    public void activate() {
        area.activate();
    }

    @Override
    public void deactivate() {
        area.deactivate();
    }

    @Override
    public String getStatus() {
        return area.getStatus();
    }

    @Override
    public double getPowerUsage() {
        return area.getPowerUsage();
    }

    @Override
    public Class<?> getDeviceType() {
        return area.getDeviceType();
    }

    @Override
    public void addChild(SmartDevice child) {
        area.addChild(child);
    }

    @Override
    public void removeChild(SmartDevice child) {
        area.removeChild(child);
    }

    @Override
    public List<SmartDevice> getChildren() {
        return area.getChildren();
    }
}

class EcoMode extends AreaDecorator {
    // private boolean isActive;
    private double powerLimit;

    public EcoMode(AreaInterface area, double powerLimit) {
        super(area);
        this.powerLimit = powerLimit;
    }

    @Override
    public void activate() {
        area.activate();

        double totalPower = area.getPowerUsage();

        for (int i = area.getChildren().size() - 1;
            i >= 0 && totalPower > powerLimit;
            i--) {

            SmartDevice physicalDevice = area.getChildren().get(i);
            physicalDevice.deactivate();

            totalPower = area.getPowerUsage();
        }
    }

    @Override
    public String getStatus() {
        return area.getStatus() + " (Eco Mode: " + powerLimit + "W limit)";
    }
}

class GuestMode extends AreaDecorator {
    private Set<Class<?>> allowed;

    public GuestMode(AreaInterface area, Set<Class<?>> allowed) {
        super(area);
        this.allowed = allowed;
    }

    @Override
    public void activate() {
        area.activate();
        applyGuestRestrictions(area);
    }

    private void applyGuestRestrictions(AreaInterface area) {
        for (SmartDevice child : area.getChildren()) {
            if (child.getDeviceType() == Room.class ||
                child.getDeviceType() == Home.class) {
                applyGuestRestrictions((Area) child);
            }
            else if (!allowed.contains(child.getDeviceType())) {
                child.deactivate();
            }
        }
    }

    @Override
    public String getStatus() {
        return area.getStatus() + " (guest-restricted: only " + allowed + " allowed)";
    }
}
