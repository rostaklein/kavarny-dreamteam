/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DatabaseGetters;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import kavarny_dreamteam.Main;

/**
 * Okno pro login uživatele
 * @author rostaklein
 */
public class LoginForm {
    private Main main;
    private Scene scene;

    private TextField email;
    private PasswordField pass;
    private StackPane pane;

    private Label message = new Label("");

    /**
     * @param main hlavní třída
     */
    public LoginForm(Main main){
        this.main = main;
        createRegistration(); 
    }

    /**
     * Vytvoření polí, labelů
     */
    private void createRegistration() {

        VBox vbox = new VBox();
        pane = new StackPane();
        Font headingFontStyle = Font.font("Helvetica", FontWeight.BOLD, 20);

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        
        pane.setPrefSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        vbox.setAlignment(Pos.CENTER);
        
        email = new TextField();
        pass = new PasswordField();
        email.setPromptText("pepiknovak@seznam.cz");
        pass.setPromptText("Vaše tajné heslo");
        
        Button logIn = new Button("Přihlásit");

        VBox logBox = new VBox();
        Text heading = new Text("U nás najdeš tu správnou kavárnu!");
        heading.setTextAlignment(TextAlignment.CENTER);
        heading.setFont(headingFontStyle);
        logBox.getChildren().addAll(
                new Label("Váš email"),
                email,
                new Label("Vaše heslo"),
                pass);
        logBox.setSpacing(10);
        logBox.setPadding(new Insets(30, 0, 0, 0));
        logBox.setMaxWidth(250);

        VBox regBox = new VBox();
        regBox.setSpacing(20);
        regBox.setPadding(new Insets(30, 0, 30, 0));
        regBox.setAlignment(Pos.CENTER);
        Text regText = new Text("Nejsi členem?");
        regText.setFont(headingFontStyle);

        Button register = new Button("Chci se registrovat");

        regBox.getChildren().addAll(regText, register);

        vbox.getChildren().addAll(
                heading,
                logBox,
                message,
                logIn,
                regBox
        );
        vbox.setBackground(main.getBgImage());
        pane.getChildren().addAll(vbox);
        pane.setBorder(Border.EMPTY);

        //when clicking register, get new scene with register content
        register.setOnAction(event -> {
            scene = new Scene(new RegistrationForm(main).getContent(), Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            main.setScene(scene);
        });

        //button click handler
        logIn.setOnAction(event -> logInCheck());
        //enter in password field handler
        pass.setOnAction(event -> logInCheck());
        
    }

    public StackPane getContent() {
        return pane;
    }

    private void logInCheck() {
        //spustí metodu confirm pro přihlášení
        String loginStatus = main.getUserManagement().logIn(email.getText(), pass.getText());
        message.setText(loginStatus);
        message.setTextFill(Paint.valueOf("RED"));
        message.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        //přilášení masterAdmina
        if (email.getText().equals(main.getSuperUser().getName()) && pass.getText().equals(main.getSuperUser().getPassword())) {
            main.getSuperUser().setSigned(true);
            main.setSignedUser(new DatabaseGetters().getUserById(1));
            Scene scene = new Scene(new MainWindow(main).getContent(), 300, 250);
            main.setScene(scene);
        }

    }
}
