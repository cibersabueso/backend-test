DROP DATABASE parking_db;
CREATE DATABASE IF NOT EXISTS parking_db;
use parking_db;

CREATE TABLE vehicleType (
    vehicleTypeId int(11) unsigned NOT NULL AUTO_INCREMENT,
    name varchar(200) NOT NULL,
    createdAt timestamp NULL DEFAULT NULL,
    updatedAt timestamp NULL DEFAULT NULL,
    CONSTRAINT pkVehicleTypeId PRIMARY KEY (vehicleTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE latin1_general_ci;

CREATE TABLE vehicle (
	vehicleId int(11) unsigned NOT NULL AUTO_INCREMENT,
    vehicleTypeId int(11) unsigned NOT NULL,
    licenseNumber varchar(6) NOT NULL,
	createdAt timestamp NULL DEFAULT NULL,
	updatedAt timestamp NULL DEFAULT NULL,
	CONSTRAINT pkVehicleId PRIMARY KEY (vehicleId),
    CONSTRAINT fkVehicleVehicleTypeId FOREIGN KEY (vehicleTypeId) REFERENCES vehicleType(vehicleTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE latin1_general_ci;

CREATE TABLE parking (
	parkingId int(11) unsigned NOT NULL AUTO_INCREMENT,
	vehicleId int(11) unsigned NOT NULL,
    parkingStart DATETIME NOT NULL ,
    parkingEnd DATETIME,
    createdAt timestamp NULL DEFAULT NULL,
	updatedAt timestamp NULL DEFAULT NULL,
	CONSTRAINT pkParkingId PRIMARY KEY (parkingId),
    CONSTRAINT fkParkingVehicleId FOREIGN KEY (vehicleId) REFERENCES vehicle(vehicleId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE latin1_general_ci;

CREATE TABLE feed (
	feedId int(11) unsigned NOT NULL AUTO_INCREMENT,
    vehicleTypeId int(11) unsigned NOT NULL,
    name varchar(200) NOT NULL,
    price varchar(200) NOT NULL,
	createdAt timestamp NULL DEFAULT NULL,
	updatedAt timestamp NULL DEFAULT NULL,
    CONSTRAINT pkFeedId PRIMARY KEY (feedId),
    CONSTRAINT fkFeedVehicleTypeId FOREIGN KEY (vehicleTypeId) REFERENCES vehicleType(vehicleTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE latin1_general_ci;