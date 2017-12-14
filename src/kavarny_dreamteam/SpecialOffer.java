/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kavarny_dreamteam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Třída speciální nabídky
 * @author Rostislav Klein
 */
public class SpecialOffer {


    private int id;
    private String name;
    private Timestamp start;
    private Timestamp until;

    public SpecialOffer(int id, String name, Timestamp start, Timestamp until) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.until = until;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getUntil() {
        return until;
    }
}