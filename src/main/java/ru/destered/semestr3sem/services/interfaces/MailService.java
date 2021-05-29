package ru.destered.semestr3sem.services.interfaces;


import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.EmailForm;
import ru.destered.semestr3sem.models.User;

public interface MailService {
    void sendMail(UserDto userDto);

    void sendSupportMail(EmailForm form);

    void sendMail(User user);
}
