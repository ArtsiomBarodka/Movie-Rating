package epam.my.project.model.form;

import epam.my.project.model.validation.Violations;

import java.sql.Date;
import java.sql.Time;


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

    Date convertToDate(String s) {
        return Date.valueOf(s);
    }

    Time convertToTime(String s){
        return Time.valueOf(s);
    }

    Boolean convertToBoolean(String s){
        return Boolean.valueOf(s);
    }
}
