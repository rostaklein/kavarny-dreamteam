CREATE TABLE kavarny.users
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email varchar(255),
    password varchar(255),
    salt varchar(255),
    admin tinyint(1),
    wantsToBeAdmin tinyint(1)
);
CREATE UNIQUE INDEX users_email_uindex ON kavarny.users (email);
INSERT INTO kavarny.users (email, password, salt, admin, wantsToBeAdmin) VALUES ('admin@kafe.cz', null, null, 1, 0);
INSERT INTO kavarny.users (email, password, salt, admin, wantsToBeAdmin) VALUES ('email@nikde.com', 'a5e245aebcc1bc76c84b99a67196c669a6df1eb1c12593669ded077112418f02d182d9cd74702160a3a43cbf7de7d5cff2eddf7830d13cda88bf6eb71d335a3f', 'M42m5rblUNw9Jgcxt2waCy10xJoKEWA2vlyLPbiTh9A=', 0, 0);
INSERT INTO kavarny.users (email, password, salt, admin, wantsToBeAdmin) VALUES ('email@email.com', '9c07580030d89ed7bf501a3bca32f02e3ef0325398252ac6cc397ec1c886be4261fd4ab35b07e0d8cde7169d96d7eab6155f9ee3dac73509b0d4883b2dc03850', 't90cL0HsSf5rKL7F7pYfzxrEOiwT6BcShfmJu8jqcPM=', 0, 0);
INSERT INTO kavarny.users (email, password, salt, admin, wantsToBeAdmin) VALUES ('email@email.cz', '9f60505bfc450c3a291167f3d5620ec5bd0781dad5d6a4c8c34e459e39cfc06930da13669f3d04f62b87ab3f366d0f4ca7fd662b40b13ac889954da5c3d15603', '4XJwzsFNFYmNdwNPtb+pG94r2sXKmB92vvqlL4vFBHo=', 0, 1);
INSERT INTO kavarny.users (email, password, salt, admin, wantsToBeAdmin) VALUES ('pepa@novak.com', '253d0831c98f8e61528a80001a3334fa88afc27e6c5e406678f7495a3bf72ae61d310dd112db1eb13fbfa5347cb8d59b2e0ef85b69056006d4b12f9f3e28dceb', 'fW+RxhQlUie4+zlANeaz6MuNuytyilXUEce9M2Mecz8=', null, null);
INSERT INTO kavarny.users (email, password, salt, admin, wantsToBeAdmin) VALUES ('pepa@novak.comm', '1bd99a64fdb5df1b22a983b2e48def6b37c20c95332495290117c4b047468fab64124a15bd234cced5df762b6199d3e47abefff9b1330575625fe722592247de', 'bCkrJHKQOvt53poLPMLoqLBwNvNafYzizB6+k+ugnOg=', null, null);