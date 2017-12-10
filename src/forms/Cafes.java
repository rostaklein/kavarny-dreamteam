/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DB.Database;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Třída slouží pro vytvoření formuláře pro založení nové kavárny
 * @author rostaklein
 */
public class Cafes extends FlowPane{
    
    //message label pro komunikaci s uživatelem
    private Label message;
    private TextField nazev;
    private TextField adresa;
    private TextArea popis;


    /**
     * inicializuje vše potřebné pro vytvoření formuláře
     */
    public Cafes(kavarny_dreamteam.Cafes cafe){
        boolean editMode = false;
        if(cafe != null){
            editMode = true;
        }
        VBox vbox = new VBox();
        nazev = new TextField();
        adresa = new TextField();
        popis = new TextArea();
        Text heading = new Text();
        Button submit = new Button();
        
        //message label pro komunikaci s uživatelem
        message = new Label("");

        heading.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
        vbox.getChildren().add(heading);
        vbox.setPadding(new Insets(10, 5, 5, 10));

        Label labelNazev = new Label("Název kavárny:");
        nazev.setPromptText("Kavárna u dvou much");
        Label labelAdresa = new Label("Adresa:");
        adresa.setPromptText("U Hrocha 13, Praha 4");
        Label labelPopis = new Label("Popis kavárny:");
        popis.setPromptText("Doporučujeme přidat zajímavý popis.");
        vbox.setSpacing(5);

        if(editMode){
            heading.setText("Editujete kavárnu - "+cafe.getNazev());
            nazev.setText(cafe.getNazev());
            adresa.setText(cafe.getAdresa());
            popis.setText(cafe.getPopis());
            submit.setText("Uložit změny");
        }else{
            heading.setText("Přidejte novou kavárnu");
            submit.setText("Přidat kavárnu");
        }

        vbox.getChildren().addAll(labelNazev, nazev, labelAdresa, adresa, labelPopis, popis);


        boolean finalEditMode = editMode;
        submit.setOnAction(event -> {
            if(nazev.getText().isEmpty() || adresa.getText().isEmpty() || popis.getText().isEmpty()){
                message.setText("Nutno vyplnit všechna pole.");
            }else{
                if(finalEditMode){
                    if(updateInDb(cafe.getId())){
                        message.setText("Změny v kavárně byla úspěšně uloženy.");
                    }else{
                        message.setText("Něco se nepovedlo. Změny v kavárně nebyly uloženy.");
                    }
                }else{
                    if(insertIntoDb()){
                        message.setText("Kavárna byla úspěšně přidána.");
                        nazev.setText("");
                        adresa.setText("");
                        popis.setText("");
                    }else{
                        message.setText("Něco se nepovedlo. Kavárna nebyla vložena.");
                    }
                }

            }
        });

        HBox submitLine = new HBox();
        submitLine.getChildren().addAll(message, submit);
        submitLine.setSpacing(20);
        submitLine.setAlignment(Pos.CENTER_RIGHT);

        vbox.getChildren().addAll(submitLine);
        //vloží nadpis a message label
        this.getChildren().addAll(vbox);
    }

    /**
     * Vkládá nová data z formuláře do databáze.
     * @return zda se operace povedla, nebo ne
     */
    private boolean insertIntoDb(){
        PreparedStatement preparedStatement = Database.getPrepStatement("INSERT into kavarny (name, adress, description) VALUES (?, ?, ?)");
        try {
            if (preparedStatement != null) {
                preparedStatement.setString(1, nazev.getText());
                preparedStatement.setString(2, adresa.getText());
                preparedStatement.setString(3, popis.getText());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Vkládá nová data z formuláře do databáze.
     * @return zda se operace povedla, nebo ne
     */
    private boolean updateInDb(int id){
        PreparedStatement preparedStatement = Database.getPrepStatement("UPDATE kavarny SET name=?, adress=?, description=? where id="+id);
        try {
            if (preparedStatement != null) {
                preparedStatement.setString(1, nazev.getText());
                preparedStatement.setString(2, adresa.getText());
                preparedStatement.setString(3, popis.getText());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

}
