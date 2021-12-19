package com.oched.booksprj.controllers;

import com.oched.booksprj.requests.ActionRequest;
import com.oched.booksprj.requests.EditBookRequest;
import com.oched.booksprj.requests.NewBookRequest;
import com.oched.booksprj.responses.BookInfoResponse;
import com.oched.booksprj.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book-rest")
public class RestBookController {
    private final BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<BookInfoResponse> getBook(final @PathVariable Long id) {
        return new ResponseEntity<>(this.bookService.getById(new ActionRequest(id)), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<BookInfoResponse[]> getAllBooks() {
        return new ResponseEntity<>(
                this.bookService.getAll().toArray(new BookInfoResponse[0]),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewBook(
            final @Valid @RequestBody NewBookRequest request
    ) {
        this.bookService.addBook(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<BookInfoResponse> updateBook(
            final @Valid @RequestBody EditBookRequest request
    ) {
        return new ResponseEntity<>(this.bookService.editBook(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookInfoResponse[]> deleteBook(final @PathVariable Long id) {
        this.bookService.deleteBook(new ActionRequest(id));

        return new ResponseEntity<>(this.bookService.getAll().toArray(new BookInfoResponse[0]), HttpStatus.OK);
    }
}

