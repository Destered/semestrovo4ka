package ru.destered.semestr3sem.services.implementations;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.EmailForm;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.MailService;
import ru.destered.semestr3sem.services.interfaces.SenderService;
import ru.destered.semestr3sem.services.interfaces.TemplateProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final TemplateProcessor templateProcessor;
    private final SenderService senderService;
    private final UsersRepository repository;

    @Value("${server.basic.address}")
    private String serverBasicAddress;

    @Value("${admin.email}")
    private String adminEmail;

    @Override
    public String sendMail(UserDto userDto) {
        Map<String, String> parameters = new HashMap<>();
        try {
            User user = repository.findById(userDto.getId())
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
            parameters.put("name", user.getUsername());
        } catch(Throwable ex){
            return "redirect:/error";
        }
        parameters.put("link", serverBasicAddress + "confirm/" + userDto.getCode());
        sendMail(parameters, "mail.ftl", userDto.getEmail(), "Confirm your registration");
        return "redirect:/login";
    }

    @Override
    public void sendSupportMail(EmailForm form) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("email", form.getEmail());
        parameters.put("text", form.getText());
        sendMail(parameters, "supportMail.ftl", adminEmail, "Support request");
    }

    @Override
    public void sendMail(User user){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", user.getUsername());
        parameters.put("email", user.getEmail());
        parameters.put("password", user.getPassword());
        parameters.put("role", user.getRole().toString());
        parameters.put("state", user.getState().toString());
        parameters.put("phone", user.getPhone());
        sendMail(parameters, "userDetailsMail.ftl", user.getEmail(), "Send user details");
    }


    private void sendMail(Map<String, String> parameters, String template, String email, String subject) {
        String html = templateProcessor.getProcessedTemplate(parameters, template);
        senderService.sendMessage(subject, email, html);
    }

}
