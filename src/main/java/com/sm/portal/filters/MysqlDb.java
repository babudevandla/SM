package com.sm.portal.filters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlDb {

	public static void main(String[] args) {
		 
		MysqlDb example = new MysqlDb();
        example.getUserDetaisl("");
    }
 
    public Integer getUserDetaisl(String uname) {
        Connection connection = null;
        PreparedStatement statement = null;
 
        Integer userId=null;
        try {
            String sql = "SELECT * from users WHERE mobile_no = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, uname);
            ResultSet rs = statement.executeQuery();
 
            while (rs.next()) {
 
                userId = rs.getInt("userId");
 
            }
 
        } catch (SQLException e) {
 
            System.out.println(e.getMessage());
 
        } finally {
 
            try {
                if (statement != null) {
                    statement.close();
                }
 
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
 
        }
		return userId;
    }
 
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sm_portal", "root", "password");
 
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return connection;
    }
}
