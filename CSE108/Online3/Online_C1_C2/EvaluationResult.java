import java.util.ArrayList;
import java.util.List;

public abstract class EvaluationResult {
    Evaluation evaluation;
    List<Student> students = new ArrayList<>();
    double score;

    public EvaluationResult(Evaluation evaluation, double score) {
        this.evaluation = evaluation;
        this.score = score;
    }

    public EvaluationResult(Evaluation evaluation, List<Student> students, double score) {
        this.evaluation = evaluation;
        this.students = students;
        this.score = score;
    }

    public List<Student> getStudents() {
        return students;
    }

    abstract boolean isGroupResult();
    abstract void showInfo();
    boolean isValid(Course course)
    {
        for (Student s : students)
        {
            if (!course.getEnrolledStudents().contains(s)) return false;
        }
        if (!course.getEvaluations().contains(this)) return false;
        return true;
    }
}
