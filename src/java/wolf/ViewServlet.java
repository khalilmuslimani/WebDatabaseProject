/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolf;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wolf
 */
public class ViewServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            response.setContentType("text/html");
            out.println("<html><body>");            
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Albums", "wolf", "Khalil08");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from WOLF.ALBUMINFO");
                out.println("<table border=1 width=50% height=50%>");
                out.println("<tr><th>ID</th><th>Title</th><th>Artist</th><th>Length</th><th>Genre</th><th>Release Year</th></tr>");
                while(rs.next()){
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String artist = rs.getString("artist");
                    String length = rs.getString("length");
                    String genre = rs.getString("genre");
                    String release_year = rs.getString("release_year");
                    out.println("<tr><td>" + id + "</td><td>" + title + "</td><td>" + artist + "</td><td>" + length + "</td><td>" + genre + "</td><td>" + release_year+ "</td></tr>");
                }
                out.println("</table");
                out.println("</html></body>");
                conn.close();
                out.println("<ul><li><a href =\"index.html\">Home</a></li>\n");
                out.println("<li><a href =\"add.html\">Add Record</a></li>\n");
                out.println("<li><a href =\"search.html\">Search Record</a></li>\n");
                out.println("<li><a href =\"view.html\">View Database Contents</a></li></ul>\n");
                
            }
            catch(Exception e){
                out.println(e);
            }
            
        }
    }

}
