package ru.destered.semestr3sem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
@Tag(name="File controller", description="File controller API")
public class FileUploadController {

    @GetMapping
    public String provideUploadInfo() {
        return "download_page";
    }

    @Operation(
            summary = "Upload image",
            description = "Allow you upload image (also req file)"
    )
    @PostMapping
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("D:\\SemestrFiles\\" + name + ".png")));
                stream.write(bytes);
                stream.close();
                return "Вы удачно загрузили " + name + " в " + name + ".png!";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }

}
