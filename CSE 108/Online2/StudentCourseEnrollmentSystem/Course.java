public class Course
{
    private int id;
    private String name;
    private int capacity;
    private int stuCount;
    private Student students[];

    private static int courseCount = 0;
    private static Course courses[] = new Course[30];

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getStuCount() {
        return stuCount;
    }

    public static int getCourseCount() {
        return courseCount;
    }

    public static Course getCourse(int id)
    {
        if (id <= courseCount) return courses[id - 1];

        else return null;
    }

    public int findStudent(int id)
    {
        for (int i = 0; i < stuCount; i++)
        {
            if (students[i].getId() == id) return i;
        }
        return -1;
    }

    public Course(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;

        stuCount = 0;
        students = new Student[capacity];
    }

    public static void addCourse(String name, int capacity)
    {
        if (courseCount < 30)
        {
            courses[courseCount++] = new Course(courseCount, name, capacity);
            // return courses[courseCount-1];
        }
        else
        {
            System.out.println("Cannot have more than 30 courses");
            // return null;
        }
    }

    public static Course findCourse(int id)
    {
        for (int i = 0; i < courseCount; i++)
        {
            if (courses[i].getId() == id) return courses[i];
        }
        return null;
    }

    public static void displayCourses()
    {
        for (int i = 0; i < courseCount; i++)
        {
            courses[i].display();
        }
    }

    public void display()
    {
        System.out.print("Course ID: " + id + ", Course Name: " + name);
        System.out.println(", Capacity: " + capacity + ", Current Enrollment Count: " + stuCount);

        if (stuCount == 0) System.out.println("No student enrolled");

        else
        {
            System.out.println("Students: ");
            for (int i = 0; i < stuCount; i++) students[i].display();
        }
    }

    public void enroll(Student stu)
    {
        if (stuCount < capacity && findStudent(stu.getId()) == -1)
        {
            students[stuCount++] = stu;
            System.out.println("New student enrolled");
        }
        else if (stuCount >= capacity)
        {
            System.out.println("Course capacity full");
        }
        else
        {
            System.out.println("Student already enrolled");
        }
    }

    public void drop(int id)
    {
        int idx = findStudent(id);
        if (idx != -1)
        {
            for (int i = idx; i < stuCount; i++) students[i] = students[i+1];
            stuCount--;
            System.out.println("Student dropped");
        }
        else
        {
            System.out.println("Student not enrolled");
        }
    }
}
