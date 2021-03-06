package com.group56.searchservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Advert implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String carLocation;
    private LocalDateTime rentFrom;
    private LocalDateTime rentUntil;
    private LocalDateTime availableForRentFrom;
    private LocalDateTime availableForRentUntil;
    private boolean isProtectionAvailable;
    private BigDecimal protectionPrice;
    private BigDecimal price;
    private String publisher;
    private String uuid;
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "advert")
    private List<Mark> marks = new ArrayList<>();

    @OneToMany(mappedBy = "advert")
    private List<Comment> comments = new ArrayList<>();
}

