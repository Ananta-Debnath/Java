public class LabTest extends MedicalProcedure{
    String location;

    public LabTest(String procedureId, String procedureName, String scheduledTime, Doctor performedBy, String location) {
        super(procedureId, procedureName, scheduledTime, performedBy);
        this.location = location;
    }

    @Override
    void validate() throws RuntimeException{
        if (!(location.equals("clinic") || location.equals("hospitalPremise")))
            throw new RuntimeException("Invalid Procedure");
    }
}
