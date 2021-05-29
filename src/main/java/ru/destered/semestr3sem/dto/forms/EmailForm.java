package ru.destered.semestr3sem.dto.forms;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailForm {
    @Email
    private String email;
    @NotBlank
    private String text;
}
