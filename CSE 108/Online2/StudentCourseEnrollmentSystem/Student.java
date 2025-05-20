public class Student
{
    private int id;
    private String name;

    private static int stuCount = 0;
    private static Student students[] = new Student[100];

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static int getStuCount() {
        return stuCount;
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Student addStudent(int id, String name)
    {
        if (stuCount < 100)
        {
            students[stuCount++] = new Student(id, name);
            return students[stuCount-1];
        }
        else
        {
            System.out.println("Cannot have more than 100 students");
            return null;
        }
    }

    public static Student findStudent(int id)
    {
        for (int i = 0; i < stuCount; i++)
        {
            if (students[i].getId() == id) return students[i];
        }
        return null;
    }

    public void display()
    {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}
