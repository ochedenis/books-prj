CREATE TABLE authors (
     id bigint NOT NULL AUTO_INCREMENT,
     first_name varchar(255) NOT NULL,
     last_name varchar(255) NOT NULL,
     PRIMARY KEY (id),
     KEY (first_name, last_name)
);

