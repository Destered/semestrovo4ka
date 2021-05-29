package ru.destered.semestr3sem.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpForm{
    @NotBlank(message = "email is mandatory")
    @Email(message = "Input correct email")
    private String email;

    @Size(min = 8, max = 20)
    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "username is mandatory")
    private String username;

    private String phone;
}
