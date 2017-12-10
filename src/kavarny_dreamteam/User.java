/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kavarny_dreamteam;

import DB.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Třída uživatel drží informace o přihlášeném uživateli
 * a zároveň jsou její instance použity v listu uživatelů
 * @author rostaklein
 */
public class User {
    
    private int id;
    private String email;
    private boolean admin;
    private boolean wantsToBeAdmin;
    
    /**
     * Konstruktor inicializuje všechny informace, které můžeme o uživateli znát
     * každá z nich je důležita
     * @param id uživatele
     * @param email uživatele
     * @param admin  uživatele
     * @param wantsToBeAdmin stav uživatele
     */
   public User(int id, String email, boolean admin, boolean wantsToBeAdmin) {
        this.id = id;
        this.email = email;
        this.admin = admin;
        this.wantsToBeAdmin = wantsToBeAdmin;
    }
    
   /**
    * 
    * @return wantsToBeAdmin value
    */
    public boolean getWantsToBeAdmin() {
        return wantsToBeAdmin;
    }

    /**
     * nastavuje nový stav uživatele v rámci wantsToBeAdmin
     * @param wantsToBeAdmin stav uživatele
     */
    public void setWantsToBeAdmin(boolean wantsToBeAdmin) {
        this.wantsToBeAdmin = wantsToBeAdmin;
    }
    
   /**
    * 
    * @return id uživatele
    */
    public int getId() {
        return id;
    }


    /**
     * varcí jméno (email) uživatele
     * @return email
     */
    public String getEmail() {
        return email;
    }


    /**
     * vrátí hodnotu práv pro uživatele
     * @return míra práv
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Super adminem je pouze uživatel s id 0.
     * Tento uživatel může schvalovat žádosti o admina.
     * @return true pokud je user id rovno nule
     */
    public boolean isSuperAdmin(){
        return this.id == 1;
    }

    
}
