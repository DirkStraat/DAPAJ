-- create table dapaj;
CREATE USER 'dapaj'@'localhost' IDENTIFIED BY 'bank';
GRANT ALL PRIVILEGES ON dapaj . * TO 'dapaj'@'localhost';
SET @@global.time_zone = '+02:00';
SET @@session.time_zone = '+02:00';