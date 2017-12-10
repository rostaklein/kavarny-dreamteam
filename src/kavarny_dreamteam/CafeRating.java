/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kavarny_dreamteam;

import DB.Database;
import DB.DatabaseGetters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Vytvoří nové hodnocení pro určitou kavárnu a
 * toto následně uloží do listu
 * @author Jakub Mareš
 */
public class CafeRating {
    
   
    private int id;
    private String ratingText;
    private int ratingInt;
    private User user;
    private Cafes cafe;

    /**
     * konstruktor který inicializuje listy a přiřadí 
     * proměnné cafeId hodnotu idčka konkrétní kavárny
     * @param
     */
    public CafeRating(int id, String ratingText, int ratingInt, User user, Cafes cafe) {
        this.id = id;
        this.ratingText = ratingText;
        this.ratingInt = ratingInt;
        this.user = user;
        this.cafe = cafe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public int getRatingInt() {
        return ratingInt;
    }

    public void setRatingInt(int ratingInt) {
        this.ratingInt = ratingInt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cafes getCafe() {
        return cafe;
    }

    public void setCafe(Cafes cafe) {
        this.cafe = cafe;
    }
}