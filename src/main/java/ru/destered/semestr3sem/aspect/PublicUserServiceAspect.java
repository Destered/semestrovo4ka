package ru.destered.semestr3sem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class PublicUserServiceAspect {

        private Logger logger = Logger.getLogger(PublicUserServiceAspect.class.getName());

        @Pointcut("execution(public * ru.destered.semestr3sem.services.implementations.UsersServiceImpl.*(..))")
        public void getPublicInfo() {
            //Need for aspect after and before working
        }

        @Around(value = "getPublicInfo()")
        public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
            logger.log(Level.INFO, "Invoked: " + joinPoint.getSignature());
            logger.log(Level.INFO, "Arguments: " + Arrays.toString(joinPoint.getArgs()));
            logger.log(Level.INFO, "Started: " + LocalTime.now());
            logger.log(Level.INFO, "Ended: " + LocalTime.now());
            return joinPoint.proceed();
        }

        @AfterReturning(value = "getPublicInfo()", returning = "returningValue")
        public void logReturningValue(JoinPoint joinPoint, Object returningValue) {
            logger.log(Level.INFO, "Returned value: " + returningValue);
        }

    }
