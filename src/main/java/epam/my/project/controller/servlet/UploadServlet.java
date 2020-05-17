package epam.my.project.controller.servlet;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.ImageCategory;
import epam.my.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import static org.apache.logging.log4j.LogManager.getLogger;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = Constants.MAX_UPLOADED_IMAGE_SIZE)
public class UploadServlet extends HttpServlet {
    private static final Logger logger = getLogger(UploadServlet.class);
    private ServiceFactory serviceFactory;

    @Override
    public void init() throws ServletException {
        this.serviceFactory = ServiceFactory.SERVICE_FACTORY_INSTANCE;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("image"); // Retrieves <input type="file" name="file">
        InputStream fileContent = filePart.getInputStream();
        serviceFactory.getImageService().downloadImageFromStorage(fileContent, ImageCategory.USER_PHOTO);
    }
}
