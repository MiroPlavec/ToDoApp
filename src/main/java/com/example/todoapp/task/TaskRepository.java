package com.example.todoapp.task;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;


public interface TaskRepository extends Repository<Task, Integer> {

    @Modifying
    @Query("INSERT INTO tasks (task_title, task_description, completed, after_deadline, deadline_date) " +
            "VALUES (:title, :description, :isCompleted, :isAfterDeadline, :deadlineDate)")
    void save(String title, String description, boolean isCompleted, boolean isAfterDeadline, LocalDate deadlineDate);

    @Query("SELECT * FROM tasks WHERE task_id = :id")
    Task findById(int id);

    @Modifying
    @Query("DELETE FROM tasks WHERE task_id = :id")
    int delete(int id);

    @Modifying
    @Query( "UPDATE tasks " +
            "SET " +
            "task_title=:title, " +
            "task_description=:description, " +
            "completed=:isCompleted, " +
            "after_deadline=:isAfterDeadline, " +
            "deadline_date=:deadlineDate " +
            "WHERE task_id=:id")
    int update(String title, String description, boolean isCompleted, boolean isAfterDeadline, LocalDate deadlineDate, int id);

    @Query("SELECT * FROM tasks")
    Iterable<Task> findAll();

    @Modifying
    @Query("UPDATE tasks SET completed = true WHERE task_id = :id")
    int completeTask(int id);

    @Query("SELECT * FROM tasks WHERE completed = true")
    Iterable<Task> findCompletedTask();

    @Query("SELECT * FROM tasks WHERE completed = false")
    Iterable<Task> findUncompletedTasks();
}
