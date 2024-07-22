package com.CashHollister;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ReadFromJson {
    private static final String FILE_PATH = "src/main/resources/comments.json";

    public static List<CommentData> readCommentData() throws IOException { 
        ObjectMapper objectMapper = new ObjectMapper(); 
        File file = new File(FILE_PATH);
        List<CommentData> commentsList = new ArrayList<>();

        JsonNode rootNode = objectMapper.readTree(file);
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
        // return the list of comments
        return commentsList;
    }
}

        