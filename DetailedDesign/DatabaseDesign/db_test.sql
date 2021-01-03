create table Bike(
	bike_name text,
	id integer,
	license_plate text,
	price numeric(13,2),
	batery integer,
	bike_type text,
	status text,
	lastest_update date,
	present_station integer
);

create table Station(
	station_name text,
	id integer,
	address text,
	acreage text,
	available_bike integer,
	available_rack integer,
	status text,
	lastest_update date
);

create table retal(
	id int,
	bike_id int,
	customer_id int,
	rental_transaction_id int,
	rent_station_id int,
	status text,
	time_start date,
	time_end date
);

create table transactions(
	id int,
	amount numeric(13,2),
	description text,
	card_id int,
	transaction_date date,
	command text
);

create table customer(
	id int,
	customer_name text, 
	email text,
	customer_password text,
	birthday date,
	card_id int
);

create table card(
	id int,
	balance numeric(13,2),
	card_number text,
	card_holder_name text,
	issuing_bank text,
	expiration_date date,
	security_code text
);

create table rentalTansection(
	rental_id int,
	transaction_id int
);

alter table bike add primary key (id);
