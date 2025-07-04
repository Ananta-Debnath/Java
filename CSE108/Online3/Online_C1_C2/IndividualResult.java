import java.util.List;

public class IndividualResult extends EvaluationResult{
    public IndividualResult(Evaluation evaluation, Student s, double score) {
        super(evaluation, score);
        students.add(s);
    }

    @Override
    boolean isGroupResult() {
        return false;
    }

    @Override
    void showInfo() {

    }
}
