package com.CashHollister;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
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

        JsonNode rootNode = objectMapper.readTree(file);
        if (rootNode != null && rootNode.isArray()) {
            commentsArray = (ArrayNode) rootNode;
        } else {
            commentsArray = objectMapper.createArrayNode();
        }

        // handle comments exceeding max limit
        if (commentsArray.size() >= MAX_COMMENTS) {
            return;
        }

        // create a new comments node
        ObjectNode commentNode = objectMapper.createObjectNode(); 
        commentNode.put("name", name); 
        commentNode.put("comment", comment); 
        commentNode.put("likes", 0); 
        

        // add the comment node to the existing array
        commentsArray.add(commentNode);

        // write to comments.json
        objectMapper.writeValue(file, commentsArray); 
    } 
}
