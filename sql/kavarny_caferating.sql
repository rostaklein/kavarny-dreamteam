CREATE TABLE kavarny.caferating
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId int(11),
    cafeId int(11),
    ratingInt tinyint(4),
    ratingText text,
    CONSTRAINT caferating_users_id_fk FOREIGN KEY (userId) REFERENCES users (id),
    CONSTRAINT caferating_kavarny_id_fk FOREIGN KEY (cafeId) REFERENCES kavarny (id)
);
CREATE INDEX caferating_kavarny_id_fk ON kavarny.caferating (cafeId);
CREATE INDEX caferating_users_id_fk ON kavarny.caferating (userId);