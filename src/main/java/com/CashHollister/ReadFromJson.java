package com.CashHollister;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ReadFromJson {
    private static final String DEV_RESOURCE_PATH = "src/main/resources/comments.json";

    // Method to determine the file path based on the environment
    private static boolean isDevelopmentEnvironment() {
        File localFile = new File(DEV_RESOURCE_PATH);
        return localFile.exists();
    }

    public static List<CommentData> readCommentData() throws IOException {
        // handle json parsing
        ObjectMapper objectMapper = new ObjectMapper();
        // init empty list
        List<CommentData> commentsList = new ArrayList<>();
        // verify if in a dev env
        boolean isDevelopment = isDevelopmentEnvironment();

        JsonNode rootNode;// declare var to hold root node of the parsed json
        if (isDevelopment) {
            rootNode = objectMapper.readTree(new File(DEV_RESOURCE_PATH)); //read from dev path if in dev env
        } else {
            // read from class path using input stream when deployed
            InputStream inputStream = getResourceAsStream("comments.json");
            if (inputStream == null) {
                throw new IOException("Resource not found: comments.json");
            }
            rootNode = objectMapper.readTree(inputStream);
            inputStream.close();
        }

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

    // used to read comments data via input stream
    private static InputStream getResourceAsStream(String resource) {
        return ReadFromJson.class.getClassLoader().getResourceAsStream(resource);
    }
}

        