package ru.destered.semestr3sem.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.models.User;

@Mapper(componentModel = "spring")
public interface FormUserMapper {
    @Mappings({
            @Mapping(target = "email", source = "form.email"),
            @Mapping(target = "password", source = "form.password"),
            @Mapping(target = "username", source = "form.username")
    })
    User signUpToUser(SignUpForm form);

    @InheritInverseConfiguration
    SignUpForm userToSignUp(User user);
}