package userMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet {
	
private final static String query ="select name,Designation,Department,Joined_date from user where id=?";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  PrintWriter pw = res.getWriter();
	  res.setContentType("text/html");
	  
	  int id= Integer.parseInt(req.getParameter("id"));
	  
	  pw.println("<link rel='stylesheet'href='css/bootstrap.css'></link>");
	 
	  try {
		  
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
		  
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  try (Connection con= DriverManager.getConnection("jdbc:mysql:///usermgmt","root","123456");
	  
	  PreparedStatement ps=con.prepareStatement(query);){
		  
		  ps.setInt(1, id);
		  
	ResultSet rs= ps.executeQuery();
	rs.next();
	pw.println("<div style ='margin:auto;width:500px; margin-top:100px;'>");
	
	pw.println("<form action ='edit?id="+id+"' method='post'>");
	pw.println("<table class='table table-hover table-striped'>");
	
	pw.println("<tr> ");
	pw.println("<td> Name</td>");
	pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
	
	pw.println("<tr> ");
	pw.println("<td> Destination</td>");
	pw.println("<td><input type='text' name='Destination' value='"+rs.getString(2)+"'></td>");
	
	pw.println("<tr> ");
	pw.println("<td> Department</td>");
	pw.println("<td><input type='text' name='Department' value='"+rs.getString(3)+"'></td>");
	
	pw.println("<tr> ");
	pw.println("<td> Joined date</td>");
	pw.println("<td><input type='date' name='Joined_date' value='"+rs.getString(4)+"'></td>");
	
	
	
	
	
	
	
	
	pw.println("</tr> ");
	
	pw.println("<tr> ");
	pw.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button> </td>");
	pw.println("<td><button type='reset' <button class='btn btn-outline-danger'>cancel</button></td>");
	
	
	
	pw.println("</table>");
	
	
	pw.println("</form>"); 
		 
		  
		  
		
	} catch (SQLException se) {
		pw.println("<h2 class='bg-danger text-light-center'>"+se.getMessage()+"</h2>");
		se.printStackTrace();
		
	}catch(Exception e) {
		
		e.printStackTrace();
	}
	  
	  pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
	 
	  pw.println("</div>");
	  pw.close();
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}



}