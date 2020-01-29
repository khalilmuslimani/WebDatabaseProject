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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Wolf
 */
public class AddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            int id = Integer.parseInt(request.getParameter("idtxt"));
            String title = request.getParameter("titletxt");
            String artist = request.getParameter("artisttxt");
            String length = request.getParameter("lengthtxt");
            String genre = request.getParameter("genretxt");
            String release_year = request.getParameter("releasetxt");
            
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Albums", "wolf", "Khalil08");
                PreparedStatement pst = conn.prepareStatement("insert into WOLF.ALBUMINFO values(?, ?, ?, ?, ?, ?)");
                pst.setInt(1, id);
                pst.setString(2, title);
                pst.setString(3, artist);
                pst.setString(4, length);
                pst.setString(5, genre);
                pst.setString(6, release_year);
                int row = pst.executeUpdate();
                out.println("<h1>"+row+" record added successfully to the database</h1>");
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
