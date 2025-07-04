public class CT extends Evaluation{
    String examHall;
    String examTime;

    public CT(String title, int totalMarks, double weightage, String examHall, String examTime) {
        super(title, totalMarks, weightage);
        this.examHall = examHall;
        this.examTime = examTime;
    }

    @Override
    void getEvaluationInfo() {

    }
}
