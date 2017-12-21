/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import kavarny_dreamteam.*;
import kavarny_dreamteam.Main;
import kavarny_dreamteam.User;
/**
 * Třída korigující připojení do DB
 * Je situována jako jedináče, má proto privátní konstruktor a
 * je možné požádat pouze o její instanci
 * @author rostaklein
 */
public class Database {
   private static Database instance = null;
   
   private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
   private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/kavarny";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "root";
   
   /**
    * Exists only to defeat instantiation.
    */
   protected Database() {}
   
   /**
    * pokud ještě neexistuje instance této třídy, vytvoří ji
    * v opačném případě vrátí sama sebe
    * @return insatnci sama sebe
    */
   public static Database getInstance() {
      if(instance == null) {
         instance = new Database();
      }
       return instance;
   }

    /**
     * prepares statement for the database
     * @param sqlStatement
     * @return prepared statement to set parameters
     */
    public static PreparedStatement getPrepStatement(String sqlStatement){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                return connection.prepareStatement(sqlStatement);
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

   
}