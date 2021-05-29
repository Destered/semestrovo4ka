package ru.destered.semestr3sem.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadingImageService {
    String upload(MultipartFile file) throws IOException;
}
