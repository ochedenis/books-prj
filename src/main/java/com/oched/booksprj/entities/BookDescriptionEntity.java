package com.oched.booksprj.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDescriptionEntity {
    private long id;
    private String title;
    private int year;
    private AuthorEntity authorEntity;
    private BookContentEntity content;
}
