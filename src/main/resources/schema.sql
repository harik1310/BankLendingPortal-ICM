 create table Loan_App_Master (
        Loan_App_Id varchar(255) not null  primary key,
        Application_Date date,
        Interest_Rate float(24)
    );
create table Loan_App_Detail_Master (
        Id varchar(255) not null primary key,
        Installment integer,
        Interest_Rate float(24),
        Last_Date_Of_Install_Pay date,
        Month_No integer,
        P_Outstanding_Begin_Of_Mon integer,
        P_Repayment integer,
        P_Outstanding_End_Of_Mon integer,
        Loan_App_Id varchar(255) not null,
        foreign key (Loan_App_Id ) references Loan_App_Master(Loan_App_Id)
    );

    create table Loan_Master (
        Loan_Id varchar(255) not null  primary key,
        Date_Of_Creation date,
        Interest_Rate float(24),
        Type_Of_Loan varchar(255) check (type_of_loan in ('PERSONAL_LOANS','AUTO_LOANS','BUSINESS_LOANS','HOME_LOANS'))
    );