CREATE TABLE Report_Status(
    VIN varchar(25) NOT NULL Primary key,
    Date_Updated_On NOT NULL date,
    Time_Updated_On NOT NULL time,
    Lock_Status_Set_To NOT NULL char(10),
    Last_Updated_User NOT NULL char(15)
);