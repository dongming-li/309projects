CREATE TABLE BUSINESSES(
ID int,
Username varchar(255),
BusinessTitle varchar(255),
Phone int,
Email varchar(255),
Password varchar(255),
Address varchar(255),
City varchar(255),
PRIMARY KEY (ID)
);

DROP TABLE BUSINESSES;

CREATE TABLE Businesses(
ID int,
Username varchar(255),
BusinessTitle varchar(255),
Phone int,
Email varchar(255),
Password varchar(255),
Address varchar(255),
City varchar(255),
PRIMARY KEY (ID)
);

CREATE TABLE Users(
ID int,
FirstName varchar(255),
LastName varchar(255),
Username varchar(255),
Password varchar(255),
Phone int,
Email varchar(255),
Location varchar(255),
FriendsCount int,
PRIMARY KEY (ID)
);