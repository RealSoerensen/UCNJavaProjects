public class Student extends Person implements StudentIF {
    private String major;

    public Student(String name, int age, String major) {
        super(name, age);
        this.major = major;
    }

    @Override
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void printInfo() {
        System.out.println("Student: " + getName() + ", age: " + getAge() + ", major: " + getMajor());
    }
}
