package epam.my.project.controller.servlet;

import epam.my.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.apache.logging.log4j.LogManager.getLogger;

@WebServlet("/*")
public class FrontServlet extends HttpServlet {
    private static final Logger logger = getLogger(FrontServlet.class);
    private ServiceFactory serviceFactory;

    @Override
    public void init() throws ServletException {
        this.serviceFactory = ServiceFactory.SERVICE_FACTORY_INSTANCE;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
