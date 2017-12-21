/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kavarny_dreamteam;

import DB.Database;
import DB.DatabaseGetters;
import GUI.MainWindow;
import javafx.scene.Scene;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;


/**
 * Třída slouží k při přihlašování a registrování uživatelů do aplikace.
 * @author Rosťa
 */
public class UserManagement {
    
    private Main main;
    /**
     * konstruktor třídy UserManagement
     * @param main Hlavní řída aplikace
     */
    public UserManagement(Main main){
        this.main = main;
    }

    /**
     * metoda zkontroluje, zda se uživatel nachází v databázi a zda zadal správné heslo
     * dle toho vrátí text do labelu
     * @param email kterým se uživatel přihlašuje
     * @param passwd které uživatel zadal
     * @return text labelu
     */
    public String logIn(String email, String passwd){
        //uživatel nevyplnil všechny pole formuláře
        if(email.equals("") || passwd.equals("")){
            return "Chybí zadat obě položky.";
        }
        try {
            PreparedStatement stmt = Database.getPrepStatement("SELECT * from users where email = ?");
            if (stmt != null) {
                stmt.setString(1, email);
                ResultSet results = stmt.executeQuery();
                if(!results.next()){
                    return "Tento email neexistuje.";
                }else{
                    do{
                        String salt = results.getString("salt");
                        System.out.println("salt: "+salt);
                        PreparedStatement stmt2 = Database.getPrepStatement("SELECT * from users where email = ? and password = ?");
                        ResultSet userIsValid = null;
                        if (stmt2 != null) {
                            try {
                                stmt2.setString(1, email);
                                stmt2.setString(2, getHashedPwd(passwd,salt));
                                userIsValid = stmt2.executeQuery();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                        System.out.println("hash: " + getHashedPwd(passwd,salt));
                        if (userIsValid != null) {
                            if(!userIsValid.next()){
                                return "Email znám, špatné heslo.";
                            }else{
                                do {
                                    int id = userIsValid.getInt("id");
                                    main.setSignedUser(new DatabaseGetters().getUserById(id));
                                    Scene scene = new Scene(new MainWindow(main).getContent(), 300, 250);
                                    main.setScene(scene);
                                }while(userIsValid.next());
                            }
                            System.out.println(userIsValid.last());
                        }
                    }while(results.next());
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "Něco je špatně";
    }
    
    /**
     * proces při registraci.
     * Nejprve se zkontroluje, zda ještě není email použit
     * @param email email, který chce user používat
     * @param passwd heslo, které chce user používat
     * @return zda se podařilo uživatele založit
     */
    public boolean newUser(String email, String passwd) {
        if(email.equals("") || passwd.equals("")){
            return false;
        }
        try {
            PreparedStatement stmt = Database.getPrepStatement("SELECT * from users where email = ?");
            if (stmt != null) {
                stmt.setString(1, email);
                ResultSet results = stmt.executeQuery();
                //check if alredy exists
                if(results.next()){
                    return false;
                }else{
                    final Random r = new SecureRandom();
                    byte[] salt = new byte[32];
                    r.nextBytes(salt);
                    String encodedSalt = Base64.getEncoder().encodeToString(salt);
                    passwd = getHashedPwd(passwd, encodedSalt);
                    PreparedStatement stmtInsert = Database.getPrepStatement("INSERT INTO users (email, password, salt, admin, wantsToBeAdmin) values (? , ?, ?, 0, 0)");
                    if (stmtInsert != null) {
                        stmtInsert.setString(1, email);
                        stmtInsert.setString(2, passwd);
                        stmtInsert.setString(3, encodedSalt);
                        stmtInsert.executeUpdate();
                        System.out.println("New user "+email+" has just registered");
                        return true;
                    }


                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Hashuje heslo
     * @param passwordToHash
     * @param salt
     * @return zahashované heslo
     */
    private String getHashedPwd(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    
}
