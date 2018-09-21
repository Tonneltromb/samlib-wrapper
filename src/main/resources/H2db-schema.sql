DROP TABLE IF EXISTS authors;
CREATE TABLE authors (
  author_ID   INT AUTO_INCREMENT,
  first_name  VARCHAR(64),
  last_name   VARCHAR(64),
  father_name VARCHAR(64),
  short_name  VARCHAR(64),
  pseudonym   VARCHAR(64),
  samlib_ID   VARCHAR(32),
  PRIMARY KEY (author_ID)
);

DROP TABLE IF EXISTS author_info;
CREATE TABLE author_info (
  info_ID        INT AUTO_INCREMENT,
  author_ID      INT NOT NULL,
  about_author   VARCHAR(2000),
  day_of_birth   INT,
  month_of_birth INT,
  year_of_birth  INT,
  email          VARCHAR(64),
  web_site       VARCHAR(64),
  PRIMARY KEY (info_ID),
  FOREIGN KEY (author_ID) REFERENCES authors (author_ID)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books (
  book_ID    INT AUTO_INCREMENT,
  author_ID  INT AUTO_INCREMENT,
  title      VARCHAR(256) NOT NULL,
  samlib_ref VARCHAR(256) NOT NULL,
  size       INT,
  PRIMARY KEY (book_ID),
  FOREIGN KEY (author_ID) REFERENCES authors (author_ID)
);

DROP TABLE IF EXISTS chapters;
CREATE TABLE chapters (
  chapter_ID  INT AUTO_INCREMENT,
  book_ID     INT NOT NULL,
  part_number INT,
  title       VARCHAR(64),
  content     CLOB,
  PRIMARY KEY (chapter_ID),
  FOREIGN KEY (book_ID) REFERENCES books (book_ID)
);

-- todo table
-- DROP TABLE IF EXISTS chapters_content;
-- CREATE TABLE chapters_content (
--   chapter_ID INT NOT NULL ,
--   getBookContent CLOB
--   PRIMARY KEY (chapter_ID),
--
-- );


DROP TABLE IF EXISTS books_authors;
CREATE TABLE books_authors (
  pair_ID   INT AUTO_INCREMENT,
  book_ID   INT NOT NULL,
  author_ID INT NOT NULL,
  PRIMARY KEY (pair_ID),
  FOREIGN KEY (book_ID) REFERENCES books (book_ID),
  FOREIGN KEY (author_ID) REFERENCES authors (author_ID)
);

DROP TABLE IF EXISTS top_hundred;
CREATE TABLE top_hundred (
  record_ID INT,
  author_ID INT,
  book_ID   INT,
  PRIMARY KEY (record_ID),
  FOREIGN KEY (author_ID) REFERENCES authors (author_ID),
  FOREIGN KEY (book_ID) REFERENCES books (book_ID)
);
