create table padaj;
CREATE USER 'padaj'@'localhost' IDENTIFIED BY 'bank';
GRANT ALL PRIVILEGES ON padaj . * TO 'padaj'@'localhost';
SET @@global.time_zone = '+02:00';
SET @@session.time_zone = '+02:00';