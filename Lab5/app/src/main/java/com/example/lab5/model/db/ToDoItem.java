package com.example.lab5.model.db;

import java.util.Date;

public class ToDoItem {
    public ToDoItem(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDesription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    private String title;
    private String description;
    private Date dueDate;


}
