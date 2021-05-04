package ru.destered.semestr3sem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.destered.semestr3sem.models.User;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String username;
    private String code;
    private String email;
    private String state;
    private String role;
    private String phone;

    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .code(user.getCurrentConfirmationCode())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public String toString() {
        return id+";"+username+";"+email;
    }
}
