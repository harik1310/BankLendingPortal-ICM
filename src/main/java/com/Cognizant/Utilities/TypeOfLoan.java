package com.Cognizant.Utilities;


public enum TypeOfLoan {
	Personal_Loans("Personal loans"),
	Auto_Loans("Auto loans"),
	Business_Loans("Business loans"),
	Home_Loans("Home loans");
	
	private final String loan;
    
	TypeOfLoan(String loan) {
        this.loan = loan;
    }
	
    public String getLoan() {
        return loan;
    }		
}
