package com.example.todoapp.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

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
    public Task(String title, String description, boolean isCompleted, LocalDate deadlineDate) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.deadlineDate = deadlineDate;
        this.creationDate = LocalDate.now();
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

}
