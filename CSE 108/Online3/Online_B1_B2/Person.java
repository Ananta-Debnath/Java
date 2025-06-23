public abstract class Person {
    String name;
    int age;
    String gender;
    String contactInfo;

    public Person(String name, int age, String gender, String contactInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    abstract void showInfo();
}
