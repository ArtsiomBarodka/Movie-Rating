package epam.my.project.service;

public interface ImageService {
    String downloadImage(String url);
    boolean deleteImage(String path);
}
