/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kavarny_dreamteam;

import java.sql.Timestamp;


/**
 * Vytvoří nové hodnocení pro určitou kavárnu a
 * toto následně uloží do listu
 * @author Rostislav Klein
 */
public class Coffees {


    private int id;
    private String name;
    private Cafes cafe;

    public Coffees(int id, String name, Cafes cafe) {
        this.id = id;
        this.name = name;
        this.cafe = cafe;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Cafes getCafe() {
        return cafe;
    }
}