DROP TABLE IF EXISTS note;

CREATE TABLE note (
    id IDENTITY PRIMARY KEY,
    title VARCHAR(100),
    content VARCHAR(1000),
    CONSTRAINT CK_title CHECK ( CHAR_LENGTH(title) > 0 ),
    CONSTRAINT CK_content CHECK ( CHAR_LENGTH(content) > 0 )
);