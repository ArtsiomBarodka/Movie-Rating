package epam.my.project.validation;

public interface Validator<F> {
    boolean validate(F value);
}
