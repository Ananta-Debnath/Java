public abstract class MedicalProcedure {
    String procedureId;
    String procedureName;
    String scheduledTime;
    Doctor performedBy;

    public MedicalProcedure(String procedureId, String procedureName, String scheduledTime, Doctor performedBy) {
        this.procedureId = procedureId;
        this.procedureName = procedureName;
        this.scheduledTime = scheduledTime;
        this.performedBy = performedBy;
    }

    public Doctor getPerformedBy() {
        return performedBy;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    @Override
    public String toString() {
        return procedureName;
    }

    abstract void validate();
}
