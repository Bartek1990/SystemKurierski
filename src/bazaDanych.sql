CREATE DATABASE PDS;
USE PDS;

CREATE TABLE USER
(
userid int unsigned not null auto_increment primary key,
login varchar(30) not null,
password varchar(50) not null,
dataid int not null,
nip numeric(10)
);

CREATE TABLE DATA
(
dataid int not null auto_increment primary key,
name varchar(100),
countryid int not null,
details varchar(200) not null,
zipcode int not null,
city varchar(50) not null,
tel varchar(16) not null,
mail varchar(40)
);

CREATE TABLE COUNTRY
(
countryid int not null auto_increment primary key,
name varchar(50) not null
);

CREATE TABLE PERSON
(
userid int not null,
forename varchar(30) not null,
surname varchar(30) not null
);

CREATE TABLE CORPORATION
(
userid int not null,
name varchar(100) not null,
regon varchar(20) not null
);

CREATE TABLE ADDRESS_BOOK
(
userid int not null,
dateid int not null,
title varchar(100) not null
);

CREATE TABLE SHIPMENT
(
shipmentid int not null auto_increment primary key,
dataid int not null,
sourceid int not null,
returnid int not null,
serviceid int not null,
weightid int not null,
amount int not null,
paymentid int not null,
sdate timestamp not null,
ddate timestamp,
statusid int not null,
paid boolean not null
);

CREATE TABLE PAYMENT
(
paymentid int not null auto_increment primary key,
type varchar(50) not null,
price numeric(10,2)
);
CREATE TABLE WEIGHT
(
weightid int auto_increment not null primary key,
intervalf numeric(10,4) not null,
intervalt numeric(10,4) not null,
price numeric(10,2) not null
);

CREATE TABLE SERVICE
(
serviceid int  not null auto_increment primary key,
type varchar(100) not null, 
price numeric(10,2)
);

CREATE TABLE STATUS
(
statusid int not null auto_increment primary key,
title varchar(30) not null
);

CREATE TABLE BASE_HISTORY
(
baseid int not null,
shipmentid int not null,
odate timestamp not null
);

CREATE TABLE BASE
(
baseid int not null auto_increment primary key,
dataid int not null,
headid int
);

CREATE TABLE EMPLOYEE
(
employeeid int not null auto_increment primary key,
login varchar(30) not null,
password varchar(50) not null,
forename varchar(30) not null,
surname varchar(30) not null,
dataid int not null,
empdate timestamp not null,
earnings numeric(10,2) not null,
baseid int,
worktime varchar(100),
available boolean not null,
nip numeric(10) not null,
account varchar(30)
);

CREATE TABLE COURIER_HISTORY 
(
courierid int not null,
shipmentid int not null,
date timestamp not null,
oncar boolean not null,
delivered boolean not null
);

CREATE TABLE COURIER
(
empolyeeid int not null,
location_x varchar(100),
location_y varchar(100),
tel varchar(16) not null
);
CREATE TABLE OPERATOR_HISTORY
(
operatorid int not null,
shipmentid int not null,
date timestamp not null
);


CREATE TABLE OPERATOR
(
operatorid int not null auto_increment primary key,
employeeid int not null,
tel varchar(16) not null
);

CREATE TABLE PLACE
(
userid int not null,
dataid int not null,
title varchar(100)
);
