DROP TABLE IF EXISTS Movie;
DROP TABLE IF EXISTS Session;
DROP TABLE IF EXISTS Cinema;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS FailedTransaction;
DROP TABLE IF EXISTS Card;
DROP TABLE IF EXISTS Staff;
DROP TABLE IF EXISTS GiftCard;

CREATE TABLE Movie (
    movie_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE,
    synopsis TEXT,
    classification TEXT,
    release_date TEXT,
    director TEXT,
    cast TEXT,
    CHECK (classification IN ('G', 'PG', 'M', 'MA15+', 'R18+'))
);

CREATE TABLE Session (
    session_id INTEGER PRIMARY KEY AUTOINCREMENT,
    movie_id INTEGER,
    cinema_name TEXT,
    screen_size TEXT,
    time TEXT,
    total_seats INTEGER,
    booked_seats INTEGER,
    FOREIGN KEY(movie_id) REFERENCES Movie(movie_id) ON DELETE CASCADE,
    FOREIGN KEY(cinema_name) REFERENCES Cinema(cinema_name),
    CHECK (screen_size IN ('Gold', 'Silver', 'Bronze'))
);

CREATE TABLE Cinema (
    cinema_name TEXT PRIMARY KEY
);

CREATE TABLE Book (
    transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,
    number_people INTEGER,
    username TEXT,
    session_id INTEGER,
    FOREIGN KEY(session_id) REFERENCES Session(session_id) ON DELETE CASCADE,
    FOREIGN KEY(username) REFERENCES Customer(username) ON DELETE CASCADE
);

CREATE TABLE Card (
    card_number TEXT PRIMARY KEY,
    name TEXT
);

CREATE TABLE Customer (
    username TEXT PRIMARY KEY,
    password TEXT,
    card_number TEXT,
    FOREIGN KEY(card_number) REFERENCES Card(card_number)
);

CREATE TABLE FailedTransaction (
    timestamp TEXT,
    username TEXT,
    reason TEXT,
    PRIMARY KEY (username, timestamp),
    FOREIGN KEY (username) REFERENCES Customer(username),
    CHECK (reason in ('timeout', 'user cancelled', 'card payment failed'))
);

CREATE TABLE Staff (
    staff_id TEXT PRIMARY KEY,
    role TEXT,
    password TEXT,
    CHECK (role IN ('Cinema Staff', 'Manager'))
);

CREATE TABLE GiftCard (
    card_number TEXT PRIMARY KEY,
    redeemed INTEGER
);