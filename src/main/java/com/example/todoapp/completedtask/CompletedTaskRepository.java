package com.example.todoapp.completedtask;

import com.example.todoapp.task.Task;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CompletedTaskRepository extends Repository<Task, Integer> {

    @Query("SELECT * FROM tasks WHERE completed=true")
    List<Task> findAllCompletedTasks();


    @Modifying
    @Query("DELETE FROM tasks WHERE task_id = :id")
    int deleteCompletedTask(String id);
}
