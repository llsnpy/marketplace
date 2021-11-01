INSERT INTO users (
id, login, password
) VALUES (
4, 'user1', 'user1'
);

INSERT INTO users (
id, login, password
) VALUES (
5, 'user2', 'user2'
);

INSERT INTO users (
id, login, password
) VALUES (
6, 'user3', 'user3'
);

INSERT INTO buyer (
id, name, surname, money, age
) VALUES (
2, 'Oleg', 'Bocharov', 345.00, 28
);

INSERT INTO buyer (
id, name, surname, money, age
) VALUES (
5, 'Aleks', 'Andreev', 91.83, 22
);

INSERT INTO buyer (
id, name, surname, money, age
) VALUES (
6, 'Ivan', 'Ivanov', 10.89, 14
);

INSERT INTO developer (
id, name, money, rating
) VALUES (
1, 'EA sports', 700.45, 3
);

INSERT INTO developer (
id, name, money, rating
) VALUES (
3, 'Riot games', 600.61, 4
);

INSERT INTO developer (
id, name, money, rating
) VALUES (
4, 'Valve', 650.56, 5
);

INSERT INTO game (
id, developer_id, name, genre, date, price, pre_sale, sale_price
) VALUES (
1, 1, 'FIFA21', 'sport', '2020-12-31', 13.76, false, 0.0
);

INSERT INTO game (
id, developer_id, name, genre, date, price, pre_sale, sale_price
) VALUES (
2, 3, 'Valorant', 'shooter', '2021-10-27', 40.50, true, 30.50
);

INSERT INTO game (
id, developer_id, name, genre, date, price, pre_sale, sale_price
) VALUES (
3, 4, 'CS:GO', 'shooter', '2015-05-23', 20.99, false, 0.0
);

INSERT INTO shop_list (
id, buyer_id, game_id, date, price
) VALUES (
1, 2, 1, '2021-06-12', 13.76
);

INSERT INTO shop_list (
id, buyer_id, game_id, date, price
) VALUES (
2, 5, 3, '2021-10-28', 20.99
);
