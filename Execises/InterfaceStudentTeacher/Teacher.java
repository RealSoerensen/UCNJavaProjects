public class Teacher extends Person implements TeacherIF {
    private double salary;

    public Teacher(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void printInfo() {
        System.out.println("Teacher: " + getName() + ", age: " + getAge() + ", salary: " + getSalary());
    }
}
