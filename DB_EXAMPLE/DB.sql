-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 26 Maj 2017, 11:39
-- Wersja serwera: 10.1.19-MariaDB
-- Wersja PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `hurtowniaspozywcza`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dostawca_importer`
--

CREATE TABLE `dostawca_importer` (
  `dostawca_importer_id` int(11) NOT NULL,
  `nazwa` varchar(255) NOT NULL,
  `typ` varchar(255) NOT NULL,
  `wlasciciel` varchar(255) NOT NULL,
  `adres` varchar(255) NOT NULL,
  `telefon` int(11) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `dostawca_importer`
--

INSERT INTO `dostawca_importer` (`dostawca_importer_id`, `nazwa`, `typ`, `wlasciciel`, `adres`, `telefon`, `email`) VALUES
(1, 'RK', 'Owoce', 'Ryszard Kalisz', 'Warszawa, ul. Wiejska 4', 777888999, 'rysio@kalisz.pl'),
(2, 'Bbor', 'Warzywa', 'Antoni Hej', 'Wroclaw ul.Ciepla 4', 876876876, 'wroc98@wp.pl'),
(3, 'WW', 'warzywa', 'Wieslaw Wolga', 'Wilczyn 201', 765765765, 'wilczyn@o2.pl');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `klient_id` int(11) NOT NULL,
  `nazwa` varchar(255) NOT NULL,
  `wlasciciel` varchar(255) NOT NULL,
  `adres` varchar(255) NOT NULL,
  `telefon` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `rabat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`klient_id`, `nazwa`, `wlasciciel`, `adres`, `telefon`, `email`, `rabat`) VALUES
(1, 'port', 'Pawel Roool', 'Rzeszow ul.Sloneczna 2', 666777666, 'pawel@okon.pl', 20),
(2, 'Botafogo', 'Wieslaw Paleta', 'Ciechocinek 25', 678678678, 'wiesiek@buiaczek.pl', 10),
(3, 'Owocowy Raj', 'Pawel Los', 'Losienice 204', 888999777, 'losienice@wp.pl', 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkt`
--

CREATE TABLE `produkt` (
  `produkt_id` int(11) NOT NULL,
  `nazwa` varchar(255) NOT NULL,
  `cena_jednostkowa` int(11) NOT NULL,
  `data_waznosci` date NOT NULL,
  `ilosc` int(11) NOT NULL,
  `polozenie` varchar(30) NOT NULL,
  `dostawca_importer_id` int(11) DEFAULT NULL,
  `kraj_pochodzenia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `produkt`
--

INSERT INTO `produkt` (`produkt_id`, `nazwa`, `cena_jednostkowa`, `data_waznosci`, `ilosc`, `polozenie`, `dostawca_importer_id`, `kraj_pochodzenia`) VALUES
(1, 'Pomidory', 4, '2017-03-31', 4000, '1a', 1, 'Mongolia'),
(2, 'Morele', 7, '2017-04-19', 8000, '4b', 1, 'Wlochy'),
(3, 'Marchewki', 1, '2017-05-21', 10000, '2a', 3, 'Watykan'),
(4, 'Zielony groszek', 3, '2017-05-12', 25000, '1', 3, 'Albania'),
(5, 'Szparagi', 8, '2017-08-10', 8000, '2', 1, 'Andora');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkt_temp`
--

CREATE TABLE `produkt_temp` (
  `produkt_temp_id` int(11) NOT NULL,
  `nazwa` varchar(255) NOT NULL,
  `cena_jednostkowa` int(11) NOT NULL,
  `data_waznosci` date NOT NULL,
  `ilosc` int(11) NOT NULL,
  `polozenie` varchar(30) NOT NULL,
  `dostawca_importer_id` int(11) NOT NULL,
  `kraj_pochodzenia` varchar(255) NOT NULL,
  `przyjecie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `produkt_temp`
--

INSERT INTO `produkt_temp` (`produkt_temp_id`, `nazwa`, `cena_jednostkowa`, `data_waznosci`, `ilosc`, `polozenie`, `dostawca_importer_id`, `kraj_pochodzenia`, `przyjecie_id`) VALUES
(1, 'ogorek szklarniowy', 5, '2017-03-30', 7000, '', 0, 'Polska', NULL),
(2, 'Cebula', 1, '2017-05-16', 9000, '', 0, 'Polska', NULL),
(3, 'Dynia', 2, '2017-05-10', 50, '4h', 0, 'Chile', NULL),
(4, '12', 12, '2017-05-22', 12, '12', 0, '123', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkt_zamowienie`
--

CREATE TABLE `produkt_zamowienie` (
  `produkt_zamowienie_id` int(11) NOT NULL,
  `produkt_id` int(11) DEFAULT NULL,
  `zamowienie_id` int(11) DEFAULT NULL,
  `ilosc` int(11) NOT NULL,
  `cena_jednostkowa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `produkt_zamowienie`
--

INSERT INTO `produkt_zamowienie` (`produkt_zamowienie_id`, `produkt_id`, `zamowienie_id`, `ilosc`, `cena_jednostkowa`) VALUES
(40, 2, 49, 20, 11);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przyjecie`
--

CREATE TABLE `przyjecie` (
  `przyjecie_id` int(11) NOT NULL,
  `uzytkownik_id` int(11) DEFAULT NULL,
  `data_przyjecia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `przyjecie`
--

INSERT INTO `przyjecie` (`przyjecie_id`, `uzytkownik_id`, `data_przyjecia`) VALUES
(1, 1, '2017-04-11');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `raport`
--

CREATE TABLE `raport` (
  `raport_id` int(11) NOT NULL,
  `typ` varchar(255) NOT NULL,
  `data_wygenerowania` date NOT NULL,
  `uzytkownik_id` int(11) DEFAULT NULL,
  `sciezka` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `raport`
--

INSERT INTO `raport` (`raport_id`, `typ`, `data_wygenerowania`, `uzytkownik_id`, `sciezka`) VALUES
(9, 'Daty waznosci', '2017-05-25', 99, 'reports\\RaportWaznosci2017-05-25.pdf'),
(10, 'Daty waznosci', '2017-05-25', 99, 'reports\\RaportWaznosci2017-05-25.pdf'),
(11, 'Daty waznosci', '2017-05-25', 99, 'reports\\RaportWaznosci2017-05-25.pdf'),
(12, 'Raport sprzedazy', '2017-05-25', 99, 'reports\\RaportSprzedazy2017-05-25.pdf'),
(13, 'Raport sprzedazy', '2017-05-25', 99, 'reports\\RaportSprzedazy2017-05-25.pdf'),
(14, 'Raport Przyjecia', '2017-05-25', 99, 'reports\\RaportPrzyjecia2017-05-25.pdf');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `uzytkownik_id` int(11) NOT NULL,
  `imie` varchar(255) NOT NULL,
  `nazwisko` varchar(255) NOT NULL,
  `uprawnienia` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `haslo` varchar(255) NOT NULL,
  `uwagi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`uzytkownik_id`, `imie`, `nazwisko`, `uprawnienia`, `login`, `haslo`, `uwagi`) VALUES
(1, 'Magdalena', 'Mag', 'Kierownik', 'user9', 'heheheh23', ''),
(99, 'admin', 'admin', 'admin', 'admin', 'admin', ''),
(100, 'Gregory', 'Greg', 'Pracownik', 'pracownik1', 'pracownik', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie`
--

CREATE TABLE `zamowienie` (
  `zamowienie_id` int(11) NOT NULL,
  `klient_id` int(11) DEFAULT NULL,
  `uzytkownik_id` int(11) DEFAULT NULL,
  `uwagi` varchar(255) NOT NULL,
  `wartosc` int(11) NOT NULL,
  `stan` varchar(155) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `zamowienie`
--

INSERT INTO `zamowienie` (`zamowienie_id`, `klient_id`, `uzytkownik_id`, `uwagi`, `wartosc`, `stan`) VALUES
(1, 1, 1, ':)', 0, ''),
(2, 2, 1, 'brak\r\n', 0, ''),
(49, 2, 1, 'Brak towaru nr.1', 23, 'Uwagi'),
(50, 1, 1, '', 2, 'nowe'),
(51, 1, 1, '', 432, 'Wykonane');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `dostawca_importer`
--
ALTER TABLE `dostawca_importer`
  ADD PRIMARY KEY (`dostawca_importer_id`),
  ADD KEY `dostawca_importer_id` (`dostawca_importer_id`);

--
-- Indexes for table `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`klient_id`);

--
-- Indexes for table `produkt`
--
ALTER TABLE `produkt`
  ADD PRIMARY KEY (`produkt_id`),
  ADD KEY `dostawca_importer_id` (`dostawca_importer_id`);

--
-- Indexes for table `produkt_temp`
--
ALTER TABLE `produkt_temp`
  ADD PRIMARY KEY (`produkt_temp_id`),
  ADD KEY `przyjecie_id` (`przyjecie_id`),
  ADD KEY `dostawca_importer_id` (`dostawca_importer_id`);

--
-- Indexes for table `produkt_zamowienie`
--
ALTER TABLE `produkt_zamowienie`
  ADD PRIMARY KEY (`produkt_zamowienie_id`),
  ADD KEY `produkt_id` (`produkt_id`,`zamowienie_id`),
  ADD KEY `produkt_zamowienie_ibfk_2` (`zamowienie_id`);

--
-- Indexes for table `przyjecie`
--
ALTER TABLE `przyjecie`
  ADD PRIMARY KEY (`przyjecie_id`),
  ADD KEY `uzytkownik_id` (`uzytkownik_id`);

--
-- Indexes for table `raport`
--
ALTER TABLE `raport`
  ADD PRIMARY KEY (`raport_id`),
  ADD KEY `uzytkownik_id` (`uzytkownik_id`);

--
-- Indexes for table `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`uzytkownik_id`);

--
-- Indexes for table `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD PRIMARY KEY (`zamowienie_id`),
  ADD KEY `klient_id` (`klient_id`,`uzytkownik_id`),
  ADD KEY `uzytkownik_id` (`uzytkownik_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `dostawca_importer`
--
ALTER TABLE `dostawca_importer`
  MODIFY `dostawca_importer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `klient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `produkt`
--
ALTER TABLE `produkt`
  MODIFY `produkt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT dla tabeli `produkt_temp`
--
ALTER TABLE `produkt_temp`
  MODIFY `produkt_temp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT dla tabeli `produkt_zamowienie`
--
ALTER TABLE `produkt_zamowienie`
  MODIFY `produkt_zamowienie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT dla tabeli `przyjecie`
--
ALTER TABLE `przyjecie`
  MODIFY `przyjecie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT dla tabeli `raport`
--
ALTER TABLE `raport`
  MODIFY `raport_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `uzytkownik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;
--
-- AUTO_INCREMENT dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  MODIFY `zamowienie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `produkt`
--
ALTER TABLE `produkt`
  ADD CONSTRAINT `produkt_ibfk_1` FOREIGN KEY (`dostawca_importer_id`) REFERENCES `dostawca_importer` (`dostawca_importer_id`) ON DELETE CASCADE ON UPDATE SET NULL;

--
-- Ograniczenia dla tabeli `produkt_temp`
--
ALTER TABLE `produkt_temp`
  ADD CONSTRAINT `produkt_temp_ibfk_1` FOREIGN KEY (`przyjecie_id`) REFERENCES `przyjecie` (`przyjecie_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `produkt_zamowienie`
--
ALTER TABLE `produkt_zamowienie`
  ADD CONSTRAINT `produkt_zamowienie_ibfk_1` FOREIGN KEY (`produkt_id`) REFERENCES `produkt` (`produkt_id`) ON DELETE CASCADE ON UPDATE SET NULL,
  ADD CONSTRAINT `produkt_zamowienie_ibfk_2` FOREIGN KEY (`zamowienie_id`) REFERENCES `zamowienie` (`zamowienie_id`) ON DELETE CASCADE ON UPDATE SET NULL;

--
-- Ograniczenia dla tabeli `przyjecie`
--
ALTER TABLE `przyjecie`
  ADD CONSTRAINT `przyjecie_ibfk_1` FOREIGN KEY (`uzytkownik_id`) REFERENCES `uzytkownik` (`uzytkownik_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `raport`
--
ALTER TABLE `raport`
  ADD CONSTRAINT `raport_ibfk_1` FOREIGN KEY (`uzytkownik_id`) REFERENCES `uzytkownik` (`uzytkownik_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD CONSTRAINT `zamowienie_ibfk_1` FOREIGN KEY (`klient_id`) REFERENCES `klient` (`klient_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `zamowienie_ibfk_2` FOREIGN KEY (`uzytkownik_id`) REFERENCES `uzytkownik` (`uzytkownik_id`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
