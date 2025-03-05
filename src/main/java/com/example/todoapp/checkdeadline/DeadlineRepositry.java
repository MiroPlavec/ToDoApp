package com.example.todoapp.checkdeadline;

import com.example.todoapp.task.Task;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

public interface DeadlineRepositry extends Repository<Task, Integer> {

    @Modifying
    @Query("UPDATE tasks SET after_deadline=true WHERE deadline_date < :today")
    int checkDeadlinesDate(String today);
}
