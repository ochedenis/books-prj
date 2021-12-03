package com.oched.booksprj.services;

import com.oched.booksprj.entities.Author;
import com.oched.booksprj.entities.BookDescription;
import com.oched.booksprj.requests.AddBookRequest;
import com.oched.booksprj.repositories.AuthorRepository;
import com.oched.booksprj.repositories.BookRepository;
import com.oched.booksprj.responses.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public void addBook(AddBookRequest request) {
        List<BookDescription> bookList = bookRepository.getBooks();
        List<Author> authors = authorRepository.getAuthors();
        Author author = null;

        for(Author listAuthor: authors) {
            if(
                    listAuthor.getFirstName().equals(request.getAuthorFirstName()) &&
                            listAuthor.getLastName().equals(request.getAuthorLastName())
            ) {
                author = listAuthor;
            }
        }

        if(author == null) {
            author = new Author(
                    authors.size(),
                    request.getAuthorFirstName(),
                    request.getAuthorLastName(),
                    null
            );
        }

        bookList.add(
                new BookDescription(
                        bookList.size(),
                        request.getTitle(),
                        request.getYear(),
                        author,
                        null
                )
        );
    }

    public List<BookResponse> getAll() {
        List<BookDescription> bookList = bookRepository.getBooks();

        return bookList.stream().map(
                entry -> new BookResponse(
                        entry.getTitle(),
                        entry.getYear(),
                        entry.getAuthor().getFirstName(),
                        entry.getAuthor().getLastName()
                )).collect(Collectors.toList());
    }
}
