package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import kavarny_dreamteam.Cafes;
import kavarny_dreamteam.Main;

import java.util.ArrayList;

/**
 * Vytváří list kaváren ze zadaného pole kaváren.
 */
class KavarnyList extends VBox{
    private VBox allItems = new VBox();
    /**
     * Vytváří list kaváren ze zadaného pole kaváren.
     * @param cafes jaké kavárny bude zobrazovat
     * @param mainWindow kvůli přenastavování contentu ve scrollpane
     * @param main kvůli tomu, aby zjistil, kdo je momentálně přihlášen
     */
    KavarnyList(ArrayList<Cafes> cafes, MainWindow mainWindow, Main main, String title) {
        if(title.isEmpty()){
            title="Všechny kavárny";
        }

        Label label = new Label(title);
        label.setPadding(new Insets(10, 0, 10, 0));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        this.getChildren().add(label);
        if(cafes.isEmpty()){
            Label empty = new Label("Bohužel, žádná kavárna nevyhovuje vašemu výběru.");
            empty.setPadding(new Insets(20));
            empty.setAlignment(Pos.CENTER);
            this.getChildren().add(empty);
        }else{
            cafes.forEach((kavarna) -> {
                        Text nazevKavarny = new Text(kavarna.getNazev());
                        nazevKavarny.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                        Text adresa = new Text(kavarna.getAdresa());
                        Button detailButton = new Button("Detail");
                        detailButton.setOnAction(event -> {
                            mainWindow.getScroll().setContent(new CafeDetail(kavarna, main));
                        });
                        VBox layout = new VBox();
                        HBox buttons = new HBox();
                        buttons.getChildren().addAll(detailButton);
                        buttons.setSpacing(5);
                        if(main.getSignedUser().isAdmin()){
                            Button editButton = new Button("Editovat");
                            editButton.setOnAction(event -> {
                                mainWindow.getScroll().setContent(new GUI.forms.Cafes(kavarna, main));
                                System.out.println("Uživatel chce editovat kavárnu "+kavarna.getNazev());
                            });
                            buttons.getChildren().add(editButton);
                        }

                        layout.getChildren().addAll(nazevKavarny, adresa, buttons);
                        layout.setSpacing(7);
                        layout.setPadding(new Insets(10, 0, 10, 0));
                        layout.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: #e4e4e4;");
                        allItems.getChildren().add(layout);

                    }
            );
        }
        this.getChildren().add(allItems);
        this.setPadding(new Insets(0, 20, 20, 20));
    }
}
