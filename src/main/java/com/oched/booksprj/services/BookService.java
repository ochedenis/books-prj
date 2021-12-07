package com.oched.booksprj.services;

import com.oched.booksprj.entities.AuthorEntity;
import com.oched.booksprj.entities.BookContentEntity;
import com.oched.booksprj.entities.BookDescriptionEntity;
import com.oched.booksprj.repositories.BookContentRepository;
import com.oched.booksprj.requests.BookRequest;
import com.oched.booksprj.repositories.AuthorRepository;
import com.oched.booksprj.repositories.BookRepository;
import com.oched.booksprj.requests.DeleteBookRequest;
import com.oched.booksprj.responses.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookContentRepository contentRepository;

    public void addBook(BookRequest request) {
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
    }

    public void editBook(BookRequest request) {
        Optional<BookDescriptionEntity> optional = this.bookRepository.findById(request.getId());

        if(optional.isEmpty()) {
            throw new RuntimeException();
        }

        BookDescriptionEntity book = optional.get();

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
    }

    public BookResponse getById(long id) {
        Optional<BookDescriptionEntity> optional = this.bookRepository.findById(id);

        if(optional.isEmpty()) {
            throw new RuntimeException();
        }

        BookDescriptionEntity book = optional.get();

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getYear(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName()
        );
    }

    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    public List<BookResponse> getAll() {
        List<BookDescriptionEntity> list = bookRepository.findAll();

        return list.stream().map(book -> new BookResponse(
                    book.getId(),
                    book.getTitle(),
                    book.getYear(),
                    book.getAuthor().getFirstName(),
                    book.getAuthor().getLastName()
                )).collect(Collectors.toList());
    }
}
