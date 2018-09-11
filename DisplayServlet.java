// Saved as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/display") 
public class DisplayServlet extends HttpServlet {  // JDK 6 and above only
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
 
      Connection conn = null;
      Statement stmt = null;
      try {
         // Step 1: Create a database "Connection" object
         // For MySQL
         Class.forName("com.mysql.jdbc.Driver");  // Needed for JDK9/Tomcat9
         conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/clicker?useSSL=false", "myuser", "asdfasdf");  // <<== Check
 
         // Step 2: Create a "Statement" object inside the "Connection"
         stmt = conn.createStatement();
 
         // Step 3 & 4: Execute a SQL SELECT query and Process the query result
		 out.println("<html><head><title>Results</title></head><body>");
 
		 // Retrieve the books' id. Can order more than one books.
		
		 sqlStr = "Select choice, * as count from responses where questionNo = " 
					+ questionNoStr + "qroup by choice;";
		int count = stmt.executeUpdate(sqlStr);
		 out.println("Your response has been submitted.");
		
 
		
      } catch (SQLException ex) {
         ex.printStackTrace();
     } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
     } finally {
         out.close();
         try {
            // Step 5: Close the Statement and Connection
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }
}