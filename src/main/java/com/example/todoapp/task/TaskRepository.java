package com.example.todoapp.task;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;


public interface TaskRepository extends Repository<Task, Integer> {

    @Modifying
    @Query("INSERT INTO tasks (task_title, task_description, completed, deadline_date, creation_date) " +
            "VALUES (:title, :description, :isCompleted, :deadlineDate, :creationDate)")
    void save(String title, String description, boolean isCompleted, LocalDate deadlineDate, LocalDate creationDate);

    @Query("SELECT * FROM tasks WHERE task_id = :id")
    Task findById(int id);

    @Modifying
    @Query("DELETE FROM tasks WHERE task_id = :id")
    int delete(int id);

    @Modifying
    @Query("UPDATE tasks " +
            "SET task_title=:title, task_description=:description, completed=:isCompleted, deadline_date=:deadlineDate, creation_date=:creationDate " +
            "WHERE task_id=:id")
    int update(String title, String description, boolean isCompleted, LocalDate deadlineDate, LocalDate creationDate, int id);

    @Query("SELECT * FROM tasks")
    Iterable<Task> findAll();
}
