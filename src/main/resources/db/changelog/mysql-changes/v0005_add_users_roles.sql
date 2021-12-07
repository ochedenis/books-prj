CREATE TABLE user_roles (
    user_id bigint NOT NULL,
    roles varchar(255) DEFAULT NULL,
    KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);