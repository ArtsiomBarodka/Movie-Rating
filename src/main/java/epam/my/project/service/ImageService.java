package epam.my.project.service;

import epam.my.project.configuration.ImageCategory;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {
    String downloadImageFromUrl(String url, ImageCategory imageCategory) throws IOException;

    String downloadImageFromStorage(InputStream in, ImageCategory imageCategory) throws IOException;

    boolean deleteImage(String path);
}
