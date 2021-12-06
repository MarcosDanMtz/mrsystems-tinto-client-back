create database mrsystems_tinto_db;
use mrsystems_tinto_db;
------------------ property_type TABLE -----------------
CREATE TABLE property_type(
	id INT NOT NULL UNIQUE,
	PRIMARY KEY(id),
	name varchar(100)
);

SELECT * FROM property_type

INSERT INTO property_type VALUES(1, "Color");

------------------ properties TABLE -----------------

CREATE TABLE properties(
	id varchar(50) NOT NULL UNIQUE,
	PRIMARY KEY(id),
	name varchar(150) NOT NULL,
	description varchar(800) NOT NULL,
	img varchar(500) NOT NULL,
	add_price  decimal DEFAULT NULL,
	property_type_id INT NOT NULL,
	FOREIGN KEY (property_type_id)
        REFERENCES property_type(id)
);

DROP TABLE properties;

INSERT INTO properties VALUES ("6173564F-5103-4445-9219-AE4960DE6589", 
							"propiedad TESTING", 
							"Description de la propiedad",
							"NA",
							1);

SELECT * FROM properties;

------------------ product TABLE -----------------

CREATE TABLE product(
	id varchar(50) NOT NULL UNIQUE,
	PRIMARY KEY(id),
	name varchar(250) NOT NULL,
	description varchar(800) NOT NULL,
	img varchar(500) NOT NULL,
	price decimal NOT NULL,
	active bool NOT NULL,
	last_update datetime DEFAULT NULL,
	creation_date datetime NOT NULL
);

SELECT * FROM product;
DROP TABLE product


------------------ product_properties TABLE -----------------

CREATE TABLE product_properties(
	product_id varchar(50) NOT NULL,
    FOREIGN KEY (product_id)
        REFERENCES product(id),
    properties_id varchar(50) NOT NULL,
    FOREIGN KEY (properties_id)
        REFERENCES properties(id)
);
DROP TABLE product_properties;
SELECT * FROM product_properties pp ;

d5ea12c7-ecb4-4690-a644-c1ac0fe04639, 698c456f-5967-4184-95a7-bbb511563513
