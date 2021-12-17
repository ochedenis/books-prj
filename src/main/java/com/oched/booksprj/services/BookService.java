package com.oched.booksprj.services;

import com.oched.booksprj.entities.AuthorEntity;
import com.oched.booksprj.entities.BookContentEntity;
import com.oched.booksprj.entities.BookDescriptionEntity;
import com.oched.booksprj.exceptions.BadRequestException;
import com.oched.booksprj.repositories.BookContentRepository;
import com.oched.booksprj.requests.ActionRequest;
import com.oched.booksprj.requests.EditBookRequest;
import com.oched.booksprj.requests.NewBookRequest;
import com.oched.booksprj.repositories.AuthorRepository;
import com.oched.booksprj.repositories.BookRepository;
import com.oched.booksprj.responses.BookInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookContentRepository contentRepository;

    public void addBook(NewBookRequest request) {
        Optional<AuthorEntity> optionalAuthor = authorRepository.findByFirstNameAndLastName(
                request.getAuthorFirstName(),
                request.getAuthorLastName()
        );

        AuthorEntity author;

        if(optionalAuthor.isPresent()) {
            author = optionalAuthor.get();
        } else {
            author = new AuthorEntity(
                    request.getAuthorFirstName(),
                    request.getAuthorLastName()
            );

            authorRepository.save(author);
        }

        BookContentEntity bookContent = new BookContentEntity(request.getContent());

        contentRepository.save(bookContent);

        BookDescriptionEntity newBook = new BookDescriptionEntity(
                request.getTitle(),
                request.getYear(),
                author,
                bookContent
        );

        bookRepository.save(newBook);
        clearCache();
    }

    @Cacheable("listCache")
    public List<BookInfoResponse> getAll() {
        List<BookDescriptionEntity> list = bookRepository.findAll();


        return list.stream().map(book -> new BookInfoResponse(
                book.getId(),
                book.getTitle(),
                book.getYear(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName()
        )).collect(Collectors.toList());
    }

    public void deleteBook(ActionRequest request) {
        this.bookRepository.deleteById(request.getId());
    }

    public BookInfoResponse editBook(EditBookRequest request) {
        Optional<BookDescriptionEntity> optional = this.bookRepository.findById(request.getId());

        BookDescriptionEntity book = optional.orElseThrow(
                () -> new BadRequestException("No book with such id!", HttpStatus.BAD_REQUEST)
        );

        Optional<AuthorEntity> optionalAuthor = this.authorRepository.findByFirstNameAndLastName(
                request.getAuthorFirstName(),
                request.getAuthorLastName()
        );

        AuthorEntity author;

        if(optionalAuthor.isPresent()) {
            author = optionalAuthor.get();
        } else {
            author = new AuthorEntity(
                    request.getAuthorFirstName(),
                    request.getAuthorLastName()
            );

            authorRepository.save(author);
        }

        book.setAuthor(author);
        book.setTitle(request.getTitle());
        book.setYear(request.getYear());

        this.bookRepository.save(book);

        clearCache();

        return new BookInfoResponse(
                book.getId(),
                book.getTitle(),
                book.getYear(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName()
        );
    }

    @Cacheable(value = "singleCache", condition="#request.getId()==3")
    public BookInfoResponse getById(ActionRequest request) {
        Optional<BookDescriptionEntity> optional = this.bookRepository.findById(request.getId());

        BookDescriptionEntity book = optional.orElseThrow(
                () -> new BadRequestException("No book with such id!", HttpStatus.BAD_REQUEST)
        );

        return new BookInfoResponse(
                book.getId(),
                book.getTitle(),
                book.getYear(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName()
        );
    }

    @Scheduled(fixedRate = 60000, fixedDelay = 600000)
    @CacheEvict({"listCache", "singleCache"})
    public void clearCache() {
        log.info("CACHE log :::::::::::::::::::::::::> listCache was cleared!");
    }
}
