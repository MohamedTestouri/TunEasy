/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author weixin
 */
public class Connection {
    public String url ="jdbc:mysql://localhost:3306/tuneasy";
    public String login="root";
    public String password ="";
    public java.sql.Connection sqlConnection;
    public static Connection connection;

    public Connection() {
        try {
           sqlConnection = DriverManager.getConnection(url, login, password);
            System.out.println("Connection établie");
        } catch (SQLException ex) {
            System.out.println("Problème de connection");
            System.out.println(ex.getMessage());
        }
    
    }
    public java.sql.Connection getConnection(){
        return sqlConnection;
    }
    public static Connection getInstance(){
        if(connection == null)
            connection = new Connection();
        return connection;
        
    } 
    
}
