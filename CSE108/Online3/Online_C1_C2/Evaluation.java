public abstract class Evaluation {
    String title;
    int totalMarks;
    double weightage;

    public Evaluation(String title, int totalMarks, double weightage) {
        this.title = title;
        this.totalMarks = totalMarks;
        this.weightage = weightage;
    }

    public String getTitle() {
        return title;
    }

    abstract void getEvaluationInfo();
}
