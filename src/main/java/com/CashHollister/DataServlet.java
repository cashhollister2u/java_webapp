package com.CashHollister;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create an instance of SignatureData
        SignatureData signatureData = new SignatureData("John Doe", 1);

        // Set the data as an attribute in the request
        request.setAttribute("signatureData", signatureData);

        // Forward the request to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}