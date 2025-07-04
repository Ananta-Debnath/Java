public class Viva extends Evaluation{
    String vivaBoard;

    public Viva(String title, int totalMarks, double weightage, String vivaBoard) {
        super(title, totalMarks, weightage);
        this.vivaBoard = vivaBoard;
    }

    @Override
    void getEvaluationInfo() {

    }
}
