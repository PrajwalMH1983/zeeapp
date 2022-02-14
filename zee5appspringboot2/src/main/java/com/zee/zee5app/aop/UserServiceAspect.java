package com.zee.zee5app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect		//Container where you will hold all your aop code
public class UserServiceAspect {
	//This AOP is getting direct internal access
	//PointCut Expression - criteria of execution
	//joinPoint - method executed or called like addUser
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//Here we have to setup the context 
	//For every method from the service Impl will be monitored by this user Service aspect 
	//before calling any method from this service impl 
	
	//wherever u are using @Repository notation there this will be active 
	@Pointcut("within(@org.springframework.stereotype.Repository *)" + 
	"|| within(@org.springframework.stereotype.Service *)" + 
			"within(within@org.springframework.web.bind.annotation.RestController *)")
	
	//Here for all the repository restControllers whenever there is an error
	//For all the errors it should generate the log
	//To avoid complexity we made this a separate function
	public void springPointCutExp() {
		// TODO Auto-generated method stub
		
	}
	
	
	//We have used the above method name in pointcut here 
	@AfterThrowing(pointcut = "springPointCutExp()" , throwing = "e")
	public void logAfterThrowingException(JoinPoint joinPoint , Throwable e) {
		log.error("exception {}.{}() with cause {}" , joinPoint.getSignature().getDeclaringTypeName() , 
				joinPoint.getSignature().getName() , e.getCause() != null ? e.getCause():"NULL");
	}
	
	//@Around(value = )	//This runs before and after all the methods matching with pointcut expression
	
	//when this action is going to happen , when this criteria is matched what would be ur action , that action would be your advice
	//On postman we had addUser method right so that becomes the joinPoint
	//By using this we get hello from only those functions which start with get
	@Before(value = "execution(* com.zee.zee5app.service.Impl.*.get*(..))")
	//@After(value = "execution(* com.zee.zee5app.controller.*.*(..))")
	public void beforAllServiceMethods(JoinPoint joinPoint) {
		System.out.println("hello");
		System.out.println(joinPoint.getTarget());
	}
}
