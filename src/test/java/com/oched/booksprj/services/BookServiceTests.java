package com.oched.booksprj.services;

import com.oched.booksprj.entities.BookDescriptionEntity;
import com.oched.booksprj.exceptions.BadRequestException;
import com.oched.booksprj.repositories.AuthorRepository;
import com.oched.booksprj.repositories.BookContentRepository;
import com.oched.booksprj.repositories.BookRepository;
import com.oched.booksprj.requests.EditBookRequest;
import com.oched.booksprj.requests.NewBookRequest;
import com.oched.booksprj.responses.BookInfoResponse;
import com.oched.booksprj.test_data.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BookService.class)
@MockBean({
        BookContentRepository.class, Scheduler.class
})
class BookServiceTests {
    @Autowired
    private BookService bookService;

    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private BookRepository bookRepository;

    private BookDescriptionEntity testBook = TestData.getTestBook();

    @Test
    void addBookTest() {
        NewBookRequest request = new NewBookRequest(
                "Some title",
                1992,
                "Some first name",
                "Some second name",
                "12"
        );

        when(authorRepository.findByFirstNameAndLastName(
                request.getAuthorFirstName(),
                request.getAuthorLastName())
        ).thenReturn(Optional.empty());

        Exception exception = assertThrows(BadRequestException.class, () ->
                bookService.addBook(request)
        );
        assertEquals(
                "Content is too short!",
                exception.getMessage()
        );

        request.setContent("Some content");

        bookService.addBook(request);
    }

    @Test
    void editBookTest() {
        EditBookRequest request = new EditBookRequest(
                -1L,
                testBook.getTitle(),
                testBook.getYear(),
                testBook.getAuthor().getFirstName(),
                testBook.getAuthor().getLastName()
        );

        when(bookRepository.findById(
                request.getId()
        )).thenReturn(Optional.empty());

        BadRequestException exception = assertThrows(BadRequestException.class, () ->
                bookService.editBook(request)
        );
        assertEquals(
                "No book with such id!",
                exception.getMessage()
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());

        when(bookRepository.findById(
                request.getId()
        )).thenReturn(Optional.of(new BookDescriptionEntity()));
        when(authorRepository.findByFirstNameAndLastName(
                request.getAuthorFirstName(),
                request.getAuthorLastName()
        )).thenReturn(Optional.empty());
        // any(String.class)


        BookInfoResponse response = bookService.editBook(request);

        assertEquals(testBook.getTitle(), response.getTitle());
        assertEquals(testBook.getYear(), response.getYear());
    }
}