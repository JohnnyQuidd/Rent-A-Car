package com.group56.adminservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private boolean isActive = true;
    private boolean isBlocked = false;
    private int numberOfAdvertsCancelled = 0;
    private boolean modified = false;
}
