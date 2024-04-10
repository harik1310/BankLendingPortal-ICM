package com.cognizant.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

	public enum level {
		INFO, DEBUG, ERROR
	}
	private static String placeHolder = "{} {}";

//	@Before(" execution(* com.cognizant.*.*.*(..))")
//	public void beforeAdvice(JoinPoint point) {
//		log.info(placeHolder, level.INFO, point.getSignature().getName());
//	}

//	@After(" execution(* com.cognizant.*.*.*(..))")
//	public void afterAdvice(JoinPoint point) {
//		log.info(placeHolder, level.INFO, point.getSignature().getName());
//	}

	@AfterReturning(pointcut = "execution(* com.cognizant.*.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint point, Object result) {
		log.debug(placeHolder, level.DEBUG, point.getSignature().getName());
	}

	@AfterThrowing(pointcut = "execution(* com.cognizant.*.*.*(..))", throwing = "error")
	public void afterThrowing(JoinPoint point, Throwable error) {
		log.error(placeHolder+" {}", level.ERROR, point.getSignature().getName(),error.getMessage());
	}

	@Pointcut("execution(* com.cognizant.*.*.*(..))")
	public void getPointCut() {

	}
	
	@Around("getPointCut()")
	public Object aroundAdvice(ProceedingJoinPoint point) {
		log.info(placeHolder, level.INFO, point.getSignature().getName());
		Object returnValue = null;
		try {
			returnValue = point.proceed();
		} catch (Throwable e) {
			log.error(placeHolder, level.ERROR, e.getMessage());
		}
		log.debug(placeHolder, level.DEBUG, returnValue);

		return returnValue;
	}

}
