package epam.my.project.tag;

import epam.my.project.model.validation.Violations;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class UseViolationsTag extends SimpleTagSupport {
    private String field;
    private Violations violations;

    public void setField(String field) {
        this.field = field;
    }

    public void setViolations(Violations violations) {
        this.violations = violations;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String message = violations.hasError(field) ? violations.getMessage(field): "";
        JspWriter out = getJspContext().getOut();
        out.println(message);
    }
}
