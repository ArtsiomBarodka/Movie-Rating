package epam.my.project.service;

import epam.my.project.configuration.ImageCategory;

public interface ImageService {
    String downloadImage(String url, ImageCategory imageCategory);

    boolean deleteImage(String path);
}
