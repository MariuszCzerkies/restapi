package com.example.restapi.external.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    private String title;
    private String content;
    private LocalDateTime created;
}
