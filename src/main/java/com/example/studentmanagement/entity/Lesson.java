package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table(name = "lesson")
@Entity
@Data
@ToString(exclude="user")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String duration;
    private String price;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @ManyToOne
    private User user;

}
