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
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wolf
 */
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            String artist = request.getParameter("artisttxt");
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Albums", "wolf", "Khalil08");
                PreparedStatement pst = conn.prepareStatement("select * from WOLF.ALBUMINFO where artist=?");
                pst.setString(1, artist);
                out.print("<table width=25% border=1>");
                out.print("<center><h1>Results:</h1></center>");
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                while(rs.next()){
                    out.print("<tr>");
                    out.print("<td>" + rsmd.getColumnName(1) + "</td>");
                    out.print("<td>" + rs.getInt(1) + "</td></tr>");
                    out.print("<tr><td>" + rsmd.getColumnName(2) + "</td>");
                    out.print("<td>" + rs.getString(2) + "</td></tr>");
                    out.print("<tr><td>" + rsmd.getColumnName(3) + "</td>");
                    out.print("<td>" + rs.getString(3) + "</td></tr>");
                    out.print("<tr><td>" + rsmd.getColumnName(4) + "</td>");
                    out.print("<td>" + rs.getString(4) + "</td></tr>");
                    out.print("<tr><td>" + rsmd.getColumnName(5) + "</td>");
                    out.print("<td>" + rs.getString(5) + "</td></tr>");
                    out.print("<tr><td>" + rsmd.getColumnName(6) + "</td>");
                    out.print("<td>" + rs.getString(6) + "</td></tr>");
                }
                out.print("</table>");
                out.println("<ul><li><a href =\"index.html\">Home</a></li>\n");
                out.println("<li><a href =\"add.html\">Add Record</a></li>\n");
                out.println("<li><a href =\"search.html\">Search Record</a></li>\n");
                out.println("<li><a href =\"view.html\">View Database Contents</a></li></ul>\n");
            }
            catch(Exception e2){
                out.println(e2);
            }
        }
    }

}
