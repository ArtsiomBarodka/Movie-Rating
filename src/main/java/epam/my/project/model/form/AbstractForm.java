package epam.my.project.model.form;

import epam.my.project.model.validation.Violations;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;


public abstract class AbstractForm {
    Violations violations;

    AbstractForm() {
        this.violations = new Violations();
    }

    public Violations getViolations(){
        return violations;
    }

    Integer convertToInteger(String s){
        return Integer.parseInt(s);
    }

    Long convertToLong(String s){
        return Long.parseLong(s);
    }

    Short convertToYear(String year) {
        return Short.valueOf(year);
    }

    Time convertToTime(String s){
        return Time.valueOf(s);
    }

    Boolean convertToBoolean(String s){
        return Objects.nonNull(s);
    }
}
