package com.CashHollister;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ReadFromJson {
    private static final String DEV_RESOURCE_PATH = "src/main/resources/comments.json";
    private static final String DEPLOYMENT_PATH = "/opt/tomcat/webapps/java_webapp/WEB-INF/classes/comments.json";

    // Method to determine the file path based on dev/deployed env
    private static String getFilePath() {
        File localFile = new File(DEV_RESOURCE_PATH);
        if (localFile.exists()) {
            return DEV_RESOURCE_PATH;
        } else {
            return DEPLOYMENT_PATH;
        }
    }

    public static List<CommentData> readCommentData() throws IOException {
        // handle json parsing
        ObjectMapper objectMapper = new ObjectMapper();
        // init empty list
        List<CommentData> commentsList = new ArrayList<>();
        String filePath = getFilePath();

        
        JsonNode rootNode = objectMapper.readTree(new File(filePath)); //read from dev path if in dev env

        // if the comments list is not as expected return an empty array
        if (rootNode == null || !rootNode.isArray()) {
            return commentsList;
        }

        // package the data and return to be read on screen
        ArrayNode commentsArray = (ArrayNode) rootNode;
        for (JsonNode jsonNode : commentsArray) {
            String name = jsonNode.get("name").asText();
            String comment = jsonNode.get("comment").asText();
            int likes = jsonNode.get("likes").asInt();
            commentsList.add(new CommentData(name, comment, likes));
        }

        return commentsList;
    }
}

        