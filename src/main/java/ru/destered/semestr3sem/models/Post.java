package ru.destered.semestr3sem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    private Date dateOfCreation;

    private Double rating;

    private Integer ratingCount;


    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tagsList = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "post",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();


    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addTag(Tag tag) {
        this.tagsList.add(tag);
        tag.getPosts().add(this);
    }


    public void removeTag(Tag tag) {
        this.tagsList.remove(tag);
        tag.getPosts().remove(this);
    }
}
