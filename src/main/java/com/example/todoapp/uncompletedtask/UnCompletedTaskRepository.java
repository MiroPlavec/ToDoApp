package com.example.todoapp.uncompletedtask;

import com.example.todoapp.task.Task;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;


public interface UnCompletedTaskRepository extends Repository<Task, Integer> {

    @Modifying
    @Query("INSERT INTO tasks VALUES (NULL, :title, :description, :deadlineDate, :isCompleted, :isAfterDeadline)")
    void save(String title, String description, boolean isCompleted, boolean isAfterDeadline, LocalDate deadlineDate);


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


    @Modifying
    @Query("UPDATE tasks SET completed = true WHERE task_id = :id")
    int completeTask(int id);

    @Query("SELECT * FROM tasks WHERE completed=false AND after_deadline=false")
    List<Task> findUncompletedTasks();
}
