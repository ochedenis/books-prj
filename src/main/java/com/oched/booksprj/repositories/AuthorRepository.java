package com.oched.booksprj.repositories;

import com.oched.booksprj.entities.AuthorEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
@Scope("singleton")
public class AuthorRepository {
    private List<AuthorEntity> authorEntities;

    public AuthorRepository() {
        this.authorEntities = new ArrayList<>();
    }
}
