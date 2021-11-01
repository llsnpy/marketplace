CREATE TABLE users (
id SERIAL,
login VARCHAR (15) NOT NULL UNIQUE,
password VARCHAR (50) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE buyer (
id INTEGER NOT NULL PRIMARY KEY,
name VARCHAR(30) NOT NULL,
surname VARCHAR(30) NOT NULL,
money NUMERIC(16, 2) NOT NULL,
age SMALLINT NOT NULL,
FOREIGN KEY (id) REFERENCES users (id)
);

CREATE TABLE developer (
id INTEGER NOT NULL ,
name VARCHAR(20) NOT NULL UNIQUE,
money NUMERIC(16, 2) NOT NULL,
rating INTEGER NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES users (id)
);

CREATE TABLE game (
id SERIAL,
developer_id SERIAL,
name VARCHAR(25) NOT NULL,
genre VARCHAR(10) NOT NULL,
date TIMESTAMP NOT NULL,
price NUMERIC(16, 2),
pre_sale BOOLEAN NOT NULL,
sale_price NUMERIC(16, 2),
PRIMARY KEY (id),
FOREIGN KEY (developer_id) REFERENCES developer (id)
);

CREATE TABLE shop_list (
id SERIAL,
buyer_id SERIAL,
game_id SERIAL,
date DATE NOT NULL,
price NUMERIC(16, 2) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (buyer_id) REFERENCES buyer (id),
FOREIGN KEY (game_id) REFERENCES game (id)
);

CREATE TABLE buyers_with_sale (
id SERIAL,
buyer_id SERIAL,
game_id SERIAL,
PRIMARY KEY (id),
FOREIGN KEY (buyer_id) REFERENCES buyer(id),
FOREIGN KEY (game_id) REFERENCES game(id),
CONSTRAINT unique_pairs UNIQUE (buyer_id, game_id)
);


