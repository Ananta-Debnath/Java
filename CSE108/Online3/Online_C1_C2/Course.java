import java.util.ArrayList;
import java.util.List;

public class Course implements  Enrollable{
    String courseCode;
    String title;
    Double credit;
    List<Course> prerequisites = new ArrayList<>();
    List<Evaluation> evaluations = new ArrayList<>();
    List<Student> enrolledStudents = new ArrayList<>();

    public Course(String courseCode, String title, Double credit) {
        this.courseCode = courseCode;
        this.title = title;
        this.credit = credit;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addPrerequisites(Course c)
    {
        for (Course c1 : prerequisites)
        {
            if (c1.courseCode.equals(c.courseCode)) return;
        }
        prerequisites.add(c);
    }

    public void addEvaluations(Evaluation eval)
    {
        for (Evaluation eval1 : evaluations)
        {
            if (eval.getTitle().equals(eval1.getTitle())) return;
        }
        evaluations.add(eval);
    }

    @Override
    public void enroll(Student s) throws EnrollmentException
    {
        for (Course c : prerequisites)
        {
            if (!s.getCompletedCourses().contains(c))
                throw new EnrollmentException("Prerequisite not fulfilled");
        }
        for (Student s1 : enrolledStudents)
        {
            if (s1.getStudentId().equals(s.getStudentId())) return;
        }
        enrolledStudents.add(s);
    }
}
