package epam.my.project.model.entity;

public class Country extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 1313806529887977090L;

    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CountryDAOImpl{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
