package ru.destered.semestr3sem.services.interfaces;

import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.dto.forms.UserAuthForm;

public interface EditProfileService {
    void updateProfile(Long userId, SignUpForm form);
}
