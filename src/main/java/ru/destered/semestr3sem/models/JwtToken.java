package ru.destered.semestr3sem.models;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
