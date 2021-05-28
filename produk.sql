-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2021 at 07:06 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.2.32
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
--
-- Database: `resto`
--
-- --------------------------------------------------------
--
-- Table structure for table `product`
--
CREATE TABLE `produk` (
 `KodeProduk` char(5) NOT NULL,
 `NamaProduk` varchar(200) NOT NULL,
 `JenisProduk` varchar(15) NOT NULL,
 `TipeProduk` varchar(20) NOT NULL,
 `Harga` int(11) NOT NULL,
 `Jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `product`
--
INSERT INTO `produk` (`KodeProduk`, `NamaProduk`, `JenisProduk`, `TipeProduk`, `Harga`, 
`Jumlah`) VALUES
('MK001', 'Nasi Goreng Tradisional', 'Makanan', 'Indonesia', 25000, 10),
('MK002', 'Sapo Tahu Seafood Oriental', 'Makanan', 'China', 50000, 15),
('MK003', 'Wagyu Steak', 'Makanan', 'Western', 100000, 8),
('MN001', 'Cheesy Boba Brown Sugar Milk', 'Minuman', 'China', 30000, 20),
('MN002', 'Es Teh Manis', 'Minuman', 'Indonesia', 15000, 30),
('MN003', 'French Sparkle Cocktail', 'Minuman', 'Western', 40000, 25);
--
-- Indexes for dumped tables
--
--
-- Indexes for table `product`
--
ALTER TABLE `produk`
 ADD PRIMARY KEY (`KodeProduk`);
COMMIT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;