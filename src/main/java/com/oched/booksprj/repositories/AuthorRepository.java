package com.oched.booksprj.repositories;

import com.oched.booksprj.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<AuthorEntity> findByIdAndFirstNameAndLastName(long id, String firstName, String lastName);
}
