/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kavarny_dreamteam;

import DB.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Třída kavárna představuje jednotlivou kavárnu
 * Přepravka, ve které se nesou informace o dané kavárně
 * @author rostaklein
 */
public class Cafes {

    //artibuty, které si o sobě kavárna pamatuje
    private int id;
    private String nazev;
    private String adresa;
    private String popis;
    private Timestamp added;
    private User user;
    
    
    /**
     * Defaultní konstruktor
     */
    public Cafes(){};

    
    /**
     * přetížený konstruktor, ve kterém se zadají všechny potřebné informace o nově založené
     * kavárně
     * @param id kavárny
     * @param nazev kavárny
     * @param adresa kavárny
     * @param popis  kavárny
     */
    public Cafes(int id, String nazev, String adresa, String popis, Timestamp added, User user) {
        this.id = id;
        this.nazev = nazev;
        this.adresa = adresa;
        this.popis = popis;
        this.added = added;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public Timestamp getAdded() {
        return added;
    }

    public User getUser() {
        return user;
    }
}
