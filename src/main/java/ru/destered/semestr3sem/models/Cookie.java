package ru.destered.semestr3sem.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cookie")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cookie {
    @Id
    private String uuid;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public static Cookie fromValueAndUser(String value, User user) {
        return Cookie.builder()
                .uuid(value)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
    }
}
