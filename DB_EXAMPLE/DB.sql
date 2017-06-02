-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 02 Cze 2017, 23:11
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
  `kraj_pochodzenia` varchar(255) NOT NULL,
  `data_przyjecia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `produkt`
--

INSERT INTO `produkt` (`produkt_id`, `nazwa`, `cena_jednostkowa`, `data_waznosci`, `ilosc`, `polozenie`, `dostawca_importer_id`, `kraj_pochodzenia`, `data_przyjecia`) VALUES
(1, 'Pomidory', 4, '2017-03-31', 3978, '1a', 1, 'Mongolia', '0000-00-00'),
(2, 'Morele', 7, '2017-04-19', 7968, '4b', 1, 'Wlochy', '0000-00-00'),
(3, 'Marchewki', 1, '2017-05-21', 9988, '2a', 3, 'Watykan', '0000-00-00'),
(4, 'Zielony groszek', 3, '2017-05-12', 25000, '1', 3, 'Albania', '0000-00-00'),
(5, 'Szparagi', 8, '2017-08-10', 7998, '2', 1, 'Andora', '0000-00-00'),
(6, '2', 2, '2017-06-06', 2, '2', 2, '2', '2017-06-02');

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
  `kraj_pochodzenia` varchar(255) NOT NULL,
  `przyjecie_id` int(11) DEFAULT NULL,
  `dostawca_importer_id` int(11) DEFAULT NULL,
  `data_przyjecia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `produkt_temp`
--

INSERT INTO `produkt_temp` (`produkt_temp_id`, `nazwa`, `cena_jednostkowa`, `data_waznosci`, `ilosc`, `polozenie`, `kraj_pochodzenia`, `przyjecie_id`, `dostawca_importer_id`, `data_przyjecia`) VALUES
(1, 'ogorek szklarniowy', 5, '2017-03-30', 7000, '', 'Polska', 1, 1, '0000-00-00'),
(2, 'Cebula', 1, '2017-05-16', 9000, '', 'Polska', 1, 2, '0000-00-00'),
(3, 'Dynia', 2, '2017-05-10', 50, '4h', 'Chile', 1, 3, '0000-00-00'),
(4, '12', 12, '2017-05-22', 12, '12', '123', 1, 2, '0000-00-00');

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
(40, 2, 49, 20, 11),
(49, 2, 55, 10, 12),
(50, 1, 55, 1, 12),
(51, 2, 56, 12, 7),
(52, 3, 56, 12, 1);

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
(24, 'Daty waznosci', '2017-06-01', 99, 'reports\\RaportWaznosci2017-06-01.pdf'),
(25, 'Raport Przyjecia', '2017-06-01', 99, 'reports\\RaportPrzyjecia2017-06-01.pdf'),
(26, 'Raport Przyjecia', '2017-06-01', 99, 'reports\\RaportPrzyjecia2017-06-01.pdf'),
(27, 'Raport Przyjecia', '2017-06-01', 99, 'reports\\RaportPrzyjecia2017-06-01.pdf'),
(28, 'Daty waznosci', '2017-06-01', 99, 'reports\\RaportWaznosci2017-06-01.pdf'),
(29, 'Raport Przyjecia', '2017-06-01', 99, 'reports\\RaportPrzyjecia2017-06-01.pdf'),
(30, 'Daty waznosci', '2017-06-01', 99, 'reports\\RaportWaznosci2017-06-01.pdf'),
(31, 'Daty waznosci', '2017-06-01', 99, 'reports\\RaportWaznosci2017-06-01.pdf'),
(32, 'Daty waznosci', '2017-06-01', 99, 'reports\\RaportWaznosci2017-06-01.pdf'),
(33, 'Raport Przyjecia', '2017-06-01', 99, 'reports\\RaportPrzyjecia2017-06-01.pdf'),
(34, 'Raport Przyjecia', '2017-06-01', 99, 'reports\\RaportPrzyjecia2017-06-01.pdf'),
(35, 'Daty waznosci', '2017-06-01', 99, 'reports\\RaportWaznosci2017-06-01.pdf'),
(36, 'Daty waznosci', '2017-06-01', 99, 'reports\\RaportWaznosci2017-06-01.pdf'),
(37, 'Raport Przyjecia', '2017-06-01', 99, 'reports\\RaportPrzyjecia2017-06-01.pdf'),
(38, 'Daty waznosci', '2017-06-01', 99, 'reports\\RaportWaznosci2017-06-01.pdf'),
(39, 'Raport Przyjecia', '2017-06-01', 99, 'reports\\RaportPrzyjecia2017-06-01.pdf'),
(40, 'Daty waznosci', '2017-06-02', 99, 'reports\\RaportWaznosci2017-06-02.pdf'),
(41, 'Raport Przyjecia', '2017-06-02', 99, 'reports\\RaportPrzyjecia2017-06-02.pdf'),
(42, 'Raport Przyjecia', '2017-06-02', 99, 'reports\\RaportPrzyjecia2017-06-02.pdf');

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
(99, 'admin', 'admin', 'admin', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', ''),
(100, 'Gregory', 'Greg', 'Pracownik', 'pracownik1', 'pracownik', ''),
(101, 'Krzysztof', 'Krawczyk', '1', 'krawczyk', 'ac4f352566d26649578fb9202213e9e6cf800c73', ''),
(103, 'Kiero', 'kier', 'Kierownik', 'kier', 'a96d48f7e005db9efd4ae09d398efaf27c013c57', ''),
(104, 'prac', 'prac', 'Pracownik', 'prac', '7804974ec946455cc5787d9bc8d80415018fcf83', '');

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
(51, 1, 1, '', 432, 'Wykonane'),
(52, 3, 1, '', 30, 'Nowe'),
(53, 3, 1, '', 30, 'Nowe'),
(54, 2, 99, '', 240, 'Nowe'),
(55, 2, 99, 'uwaga', 240, 'Nowe'),
(56, 2, 99, 'Tomek Chuj', 8, 'Uwagi');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `dostawca_importer`
--
ALTER TABLE `dostawca_importer`
  ADD PRIMARY KEY (`dostawca_importer_id`),
  ADD KEY `dostawca_importer_id` (`dostawca_importer_id`),
  ADD KEY `dostawca_importer_id_2` (`dostawca_importer_id`);

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
  MODIFY `dostawca_importer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `klient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `produkt`
--
ALTER TABLE `produkt`
  MODIFY `produkt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `produkt_temp`
--
ALTER TABLE `produkt_temp`
  MODIFY `produkt_temp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `produkt_zamowienie`
--
ALTER TABLE `produkt_zamowienie`
  MODIFY `produkt_zamowienie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT dla tabeli `przyjecie`
--
ALTER TABLE `przyjecie`
  MODIFY `przyjecie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT dla tabeli `raport`
--
ALTER TABLE `raport`
  MODIFY `raport_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `uzytkownik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;
--
-- AUTO_INCREMENT dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  MODIFY `zamowienie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `produkt`
--
ALTER TABLE `produkt`
  ADD CONSTRAINT `produkt_ibfk_1` FOREIGN KEY (`dostawca_importer_id`) REFERENCES `dostawca_importer` (`dostawca_importer_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `produkt_temp`
--
ALTER TABLE `produkt_temp`
  ADD CONSTRAINT `produkt_temp_ibfk_1` FOREIGN KEY (`przyjecie_id`) REFERENCES `przyjecie` (`przyjecie_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `produkt_temp_ibfk_2` FOREIGN KEY (`dostawca_importer_id`) REFERENCES `dostawca_importer` (`dostawca_importer_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `produkt_zamowienie`
--
ALTER TABLE `produkt_zamowienie`
  ADD CONSTRAINT `produkt_zamowienie_ibfk_1` FOREIGN KEY (`produkt_id`) REFERENCES `produkt` (`produkt_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `produkt_zamowienie_ibfk_2` FOREIGN KEY (`zamowienie_id`) REFERENCES `zamowienie` (`zamowienie_id`) ON DELETE SET NULL ON UPDATE SET NULL;

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
