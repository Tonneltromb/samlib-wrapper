CREATE TABLE authors (
  author_ID   INT AUTO_INCREMENT,
  first_name  VARCHAR(60) NOT NULL,
  last_name   VARCHAR(60) NOT NULL,
  father_name VARCHAR(60) NOT NULL,
  pseudonym   VARCHAR(60) NOT NULL,
  birth_date  DATE,
  PRIMARY KEY (author_ID)
);

CREATE TABLE books (
  book_ID INT AUTO_INCREMENT,
  title VARCHAR(256) NOT NULL,
  PRIMARY KEY (book_ID)
);

CREATE TABLE books_authors (
  pair_ID INT AUTO_INCREMENT,
  book_ID INT NOT NULL ,
  author_ID INT NOT NULL ,
  PRIMARY KEY (pair_ID),
  FOREIGN KEY (book_ID) REFERENCES books(book_ID),
  FOREIGN KEY (author_ID) REFERENCES authors(author_ID)
);
