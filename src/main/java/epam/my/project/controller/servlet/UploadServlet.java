package epam.my.project.controller.servlet;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.ImageCategory;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

@WebServlet("/upload/*")
@MultipartConfig(maxFileSize = Constants.MAX_UPLOADED_IMAGE_SIZE)
public class UploadServlet extends AbstractServlet {
    private static final int SUBSTRING_INDEX = "/upload/".length();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("image");
        String mediaDirPath = req.getServletContext().getRealPath("/");
        InputStream fileContent = filePart.getInputStream();
        try {
            String category = req.getRequestURI().substring(SUBSTRING_INDEX);
            String imageLink = serviceFactory.getImageService().downloadImageFromStorage(mediaDirPath, fileContent, ImageCategory.of(category));
            JSONObject json = new JSONObject().put("ImageLink", imageLink);
            sendJson(json, resp);
        } catch (Exception e) {
            handleException(e);
        }

    }

    private void sendJson(JSONObject json, HttpServletResponse response) throws IOException {
        String content = json.toString();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (Writer wr = response.getWriter()) {
            wr.write(content);
            wr.flush();
        }
    }
}
