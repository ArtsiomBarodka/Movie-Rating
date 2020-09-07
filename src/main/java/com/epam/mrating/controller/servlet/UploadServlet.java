package com.epam.mrating.controller.servlet;

import com.epam.mrating.configuration.ImageCategory;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.configuration.Constants;
import com.epam.mrating.controller.request.RequestParameterNames;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Upload servlet.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@WebServlet("/upload/*")
@MultipartConfig(maxFileSize = Constants.MAX_UPLOADED_IMAGE_SIZE)
public class UploadServlet extends AbstractServlet {
    private static final int SUBSTRING_INDEX = "/upload/".length();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part filePart = req.getPart(RequestParameterNames.IMAGE);
            String mediaDirPath = req.getServletContext().getRealPath("/");
            InputStream fileContent = filePart.getInputStream();
            String category = req.getRequestURI().substring(SUBSTRING_INDEX);
            String imageLink = serviceFactory.getImageService().downloadImageFromStorage(mediaDirPath, fileContent, ImageCategory.of(category));
            JSONObject json = new JSONObject().put("ImageLink", imageLink);
            ViewUtil.sendJSON(json,req,resp);
        } catch (Exception e) {
            handleException(e);
        }
    }
}
