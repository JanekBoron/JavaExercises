package com.example.tpov2;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;


import javax.servlet.annotation.*;
@WebServlet("/Display")
public class Display extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {

        String dbUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;database=TPO5;encrypt=true;trustServerCertificate=true;";
        String user="sa";
        String password="1qaz@WSX";

        String typ = req.getParameter("car");
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.println("<html><body>");


        try
            {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(dbUrl,user,password);
                Statement stmt = con.createStatement();
                System.out.println(typ);
                ResultSet rs = stmt.executeQuery("select * from Samochód WHERE Typ='"+typ+"'");                  //WHERE typ = "" + req + "'"
                out.println("<table border=1 width=50% height=50%>");
                out.println("<thead>");
                out.println("<tr><th>CarID</th><th>Marka</th><th>RokProdukcji</th><th>Przebieg</th><tr>");
                out.println("</thead>");
                out.println("<tbody>");
                while (rs.next())
                {
                    String n = rs.getString("Typ");
                    String nm = rs.getString("Marka");
                    String s = rs.getString("rokprodukcji");
                    String przebieg= rs.getString("przebieg");
                    out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td><td>"+przebieg+"</td></tr>");
//                    System.out.println(n +nm +s);
                }
                out.println("</tbody>");
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
                con.close();
            }
        catch (Exception e)
        {
            out.println("error");
            System.out.println(e);
        }
    }
}
