/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kavarny_dreamteam;

import GUI.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Hlavní třída aplikace zajišťuje prvotní inicializaci pamatuje si hlavní
 * proměnné nastavuje šířku okna
 *
 * @author netj01
 */
public class Main extends Application {

    private Scene scene;
    private UserManagement userManagement;
    private User signedUser;
    private MasterAdmin masterAdmin;

    public static final double WINDOW_WIDTH = 700;
    public static final double WINDOW_HEIGHT = 450;

    /**
     * spouštěcí metoda javaFx inicializuje program
     *
     * @param primary hlavní stage aplikace
     */
    @Override
    public void start(Stage primary) {
        masterAdmin = new MasterAdmin("master", "master");

        userManagement = new UserManagement(this);
        signedUser = null;
        boolean devmode = true;

        if(devmode){
            this.setSignedUser(0, "superadmin", true, false);
            scene = new Scene(new MainWindow(this).getContent(), 300, 250);
        }else{
            scene = new Scene(new LoginForm(this).getContent(), 300, 250);
        }


        primary.setTitle("Kavárny - Dream Team");
        primary.setScene(scene);
        primary.setWidth(WINDOW_WIDTH);
        primary.setHeight(WINDOW_HEIGHT);
        primary.show();

    }

    /**
     * vrací instanci třídy masteradmin
     *
     * @return masterAdmin
     */
    public MasterAdmin getSuperUser() {
        return masterAdmin;
    }

    /**
     *
     * @return třídu, která řídí přihlašovací proces
     */
    public UserManagement getUserManagement() {
        return userManagement;
    }

    public Background getBgImage(){
        BackgroundImage myBI= new BackgroundImage(
                new Image("/images/beans_bg.jpg",1280,800,true,true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1280, 800, false, false,true, true)
        );
        return new Background(myBI);
    }

    /**
     * nastaví novou scénu do stage a následně ji vykreslí
     *
     * @param scene nová scéna
     */
    public void setScene(Scene scene) {
        Stage stage = (Stage) this.scene.getWindow();
        stage.setScene(scene);
        stage.show();
        this.scene = scene;
    }

    /**
     * nastaví přihlášeného uživatele instance třídy User
     *
     * @param id uživatele
     * @param email uživatele
     * @param admin práva uživatele
     * @param wantsToBeAdmin stav uživatele
     */
    public void setSignedUser(int id, String email, boolean admin, boolean wantsToBeAdmin) {
        signedUser = new User(id, email, admin, wantsToBeAdmin);
    }

    /**
     *
     * @return přihlášeného uživatele
     */
    public User getSignedUser() {
        return signedUser;
    }

    /**
     * smaže hodnotu proměnné přihlášeného usera
     */
    public void signOutCurrentUser() {
        signedUser = null;
    }

    /**
     * Spouštěcí třída aplikace
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
