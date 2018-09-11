import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*; 

@WebServlet("/results")
public class DisplayResults extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clicker?useSSL=false", "myuser", "asdfasdf");
			stmt = conn.createStatement();
			
			int qnCount;
			
			String qnCountStr = request.getParameter("qnCount");
			if (qnCountStr==null)
				qnCount =1;
			else
				qnCount = Integer.parseInt(qnCountStr); 
			int qnNum = 0;
			
			out.println("<html><title>Results</title>");
			
			int data[][] = new int[qnCount][4]; //make array to store data
			char possChoice[] = {'a', 'b', 'c', 'd'}; 
			
			for(int i = 0; i < qnCount; i++) {
				for(int j = 0; j < 4; j++) {
					ResultSet getData = stmt.executeQuery("select count(*) from responses where questionNo = " + (i+1) + " and choice = '" + possChoice[j] + "'");
					getData.next();
					data[i][j] = getData.getInt(1);
				}
			}
			
			//Testing results
			/*out.println("<body>");
			for(int i = 0; i < qnCount; i++) {
				for(int j = 0; j < 4; j++) {
					out.println("<p>Qn" + (i+1) + " " + possChoice[j] + " = " + data[i][j] + "</p>"); 
				}
			}*/
			out.println("<!DOCTYPE html>"
				+"<html>"
				+"<head>"
				+"<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'><style>.w3-button {width:150px;}"
				+"body {    font-family: Arial;}"
				+".flex-container {  display: flex;  flex-wrap: wrap;}"
				+"div.container {    width: 100%;    border: 1px solid gray;}"
				+"header, footer {    padding: 1em;    color: white;    background-color: lightBlue;    clear: left;    text-align: center;}"
				+".center {    text-align: center;    border: 3px purple;}"
				+"article {    margin-left: 250px; padding: 1em;    overflow: hidden;}"
				+"</style></head><body><div class='container'><header>   <h5>Results</h5></header>  <div class='center'>"
				+"<article id=frame'>");
			
			//Print results out
			out.println("<script type='text/javascript'>"
						+	"window.onload = function () {"
						+		"var chart = new CanvasJS.Chart('chartContainer',"
						+				"{"
						+			"title:{"
						+			"text: 'Answer Results'"
						+		"},"
						+			"axisY: {"
						+			"title: 'Number of respondents',"
						+			"maximum: 10"
						+		"},"
						+			"data: ["
						+			       "{"
						+			    	   "type: 'bar',"
						+			    	   "showInLegend: true,"
						+			    	   "legendText: 'A',"
						+			    	   "color: '#7FB8FF',"
						+			    	   "dataPoints: [");
				for (int i= qnCount; i>1 ;i--)
					out.println("{ y: " + data[i-1][0] + ", label: 'Q" + i +"'},");
						
				out.println(			    	                "{ y: " + data[0][0] + ", label: 'Q1'}"
						+			    	                "]"
						+			       "},"
						+			       "{"
						+			    	   "type: 'bar',"
						+			    	   "showInLegend: true,"
						+			    	   "legendText: 'B',"
						+			    	   "color: '#5563E3',"
						+			    	   "dataPoints: [");
				for (int i= qnCount; i>1 ;i--)
					out.println("{ y: " + data[i-1][1] + ", label: 'Q" + i +"'},");
				out.println(			    	                "{ y: " + data[0][1] + ", label: 'Q1'}"
						+			    	                "]"
						+			       "},"
						+			       "{"
						+			    	   "type: 'bar',"
						+			    	   "showInLegend: true,"
						+			    	   "legendText: 'C',"
						+			    	   "color: '#091DCB',"
						+			    	   "dataPoints: [");
				for (int i= qnCount; i>1 ;i--)
					out.println("{ y: " + data[i-1][2] + ", label: 'Q" + i +"'},");
				out.println(			    	                "{ y: " + data[0][2] + ", label: 'Q1'}"
						+			    	                "]"
						+			       "},"
						+			       "{"
						+			    	   "type: 'bar',"
						+			    	   "showInLegend: true,"
						+			    	   "legendText: 'D',"
						+			    	   "color: '#0A146F',"
						+			    	   "dataPoints: [");
				for (int i= qnCount; i>1 ;i--)
					out.println("{ y: " + data[i-1][3] + ", label: 'Q" + i +"'},");
				out.println(			    	                "{ y: " + data[0][3] + ", label: 'Q1'}"
						+			    	                "]"
						+			       "}"
						+			       "]"
						+				"});"
						+		"chart.render();"
						+	"}"
						+	"</script>"
						+	"<script type='text/javascript' src='https://canvasjs.com/assets/script/canvasjs.min.js'></script>"
						+	"<body>"
						+	"<div class='center' id='chartContainer' style='height: 80%; width: 80%;'>"
						+	"</div>");
			out.println("</article><div class='center'><form method='get' action='results'>");
			out.println("<p>Enter Number of Questions: <input type='number' name='qnCount' required /></p>");
			out.println("<p><input type='submit' value='See Results'/>");
			out.println("</div></form>");
						
			out.println("<footer>Copyright &copy; Chia Yen & Lianne 2018 </footer>"
				+"</div></body></html>");
			
			
			
			/*int questionNum = 1;
			String choice = request.getParameter("choice");
			//String questionNum = request.getParameter("questionNum");
			String sqlStr = "Insert into responses (questionNo, choice) values (" + questionNum + ", '" + choice + "')";
			questionNum++;
			int count = stmt.executeUpdate(sqlStr);
			
			out.println("Thank you for your selection.</body></html>");*/
			
			
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			out.close();
			try{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
