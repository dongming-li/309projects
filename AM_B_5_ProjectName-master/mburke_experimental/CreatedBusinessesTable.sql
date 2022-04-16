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


/*We will need to have a method that creates a new table
 named User(UserID)Friends for every User in the Users table. */
CREATE TABLE User1Friends(
UserID int,
FriendUserID int,
DateBecameFriends varchar(255),
BestFriends bit,
Family bit,
GroupName varchar(255),
FOREIGN KEY (UserID) REFERENCES Users(ID)
);

CREATE TABLE Promotions(
BusinessID int,
PromoTitle varchar(255),
PromoDescription text,
PromoDate date,
PromoDuration time,
PromoStartDate datetime,
PromoEndDate datetime,
PromoImage blob,
FOREIGN KEY (BusinessID) REFERENCES Businesses(ID)
);
