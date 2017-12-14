-- phpMyAdmin SQL Dump
-- version 4.7.0-beta1
-- https://www.phpmyadmin.net/
--
-- Počítač: localhost
-- Vytvořeno: Pát 15. pro 2017, 00:50
-- Verze serveru: 5.6.34
-- Verze PHP: 7.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `kavarny`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `caferating`
--

CREATE TABLE `caferating` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `cafeId` int(11) DEFAULT NULL,
  `ratingInt` tinyint(4) DEFAULT NULL,
  `ratingText` text,
  `added` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `caferating`
--

INSERT INTO `caferating` (`id`, `userId`, `cafeId`, `ratingInt`, `ratingText`, `added`) VALUES
(2, 1, 12, 2, 'Nic moc teda.', '2017-12-10 21:47:14'),
(3, 8, 11, 4, 'Docela to šlo.', '2017-12-10 21:53:13'),
(4, 1, 11, 4, 'Celkem v pohodě.', '2017-12-10 21:56:54'),
(5, 1, 14, 3, 'No, už jsem viděl i lepší teda.', '2017-12-10 22:27:49'),
(6, 1, 11, 2, 'Nic moc.', '2017-12-10 22:30:47'),
(7, 1, 12, 3, 'Dalo se to.', '2017-12-10 22:35:14'),
(8, 1, 20, 1, 'Vzkaz zde', '2017-12-10 22:36:48'),
(9, 1, 12, 1, 'Už nikdy nepřijdu. Hrůza.', '2017-12-10 22:38:41'),
(10, 1, 12, 1, '', '2017-12-10 22:44:33'),
(11, 1, 12, 1, 'Celkem špatný, no..', '2017-12-10 22:44:42'),
(12, 1, 11, 3, '', '2017-12-10 22:51:54'),
(13, 8, 11, 5, 'Moc se mi tady líbilo!', '2017-12-12 09:45:19'),
(14, 8, 14, 1, 'Hipster fancy shit...', '2017-12-12 09:45:35'),
(15, 8, 20, 3, 'Samej ajťák tady, nic pro normální lidi.', '2017-12-12 09:45:59'),
(16, 8, 20, 1, 'Tohle je extrémně dlouhý vzkaz, který nejspíše úplně zboří tento panel.', '2017-12-12 09:46:28'),
(17, 1, 22, 1, 'Nic moc no.', '2017-12-12 09:54:53'),
(18, 1, 12, 3, 'Dalo se to.', '2017-12-14 14:37:56'),
(19, 1, 14, 1, 'Vzkaz zde.', '2017-12-14 15:50:02');

-- --------------------------------------------------------

--
-- Struktura tabulky `coffees`
--

CREATE TABLE `coffees` (
  `id` int(11) NOT NULL,
  `cafeId` int(11) DEFAULT NULL,
  `name` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabulky `kavarny`
--

CREATE TABLE `kavarny` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT '',
  `adress` varchar(255) DEFAULT '',
  `description` text,
  `userId` int(11) DEFAULT NULL,
  `added` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `kavarny`
--

INSERT INTO `kavarny` (`id`, `name`, `adress`, `description`, `userId`, `added`) VALUES
(11, 'Studentská kavárna', 'Chemická 955, Praha 4', 'Zde si opravdu pochutnáte.', 10, '2017-12-10 15:24:41'),
(12, 'U rozzuřeného losa, který prošel celou Saharu', 'Tvrdíčkova 189, Praha 8', 'Zde si opravdu pochutnáte.', 10, '2017-12-10 15:24:41'),
(14, 'CafeThin', 'Křižíkovo náměsí 8, Praha 2', 'Není zde nic, o co byste mohli přijít.', 10, '2017-12-10 15:24:41'),
(20, 'Paralelní polis', 'K Nádraží 145/6, Praha-Holešovice', 'Ano, zde můžete platit Bitcoinem!', 10, '2017-12-10 15:24:41'),
(22, '; DROP table zkouska;', 'Ahoj', 'Spadlo to?\n', 1, '2017-12-12 10:54:40');

-- --------------------------------------------------------

--
-- Struktura tabulky `special_offers`
--

CREATE TABLE `special_offers` (
  `id` int(11) NOT NULL,
  `cafeId` int(11) DEFAULT NULL,
  `name` text,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `until` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `special_offers`
--

INSERT INTO `special_offers` (`id`, `cafeId`, `name`, `start`, `until`) VALUES
(2, 11, 'Cafe zdarma.', '2017-12-14 15:19:14', '2017-12-14 15:19:10'),
(3, 12, 'Kafe 2+1 zdarma!', '2017-12-14 23:00:00', '2017-12-15 23:00:00'),
(4, 12, 'něco', '2017-12-13 23:00:00', '2017-12-13 23:00:00'),
(5, 12, 'další', '2017-12-13 23:00:00', '2017-12-13 23:00:00'),
(6, 12, 'tady je', '2017-12-13 23:00:00', '2017-12-13 23:00:00'),
(7, 11, 'tady akce', '2017-12-13 23:00:00', '2017-12-13 23:00:00'),
(8, 14, 'Nová nabídka.', '2017-12-14 23:00:00', '2017-12-15 23:00:00'),
(9, 11, 'Nová akce', '2017-12-13 23:00:00', '2017-12-22 23:00:00');

-- --------------------------------------------------------

--
-- Struktura tabulky `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `wantsToBeAdmin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `salt`, `admin`, `wantsToBeAdmin`) VALUES
(1, 'admin@kafe.cz', NULL, NULL, 1, 0),
(6, 'email@nikde.com', 'a5e245aebcc1bc76c84b99a67196c669a6df1eb1c12593669ded077112418f02d182d9cd74702160a3a43cbf7de7d5cff2eddf7830d13cda88bf6eb71d335a3f', 'M42m5rblUNw9Jgcxt2waCy10xJoKEWA2vlyLPbiTh9A=', 0, 0),
(7, 'email@email.com', '9c07580030d89ed7bf501a3bca32f02e3ef0325398252ac6cc397ec1c886be4261fd4ab35b07e0d8cde7169d96d7eab6155f9ee3dac73509b0d4883b2dc03850', 't90cL0HsSf5rKL7F7pYfzxrEOiwT6BcShfmJu8jqcPM=', 0, 0),
(8, 'email@email.cz', '9f60505bfc450c3a291167f3d5620ec5bd0781dad5d6a4c8c34e459e39cfc06930da13669f3d04f62b87ab3f366d0f4ca7fd662b40b13ac889954da5c3d15603', '4XJwzsFNFYmNdwNPtb+pG94r2sXKmB92vvqlL4vFBHo=', 0, 1),
(10, 'pepa@novak.com', '253d0831c98f8e61528a80001a3334fa88afc27e6c5e406678f7495a3bf72ae61d310dd112db1eb13fbfa5347cb8d59b2e0ef85b69056006d4b12f9f3e28dceb', 'fW+RxhQlUie4+zlANeaz6MuNuytyilXUEce9M2Mecz8=', NULL, NULL),
(11, 'pepa@novak.comm', '1bd99a64fdb5df1b22a983b2e48def6b37c20c95332495290117c4b047468fab64124a15bd234cced5df762b6199d3e47abefff9b1330575625fe722592247de', 'bCkrJHKQOvt53poLPMLoqLBwNvNafYzizB6+k+ugnOg=', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabulky `zkouska`
--

CREATE TABLE `zkouska` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `caferating`
--
ALTER TABLE `caferating`
  ADD PRIMARY KEY (`id`),
  ADD KEY `caferating_kavarny_id_fk` (`cafeId`),
  ADD KEY `caferating_users_id_fk` (`userId`);

--
-- Klíče pro tabulku `coffees`
--
ALTER TABLE `coffees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `coffees_kavarny_id_fk` (`cafeId`);

--
-- Klíče pro tabulku `kavarny`
--
ALTER TABLE `kavarny`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kavarny_users_id_fk` (`userId`);

--
-- Klíče pro tabulku `special_offers`
--
ALTER TABLE `special_offers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `special_offers_kavarny_id_fk` (`cafeId`);

--
-- Klíče pro tabulku `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_uindex` (`email`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `caferating`
--
ALTER TABLE `caferating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pro tabulku `coffees`
--
ALTER TABLE `coffees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pro tabulku `kavarny`
--
ALTER TABLE `kavarny`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT pro tabulku `special_offers`
--
ALTER TABLE `special_offers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pro tabulku `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `caferating`
--
ALTER TABLE `caferating`
  ADD CONSTRAINT `caferating_kavarny_id_fk` FOREIGN KEY (`cafeId`) REFERENCES `kavarny` (`id`),
  ADD CONSTRAINT `caferating_users_id_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Omezení pro tabulku `coffees`
--
ALTER TABLE `coffees`
  ADD CONSTRAINT `coffees_kavarny_id_fk` FOREIGN KEY (`cafeId`) REFERENCES `kavarny` (`id`);

--
-- Omezení pro tabulku `kavarny`
--
ALTER TABLE `kavarny`
  ADD CONSTRAINT `kavarny_users_id_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Omezení pro tabulku `special_offers`
--
ALTER TABLE `special_offers`
  ADD CONSTRAINT `special_offers_kavarny_id_fk` FOREIGN KEY (`cafeId`) REFERENCES `kavarny` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
