package com.CashHollister;

import java.io.IOException;

public class CommentData {
    private String name;
    private String comment;

    // Constructor
    public CommentData(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    // Method to save the object to a JSON file
    public void saveToJsonFile() throws IOException{
       WriteToJson.saveToJsonFile(name, comment);
    } 
}