import java.util.ArrayList;
import java.util.List;

public class PersonContainer {
    private List<Person> persons;
    private static PersonContainer instance;

    public PersonContainer() {
        persons = new ArrayList<>();
    }

    public static PersonContainer getInstance() {
        if (instance == null) {
            instance = new PersonContainer();
        }
        return instance;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public List<Person> getPersons() {
        return persons;
    }
}
