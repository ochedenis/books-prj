CREATE TABLE test_table
(
    id                       BIGSERIAL PRIMARY KEY,
    test_text                VARCHAR(250),
    updated_on               TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_on               TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
);
