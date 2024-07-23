package com.CashHollister;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;

public class WriteToJson {
    private static final String RESOURCE_NAME = "comments.json";
    private static final int MAX_COMMENTS = 200;
    
    // Method to determine the file path based on dev/deployed env
    private static String getFilePath() {
        String localPath = "src/main/resources/" + RESOURCE_NAME;
        String deploymentPath = "/opt/tomcat/webapps/java_webapp/WEB-INF/classes/" + RESOURCE_NAME;
        
        File localFile = new File(localPath);
        if (localFile.exists()) {
            return localPath;
        } else {
            return deploymentPath;
        }
    }

    public static void saveToJsonFile(String name, String comment, int likes) throws IOException {
        // used for json parsing
        ObjectMapper objectMapper = new ObjectMapper(); 
        ArrayNode commentsArray; // declare a var to hold the array 
        String filePath = getFilePath(); //sets the file based on type of enviroment dev/deploy

        // open the file path as an input stream and handle if it does not exist "null"
        InputStream inputStream = getResourceAsStream(RESOURCE_NAME);
        if (inputStream == null) {
            commentsArray = objectMapper.createArrayNode();
        } else {
            // read file data into a jsonNode
            JsonNode rootNode = objectMapper.readTree(inputStream);
            if (rootNode != null && rootNode.isArray()) {
                commentsArray = (ArrayNode) rootNode; //turn jsonNode into an array
            } else {
                commentsArray = objectMapper.createArrayNode();
            }
            inputStream.close();
        }

        // Handle comments exceeding max limit
        if (commentsArray.size() >= MAX_COMMENTS) {
            return;
        }

        // Create a new comment node
        ObjectNode commentNode = objectMapper.createObjectNode(); 
        commentNode.put("name", name); 
        commentNode.put("comment", comment); 
        commentNode.put("likes", likes); 
        
        // Add the comment node to the existing array
        commentsArray.add(commentNode);

        // Write to comments.json
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            objectMapper.writeValue(fileOutputStream, commentsArray);
        }
    }

    //used to load comments.json as an input stream
    private static InputStream getResourceAsStream(String resource) {
        return WriteToJson.class.getClassLoader().getResourceAsStream(resource);
    }
}