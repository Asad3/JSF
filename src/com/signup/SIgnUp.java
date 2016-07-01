package com.signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="member")
@RequestScoped
public class SIgnUp {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public SIgnUp() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String addMember(){
		int i = 0;
		PreparedStatement ps = null;
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup", "root", "");
			String sql = "INSERT INTO member(first_name, last_name, email, password) VALUES(?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			ps.setString(4, password);
			i = ps.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try{
				con.close();
				ps.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(i>0){
			return "success";
		}
		else{
			return "failure";
		}
	}	
	
	public String Login(){
		int i = 0;
		PreparedStatement ps = null;
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup", "root", "");
			String sql = "SELECT * from member";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				if( (email==rs.getString("email")) && (password==rs.getString("password"))){
					return "Login";
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try{
				con.close();
				ps.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return "invalid";
		
	}
	
}