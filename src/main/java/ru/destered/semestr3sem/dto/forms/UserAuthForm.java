package ru.destered.semestr3sem.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserAuthForm {
    @NotBlank(message = "email is mandatory")
    @Email(message = "Input correct email")
    @NotNull(message = "Input email")
    private String email;

    @Size(min = 8, max = 20)
    @NotNull(message = "Enter password")
    @NotBlank(message = "password is mandatory")
    private String password;

}
