package ru.destered.semestr3sem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    private String text = "";

    private String username = "";

    public static Comment commentFromText(String username, String text,Post post) {
        return Comment.builder()
                .text(text)
                .username(username)
                .post(post)
                .build();
    }
}
