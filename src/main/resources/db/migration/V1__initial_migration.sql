CREATE TABLE mountains
(
    id          INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255) NOT NULL,
    state_id    INT NOT NULL,
    pass_id     INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE passes
(
    id          INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE states
(
    id          INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);