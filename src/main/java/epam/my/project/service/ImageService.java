package epam.my.project.service;

import epam.my.project.configuration.ImageCategory;
import epam.my.project.exception.InternalServerErrorException;
import java.io.InputStream;

public interface ImageService {
    String downloadImageFromUrl(String url, ImageCategory imageCategory) throws InternalServerErrorException;

    String downloadImageFromStorage(InputStream in, ImageCategory imageCategory) throws  InternalServerErrorException;

    boolean deleteImage(String path);
}
