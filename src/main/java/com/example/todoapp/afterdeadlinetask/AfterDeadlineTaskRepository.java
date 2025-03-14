package com.example.todoapp.afterdeadlinetask;

import com.example.todoapp.task.Task;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AfterDeadlineTaskRepository extends Repository<Task, Integer> {

    @Query("SELECT * FROM tasks WHERE after_deadline = true")
    List<Task> findAll();

    @Modifying
    @Query("DELETE FROM tasks WHERE task_id = :id")
    int deleteTask(int id);


}
