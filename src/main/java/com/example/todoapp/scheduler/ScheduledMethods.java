package com.example.todoapp.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduledMethods {

    private final SchedulerTaskRepository schedulerTaskRepository;

    @Autowired
    public ScheduledMethods(SchedulerTaskRepository schedulerTaskRepository) {
        this.schedulerTaskRepository = schedulerTaskRepository;
    }

    // call 5 minutes after midnight each day and set after_deadline=true in my database
    @Scheduled(cron = "0 5 0 * * *")
    public void print(){
        LocalDate today = LocalDate.now();
        schedulerTaskRepository.checkDeadlinesDate(today.toString());
    }
}
