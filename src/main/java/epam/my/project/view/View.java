package epam.my.project.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface View {
    View init(HttpServletRequest request, HttpServletResponse response);
    void render(Object rendering) throws ServletException, IOException;
}
