import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Create Students
            Student s1 = new Student("S101", "Alice");
            Student s2 = new Student("S102", "Bob");
            Student s3 = new Student("S103", "Alan");

            // Create Courses
            Course oop = new Course("CSE101", "Object-Oriented Programming", 3.0);
            Course algo = new Course("CSE201", "Algorithms", 3.0);
            algo.addPrerequisites(oop); // algo has oop as prerequisite

            // Mark oop as completed for s1 only
            s1.addCompletedCourses(oop);

            // Add evaluations to oop
            Evaluation assign1 = new Assignment("Assignment 1", 100, 0.2, "2025-06-30", "No plagiarism");
            Evaluation ct1 = new CT("CT-1", 50, 0.3, "Room 101", "10:00 AM");
            Evaluation viva1 = new Viva("Viva 1", 50, 0.5, "Dr. Smith, Dr. John");

            oop.addEvaluations(assign1);
            oop.addEvaluations(ct1);
            oop.addEvaluations(viva1);

            // Enroll students
            oop.enroll(s1);
            oop.enroll(s2); // should succeed (no prerequisite for oop)
            oop.enroll(s3);

            algo.enroll(s1); // should succeed (s1 completed oop)
            // algo.enroll(s2); // will throw EnrollmentException (commented for testing)

            // Create evaluation results
            EvaluationResult r1 = new IndividualResult(assign1, s1, 105); // invalid, cause score > totalMarks
            EvaluationResult r2 = new IndividualResult(ct1, s1, 40);
            EvaluationResult r3 = new IndividualResult(viva1, s1, 45);

            List<Student> groupMembers = new ArrayList<>();
            groupMembers.add(s2);
            groupMembers.add(s3);
            GroupResult gr1 = new GroupResult(viva1, groupMembers, 42, "G1");

            // Add to course result
            CourseResult cr1 = new CourseResult(s1, oop);
            cr1.addEvaluationResults(r1);
            cr1.addEvaluationResults(r2);
            cr1.addEvaluationResults(r3);

            CourseResult cr2 = new CourseResult(s2, oop);
            cr2.addEvaluationResults(gr1);
            CourseResult cr3 = new CourseResult(s3, oop);
            cr3.addEvaluationResults(gr1);


            s1.addCourseResults(cr1);
            s2.addCourseResults(cr2);
            s3.addCourseResults(cr3);


            // Show student info and GPA
            s1.showInfo();
            s2.showInfo();
            s3.showInfo();

        } catch (EnrollmentException e) {
            System.out.println("Enrollment Failed: " + e.getMessage());
        }
    }
}
