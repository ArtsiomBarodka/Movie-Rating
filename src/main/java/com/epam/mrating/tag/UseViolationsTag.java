package com.epam.mrating.tag;

import com.epam.mrating.component.validator.model.Violations;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * The type Use violations tag.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class UseViolationsTag extends SimpleTagSupport {
    private String field;
    private Violations violations;

    /**
     * Sets field.
     *
     * @param field the field
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Sets violations.
     *
     * @param violations the violations
     */
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
