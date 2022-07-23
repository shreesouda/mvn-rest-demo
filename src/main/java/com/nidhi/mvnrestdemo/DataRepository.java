package com.nidhi.mvnrestdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;

//repository is nothing but dao in servlets or in MVC. here in rest, we say repository.
//this should actually interact with the database. But we aren't doing that for the moment.
public class DataRepository {
	
	//properties
	List<Model> lt;
	Connection con = null;
	
	//constructor
	public DataRepository() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "kundapura@007");
		} 
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		/*Model md = new Model();
		md.setId(1);
		md.setName("Shreenidhi");
		Model md2 = new Model();
		md2.setId(2);
		md2.setName("Rahul");
		lt = new ArrayList<>(Arrays.asList(md, md2));*/
		
	}
	
	public List<Model> retData() {
		lt = new ArrayList<>();
		String str = "SELECT * FROM players";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			while (rs.next()) {
				Model md = new Model();
				md.setId(rs.getInt(1));
				md.setName(rs.getString(2));
				md.setAge(rs.getInt(3));
				lt.add(md);
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lt;
	}
	
	public Model retDatabyId(int id) {
		/*for (Model md : lt) {
			if (md.getId() == id)
				return md;
		}
		return new Model();*/
		String str = "SELECT * FROM players WHERE id = " + id;
		Model md = new Model();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			if (rs.next()) {
				md.setId(rs.getInt(1));
				md.setName(rs.getString(2));
				md.setAge(rs.getInt(3));
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return md;
	}

	public void create(Model md) {
		String str = "INSERT INTO players VALUES(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(str);
			st.setInt(1, md.getId());
			st.setString(2, md.getName());
			st.setInt(3, md.getAge());
			st.executeUpdate();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//lt.add(md);
		//return md;
	}
	
	public void update(Model md) {
		String str = "UPDATE players SET age = ?, name = ? WHERE id = ?";
		try {
			PreparedStatement st = con.prepareStatement(str);
			st.setInt(1, md.getAge());
			st.setString(2, md.getName());
			st.setInt(3, md.getId());
			st.executeUpdate();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void delete(int id) {
		String str = "DELETE FROM players WHERE id = ?";
		//int n = 0;
		try {
			PreparedStatement st = con.prepareStatement(str);
			//st.setInt(1, md.getAge());
			//st.setString(2, md.getName());
			st.setInt(1, id);
			st.executeUpdate();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		//return n;
		
	}

}
