package ru.destered.semestr3sem.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserAuthForm {
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;

}
