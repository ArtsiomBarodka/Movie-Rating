package epam.my.project.model.validation;

@FunctionalInterface
public interface Validator<F> {
    boolean validate(F value);
}
