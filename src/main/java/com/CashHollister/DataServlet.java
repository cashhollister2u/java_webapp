package com.CashHollister;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the POST request
        String name = request.getParameter("name");
        String comment = request.getParameter("comment");

        // Create an instance of commentData
        CommentData commentData = new CommentData(name, comment, 0);

        // Save data to JSON file
        commentData.saveToJsonFile();
        
        // re-route so that the page does not resubmit form on refresh
        response.sendRedirect(request.getContextPath() + "/comments");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get the list
        List<CommentData> commentsList = ReadFromJson.readCommentData();

        // Set the data as an attribute in the request
        request.setAttribute("commentsList", commentsList);

        //Set attrbute for number of comments 
        request.setAttribute("numberOfComments", commentsList.size());

        // Forward the request to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}