CREATE TABLE kavarny.caferating
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId int(11),
    cafeId int(11),
    ratingInt tinyint(4),
    ratingText text,
    added timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT caferating_users_id_fk FOREIGN KEY (userId) REFERENCES users (id),
    CONSTRAINT caferating_kavarny_id_fk FOREIGN KEY (cafeId) REFERENCES kavarny (id)
);
CREATE INDEX caferating_kavarny_id_fk ON kavarny.caferating (cafeId);
CREATE INDEX caferating_users_id_fk ON kavarny.caferating (userId);
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 12, 2, 'Nic moc teda.', '2017-12-10 22:47:14');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (8, 11, 4, 'Docela to šlo.', '2017-12-10 22:53:13');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 11, 4, 'Celkem v pohodě.', '2017-12-10 22:56:54');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 14, 3, 'No, už jsem viděl i lepší teda.', '2017-12-10 23:27:49');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 11, 2, 'Nic moc.', '2017-12-10 23:30:47');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 12, 3, 'Dalo se to.', '2017-12-10 23:35:14');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 20, 1, 'Vzkaz zde', '2017-12-10 23:36:48');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 12, 1, 'Už nikdy nepřijdu. Hrůza.', '2017-12-10 23:38:41');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 12, 1, '', '2017-12-10 23:44:33');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 12, 1, 'Celkem špatný, no..', '2017-12-10 23:44:42');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 11, 3, '', '2017-12-10 23:51:54');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (8, 11, 5, 'Moc se mi tady líbilo!', '2017-12-12 10:45:19');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (8, 14, 1, 'Hipster fancy shit...', '2017-12-12 10:45:35');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (8, 20, 3, 'Samej ajťák tady, nic pro normální lidi.', '2017-12-12 10:45:59');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (8, 20, 1, 'Tohle je extrémně dlouhý vzkaz, který nejspíše úplně zboří tento panel.', '2017-12-12 10:46:28');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 22, 1, 'Nic moc no.', '2017-12-12 10:54:53');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 12, 3, 'Dalo se to.', '2017-12-14 15:37:56');
INSERT INTO kavarny.caferating (userId, cafeId, ratingInt, ratingText, added) VALUES (1, 14, 1, 'Vzkaz zde.', '2017-12-14 16:50:02');