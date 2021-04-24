package ru.destered.semestr3sem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tagsList = new HashSet<>();

    public void addTag(Tag tag){
        this.tagsList.add(tag);
        tag.getPosts().add(this);
    }

    public void removeTag(Tag tag){
        this.tagsList.remove(tag);
        tag.getPosts().remove(this);
    }
}
