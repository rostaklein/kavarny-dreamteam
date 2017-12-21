/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.Database;
import DB.DatabaseGetters;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import kavarny_dreamteam.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * layout pro registraci nového užiatele
 * Vytvoří labels a Text fields pro zadní nového emailu a hesla
 * @author rostaklein
 */
public class RegistrationForm {
    
    private Main main;
    private StackPane pane;
    private TextField email;
    private PasswordField passwd_1;
    private PasswordField passwd_2;
    private Label message = new Label("");

    /**
     * konstruktor pro inicializaci registrace
     * spustí metodu pro vytvoření zobrazení
     * @param main hlavní třída
     */
    RegistrationForm(Main main) {
        this.main = main;
        createRegistration();
    }

    /**
     * vytvoří textfieldy a labely pro zadání přihlašovacích údajů
     * nastaví eventHandlers pro tlačítka
     */
    private void createRegistration() {
        VBox vbox = new VBox(20);
        pane = new StackPane();
        Font headingFontStyle = Font.font("Helvetica", FontWeight.BOLD, 20);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        pane.setPrefSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(main.getBgImage());
        
                
        //Defining the registration text fields
        email = new TextField();
        passwd_1 = new PasswordField();
        passwd_2 = new PasswordField();
        
        HBox buttons = new HBox();
        //Defining the Submit button
        Button register = new Button("Registrovat");
        Button backToLogin = new Button("Zpět na přihlášení");
        
        
        email.setPromptText("pepiknovak@seznam.cz");
        passwd_1.setPromptText("Vaše tajné heslo");
        passwd_2.setPromptText("Zopakujte vaše heslo");
        passwd_1.setDisable(true);
        passwd_2.setDisable(true);

        email.setOnKeyPressed(event -> {
            if(!email.getText().matches("^\\S+@\\S+$")){
                message.setText("Tohle není validní email. Správný email má v sobě zavináč!");
            }else{
                passwd_1.setDisable(false);
                message.setText("");
            }
        });

        passwd_1.setOnKeyPressed(event -> {
            if(!passwd_1.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{5,}$")){
                message.setText("Heslo musí obsahovat alespoň jedno číslo a mít alespoň pět znaků!");
                passwd_2.setDisable(true);
            }else{
                passwd_2.setDisable(false);
                message.setText("");
            }
        });




        backToLogin.setOnAction(event -> {
            Scene scene = new Scene(new LoginForm(main).getContent(), 300, 250);
            main.setScene(scene);
        });

        register.setOnAction(event -> submitRegister()); //register by clicking button
        passwd_2.setOnAction(event -> submitRegister()); //Register by hiting enter

        VBox regForm = new VBox();
        regForm.getChildren().addAll(new Label("Váš email"), email, new Label("Nové heslo"), passwd_1, new Label("Zopakujte nové heslo"), passwd_2);
        regForm.setSpacing(10);
        regForm.setPadding(new Insets(30, 0, 0, 0));
        regForm.setMaxWidth(250);

        Text heading = new Text("Přidej se k nám!");
        heading.setTextAlignment(TextAlignment.CENTER);
        heading.setFont(headingFontStyle);

        vbox.getChildren().addAll(heading, regForm, buttons, message, register, backToLogin);
        pane.getChildren().addAll(vbox);
        
    }

    /**
     * 
     * @return layout pro scénu
     */
    StackPane getContent() {
        return pane;
    }


    /**
     * Odesílá registraci do user managementu, pokud se něco nepovede, nastaví hlášku labelu.
     */
    private void submitRegister() {
            //check if not empty
            if(email.getText().equals("") || passwd_1.getText().equals("") || passwd_2.getText().equals("")){
                    message.setText("Nutno vyplnit email i obě hesla.");
            }
            //check stejná hesla
            else if(passwd_1.getText().equals(passwd_2.getText())){
                //try to insert user into db
                if(main.getUserManagement().newUser(email.getText(), passwd_1.getText())){
                    main.getUserManagement().logIn(email.getText(), passwd_1.getText());
                }
                else{
                    message.setText("Tento email je již obsazený.");
                }
            }
            else{
                message.setText("Hesla bohužel nesouhlasí.");
            }
    }
    
}
