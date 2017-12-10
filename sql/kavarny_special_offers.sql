CREATE TABLE kavarny.special_offers
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    cafeId int(11),
    name text,
    `from` datetime,
    until datetime,
    CONSTRAINT special_offers_kavarny_id_fk FOREIGN KEY (cafeId) REFERENCES kavarny (id)
);
CREATE INDEX special_offers_kavarny_id_fk ON kavarny.special_offers (cafeId);