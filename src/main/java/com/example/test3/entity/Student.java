package com.example.test3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 32)
    private String name;

    @Min(value = 0, message = "Score must be between 0 and 100")
    @Max(value = 100, message = "Score must be between 0 and 100")
    @Column(nullable = false)
    private int score;

    @Min(value = 1, message = "Credit must be between 1 and 10")
    @Max(value = 10, message = "Credit must be between 1 and 10")
    @Column(nullable = false)
    private float credit;

    @Column(nullable = false, length = 1)
    private char grade;

    @Column(nullable = false)
    private boolean gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateReport;
}
