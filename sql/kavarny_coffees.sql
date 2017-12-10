CREATE TABLE kavarny.coffees
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    cafeId int(11),
    name text,
    CONSTRAINT coffees_kavarny_id_fk FOREIGN KEY (cafeId) REFERENCES kavarny (id)
);
CREATE INDEX coffees_kavarny_id_fk ON kavarny.coffees (cafeId);