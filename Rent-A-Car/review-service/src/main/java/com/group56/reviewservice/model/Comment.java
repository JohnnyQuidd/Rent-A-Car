package com.group56.reviewservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group56.reviewservice.enumertation.CommentStatus;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String text;
    private int mark;
    private String username;
    @ManyToOne
    private Advert advert;
    @Enumerated(EnumType.ORDINAL)
    private CommentStatus commentStatus;
}
