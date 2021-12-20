package com.oched.booksprj.aspects;

import com.oched.booksprj.responses.BookInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class DataLogger {

    @Before("execution(* com.oched.booksprj.services.BookService.*(..))")
    public void logBeforeBookService(JoinPoint joinPoint) {
        log.info("======================={Before -> BookService}=======================");
        log.info(joinPoint.getSignature().getName());
        log.info(Arrays.toString(Arrays.stream(joinPoint.getArgs()).toArray()));
        log.info(joinPoint.getSignature().toString());
        log.info(joinPoint.getKind());
        log.info("=====================================================================");
    }

    @After("execution(* com.oched.booksprj.services.BookService.*(..))")
    public void logAfterBookService(JoinPoint joinPoint) {
        log.info("======================={After -> BookService}=======================");
        log.info(joinPoint.getSignature().getName());
        log.info(Arrays.toString(Arrays.stream(joinPoint.getArgs()).toArray()));
        log.info(joinPoint.getSignature().toString());
        log.info(joinPoint.getKind());
        log.info("====================================================================");
    }

    @AfterReturning(value = "execution(* com.oched.booksprj.services.BookService.getById(..))", returning = "response")
    public void logAfterReturning(JoinPoint joinPoint, BookInfoResponse response) {
        log.info("======================={AfterReturning -> getById}=======================");
        log.info(joinPoint.getSignature().getName());
        log.info(Arrays.toString(Arrays.stream(joinPoint.getArgs()).toArray()));
        log.info(joinPoint.getSignature().toString());
        log.info(response.toString());
        log.info("=========================================================================");
    }

    @AfterThrowing(
            value = "execution(* com.oched.booksprj.services.BookService.getById(..))",
            throwing = "exception"
    )
    public void logAfterThrowing(Exception exception) {
        log.info("======================={AfterThrowing -> getById}=======================");
        log.info(exception.getMessage());
        log.info("=========================================================================");
    }
}
