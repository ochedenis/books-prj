package com.oched.booksprj.repositories;


import com.oched.booksprj.entities.BookDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookDescriptionEntity, Long> {
    Optional<BookDescriptionEntity> findTopByTitleAndYearOrderByIdDesc(String title, int year);
}
