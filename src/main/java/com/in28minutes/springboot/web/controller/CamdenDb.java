package com.in28minutes.springboot.web.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.in28minutes.springboot.web.controller.User;
public class CamdenDb {
	public List<User>  getUserDetails() throws SQLException, ClassNotFoundException
	{
	Class.forName("org.postgresql.Driver");
   List<User> lu=new ArrayList<User>();

	 //Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres") ;
	   Connection connection = DriverManager.getConnection("jdbc:postgresql://awsnlcld001.amwaternp.net:5432/ao-aw-camden", "postgres", "vjedqz)D6kd") ;
	 
	Statement st= connection.createStatement();
	//select email from login where username='ashok'
	ResultSet rs=st.executeQuery("select * from  app.hqo_company_events  where  \"isApproved\"='NO' order by event_id");
	while(rs.next())
	{
		User user = new User();
		System.out.println(rs.getString("name"));
		user.setName(rs.getString("name"));
		user.setVenue(rs.getString("venue"));
		user.setAlert_type(rs.getString("alert_type"));
		user.setDescription(rs.getString("description"));
		user.setEventId(rs.getLong("event_id"));
		lu.add(user);
	}
	return lu;
	}
	
	
	
	
	public Integer  getEmployeeDetails() throws SQLException, ClassNotFoundException
	{
	Class.forName("org.postgresql.Driver");
   List<User> lu=new ArrayList<User>();

	 Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres") ;
	 //  Connection connection = DriverManager.getConnection("jdbc:postgresql://awsnlcld001.amwaternp.net:5432/postgres", "postgres", "postgres") ;
	 
	Statement st= connection.createStatement();
	//select email from login where username='ashok'
	int rs=st.executeUpdate("COPY \"Employee\" FROM 'D:\\Employee.csv' WITH CSV HEADER;");
	System.out.println(rs+"....executed number of records");
	return rs;
	}
	
	
	
	
	
	
	
	
	
	
	
	public String updateEventState(String[] eventId) throws SQLException, ClassNotFoundException
	{
	Class.forName("org.postgresql.Driver");
	String s="successfully updated";
	String query=null;
	Connection connection = DriverManager.getConnection("jdbc:postgresql://awsnlcld001.amwaternp.net:5432/ao-aw-camden", "postgres", "vjedqz)D6kd") ;
	Statement st= connection.createStatement();
	//select email from login where username='ashok'
	for(int i=0;i<eventId.length;i++)
	{
	 query="update  app.hqo_company_events set \"isApproved\"='yes' where event_id="+eventId[i];
	 st.addBatch(query);
	}
	st.executeBatch();
	connection.commit();
	
	return s;
	}
	public String deleteEvnets(String[] eventId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Class.forName("org.postgresql.Driver");
		String s="successfully updated";
		String query=null;
		 Connection connection = DriverManager.getConnection("jdbc:postgresql://awsnlcld001.amwaternp.net:5432/ao-aw-camden", "postgres", "vjedqz)D6kd") ;
		Statement st= connection.createStatement();
		//select email from login where username='ashok'
		for(int i=0;i<eventId.length;i++)
		{
			 query="update  app.hqo_company_events set \"isApproved\"='rejected' where event_id="+eventId[i];
		 st.addBatch(query);
		}
		st.executeBatch();
		connection.commit();
		return s;
	}
}