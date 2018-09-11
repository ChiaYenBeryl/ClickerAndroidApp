// Saved as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/select") 
public class SelectServlet extends HttpServlet {  // JDK 6 and above only
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
		 out.println("<html><head><title>Select Results</title></head><body>");
 
		 // Retrieve the books' id. Can order more than one books.
		 String choiceStr = request.getParameter("choice");
		 String quesionNoStr = request.getParameter("questionNo");
		 choiceStr = choiceStr.toLowerCase();
					
		 String sqlStr = "Insert into responses (questionNo, choice) values ("
					+ quesionNoStr + ", '" + choiceStr +"');";
		int count = stmt.executeUpdate(sqlStr);
		// sqlStr = "Select * from responses where questionNo = " 
		//			+ 8 + "and choice = '" + '?' + "';";
		out.println("<!DOCTYPE html>"
		+"<html>"
		+"<head>"
		+"<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'><style>.w3-button {width:150px;}"
		+"body {    font-family: Arial;}"
		+".flex-container {  display: flex;  flex-wrap: wrap;}"
		+"div.container {    width: 100%;    border: 1px solid gray;}"
		+"header, footer {    padding: 1em;    color: white;    background-color: lightBlue;    clear: left;    text-align: center;}"
		+".center {    text-align: center;    border: 3px purple;}"
		+"article {    padding: 1em;    overflow: hidden;}"
		+"</style></head><body><div class='container'><header>   <h5>Thank you!</h5></header>  <div class='center'>"
		+"<article id=frame'>	    <h3>Your results have been submitted.</h3>   </article></div><footer>Copyright &copy; Chia Yen & Lianne 2018 </footer>"
		+"</div></body></html>"); 
		
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