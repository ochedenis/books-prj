package com.oched.booksprj.test_data;

import com.oched.booksprj.entities.AuthorEntity;
import com.oched.booksprj.entities.BookContentEntity;
import com.oched.booksprj.entities.BookDescriptionEntity;

public class TestData {
    private static final AuthorEntity testAuthor;
    private static final BookDescriptionEntity testBook;
    private static final BookContentEntity testContent;

    static {
        testAuthor = new AuthorEntity(
                "First Name", "Second Name"
        );
        testContent = new BookContentEntity(
                "Some simple content"
        );
        testBook = new BookDescriptionEntity(
                "Some title",
                2002,
                testAuthor,
                testContent
        );
    }

    public static AuthorEntity getTestAuthor() {
        return testAuthor;
    }

    public static BookDescriptionEntity getTestBook() {
        return testBook;
    }

    public static BookContentEntity getTestContent() {
        return testContent;
    }
}
