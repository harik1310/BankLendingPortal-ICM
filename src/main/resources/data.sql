 			 
  INSERT INTO Loan_App_Master (
    Loan_App_Id,
    Application_Date,
    Interest_Rate
) VALUES
    ('LA123', '2024-03-01', 5.5),
    ('LA124', '2024-03-02', 6.0),
    ('LA125', '2024-03-03', 5.8),
    ('LA126', '2024-03-10', 5.7),
    ('LA127', '2024-03-05', 5.6),
    ('LA128', '2024-03-11', 5.9),
    ('LA129', '2024-03-09', 5.4);
    
    
 INSERT INTO Loan_Master (
    Loan_Id,
    Date_Of_Creation,
    Interest_Rate,
    Type_Of_Loan
) VALUES
    ('LA123', '2024-03-01', 5.5, 'PERSONAL_LOANS'),
    ('LA124', '2024-03-02', 6.0, 'AUTO_LOANS'),
    ('LA125', '2024-03-03', 5.8, 'BUSINESS_LOANS'),
    ('LA126', '2024-03-10', 5.7, 'PERSONAL_LOANS'),
    ('LA127', '2024-03-05', 5.6, 'HOME_LOANS'),
    ('LA128', '2024-03-11', 5.9, 'AUTO_LOANS'),
    ('LA129', '2024-03-09', 5.4, 'BUSINESS_LOANS');  
   			   
 
 
