package com.example.todoapp.login;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogInAspect {

    private final LogInService logInService;

    public LogInAspect(LogInService logInService) {
        this.logInService = logInService;
    }


    @Around("execution(String com.example.todoapp.afterdeadlinetask.AfterDeadlineTaskController.*(..)) || " +
            "execution(String com.example.todoapp.completedtask.CompletedTaskController.*(..)) || " +
            "execution(String com.example.todoapp.uncompletedtask.UnCompletedTaskController.*(..)) || " +
            "execution(String com.example.todoapp.home.HomeController.*(..))")
    public String checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        if(!logInService.isLogged()){
            return "redirect:/";
        }
        return (String) joinPoint.proceed();
    }
}
