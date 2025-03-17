package com.example.todoapp.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table("tasks")
public class Task implements Comparable<Task>{

    @Id
    @Column("task_id")
    private int taskId;

    @Column("task_title")
    private String title;

    @Column("task_description")
    private String description;

    @Column("completed")
    private boolean isCompleted;

    @Column("after_deadline")
    private boolean isAfterDeadline;

    @Column("deadline_date")
    private LocalDate deadlineDate;

    @JsonCreator
    public Task(String title, String description, boolean isCompleted, boolean isAfterDeadline, LocalDate deadlineDate, int taskId) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isAfterDeadline = isAfterDeadline;
        this.deadlineDate = deadlineDate;
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

    public boolean isAfterDeadline() {
        return isAfterDeadline;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public String deadline(){
        String transformedDate = deadlineDate.format(DateTimeFormatter.ofPattern("d.MM.uuuu"));
        return transformedDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", deadlineDate=" + deadlineDate +
                '}';
    }

    // compare two deadline which one is sooner
    @Override
    public int compareTo(Task o) {
        if(this.deadlineDate.isEqual(o.deadlineDate)) return 0;
        return (this.deadlineDate.isAfter(o.getDeadlineDate())) ? 1: -1;
    }
}
