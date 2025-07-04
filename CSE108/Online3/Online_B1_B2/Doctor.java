import java.util.ArrayList;

public class Doctor extends Person implements Prescribable {
    int doctorId;
    ArrayList<String> specializations;

    public Doctor(String name, int age, String gender, String contactInfo, int doctorId) {
        super(name, age, gender, contactInfo);
        this.doctorId = doctorId;
        specializations = new ArrayList<String>();
    }

    public void addSpecialization(String specialization) {
        specializations.add(specialization);
    }

    boolean isCertifiedFor(String procedureName)
    {
        for (String str : specializations)
        {
            if (str.equals(procedureName)) return true;
        }
        return false;
    }

    @Override
    public void prescribe(Patient patient, Medication medication) {
        patient.addMedication(medication);
    }

    @Override
    public String toString() {
        return "Doctor: " + '\n' +
                "name: " + name + '\n' +
                "age: " + age + '\n' +
                "gender: " + gender + '\n' +
                "contactInfo: " + contactInfo + '\n' +
                "doctorId: " + doctorId + '\n' +
                "specializations: " + specializations + '\n';
    }

    @Override
    void showInfo() {
        System.out.println(toString());
    }
}
