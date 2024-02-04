package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String picName;
@ManyToOne
@JoinColumn(name="lesson_id")
    private Lesson lesson;
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
