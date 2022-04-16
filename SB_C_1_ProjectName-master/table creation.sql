create table ACCOUNTS (
DATABASE_ID char (45) not null,
USERNAME char (45) not null,
PASSWORD char (45) not null,
primary key (DATABASE_ID)
);

create table CHARACTERINFO (
JSON_DATA json);

create table GAME_DATA (
JSON_DATA json);