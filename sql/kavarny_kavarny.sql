CREATE TABLE kavarny.kavarny
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT '',
    adress varchar(255) DEFAULT '',
    description text,
    userId int(11),
    added datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT kavarny_users_id_fk FOREIGN KEY (userId) REFERENCES users (id)
);
CREATE INDEX kavarny_users_id_fk ON kavarny.kavarny (userId);
INSERT INTO kavarny.kavarny (name, adress, description, userId, added) VALUES ('Studentská kavárna', 'Chemická 955, Praha 4', 'Zde si opravdu pochutnáte.', 10, '2017-12-10 15:24:41');
INSERT INTO kavarny.kavarny (name, adress, description, userId, added) VALUES ('U rozzuřeného losa, který prošel celou Saharu', 'Tvrdíčkova 189, Praha 8', 'Zde si opravdu pochutnáte.', 10, '2017-12-10 15:24:41');
INSERT INTO kavarny.kavarny (name, adress, description, userId, added) VALUES ('CafeThin', 'Křižíkovo náměsí 8, Praha 2', 'Není zde nic, o co byste mohli přijít.', 10, '2017-12-10 15:24:41');
INSERT INTO kavarny.kavarny (name, adress, description, userId, added) VALUES ('Paralelní polis', 'K Nádraží 145/6, Praha-Holešovice', 'Ano, zde můžete platit Bitcoinem!', 10, '2017-12-10 15:24:41');
INSERT INTO kavarny.kavarny (name, adress, description, userId, added) VALUES ('; DROP table zkouska;', 'Ahoj', 'Spadlo to?
', 1, '2017-12-12 10:54:40');