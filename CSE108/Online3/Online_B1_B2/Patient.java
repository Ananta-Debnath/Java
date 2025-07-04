import java.util.ArrayList;

public class Patient extends Person{
    String patientId;
    ArrayList<String> allergies;
    ArrayList<Medication> medications;
    ArrayList<MedicalProcedure> medicalProcedures;

    public Patient(String name, int age, String gender, String contactInfo, String patientId) {
        super(name, age, gender, contactInfo);
        this.patientId = patientId;
        allergies = new ArrayList<String>();
        medications = new ArrayList<Medication>();
        medicalProcedures = new ArrayList<MedicalProcedure>();
    }

    void addAllergy(String allergy)
    {
        allergies.add(allergy);
    }

    void addMedication(Medication medication)
    {
        for (String allergy : allergies)
        {
            for (String allergen : medication.allergens)
            {
                if (allergy.equals(allergen)) return;
            }
        }
        medications.add(medication);
    }

    void addMedicalProcedure(MedicalProcedure medicalProcedure)
    {
        if (medicalProcedure instanceof LabTest)
        {
            medicalProcedures.add(medicalProcedure);
        }
        else
        {
            for (String specialization : medicalProcedure.getPerformedBy().specializations)
            {
                if (specialization.equalsIgnoreCase(medicalProcedure.getProcedureName()))
                {
                    medicalProcedures.add(medicalProcedure);
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Patient: " + '\n' +
                "name: " + name + '\n' +
                "age: " + age + '\n' +
                "gender: " + gender + '\n' +
                "contactInfo: " + contactInfo + '\n' +
                "patientId: " + patientId + '\n' +
                "allergies: " + allergies + '\n' +
                "medications: " + medications + '\n' +
                "medicalProcedures: " + medicalProcedures + '\n';
    }

    @Override
    void showInfo() {
        System.out.println(toString());
    }
}
