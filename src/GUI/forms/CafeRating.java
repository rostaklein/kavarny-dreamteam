/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.forms;

import DB.Database;
import GUI.CafeDetail;
import javafx.collections.FXCollections;
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
import java.util.Scanner;

/**
 * Třída slouží pro vytvoření formuláře pro založení nové kavárny
 * @author rostaklein
 */
public class CafeRating extends VBox{

    //message label pro komunikaci s uživatelem
    private Label message;
    private ChoiceBox ratingChoice;
    private TextArea ratingText;
    private Main main;
    private Cafes cafe;
    private CafeDetail cafeDetail;

    /**
     * inicializuje vše potřebné pro vytvoření formuláře
     */
    public CafeRating(kavarny_dreamteam.Cafes cafe, Main main, CafeDetail cafeDetail){
        this.main = main;
        this.cafe = cafe;
        this.cafeDetail = cafeDetail;
        boolean editMode = false;
        if(cafe != null){
            editMode = true;
        }
        Label message = new Label();
        message.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        ratingText = new TextArea();
        ratingText.setPromptText("Káva zde mi vůbec nechutnala.");
        //ratingText.setMaxWidth(this.getMaxWidth());
        ratingText.setMaxSize(200, 100);
        ratingChoice = new ChoiceBox<>();
        ratingChoice.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        ratingChoice.setValue(1);
        //Label ratingLabel = new Label("Počet hvězdiček");
        Label ratingTextLabel = new Label("Zanechte i Vy vzkaz:");
        ratingTextLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Button submit = new Button();
        submit.setText("Odeslat");

        VBox rating = new VBox();
        rating.getChildren().addAll(ratingChoice);
        rating.setSpacing(5);

        HBox submitline = new HBox();
        submitline.setSpacing(10);
        submitline.getChildren().setAll(rating, submit);
        submit.setOnAction(event -> {
            if(insertIntoDb()){
                ratingText.setText("");
                message.setText("Hodnocení uloženo.");
                cafeDetail.updateRatingListView();
            }else{
                message.setText("Něco se nepovedlo.");
            }
        });
        //vloží nadpis a message label
        this.setPadding(new Insets(20, 10, 10, 10));
        this.setSpacing(10);
        this.setAlignment(Pos.BOTTOM_LEFT);
        this.getChildren().addAll(ratingTextLabel, ratingText, submitline, message);
    }

    /**
     * Vkládá nová data z formuláře do databáze.
     * @return zda se operace povedla, nebo ne
     */
    private boolean insertIntoDb(){
        PreparedStatement preparedStatement = Database.getPrepStatement("INSERT into caferating (userId, cafeId, ratingInt, ratingText) VALUES (?, ?, ?, ?)");
        try {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, main.getSignedUser().getId());
                preparedStatement.setInt(2, cafe.getId());
                preparedStatement.setInt(3, new Scanner(ratingChoice.getValue().toString()).nextInt());
                preparedStatement.setString(4, ratingText.getText());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

}
