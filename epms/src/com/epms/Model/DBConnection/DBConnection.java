package com.epms.Model.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
   public static Connection getConnection() {
      Connection conn = null;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
         conn = DriverManager.getConnection(url,"system", "oracle");
      }catch(Exception e) {
         e.printStackTrace();
      }
      return conn;
   }
}
