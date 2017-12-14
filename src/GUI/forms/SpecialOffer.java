/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.forms;

import DB.Database;
import GUI.CafeDetail;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kavarny_dreamteam.Cafes;
import kavarny_dreamteam.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Třída slouží pro vytvoření formuláře pro založení nové kavárny
 * @author rostaklein
 */
public class SpecialOffer extends VBox{

    private TextField name;
    private Main main;
    private Cafes cafe;
    private final LocalDate[] from = {LocalDate.now()};
    private final LocalDate[] until = {LocalDate.now()};
    /**
     * inicializuje vše potřebné pro vytvoření formuláře
     */
    public SpecialOffer(Cafes cafe, Main main, CafeDetail cafeDetail){
        this.main = main;
        this.cafe = cafe;
        Label message = new Label();

        message.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label nazev = new Label("Přidat speciální akci:");
        nazev.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        name = new TextField();
        name.setPromptText("Název akce");

        DatePicker fromPick = new DatePicker();
        fromPick.setPromptText("Začátek akce");
        fromPick.setPrefWidth(150);
        fromPick.setOnAction(event -> {
            from[0] = fromPick.getValue();
            if(from[0].compareTo(LocalDate.now())<0){
                message.setText("Akce nemůže začínat v minulosti!");
                fromPick.setValue(LocalDate.now());
            }
        });

        DatePicker toPick = new DatePicker();
        toPick.setPromptText("Konec akce");
        toPick.setPrefWidth(150);
        toPick.setOnAction(event -> {
            until[0] = toPick.getValue();
            if(until[0].compareTo(from[0])<0){
                message.setText("Akce nemůže končit dříve než začala!");
                toPick.setValue(from[0].plusDays(0));
            }
        });

        Button submit = new Button();
        submit.setText("Vytvořit");

        submit.setOnAction(event -> {
            if(name.getText().isEmpty()){
                message.setText("Musíte zadat název akce!");
            }else if(!insertIntoDb()){
                message.setText("Něco se nepovedlo.");
            }else{
                fromPick.getEditor().clear();
                toPick.getEditor().clear();
                name.setText("");
                message.setText("Nabídka přidána.");
                cafeDetail.updateCenter();
            }
        });

        HBox dates = new HBox();
        dates.getChildren().addAll(name, fromPick, toPick, submit);
        dates.setSpacing(10);

        this.setPadding(new Insets(20, 0, 10, 0));
        this.setSpacing(10);
        this.setAlignment(Pos.BOTTOM_LEFT);
        this.getChildren().addAll(nazev, name, dates, message);
    }

    /**
     * Vkládá nová data z formuláře do databáze.
     * @return zda se operace povedla, nebo ne
     */
    private boolean insertIntoDb(){
        PreparedStatement preparedStatement = Database.getPrepStatement("INSERT into special_offers (cafeId, name, start, until) VALUES (?, ?, ?, ?)");
        try {
            if (preparedStatement != null) {
                Timestamp fromTime = Timestamp.valueOf(from[0].atStartOfDay());
                Timestamp toTime = Timestamp.valueOf(until[0].atStartOfDay());
                preparedStatement.setInt(1, cafe.getId());
                preparedStatement.setString(2, name.getText());
                preparedStatement.setTimestamp(3, fromTime);
                preparedStatement.setTimestamp(4, toTime);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
