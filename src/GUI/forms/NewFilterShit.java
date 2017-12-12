/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.forms;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import kavarny_dreamteam.*;

/**
 * třída vytváří okna možností pro filtraci kaváren
 * zároveň si pamatuje hodnoty, které uživatel do boxů uložil
 * @author rostaklein
 */
public class NewFilterShit {
    private final Main main;
    private TextField filterName;
    private ComboBox comboBox;
    private TextField filterRating;
    private String active;
    
    /**
     * constructor třídy s inicializací polí, do kterých
     * má uživatel možnst vyplňovat kritéria, dle kterých 
     * chce vyhledávat
     * Přiřazení hodnot do comboboxu setComboValues();
     * @param main Hlavní třída aplikace
     */
    public NewFilterShit(Main main) {
        this.main = main;
        filterName = new TextField();
        filterName.setPrefWidth(main.WINDOW_WIDTH/6);
        
        filterRating = new TextField();
        filterRating.setPrefWidth(main.WINDOW_WIDTH/12);
              
        comboBox = new ComboBox();
        
        active = "NaN";
    }
    
    
    /**
     * vytvoří selector pro filtraci dle názvu kavárny
     * @return flowPane s obsahem pro výběr
     */
    public FlowPane getNameOption() {
        FlowPane flowPane =  new FlowPane();
        flowPane.setPadding(new Insets(10, 0, 10, 0));
        
        Label label = new Label("zadejte název kavárny:");
        label.setPrefWidth((main.WINDOW_WIDTH/6)*5);
        
        flowPane.getChildren().addAll(label, filterName);
        
        active = "name";
        
        return flowPane;
    }
    
    /**
     * vytvoří selector pro filtraci dle města kavárny
     * @return flowPane s obsahem pro výběr
     */
    public FlowPane getCityOption() {
        FlowPane flowPane =  new FlowPane();
        flowPane.setPadding(new Insets(10, 0, 10, 0));
        
        Label label = new Label("zadejte město kavárny:");
        label.setPrefWidth((main.WINDOW_WIDTH/6)*5);
        
        flowPane.getChildren().addAll(label, comboBox);
        
        active = "city";
        
        return flowPane;
    }

    /**
     * vytvoří selector pro filtraci dle hodnocení kavárny
     * @return flowPane s obsahem pro výběr
     */
    public FlowPane getRatingOption() {
        FlowPane flowPane =  new FlowPane();
        flowPane.setPadding(new Insets(10, 0, 10, 0));
        
        Label label = new Label("zadejte hodnocení kavárny 1-5:");
        label.setPrefWidth((main.WINDOW_WIDTH/6)*5);
        
        flowPane.getChildren().addAll(label, filterRating);
        
        active = "rating";
        
        return flowPane;
    }

    /**
     * Vrací název aktivního filtru
     * Tedy na který z buttonů user kliknul
     * @return název aktivního filtru
     */
    public String getActive() {
        return active;
    }
    

    /**
     * pole ve kterém se nachází jméno filtrované kavárny
     * @return text field 
     */
    public TextField getFilterName() {
        return filterName;
    }
    
    /**
     * comboBox ve kterém se nachází města filtrované kavárny
     * @return text field 
     */
    public ComboBox getComboBox() {
        return comboBox;
    }

    /**
     * pole ve kterém se nachází rating filtrované kavárny
     * @return text field 
     */
    public TextField getFilterRating() {
        return filterRating;
    }

    
    
}
