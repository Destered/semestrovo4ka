package ru.destered.semestr3sem.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.destered.semestr3sem.services.interfaces.FileUploadService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String uploadFile(MultipartFile file){
        File filePath = new File(uploadPath);

        if(!filePath.exists()) filePath.mkdir();

        String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

        File postFile = new File(uploadPath + '/' + filename);
        try {
            file.transferTo(postFile);
        } catch (IOException e) {throw new IllegalStateException(e);}

        return filename;
    }


}
