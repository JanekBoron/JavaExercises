package com.example.tpov2;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/ProcessInfo")
public class ProcessInfo extends HttpServlet {
    private String message;



    public void init() {
        message = "Welcome in car rental company";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        //        String url = "/Display.jsp";
        String carName=request.getParameter("car");
        System.out.println(carName);
//        request.setAttribute("carName",carName);
//        Display display= new Display();
//        getServletContext().getRequestDispatcher(url).forward(request,response);

        // Handle any errors that may have occurred.
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("/Display");
        requestDispatcher.forward(request,response);
//        display.doGet(request,response,carName);

    }

//    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//
//
//    }


    public void destroy() {
    }
}