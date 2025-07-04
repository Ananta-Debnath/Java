public class Assignment extends Evaluation{
    String deadline;
    String plagiarismPolicy;

    public Assignment(String title, int totalMarks, double weightage, String deadline, String plagiarismPolicy) {
        super(title, totalMarks, weightage);
        this.deadline = deadline;
        this.plagiarismPolicy = plagiarismPolicy;
    }

    @Override
    public void getEvaluationInfo() {

    }
}
