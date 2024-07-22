package com.CashHollister;

import java.io.IOException;

public class CommentData {
    private String name;
    private String comment;
    private int likes;

    // Constructor
    public CommentData(String name, String comment, int likes) {
        this.name = name;
        this.comment = comment;
        this.likes = 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public int getLikes() {
        return likes;
    }

    // Method to save the object to a JSON file
    public void saveToJsonFile() throws IOException{
       WriteToJson.saveToJsonFile(name, comment, likes);
    } 
}