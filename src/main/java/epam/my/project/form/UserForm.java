package epam.my.project.form;

public class UserForm {
    private String name;
    private boolean isBanned;
    private double rating;

    public UserForm() {
    }

    public UserForm(String name, boolean isBanned, double rating) {
        this.name = name;
        this.isBanned = isBanned;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
