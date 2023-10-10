CREATE TABLE media (
  Tid INT PRIMARY KEY NOT NULL,
  releaseDate VARCHAR(30) NOT NULL,
  Title VARCHAR(20) NOT NULL
);
INSERT INTO media (Tid, releaseDate, Title)
VALUES (1, '2022-01-01', 'Movie1'),
  (2, '2021-06-15', 'Movie2'),
  (3, '2023-03-22', 'Movie3'),
  (4, '2022-11-12', 'Movie4');
CREATE TABLE users (
  Uid INT PRIMARY KEY NOT NULL,
  Age INT NOT NULL,
  phoneNumber CHAR(10) UNIQUE NOT NULL,
  Type INT NOT NULL
);
INSERT INTO users (Uid, Age, phoneNumber, Type)
VALUES (1, 25, '555-1234', 1),
  (2, 32, '555-5678', 2),
  (3, 18, '555-4321', 1),
  (4, 40, '555-8765', 2);
CREATE TABLE ratings (
  Tid INT NOT NULL,
  Uid INT NOT NULL,
  rating INT NOT NULL,
  FOREIGN KEY (Tid) REFERENCES media(Tid),
  FOREIGN KEY (Uid) REFERENCES users(Uid)
);
INSERT INTO ratings (Tid, Uid, rating)
VALUES (1, 1, 5),
  (1, 2, 4),
  (2, 3, 3),
  (3, 2, 2),
  (3, 3, 5),
  (4, 1, 4),
  (4, 4, 5);
  
CREATE TABLE languages (
  Tid INT NOT NULL,
  language VARCHAR(20) NOT NULL,
  FOREIGN KEY (Tid) REFERENCES media(Tid)
);

INSERT INTO languages (Tid, language)
VALUES (1, 'English'),
  (2, 'Spanish'),
  (3, 'German'),
  (4, 'Italian');
CREATE TABLE genre (
  Uid INT NOT NULL,
  preferredGenre1 VARCHAR(20) NOT NULL,
  preferredGenre2 VARCHAR(20) NOT NULL,
  preferredGenre3 VARCHAR(20) NOT NULL,
  FOREIGN KEY (Uid) REFERENCES users(Uid)
);
INSERT INTO genre (
    Uid,
    preferredGenre1,
    preferredGenre2,
    preferredGenre3
  )
VALUES (1, 'Action', 'Adventure', 'Drama'),
  (1, 'Adventure', 'Drama', 'Comedy'),
  (2, 'Drama', 'Comedy', 'Horror'),
  (3, 'Comedy', 'Action', 'Adventure'),
  (4, 'Horror', 'Drama', 'Comedy');