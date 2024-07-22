package com.CashHollister;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadFromJson {
    public static CommentData readCommentData() throws IOException { 
        ObjectMapper objectMapper = new ObjectMapper(); 
        JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/comments.json")); 
        String name = jsonNode.get("name").asText(); 
        String comment = jsonNode.get("comment").asText(); 

        return new CommentData(name, comment);
        
    } 
    
}
