CREATE TABLE books (
     id bigint NOT NULL AUTO_INCREMENT,
     title varchar(255) NOT NULL unique,
     year int NOT NULL,
     author_id bigint NOT NULL,
     content_id bigint NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (author_id) REFERENCES authors(id),
     FOREIGN KEY (content_id) REFERENCES books_content(id)
);
