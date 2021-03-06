DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP TABLE restraunts IF EXISTS;
DROP TABLE votes IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255),
  email            VARCHAR(255)         NOT NULL,
  password         VARCHAR(255)         NOT NULL,
  last_vote_date   DATE
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255),
  date             DATE    NOT NULL,
  price            DOUBLE,
  restraunt_id     INTEGER      NOT NULL
);
CREATE UNIQUE INDEX dishes_unique_name_date_restraunt_id_idx ON dishes (name, date, restraunt_id);

CREATE TABLE restraunts
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  update_date DATE NOT NULL,
  name         VARCHAR(255)
);

CREATE TABLE votes
(
  id           INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  restraunt_id INTEGER,
  user_id      INTEGER,
  date         DATE NOT NULL
);
CREATE UNIQUE INDEX votes_unique_restrauntId_userId_date_idx ON votes (restraunt_id, user_id, date)