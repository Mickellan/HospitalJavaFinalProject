/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject340;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
   public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hospital";
        Connection con = DriverManager.getConnection(url, "root", "");
        return con;
   }

}
