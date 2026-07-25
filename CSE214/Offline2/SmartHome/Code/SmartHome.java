import java.util.ArrayList;
import java.util.List;

interface SmartComponent {
    void activate();
    void deactivate();
    String getStatus();
    double getPowerUsage();
}

abstract class SmartDevice implements SmartComponent {
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

abstract class Area<T extends SmartComponent> implements SmartComponent {
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

class Room extends Area<SmartDevice> {
    public Room(String name) {
        super(name);
    }

    public void addDevice(SmartDevice device) {
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

// Decorators for SmartComponent
abstract class SmartComponentDecorator implements SmartComponent {
    protected SmartComponent component;

    public SmartComponentDecorator(SmartComponent component) {
        this.component = component;
    }

    @Override
    public void activate() {
        component.activate();
    }

    @Override
    public void deactivate() {
        component.deactivate();
    }

    @Override
    public double getPowerUsage() {
        return component.getPowerUsage();
    }
}

class AccessRestricted extends SmartComponentDecorator {
    private int accessCode;
    private boolean isLocked = true;

    public AccessRestricted(SmartComponent component, int accessCode) {
        super(component);
        this.accessCode = accessCode;
    }

    @Override
    public void activate() {
        if (!isLocked) {
            component.activate();
        }
    }

    @Override
    public void deactivate() {
        if (!isLocked) {
            component.deactivate();
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
            return component.getStatus() + " (LOCKED)";
        } else {
            return component.getStatus();
        }
    }
}

class TimerControlled extends SmartComponentDecorator {
    private double duration; // in seconds

    public TimerControlled(SmartComponent component, double duration) {
        super(component);
        this.duration = duration;
    }

    public void simulateTimerExpiry() {
        component.deactivate();
    }

    @Override
    public String getStatus() {
        if (!component.getStatus().equals("OFF")) {
            return component.getStatus() + " (auto-off " + duration + "s)";
        }
        else {
            return component.getStatus();
        }
    }
}
