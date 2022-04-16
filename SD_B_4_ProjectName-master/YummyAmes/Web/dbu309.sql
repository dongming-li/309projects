create database db309aliu;
use db309aliu;

/*Item 1: The restaurant table */
create table Restaurant (
RName char (20),
ID char (9) not null references account(aID),
Address char (30),
worktime char (20));

alter table Restaurant add primary key (ID);

/*Item 2: The menu table */
create table Menu (
FoodId char (9),
RID char (9) not null references Restaurant(ID),
FName char (20),
FStyle char (20),
Price double,
primary key (FoodId));

drop table Menu;

ALTER TABLE Menu
DROP COLUMN price;
ALTER TABLE Menu
ADD Price double;

/*Item 3: The customer table*/
create table Customer(
CustomerID char (9) not null references account(aID),
Name char (20),
CRID char (9) not null references Restaurant(ID),
CAddress char (30),
Telephone char (10),
TotalPrice double,
primary key (CustomerID));

drop table Customer;

/*Item 4: The Order table*/
create table OrderFood (
Foodname char (20) not null references Menu(FName),
OFoodID char (9) not null references Menu(RID),
OrderID char (9) not null references Customer(CustomerID),
FPrice double not null references Menu(Price));

/*Item 5: The deliveryman table*/
create table Deliveryman(
DName char (20) not null, 
DPhone char (10) not null,
DID char  (9) not null,
primary key(DID));


/*Item 5: The OrderInformation table*/
create table OrderInformation(
TotalPrice double,
Phone char (10) not null references Customer(Telephone),
OAddress char (20) not null references Customer(CAddress),
OrderID char (9) not null,
primary key(OrderID));

/*Item 6: The DBA table*/
create table DBA(
MID char (10) not null,
DBAName char (20) not null);

/*Item 7: The account table*/
create table c_account(
c_id char (20) not null,
c_name char (20) not null,
c_password char (20) not null,
c_email char (30),
c_phone char (10),
c_address char (50),
c_date datetime,
primary key(c_ID));