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
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
    private Timestamp added;

    /**
     * Vytvoří nový CafeRating
     * @param id id cafe ratingu
     * @param ratingText text
     * @param ratingInt hodnocení
     * @param user uživatel, který hodnocení udělil
     * @param cafe kavárna, ke které se hodnocení vztahuje
     * @param added kdy bylo přidáno
     */
    public CafeRating(int id, String ratingText, int ratingInt, User user, Cafes cafe, Timestamp added) {
        this.id = id;
        this.ratingText = ratingText;
        this.ratingInt = ratingInt;
        this.user = user;
        this.cafe = cafe;
        this.added = added;
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

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    public String getFormatedAdded(){
        Date addedOn = new Date();
        addedOn.setTime(this.getAdded().getTime());
        return new SimpleDateFormat("dd. MM. YYYY k:m").format(addedOn);
    }
}