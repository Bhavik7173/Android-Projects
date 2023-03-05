-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 11, 2021 at 11:14 AM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hubbub`
--

-- --------------------------------------------------------

--
-- Table structure for table `achievement`
--

DROP TABLE IF EXISTS `achievement`;
CREATE TABLE IF NOT EXISTS `achievement` (
  `A_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL COMMENT 'personal_profile->per_ID',
  `A_Achievement` varchar(50) NOT NULL,
  `A_Year` varchar(4) NOT NULL,
  `A_Rank` int(3) NOT NULL,
  `A_Image` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`A_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `Ad_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL,
  PRIMARY KEY (`Ad_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `alumini`
--

DROP TABLE IF EXISTS `alumini`;
CREATE TABLE IF NOT EXISTS `alumini` (
  `Al_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL,
  `Al_DOJ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Al_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `C_ID` int(11) NOT NULL,
  `Al_ID` int(11) NOT NULL COMMENT 'alumini->Al_ID',
  `C_Name` varchar(50) NOT NULL,
  `C_DOJ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `C_POST` varchar(50) NOT NULL,
  `C_Salary` double NOT NULL,
  PRIMARY KEY (`C_ID`),
  UNIQUE KEY `Al_ID` (`Al_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `E_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL,
  `E_Name` varchar(50) NOT NULL,
  `E_Details` text NOT NULL,
  `E_Status` char(1) NOT NULL,
  `E_File` text NOT NULL,
  PRIMARY KEY (`E_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
CREATE TABLE IF NOT EXISTS `faculty` (
  `F_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL,
  `Sub_ID` int(11) NOT NULL,
  PRIMARY KEY (`F_ID`),
  KEY `Per_ID` (`Per_ID`),
  KEY `Sub_ID` (`Sub_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `job_profile`
--

DROP TABLE IF EXISTS `job_profile`;
CREATE TABLE IF NOT EXISTS `job_profile` (
  `J_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL COMMENT 'Personal_Profile->Per_ID',
  `J_Required_Experience` text NOT NULL,
  `J_Details` text NOT NULL,
  `J_Salary` double NOT NULL,
  `J_Status` char(1) NOT NULL,
  `J_File` text NOT NULL,
  PRIMARY KEY (`J_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `L_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL,
  PRIMARY KEY (`L_ID`),
  UNIQUE KEY `Per_ID` (`Per_ID`),
  KEY `Per_ID_2` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `personal_profile`
--

DROP TABLE IF EXISTS `personal_profile`;
CREATE TABLE IF NOT EXISTS `personal_profile` (
  `Per_ID` int(11) NOT NULL,
  `Per_Name` varchar(50) NOT NULL,
  `Per_Date_Of_Birth` text NOT NULL,
  `Per_Gender` varchar(20) NOT NULL,
  `Per_Contact_No_1` varchar(10) NOT NULL,
  `Per_Contact_No_2` varchar(10) NOT NULL,
  `Per_Status` varchar(10) NOT NULL,
  `Per_Image` text,
  `Per_Address` text NOT NULL,
  `Per_Hobby` text NOT NULL,
  `Per_Interest` text NOT NULL,
  `Per_Email` varchar(50) NOT NULL,
  `Per_Password` varchar(50) NOT NULL,
  PRIMARY KEY (`Per_ID`),
  UNIQUE KEY `Per_Email` (`Per_Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `professional_profile`
--

DROP TABLE IF EXISTS `professional_profile`;
CREATE TABLE IF NOT EXISTS `professional_profile` (
  `Pro_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL COMMENT 'personal_profile->per_ID',
  `Pro_Qualification` text NOT NULL,
  `Pro_Experience` text NOT NULL,
  `Pro_Link` text NOT NULL,
  PRIMARY KEY (`Pro_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
CREATE TABLE IF NOT EXISTS `skills` (
  `Sk_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL COMMENT 'personal_profile->per_ID',
  `Sk_Skill` text NOT NULL,
  `Sk_Time` varchar(50) NOT NULL,
  PRIMARY KEY (`Sk_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `S_ID` int(11) NOT NULL,
  `Per_ID` int(11) NOT NULL COMMENT 'personal_profile->per_ID',
  `S_Class` varchar(50) NOT NULL,
  `S_Div` varchar(50) NOT NULL,
  `S_Mentor` varchar(50) NOT NULL,
  `S_Status` varchar(50) NOT NULL,
  PRIMARY KEY (`S_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `Sub_ID` int(50) NOT NULL,
  `Sub_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Sub_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `achievement`
--
ALTER TABLE `achievement`
  ADD CONSTRAINT `achievement_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`);

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`);

--
-- Constraints for table `alumini`
--
ALTER TABLE `alumini`
  ADD CONSTRAINT `alumini_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`);

--
-- Constraints for table `company`
--
ALTER TABLE `company`
  ADD CONSTRAINT `company_ibfk_1` FOREIGN KEY (`Al_ID`) REFERENCES `alumini` (`Al_ID`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`);

--
-- Constraints for table `faculty`
--
ALTER TABLE `faculty`
  ADD CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`Sub_ID`) REFERENCES `subject` (`Sub_ID`);

--
-- Constraints for table `job_profile`
--
ALTER TABLE `job_profile`
  ADD CONSTRAINT `job_profile_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`);

--
-- Constraints for table `professional_profile`
--
ALTER TABLE `professional_profile`
  ADD CONSTRAINT `professional_profile_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`);

--
-- Constraints for table `skills`
--
ALTER TABLE `skills`
  ADD CONSTRAINT `skills_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
