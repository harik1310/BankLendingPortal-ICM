package com.cognizant.utilities;
 
import java.util.concurrent.atomic.AtomicInteger;
 
import org.springframework.stereotype.Component;
@Component
public class LoanAppIdGenerator {
//	private LoanAppIdGenerator() {
//		super();
//	}

	private static final AtomicInteger counter = new AtomicInteger(1);
 
    public static String generateId(TypeOfLoan type) {
    	String typ = type.toString();
        String shortLastName = typ.substring(0, 2).toUpperCase();
        String formattedCounter = String.format("%04d", counter.getAndIncrement());
        return shortLastName + formattedCounter;
    }
 
 
}