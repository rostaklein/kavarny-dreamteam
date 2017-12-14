CREATE TABLE kavarny.special_offers
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    cafeId int(11),
    name text,
    start timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    until timestamp DEFAULT '0000-00-00 00:00:00' NOT NULL,
    CONSTRAINT special_offers_kavarny_id_fk FOREIGN KEY (cafeId) REFERENCES kavarny (id)
);
CREATE INDEX special_offers_kavarny_id_fk ON kavarny.special_offers (cafeId);
INSERT INTO kavarny.special_offers (cafeId, name, start, until) VALUES (11, 'Cafe zdarma.', '2017-12-14 16:19:14', '2017-12-14 16:19:10');
INSERT INTO kavarny.special_offers (cafeId, name, start, until) VALUES (12, 'Kafe 2+1 zdarma!', '2017-12-15 00:00:00', '2017-12-16 00:00:00');
INSERT INTO kavarny.special_offers (cafeId, name, start, until) VALUES (12, 'něco', '2017-12-14 00:00:00', '2017-12-14 00:00:00');
INSERT INTO kavarny.special_offers (cafeId, name, start, until) VALUES (12, 'další', '2017-12-14 00:00:00', '2017-12-14 00:00:00');
INSERT INTO kavarny.special_offers (cafeId, name, start, until) VALUES (12, 'tady je', '2017-12-14 00:00:00', '2017-12-14 00:00:00');
INSERT INTO kavarny.special_offers (cafeId, name, start, until) VALUES (11, 'tady akce', '2017-12-14 00:00:00', '2017-12-14 00:00:00');
INSERT INTO kavarny.special_offers (cafeId, name, start, until) VALUES (14, 'Nová nabídka.', '2017-12-15 00:00:00', '2017-12-16 00:00:00');
INSERT INTO kavarny.special_offers (cafeId, name, start, until) VALUES (11, 'Nová akce', '2017-12-14 00:00:00', '2017-12-23 00:00:00');