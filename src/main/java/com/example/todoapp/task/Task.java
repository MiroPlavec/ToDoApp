package com.example.todoapp.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;

@Table("tasks")
public class Task {

    @Id
    @Column("task_id")
    private int taskId;

    @Column("task_title")
    private String title;

    @Column("task_description")
    private String description;

    @Column("completed")
    private boolean isCompleted;

    @Column("deadline_date")
    private LocalDate deadlineDate;

    @Column("creation_date")
    private LocalDate creationDate;

    @JsonCreator
    public Task(String title, String description, boolean isCompleted, LocalDate deadlineDate, LocalDate creationDate, int taskId) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.deadlineDate = deadlineDate;
        this.creationDate = Objects.requireNonNullElseGet(creationDate, LocalDate::now);
        this.taskId = taskId;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getTaskId() {
        return taskId;
    }
}
