import java.util.ArrayList;
import java.util.List;

public class CourseResult {
    Student student;
    Course course;
    List<EvaluationResult> evaluationResults = new ArrayList<>();

    public CourseResult(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public void addEvaluationResults(EvaluationResult evalRes)
    {
        evaluationResults.add(evalRes);
    }

    public boolean isValid()
    {
        for (EvaluationResult evalRes : evaluationResults)
        {
            if (!evalRes.getStudents().contains(student)) return false;
            if (!evalRes.isValid(course)) return false;
        }
        return true;
    }

    public double calculateFinalScore()
    {
        double score;
        double total;
    }
}
