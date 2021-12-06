package com.oched.booksprj.entities;

import lombok.*;


import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
public class BookDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int year;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    private AuthorEntity author;
    @OneToOne
    @JoinColumn(name = "content_id")
    private BookContentEntity content;

    public BookDescriptionEntity(String title, int year, AuthorEntity author, BookContentEntity content) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.content = content;
    }
}
