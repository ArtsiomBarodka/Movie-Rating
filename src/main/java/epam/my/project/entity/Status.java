package epam.my.project.entity;

public class Status extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 2803777656142892989L;

    private String name;
    private int level;

    public Status() {
    }

    public Status(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", id=" + id +
                '}';
    }
}
