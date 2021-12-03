package com.oched.booksprj.repositories;

import com.oched.booksprj.entities.Author;
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
public class AuthorRepository {
    private List<Author> authors;

    public AuthorRepository() {
        this.authors = new ArrayList<>();
    }
}
