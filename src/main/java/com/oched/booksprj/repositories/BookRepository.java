package com.oched.booksprj.repositories;


import com.oched.booksprj.entities.BookDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDescriptionEntity, Long> {

}
