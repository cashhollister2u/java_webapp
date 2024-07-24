package com.CashHollister;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.File;

public class WriteToJson {
    private static final int MAX_COMMENTS = 200;
    
    // Method to determine the file path based on dev/deployed env
    private static String getFilePath() {
        String localPath = "src/main/resources/comments.json";
        String deploymentPath = "/opt/tomcat/webapps/java_webapp/WEB-INF/classes/comments.json";
        
        File localFile = new File(localPath);
        if (localFile.exists()) {
            return localPath;
        } else {
            return deploymentPath;
        }
    }

    public static void saveToJsonFile(String name, String comment, int likes) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode commentsArray;
        String filePath = getFilePath();

        File file = new File(filePath);

        if (file.exists()) {
            JsonNode rootNode = objectMapper.readTree(file);
            if (rootNode.isArray()) {
                commentsArray = (ArrayNode) rootNode; // if an array read the file into an array
            } else if (!rootNode.isArray()) {
                // If the root node is not an array, create a new array and add the existing node
                commentsArray = objectMapper.createArrayNode();
                commentsArray.add(rootNode); 
            } else {
                // Handle unexpected JSON structures
                throw new IOException("Unexpected JSON structure");
            }
        } else {
            commentsArray = objectMapper.createArrayNode();
        }

        if (commentsArray.size() >= MAX_COMMENTS) {
            return; // Do not add more comments if the limit is reached
        }

        //set up the new comment data to be appended as an object
        ObjectNode commentNode = objectMapper.createObjectNode();
        commentNode.put("name", name);
        commentNode.put("comment", comment);
        commentNode.put("likes", likes);

        // append the new comment object to the previous data array
        commentsArray.add(commentNode);

        //write combined data array back to the comments.json
        objectMapper.writeValue(file, commentsArray);
    }

}