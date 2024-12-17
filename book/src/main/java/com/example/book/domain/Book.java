package com.example.book.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 45)
    private String subtitle;

    @Column(length = 45)
    private String author;

    @Column(length = 45)
    private String publisher;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum Status{
        BORROWED,
        AVAILABLE
    }
}
