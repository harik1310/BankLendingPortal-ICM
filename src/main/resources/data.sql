 			 
  INSERT INTO Loan_App_Master (
    Loan_App_Id,
    Application_Date,
    Interest_Rate,
    Principal_Amount,
    Tenure
) VALUES
    ('PL1233', '2024-03-01', 5.5,1200000,64),
    ('AL1244', '2024-03-02', 6.0,1400000,64),
    ('BL1256', '2024-03-03', 5.8,2100000,64),
    ('PL1264', '2024-03-10', 5.7,4200000,64),
    ('HL1271', '2024-03-05', 5.6,5200000,64),
    ('AL1286', '2024-03-11', 5.9,5600000,64),
    ('BL1290', '2024-03-09', 5.4,1300000,64);
    
    
 INSERT INTO Loan_Master (
    Loan_Id,
    Date_Of_Creation,
    Interest_Rate,
    Type_Of_Loan
) VALUES
    ('PL1233', '2024-03-01', 5.5, 'PERSONAL_LOANS'),
    ('AL1244', '2024-03-02', 6.0, 'AUTO_LOANS'),
    ('BL1256', '2024-03-03', 5.8, 'BUSINESS_LOANS'),
    ('PL1264', '2024-03-10', 5.7, 'PERSONAL_LOANS'),
    ('HL1271', '2024-03-05', 5.6, 'HOME_LOANS'),
    ('AL1286', '2024-03-11', 5.9, 'AUTO_LOANS'),
    ('BL1290', '2024-03-09', 5.4, 'BUSINESS_LOANS');  
   
    
INSERT INTO Users (user_name,password,role,is_Account_Locked) VALUES ('admin','admin','admin',false);
INSERT INTO Users (user_name,password,role,is_Account_Locked)   VALUES ('user','user','user',false);
 
 
