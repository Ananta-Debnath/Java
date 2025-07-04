import java.util.List;

public class GroupResult extends EvaluationResult{
    String groupId;

    public GroupResult(Evaluation evaluation, List<Student> students, double score, String groupId) {
        super(evaluation, students, score);
        this.groupId = groupId;
    }

    @Override
    boolean isGroupResult() {
        return true;
    }

    @Override
    void showInfo() {

    }
}
