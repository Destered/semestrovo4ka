package ru.destered.semestr3sem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.destered.semestr3sem.dto.forms.EmailForm;
import ru.destered.semestr3sem.services.interfaces.MailService;

@RestController
@RequiredArgsConstructor
@Tag(name="Support controller", description="Support controller API")
public class SupportController {
    private final MailService mailService;

    @Operation(
            summary = "send email",
            description = "send email to support"
    )
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/sendSupportMessage")
    public RedirectView sendSupportMessage(EmailForm form) {
        mailService.sendSupportMail(form);
        return new RedirectView("/support");
    }
}
