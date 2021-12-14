package com.oched.booksprj.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books_content")
@NoArgsConstructor
public class BookContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @OneToOne(mappedBy = "content")
    private BookDescriptionEntity description;

    public BookContentEntity(String content) {
        this.content = content;
    }
}
