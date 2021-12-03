package com.oched.booksprj.repositories;

import com.oched.booksprj.entities.BookDescription;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@Scope("singleton")
public class BookRepository {
    private List<BookDescription> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }
}
