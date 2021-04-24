package ru.destered.semestr3sem.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.destered.semestr3sem.dto.forms.SignUpForm;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    @Min(6)
    private String password;

    @Column(name = "username")
    @NotNull
    private String username;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }


    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Cookie> cookieList;

    private boolean proved;

    private String currentConfirmationCode;

    public User(Long id) {
        this.id = id;
    }

    public static User fromSignUpForm(SignUpForm form) {
        return User.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();
    }
}
