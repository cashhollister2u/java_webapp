package com.CashHollister;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WriteToJson {
    public static void saveToJsonFile(String name, String comment) throws IOException{
       ObjectMapper objectMapper = new ObjectMapper(); 
        ObjectNode jsonNode = objectMapper.createObjectNode(); 
        jsonNode.put("name", name); 
        jsonNode.put("comment", comment); 
        objectMapper.writeValue(new File("src/main/resources/comments.json"), jsonNode); 
    } 
}
