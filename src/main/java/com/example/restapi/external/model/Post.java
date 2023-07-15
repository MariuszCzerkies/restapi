package com.example.restapi.external.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
//    @OneToMany(orphanRemoval = true)//pozwala na usuwanie komentarzy przy użyciu @Post -> orphanRemoval = true
    @OneToMany(cascade = CascadeType.REMOVE)//cascade = CascadeType.REMOVE -> pozwala na usuwanie @DeleteMapping("/post/{id}"), jeśli tego nie będzie to dostaniemy ->
                                            // błąd COMMENT_POST_ID: PUBLIC.COMMENT FOREIGN KEY(POST_ID) REFERENCES PUBLIC.POST(ID) (CAST(1 AS BIGINT))"; SQL statement
                                            // CascadeType.REMOVE -> tylko do usuwania
    @JoinColumn(name = "postId", updatable = false, insertable = false)// updatable = false -> przy update @Post, hibernate nie będzie nam usuwał komentarzy
                                                                        // insertable = false -> jeśli chcielibyśmy dodać komentarze to żeby hibernate ich nie zmieniał
    private List<Comment> comment;
}