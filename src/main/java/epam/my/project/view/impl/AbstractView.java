package epam.my.project.view.impl;

import epam.my.project.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractView implements View {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Override
    public View init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        return this;
    }

}
