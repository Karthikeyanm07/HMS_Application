-- Run this script in your MySQL Workbench or Command Line to set up the database and permissions if needed.

-- 1. Create Database if it doesn't exist
CREATE DATABASE IF NOT EXISTS Hos_Man_System;

-- 2. Create user (if you want a dedicated user instead of root)
-- CREATE USER 'hms_user'@'localhost' IDENTIFIED BY 'password123';
-- GRANT ALL PRIVILEGES ON Hos_Man_System.* TO 'hms_user'@'localhost';
-- FLUSH PRIVILEGES;

-- Troubleshooting:
-- If you are getting "Access denied for user 'root'@'localhost'", it means the password in application.properties is incorrect.
-- You can either:
-- A) Update src/main/resources/application.properties with your correct root password.
-- B) Reset your root password (search online for "mysql reset root password").
-- C) Create a new user with the script above and update application.properties.
