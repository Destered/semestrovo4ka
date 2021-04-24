package ru.destered.semestr3sem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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
