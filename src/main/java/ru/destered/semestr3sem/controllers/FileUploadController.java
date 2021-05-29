package ru.destered.semestr3sem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.services.implementations.UsersServiceImpl;
import ru.destered.semestr3sem.services.interfaces.EditProfileService;
import ru.destered.semestr3sem.services.interfaces.UploadingImageService;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/uploadAvatar")
@RequiredArgsConstructor
@Tag(name="File controller", description="File controller API")
public class FileUploadController {
    private final UploadingImageService uploadingImageService;
    private final UsersServiceImpl usersService;
    private final EditProfileService editUserService;

    @PostMapping
    public @ResponseBody
    RedirectView handleFileUpload(@CookieValue Cookie token,
                                  @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                String filename = uploadingImageService.upload(file);
                DecodedJWT jwt = JWT.decode(token.getValue());
                UserDto user = usersService.getUser(Long.parseLong(jwt.getSubject()));
                user.setAvatarImageName(filename);
                editUserService.updateProfileFromDto(Long.parseLong(jwt.getSubject()),user);
                return new RedirectView("/logout");
            } catch (Exception e) {
                return new RedirectView("/error");
            }
        } else {
            return new RedirectView("/error");
        }
    }

}
