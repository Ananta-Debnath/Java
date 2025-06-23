import java.util.ArrayList;

public class Medication {
    String name;
    ArrayList<String> allergens;

    public Medication(String name) {
        this.name = name;
        allergens = new ArrayList<String>();
    }

    void addAllergen(String allergen)
    {
        allergens.add(allergen);
    }

    @Override
    public String toString() {
        return name;
    }
}
