package com.cognizant.utilities;


public enum TypeOfLoan {
	PERSONAL_LOANS("Personal loans"),
	AUTO_LOANS("Auto loans"),
	BUSINESS_LOANS("Business loans"),
	HOME_LOANS("Home loans");
	
	private final String loan;
    
	TypeOfLoan(String loan) {
        this.loan = loan;
    }
	
//    public String getLoan() {
//        return loan;
//    }		
}
