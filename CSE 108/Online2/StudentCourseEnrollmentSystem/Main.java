import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // int courseNum = Integer.parseInt(args[0]);
        int courseNum = 2;

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < courseNum; i++)
        {
            String name = sc.nextLine();
            int capacity = sc.nextInt();
            sc.nextLine();
            Course.addCourse(name, capacity);
        }

        int stuID, courseID;
        String name;
        Student stu;
        Course course;

        while (true)
        {
            System.out.println("Menu:");
            System.out.println("1. Enroll a New Student in a Course");
            System.out.println("2. Enroll Existing Student in a Course");
            System.out.println("3. Drop Student from a Course");
            System.out.println("4. Display All Courses with Enrolled Students");
            System.out.println("5. Exit");

            switch(sc.nextInt())
            {
                case 1 -> {
                    stuID = sc.nextInt();
                    sc.nextLine();
                    name = sc.nextLine();
                    stu = Student.addStudent(stuID, name);

                    courseID = sc.nextInt();
                    course = Course.findCourse(courseID);

                    if (course != null && stu != null) course.enroll(stu);
                    else System.out.println("Not found");
                }

                case 2 -> {
                    stuID = sc.nextInt();
                    sc.nextLine();
                    stu = Student.findStudent(stuID);

                    courseID = sc.nextInt();
                    course = Course.findCourse(courseID);

                    if (course != null && stu != null) course.enroll(stu);
                    else System.out.println("Not found");
                }

                case 3 -> {
                    stuID = sc.nextInt();
                    sc.nextLine();

                    courseID = sc.nextInt();
                    course = Course.findCourse(courseID);

                    if (course != null) course.drop(stuID);
                    else System.out.println("Course not found");
                }

                case 4 -> Course.displayCourses();

                case 5 -> {
                    sc.close();
                    System.out.println("Exiting...");
                    return;
                }
            }
        }

    }
}
