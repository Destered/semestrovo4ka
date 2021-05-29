package ru.destered.semestr3sem.services.interfaces;

import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;

public interface EditProfileService {
    void updateProfile(Long userId, SignUpForm form);

    void updateProfileFromDto(Long userId, UserDto user);
}
