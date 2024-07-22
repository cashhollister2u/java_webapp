package com.CashHollister;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WriteToJson {
    private static final String FILE_PATH = "src/main/resources/comments.json";
    private static final int MAX_COMMENTS = 200;

    public static void saveToJsonFile(String name, String comment) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper(); 
        File file = new File(FILE_PATH);
        ArrayNode commentsArray;

        commentsArray = (ArrayNode) objectMapper.readTree(file);

        if (commentsArray.size() >= MAX_COMMENTS) {
            return;
        }

        // create a new comments node
        ObjectNode commentNode = objectMapper.createObjectNode(); 
        commentNode.put("name", name); 
        commentNode.put("comment", comment); 

        // add the comment node to the existing array
        commentsArray.add(commentNode);

        // write to comments.json
        objectMapper.writeValue(file, commentsArray); 
    } 
}
