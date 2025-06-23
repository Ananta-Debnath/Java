public class Surgery extends MedicalProcedure{
    boolean anesthesia;

    public Surgery(String procedureId, String procedureName, String scheduledTime, Doctor performedBy, boolean anesthesia) {
        super(procedureId, procedureName, scheduledTime, performedBy);
        this.anesthesia = anesthesia;
    }

    @Override
    void validate() throws RuntimeException {
        if (!anesthesia) throw new RuntimeException("Invalid Procedure");
    }
}
