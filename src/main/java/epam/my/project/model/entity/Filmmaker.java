package epam.my.project.model.entity;

public class Filmmaker extends AbstractEntity<Integer>{
    private static final long serialVersionUID = 8485914243075800755L;

    private String firstName;
    private String lastName;

    public Filmmaker() {
    }

    public Filmmaker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Filmmaker{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}
