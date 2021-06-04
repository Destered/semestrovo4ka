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
    private String phone;
    private String avatarImageName;
    private String role;


    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .username(user.getUsername())
                .avatarImageName(user.getAvatarImageName())
                .build();
    }

    @Override
    public String toString() {
        return id+";"+email+";"+username;
    }
}
