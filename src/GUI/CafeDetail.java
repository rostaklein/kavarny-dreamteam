package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kavarny_dreamteam.Cafes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Vytváří detail kavárny. Pouze vypisuje na samotném screenu informace o ní.
 */
class CafeDetail extends VBox{
    /**
     * Vytvoři VBox s informacemi o dané kavárně.
     * @param cafe
     */
    CafeDetail(Cafes cafe){
        Label nazev = new Label(cafe.getNazev());
        nazev.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nazev.setPadding(new Insets(0, 0, 5, 0));
        nazev.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: #5c5c5c;");

        HBox added = new HBox();
        added.setSpacing(20);
        Date addedOn = new Date();
        addedOn.setTime(cafe.getAdded().getTime());
        Label addedOnValue = new Label(new SimpleDateFormat("dd. MM. YYYY").format(addedOn));
        Label addedBy = new Label(cafe.getUser().getEmail());
        addedOnValue.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        addedBy.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label labelAddedOn = new Label("Přidáno: ");
        Label labelAddedBy =  new Label("Přidal uživatel: ");

        added.getChildren().addAll(labelAddedOn, addedOnValue, labelAddedBy, addedBy);
        added.setStyle("-fx-opacity: 0.4");
        added.setPadding(new Insets(15, 0, 0, 0));

        Label labelAdresa = new Label("Najdete nás na:");
        labelAdresa.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        labelAdresa.setPadding(new Insets(20, 0, 0, 0));
        Label adresa = new Label(cafe.getAdresa());

        Label labelDescription = new Label("O nás:");
        labelDescription.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        labelDescription.setPadding(new Insets(20, 0, 0, 0));
        Label description = new Label(cafe.getPopis());

        this.setPadding(new Insets(10, 20, 10, 20));
        this.getChildren().addAll(nazev, added, labelAdresa, adresa, labelDescription, description);
    }
}
