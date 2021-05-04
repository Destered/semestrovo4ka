package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.destered.semestr3sem.services.interfaces.ConfirmationService;

@Controller
@RequestMapping("/confirm")
@RequiredArgsConstructor
public class ConfirmationController {
    private final ConfirmationService confirmationService;

    @GetMapping("/{key}")
    public String confirmAccount(@PathVariable(name = "key") String code,
                                 RedirectAttributes redirectAttributes) {
        boolean confirmationResult = confirmationService.confirmByCode(code);
        String redirectUrl = confirmationResult ? "redirect:/signIn" : "redirect:/signIn";
        String infoMessage = confirmationResult ? "Successfully confirmed" : "Code not found";
        return redirectUrl;
    }
}
