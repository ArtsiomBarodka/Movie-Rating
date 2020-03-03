package epam.my.project.entity;

public class Genre extends AbstractEntity<Integer>{
    private static final long serialVersionUID = 6486558238916883878L;

    private String name;
    private String url;

    public Genre() {
    }

    public Genre(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
