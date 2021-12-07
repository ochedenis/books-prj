CREATE TABLE authors (
     id          	BIGSERIAL PRIMARY KEY,
     first_name  	VARCHAR(250) NOT NULL,
     last_name   	VARCHAR(250) NOT NULL,
     UNIQUE (first_name, last_name)
);

CREATE TABLE books_content (
   id          		BIGSERIAL PRIMARY KEY,
   content 			TEXT NOT NULL
);

CREATE TABLE books (
     id         	BIGSERIAL PRIMARY KEY,
     title 			VARCHAR(250) NOT NULL,
     year 			INTEGER NOT NULL,
     author_id  	BIGSERIAL NOT NULL,
     content_id 	BIGSERIAL NOT NULL,
     FOREIGN KEY (author_id) REFERENCES authors(id),
     FOREIGN KEY (content_id) REFERENCES books_content(id)
);

CREATE TABLE users (
    id          	BIGSERIAL PRIMARY KEY,
    login 			VARCHAR(250) UNIQUE NOT NULL,
    email 			VARCHAR(250) UNIQUE NOT NULL,
    password 		VARCHAR(250) NOT NULL,
    registered_on	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_roles (
    user_id 		BIGSERIAL UNIQUE NOT NULL,
    roles 			VARCHAR(250),
    FOREIGN KEY (user_id) REFERENCES users(id)
);