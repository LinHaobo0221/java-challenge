package jp.co.axa.apidemo.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(jp.co.axa.apidemo.services.*)")
    public void pointcut() {}

    @Before("pointcut()")
    public void logRequestStart(JoinPoint joinPoint) {
        logger.info("Log Start: Method Name :" + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(value = "pointcut()", returning = "returnValue")
    public void logRequestReturn(JoinPoint joinPoint, Object returnValue) {
        logger.info("result is :" + returnValue);
        logger.info("Log End: Method Name :" + joinPoint.getSignature().toShortString());
    }
}
