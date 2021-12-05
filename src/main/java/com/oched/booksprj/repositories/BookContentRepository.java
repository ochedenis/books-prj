package com.oched.booksprj.repositories;

import com.oched.booksprj.entities.BookContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookContentRepository extends JpaRepository<BookContentEntity, Long> {

}
