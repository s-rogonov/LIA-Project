CREATE DATABASE chat;
USE chat;
CREATE USER 'app'@'%' IDENTIFIED BY 'insertsecrethere';
GRANT ALL PRIVILEGES ON chat.* TO 'app'@'%';
FLUSH PRIVILEGES;

CREATE TABLE posts (
     id INT NOT NULL AUTO_INCREMENT,
     first VARCHAR(30) NOT NULL,
     last VARCHAR(30) NOT NULL,
     msg TEXT NOT NULL,
     PRIMARY KEY (id)
);