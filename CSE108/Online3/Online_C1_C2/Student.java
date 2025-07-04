import java.util.ArrayList;
import java.util.List;

public class Student {
    String studentId;
    String name;
    List<Course> completedCourses = new ArrayList<>();
    List<CourseResult> courseResults = new ArrayList<>();

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<Course> getCompletedCourses() {
        return completedCourses;
    }

    public double getGPA()
    {
        double gpa = 0;
        for (CourseResult cRes : courseResults) gpa += cRes.calculateFinalScore();
        gpa /= courseResults.size();
        return gpa;
    }

    public void addCompletedCourses(Course c)
    {
        for (Course c1 : completedCourses)
        {
            if (c1.courseCode.equals(c.courseCode)) return;
        }
        completedCourses.add(c);
    }

    public void addCourseResults(CourseResult cRes)
    {
        courseResults.add(cRes);
    }

    public void showInfo()
    {
        System.out.println("Name: " + name + ", ID: " + studentId);
    }
}
