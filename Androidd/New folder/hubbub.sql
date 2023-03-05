-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 26, 2021 at 04:21 AM
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
  `A_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL COMMENT 'personal_profile->per_ID',
  `A_Achievement` varchar(50) NOT NULL,
  `A_Year` varchar(4) NOT NULL,
  `A_Rank` int(3) NOT NULL,
  `A_Image` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`A_ID`),
  KEY `achievement_ibfk_1` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `achievement`
--

INSERT INTO `achievement` (`A_ID`, `Per_ID`, `A_Achievement`, `A_Year`, `A_Rank`, `A_Image`) VALUES
(2, 60, 'Degree', '2017', 4, 'abcd.jpg'),
(3, 60, 'AbC', '2015', 1, 'images/Snapchat-595281087.jpg'),
(4, 62, 'XYZ', '2013', 1, 'images/20210222_073529.jpg');

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
  `Al_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL,
  `Al_DOJ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Al_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alumini`
--

INSERT INTO `alumini` (`Al_ID`, `Per_ID`, `Al_DOJ`) VALUES
(1, 62, '2021-03-09 02:50:09');

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `C_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Al_ID` int(11) NOT NULL COMMENT 'alumini->Al_ID',
  `C_Name` varchar(50) NOT NULL,
  `C_DOJ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `C_DOL` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `C_POST` varchar(50) NOT NULL,
  `C_Salary` double NOT NULL,
  PRIMARY KEY (`C_ID`),
  UNIQUE KEY `Al_ID` (`Al_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`C_ID`, `Al_ID`, `C_Name`, `C_DOJ`, `C_DOL`, `C_POST`, `C_Salary`) VALUES
(5, 1, 'Riya U. Patel', '2015-05-05 18:30:00', '2020-06-07 18:30:00', 'Employee', 25000);

-- --------------------------------------------------------

--
-- Table structure for table `contactus`
--

DROP TABLE IF EXISTS `contactus`;
CREATE TABLE IF NOT EXISTS `contactus` (
  `C_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL,
  `C_Name` varchar(20) NOT NULL,
  `Per_Email` varchar(200) NOT NULL,
  `C_ContactNo` varchar(13) NOT NULL,
  `C_Time` varchar(50) NOT NULL,
  `C_Problem` text NOT NULL,
  PRIMARY KEY (`C_ID`),
  UNIQUE KEY `Per_Email` (`Per_Email`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contactus`
--

INSERT INTO `contactus` (`C_ID`, `Per_ID`, `C_Name`, `Per_Email`, `C_ContactNo`, `C_Time`, `C_Problem`) VALUES
(2, 62, '', 'riya@gmail.com', '9865986598', '12:00 PM - 03:00 PM', 'XYZABCDEFGH');

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
-- Table structure for table `e_like`
--

DROP TABLE IF EXISTS `e_like`;
CREATE TABLE IF NOT EXISTS `e_like` (
  `EL_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Per_Id` int(10) NOT NULL,
  `E_Like` varchar(20) NOT NULL,
  `E_Dislike` varchar(20) NOT NULL,
  `E_Interested` varchar(20) NOT NULL,
  `LDL` varchar(200) NOT NULL,
  `INI` varchar(200) NOT NULL,
  PRIMARY KEY (`EL_Id`),
  KEY `e_like_ibfk_1` (`Per_Id`)
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
  KEY `faculty_ibfk_2` (`Sub_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE IF NOT EXISTS `feedback` (
  `F_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL,
  `F_Subject` text NOT NULL,
  `F_Your_Feedback` text NOT NULL,
  PRIMARY KEY (`F_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`F_ID`, `Per_ID`, `F_Subject`, `F_Your_Feedback`) VALUES
(2, 60, 'ABC', 'XYZ'),
(3, 62, '', '');

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
-- Table structure for table `pdftable`
--

DROP TABLE IF EXISTS `pdftable`;
CREATE TABLE IF NOT EXISTS `pdftable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PdfURL` text NOT NULL,
  `PdfName` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `personal_profile`
--

DROP TABLE IF EXISTS `personal_profile`;
CREATE TABLE IF NOT EXISTS `personal_profile` (
  `Per_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_Name` varchar(50) NOT NULL,
  `Per_Date_Of_Birth` text NOT NULL,
  `Per_Gender` varchar(20) NOT NULL,
  `Per_Contact_No_1` varchar(20) NOT NULL,
  `Per_Contact_No_2` varchar(20) NOT NULL,
  `Per_Status` varchar(10) NOT NULL,
  `Per_Image` text,
  `Per_Address` text NOT NULL,
  `Per_Hobby` text NOT NULL,
  `Per_Interest` text NOT NULL,
  `Per_Email` varchar(50) NOT NULL,
  `Per_Password` varchar(50) NOT NULL,
  PRIMARY KEY (`Per_ID`),
  UNIQUE KEY `Per_Email` (`Per_Email`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personal_profile`
--

INSERT INTO `personal_profile` (`Per_ID`, `Per_Name`, `Per_Date_Of_Birth`, `Per_Gender`, `Per_Contact_No_1`, `Per_Contact_No_2`, `Per_Status`, `Per_Image`, `Per_Address`, `Per_Hobby`, `Per_Interest`, `Per_Email`, `Per_Password`) VALUES
(59, 'avi', '2004-1-17', 'MALE', '+916565397689', '+919764976497', 'STUDENT', 'images/Snapchat-1704919971.jpg', 'Bhatar', 'Reading', 'Computer', 'bspatel87330@gmail.com', 'Avi@3797'),
(60, 'Bhavik', '2004-1-13', 'MALE', '+919865986598', '+919865653623', 'STUDENT', 'images/Snapchat-1446506711.jpg', 'Bhatar', 'Reading', 'Coding', 'bhavikpatel10801@gmail.com', 'Bhavik@3797'),
(62, 'Riya U.  Patel', '2004-1-13', 'FEMALE', '+916955652295', '+919865986598', 'FACULTY', 'images/', 'Althan', 'Reading', 'Coding', 'riya@gmail.com', 'Riya@3797'),
(63, 'Aayush Makwana', '2004-1-14', 'MALE', '+919865986598', '+919865983598', 'STUDENT', 'images/IMG_20210314_103425_HDR.jpg', 'Adajan', 'CRICKET', 'Football', 'aayush@gmail.com', 'Ayush@123');

-- --------------------------------------------------------

--
-- Table structure for table `post_event`
--

DROP TABLE IF EXISTS `post_event`;
CREATE TABLE IF NOT EXISTS `post_event` (
  `E_ID` int(30) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(30) NOT NULL,
  `E_TItle` varchar(200) NOT NULL,
  `E_Image` text NOT NULL,
  `E_Details` varchar(200) NOT NULL,
  `E_File` text NOT NULL,
  `E_Starting` date NOT NULL,
  `E_Closing` date NOT NULL,
  `E_StartTime` varchar(20) NOT NULL,
  `E_EndTime` varchar(20) NOT NULL,
  `E_Location` varchar(200) NOT NULL,
  PRIMARY KEY (`E_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post_event`
--

INSERT INTO `post_event` (`E_ID`, `Per_ID`, `E_TItle`, `E_Image`, `E_Details`, `E_File`, `E_Starting`, `E_Closing`, `E_StartTime`, `E_EndTime`, `E_Location`) VALUES
(1, 62, 'XYZ', 'images/abc.pdf', 'ABC', 'pdfs/XYZ.pdf', '2021-03-22', '2021-03-22', '03:15:00', '05:15:00', 'Bhatar'),
(2, 62, 'XYZ', 'images/abc.pdf', 'ABC', 'pdfs/XYZ.pdf', '2021-03-22', '2021-03-22', '03:15:00', '05:15:00', 'Bhatar');

-- --------------------------------------------------------

--
-- Table structure for table `post_job`
--

DROP TABLE IF EXISTS `post_job`;
CREATE TABLE IF NOT EXISTS `post_job` (
  `J_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL,
  `J_Title` varchar(200) NOT NULL,
  `J_Post` varchar(200) NOT NULL,
  `J_CompanyLogo` text NOT NULL,
  `J_Details` varchar(200) NOT NULL,
  `J_UploadFile` text NOT NULL,
  `J_Salary` int(30) NOT NULL,
  `J_Opening` date NOT NULL,
  `J_Closing` date NOT NULL,
  `J_Location` text NOT NULL,
  PRIMARY KEY (`J_ID`),
  KEY `post_job_ibfk_1` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post_job`
--

INSERT INTO `post_job` (`J_ID`, `Per_ID`, `J_Title`, `J_Post`, `J_CompanyLogo`, `J_Details`, `J_UploadFile`, `J_Salary`, `J_Opening`, `J_Closing`, `J_Location`) VALUES
(15, 62, 'TXYZ', 'CEO', 'images/abc.pdf', 'jjs', 'pdfs/XYZ.pdf', 25000, '2021-03-24', '2021-03-30', 'Surat'),
(16, 62, 'Xyz', 'CEO', 'images/', 'jjs', 'pdfs/', 25000, '2021-03-23', '2021-03-30', 'Surat');

-- --------------------------------------------------------

--
-- Table structure for table `professional_profile`
--

DROP TABLE IF EXISTS `professional_profile`;
CREATE TABLE IF NOT EXISTS `professional_profile` (
  `Pro_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL COMMENT 'personal_profile->per_ID',
  `Pro_Qualification` text NOT NULL,
  `Pro_YOP` varchar(50) NOT NULL,
  `Pro_Experience` text NOT NULL,
  `Pro_Link` text NOT NULL,
  PRIMARY KEY (`Pro_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `professional_profile`
--

INSERT INTO `professional_profile` (`Pro_ID`, `Per_ID`, `Pro_Qualification`, `Pro_YOP`, `Pro_Experience`, `Pro_Link`) VALUES
(6, 62, 'BC', '2018', '4th Year', 'www.abc.com');

-- --------------------------------------------------------

--
-- Table structure for table `rateus`
--

DROP TABLE IF EXISTS `rateus`;
CREATE TABLE IF NOT EXISTS `rateus` (
  `R_id` int(10) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL,
  `Per_Email` varchar(200) NOT NULL,
  `R_Rate` double NOT NULL,
  `R_Description` text NOT NULL,
  PRIMARY KEY (`R_id`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rateus`
--

INSERT INTO `rateus` (`R_id`, `Per_ID`, `Per_Email`, `R_Rate`, `R_Description`) VALUES
(2, 62, 'riya@gmail.com', 3.5, 'ABCDEFG'),
(3, 62, 'riya@gmail.com', 3.5, 'ABCDEFG');

-- --------------------------------------------------------

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
CREATE TABLE IF NOT EXISTS `skills` (
  `Sk_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL COMMENT 'personal_profile->per_ID',
  `Sk_Skill` text NOT NULL,
  `Sk_Time` varchar(50) NOT NULL,
  PRIMARY KEY (`Sk_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skills`
--

INSERT INTO `skills` (`Sk_ID`, `Per_ID`, `Sk_Skill`, `Sk_Time`) VALUES
(2, 60, 'Reading , Coding', '2019'),
(3, 60, 'Play', '2015'),
(4, 60, 'Singing', '2005'),
(5, 62, 'Professor', '2017');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `S_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Per_ID` int(11) NOT NULL COMMENT 'personal_profile->per_ID',
  `S_Class` varchar(50) NOT NULL,
  `S_Div` varchar(50) NOT NULL,
  `S_Mentor` varchar(50) NOT NULL,
  `S_Status` varchar(50) NOT NULL,
  PRIMARY KEY (`S_ID`),
  KEY `Per_ID` (`Per_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`S_ID`, `Per_ID`, `S_Class`, `S_Div`, `S_Mentor`, `S_Status`) VALUES
(8, 60, 'BSC', 'Class', 'ABC', 'STUDENT'),
(9, 63, 'BE', 'Computer', 'xyz', 'STUDENT');

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
  ADD CONSTRAINT `achievement_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `alumini`
--
ALTER TABLE `alumini`
  ADD CONSTRAINT `alumini_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `company`
--
ALTER TABLE `company`
  ADD CONSTRAINT `company_ibfk_1` FOREIGN KEY (`Al_ID`) REFERENCES `alumini` (`Al_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `contactus`
--
ALTER TABLE `contactus`
  ADD CONSTRAINT `contactus_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `e_like`
--
ALTER TABLE `e_like`
  ADD CONSTRAINT `e_like_ibfk_1` FOREIGN KEY (`Per_Id`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `faculty`
--
ALTER TABLE `faculty`
  ADD CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `faculty_ibfk_2` FOREIGN KEY (`Sub_ID`) REFERENCES `subject` (`Sub_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `job_profile`
--
ALTER TABLE `job_profile`
  ADD CONSTRAINT `job_profile_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post_event`
--
ALTER TABLE `post_event`
  ADD CONSTRAINT `post_event_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post_job`
--
ALTER TABLE `post_job`
  ADD CONSTRAINT `post_job_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `professional_profile`
--
ALTER TABLE `professional_profile`
  ADD CONSTRAINT `professional_profile_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rateus`
--
ALTER TABLE `rateus`
  ADD CONSTRAINT `rateus_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `skills`
--
ALTER TABLE `skills`
  ADD CONSTRAINT `skills_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Per_ID`) REFERENCES `personal_profile` (`Per_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
