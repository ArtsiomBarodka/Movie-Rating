package epam.my.project.model.validation;

public interface Validator<F> {
    boolean validate(F value);
}
