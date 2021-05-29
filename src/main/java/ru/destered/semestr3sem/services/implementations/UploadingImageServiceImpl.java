package ru.destered.semestr3sem.services.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.destered.semestr3sem.services.interfaces.UploadingImageService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadingImageServiceImpl implements UploadingImageService {
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String upload(MultipartFile file) {
        File photoPath = new File(uploadPath);

        if(!photoPath.exists()) photoPath.mkdir();

        String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

        File photo = new File(uploadPath + '/' + filename);
        try {
            file.transferTo(photo);
        } catch (IOException e) {throw new IllegalStateException(e);}

        return filename;
    }
}
