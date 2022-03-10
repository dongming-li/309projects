CREATE TABLE User_Table( User_ID int PRIMARY KEY,
						 First_Name varchar(20),
						 Last_Name varchar(20),
						 Email_Address varchar(20),
						 Height int,
                         Weight int,
                         Age int,
                         BirthDate date,
                         Loss_Goal int,
                         Workout_Plan_ID int,
                         Assigned_Trainer_ID int,
                         IsTrainer int,
                         IsAdmin int
						);
                        
CREATE TABLE User_HeartRate( User_ID int PRIMARY KEY,
							 Entry_Time Datetime,
							 BRM int
							);
                            
CREATE TABLE User_Steps( User_ID int PRIMARY KEY,
					     Entry_Time Datetime,
						 Total_Steps int
							);
                            
CREATE TABLE Workout_Plan(  Workout_Plan_ID int PRIMARY KEY,
							Monday_Description varchar(2000),
							Monday_Workout varchar(1000),
							Tuesday_Description varchar(2000),
							Tuesday_Workout varchar(1000),
                            Wednesday_Description varchar(2000),
							Wednesday_Workout varchar(1000),
                            Thursday_Description varchar(2000),
							Thursday_Workout varchar(1000),
                            Friday_Description varchar(2000),
							Friday_Workout varchar(1000),
                            Saturday_Description varchar(2000),
							Saturday_Workout varchar(1000),
                            Sunday_Description varchar(2000),
							Sunday_Workout varchar(1000)
						 );
                         
CREATE TABLE Workout_Plans( Workout_Plan_ID int PRIMARY KEY,
							Plan_Name varchar(30),
                            Plan_Description varchar(2000)
							);
                            
DROP TABLE Workout_Plans;
DROP TABLE Workout_Plan;
DROP TABLE User_Steps;
DROP TABLE User_HeartRate;
DROP TABLE User_Table;