 			 
  INSERT INTO Loan_App_Master (
    Loan_App_Id,
    Application_Date,
    Interest_Rate
) VALUES
    ('PL123', '2024-03-01', 5.5),
    ('AL124', '2024-03-02', 6.0),
    ('BL125', '2024-03-03', 5.8),
    ('PL126', '2024-03-10', 5.7),
    ('HL127', '2024-03-05', 5.6),
    ('AL128', '2024-03-11', 5.9),
    ('BL129', '2024-03-09', 5.4);
    
    
 INSERT INTO Loan_Master (
    Loan_Id,
    Date_Of_Creation,
    Interest_Rate,
    Type_Of_Loan
) VALUES
    ('PL123', '2024-03-01', 5.5, 'PERSONAL_LOANS'),
    ('AL124', '2024-03-02', 6.0, 'AUTO_LOANS'),
    ('BL125', '2024-03-03', 5.8, 'BUSINESS_LOANS'),
    ('PL126', '2024-03-10', 5.7, 'PERSONAL_LOANS'),
    ('HL127', '2024-03-05', 5.6, 'HOME_LOANS'),
    ('AL128', '2024-03-11', 5.9, 'AUTO_LOANS'),
    ('BL129', '2024-03-09', 5.4, 'BUSINESS_LOANS');  
   
    
INSERT INTO Users (user_name,password,role,is_Account_Locked) VALUES ('admin','admin','admin',false);
INSERT INTO Users (user_name,password,role,is_Account_Locked)   VALUES ('user','user','user',false);
 
 
