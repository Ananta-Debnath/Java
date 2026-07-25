import java.util.ArrayList;
import java.util.List;

interface SmartComponent {
    void activate();
    void deactivate();
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









public class SmartHome {
    
}
