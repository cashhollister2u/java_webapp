package com.CashHollister;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the POST request
        String name = request.getParameter("name");
        String comment = request.getParameter("comment");

    
        // Create an instance of SignatureData
        SignatureData signatureData = new SignatureData(name, comment);

        // Save data to JSON file
        signatureData.saveToJsonFile();

        // Set the data as an attribute in the request
        request.setAttribute("signatureData", signatureData);

        // Forward the request to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests here, if needed
        SignatureData signatureData = new SignatureData("", "");

        //signatureData.saveToJsonFile();
        
        // Set the data as an attribute in the request
        request.setAttribute("signatureData", signatureData);

        // Forward the request to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}