package Exercises.InterfaceStudentTeacher;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        PersonContainer container = PersonContainer.getInstance();
        container.addPerson(new Teacher("John", 30, 1000));
        container.addPerson(new Student("Jane", 20, "Computer Science"));
        container.addPerson(new Teacher("Jack", 40, 2000));
        container.addPerson(new Student("Jill", 25, "Mathematics"));

        for (Person person : container.getPersons()) {
            person.printInfo();
        }
    }
}