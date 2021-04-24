package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Controller
@RequestMapping("/download")
@RequiredArgsConstructor
public class FileGetController {

    @GetMapping("/{filename}")
    public String getFile(@PathVariable(name = "filename") String filename,
                          RedirectAttributes redirectAttributes, Model model) throws IOException {
        String path = "D:\\SemestrFiles\\";
        if(filename.contains(".png"))  path += filename;
        else return ("redirect:/download/" + filename + ".png");
        File img = new File(path);
        byte[] data = Files.readAllBytes(img.toPath());
        byte[] encoded = Base64.getEncoder().encode(data);
        String imgDataAsBase64 = new String(encoded);
        //Если передать путь, то браузер выкенет ошибку тип загрузка локальных файлов, пашол атсюда чорт
        //Так что берём файл и переводим его
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        model.addAttribute("isLogged","true");
        model.addAttribute("image",imgAsBase64);
        return "get_picture";
    }
}
