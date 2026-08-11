import java.util.ArrayList;
import java.util.List;

enum AlertCategory {
    EARTHQUAKE,
    FLOOD,
    WILDFIRE
}

enum AlertSeverity {
    LOW,
    MEDIUM,
    HIGH
}

class Alert {
    private final String title;
    private final AlertCategory category;
    private final List<String> affectedLocations;
    private final AlertSeverity severity;
    private final String message;

    // public Alert(String title, AlertCategory category, AlertSeverity severity, String message) {
    //     this.title = title;
    //     this.category = category;
    //     this.severity = severity;
    //     this.message = message;
    //     this.affectedLocations = new ArrayList<>();
    // }

    private Alert(Builder builder) {
        this.title = builder.title;
        this.category = builder.category;
        this.severity = builder.severity;
        this.message = builder.message;
        this.affectedLocations = new ArrayList<>(builder.affectedLocations);
    }

    public static class Builder {
        private String title = "";
        private AlertCategory category;
        private List<String> affectedLocations = new ArrayList<>();
        private AlertSeverity severity;
        private String message = "";

        Builder(AlertCategory category, AlertSeverity severity) {
            this.category = category;
            this.severity = severity;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder addAffectedLocation(String location) {
            this.affectedLocations.add(location);
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Alert build() {
            Alert alert = new Alert(this);
            return alert;
        }
    }

    public String getTitle() {
        return title;
    }

    public AlertCategory getCategory() {
        return category;
    }

    public AlertSeverity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getAffectedLocations() {
        return affectedLocations;
    }

    @Override
    public String toString() {
        return "Category: " + category + ", Severity: " + severity + 
                "\nTitle: " + title + 
                "\nMessage: " + message + 
                "\nAffected Locations: " + affectedLocations;
    }
}

interface Observer {
    void update(Alert alert);
}

class Citizen implements Observer {
    private static int idCounter = 1;
    private int id;
    private String name;
    private List<Alert> receivedAlerts;

    public Citizen(String name) {
        this.id = idCounter++;
        this.name = name;
        this.receivedAlerts = new ArrayList<>();
    }

    @Override
    public void update(Alert alert) {
        receivedAlerts.add(alert);
    }

    public void displayReceivedAlerts() {
        System.out.println("--------------------------------------------------");
        System.out.println("Alerts received by " + name + "(" + id + "):");
        for (Alert alert : receivedAlerts) {
            System.out.println();
            System.out.println(alert);
        }
        System.out.println("--------------------------------------------------");
    }
}

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Alert alert);
}

class BDAlertSystem implements Subject {
    private AlertCategory category;
    private List<Observer> observers;

    public BDAlertSystem(AlertCategory category) {
        this.category = category;
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Alert alert) {
        if (alert.getCategory() != category) {
            System.out.println("Alert category does not match the system category. Notification aborted.");
            return;
        }

        for (Observer observer : observers) {
            observer.update(alert);
        }
    }
}

public class DisasterAlertSystem {
    public static void main(String[] args) {
        BDAlertSystem earthquakeAlertSystem = new BDAlertSystem(AlertCategory.EARTHQUAKE);
        BDAlertSystem floodAlertSystem = new BDAlertSystem(AlertCategory.FLOOD);
        BDAlertSystem wildfireAlertSystem = new BDAlertSystem(AlertCategory.WILDFIRE);

        Citizen alice = new Citizen("Alice");
        Citizen bob = new Citizen("Bob");
        Citizen charlie = new Citizen("Charlie");

        earthquakeAlertSystem.registerObserver(alice);
        earthquakeAlertSystem.registerObserver(bob);
        floodAlertSystem.registerObserver(bob);
        floodAlertSystem.registerObserver(charlie);
        wildfireAlertSystem.registerObserver(alice);

        Alert earthquakeAlert = new Alert.Builder(AlertCategory.EARTHQUAKE, AlertSeverity.HIGH)
                .setTitle("Severe Earthquake Alert")
                .addAffectedLocation("Dhaka")
                .addAffectedLocation("Chittagong")
                .setMessage("A severe earthquake has been detected.")
                .build();

        Alert floodAlert = new Alert.Builder(AlertCategory.FLOOD, AlertSeverity.MEDIUM)
                .setTitle("Flood Warning")
                .addAffectedLocation("Sylhet")
                .setMessage("Heavy rainfall expected.")
                .build();

        Alert floodAlert2 = new Alert.Builder(AlertCategory.FLOOD, AlertSeverity.HIGH)
                .setTitle("Severe Flood Alert")
                .addAffectedLocation("Barisal")
                .setMessage("Severe flooding expected in Barisal.")
                .build();

        Alert wildfireAlert = new Alert.Builder(AlertCategory.WILDFIRE, AlertSeverity.LOW)
                .setTitle("Wildfire Advisory")
                .addAffectedLocation("Cox's Bazar")
                .setMessage("Dry conditions may lead to wildfires.")
                .build();

        earthquakeAlertSystem.notifyObservers(earthquakeAlert);
        floodAlertSystem.notifyObservers(floodAlert);
        wildfireAlertSystem.notifyObservers(wildfireAlert);

        System.out.println("Initial alerts received by citizens:");
        alice.displayReceivedAlerts();
        bob.displayReceivedAlerts();
        charlie.displayReceivedAlerts();

        floodAlertSystem.removeObserver(bob);
        floodAlertSystem.notifyObservers(floodAlert2);

        System.out.println("After removing Bob from flood alert system and sending a new flood alert:");
        alice.displayReceivedAlerts();
        bob.displayReceivedAlerts();
        charlie.displayReceivedAlerts();
    }
}