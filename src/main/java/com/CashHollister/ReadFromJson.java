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
        ObjectMapper objectMapper = new ObjectMapper();
        List<CommentData> commentsList = new ArrayList<>();
        boolean isDevelopment = isDevelopmentEnvironment();

        JsonNode rootNode;
        if (isDevelopment) {
            rootNode = objectMapper.readTree(new File(DEV_RESOURCE_PATH));
        } else {
            InputStream inputStream = getResourceAsStream("comments.json");
            if (inputStream == null) {
                throw new IOException("Resource not found: comments.json");
            }
            rootNode = objectMapper.readTree(inputStream);
            inputStream.close();
        }

        if (rootNode == null || !rootNode.isArray()) {
            return commentsList;
        }

        ArrayNode commentsArray = (ArrayNode) rootNode;
        for (JsonNode jsonNode : commentsArray) {
            String name = jsonNode.get("name").asText();
            String comment = jsonNode.get("comment").asText();
            int likes = jsonNode.get("likes").asInt();
            commentsList.add(new CommentData(name, comment, likes));
        }

        return commentsList;
    }

    private static InputStream getResourceAsStream(String resource) {
        return ReadFromJson.class.getClassLoader().getResourceAsStream(resource);
    }
}

        