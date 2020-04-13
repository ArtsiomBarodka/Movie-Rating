package epam.my.project.service.impl;

import epam.my.project.service.FileNameGeneratorService;

import java.util.UUID;

public class FileNameGeneratorServiceImpl implements FileNameGeneratorService {
    @Override
    public String generateUniqueFileName() {
        return UUID.randomUUID().toString()+".jpg";
    }
}
