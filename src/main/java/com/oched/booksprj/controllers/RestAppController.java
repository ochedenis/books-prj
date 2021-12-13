package com.oched.booksprj.controllers;

import com.oched.booksprj.requests.ActionRequest;
import com.oched.booksprj.requests.EditBookRequest;
import com.oched.booksprj.requests.NewBookRequest;
import com.oched.booksprj.responses.BookInfoResponse;
import com.oched.booksprj.services.BookService;
import com.oched.booksprj.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest")
public class RestAppController {
    private final BookService bookService;
    private final UserService userService;

    @PostMapping("/add-new-book")
    public ResponseEntity<Void> addNewBook(
            final @Valid @RequestBody NewBookRequest request
    ) {
        this.bookService.addBook(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/books-list")
    public ResponseEntity<BookInfoResponse[]> getAllBooks() {
        return new ResponseEntity<>(this.bookService.getAll().toArray(new BookInfoResponse[0]), HttpStatus.OK);
    }

    @PutMapping("/update-book")
    public ResponseEntity<BookInfoResponse> updateBook(
            final @Valid @RequestBody EditBookRequest request
    ) {
        return new ResponseEntity<>(this.bookService.editBook(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<BookInfoResponse[]> getHello(final @PathVariable Long id) {
        this.bookService.deleteBook(new ActionRequest(id));

        return new ResponseEntity<>(this.bookService.getAll().toArray(new BookInfoResponse[0]), HttpStatus.OK);
    }

}
