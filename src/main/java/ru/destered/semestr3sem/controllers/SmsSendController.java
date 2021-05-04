package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.destered.semestr3sem.dto.SmsInfo;
import ru.destered.semestr3sem.services.interfaces.SmsConfirmService;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("/confirmPhone")
@RequiredArgsConstructor
public class SmsSendController {

    private final SmsConfirmService smsSender;

    @PermitAll
    @GetMapping("/sendSms")
    public String sendSmsMessage(@RequestParam String phone, @RequestParam String text) {
        return smsSender.sendSms(phone, text);
    }

    @PermitAll
    @GetMapping("/checkStatus")
    public String checkSmsStatus(@ModelAttribute SmsInfo smsInfo){
        return smsSender.checkSmsStatus(smsInfo);
    }
}
