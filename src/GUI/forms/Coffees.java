package GUI.forms;

import DB.Database;
import GUI.CafeDetail;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kavarny_dreamteam.Cafes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Coffees extends VBox{
    private TextField name;
    private Cafes cafe;
    private Label message;
    private CafeDetail cafeDetail;

    public Coffees(Cafes cafe, CafeDetail cafeDetail){
        this.cafe = cafe;
        this.cafeDetail = cafeDetail;
        message = new Label();
        message.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label nazev = new Label("Přidat nabízený druh kávy:");
        nazev.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        name = new TextField();
        name.setPromptText("Název kávy");

        Button submit = new Button();
        submit.setText("Přidat kávu");
        submit.setOnAction(event -> insertIntoDb());
        name.setOnAction(event -> insertIntoDb());

        HBox formLine = new HBox();
        formLine.getChildren().addAll(name, submit);
        formLine.setSpacing(10);

        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: #e4e4e4;");
        this.setSpacing(10);
        this.setAlignment(Pos.BOTTOM_LEFT);
        this.getChildren().addAll(nazev, name, formLine, message);
    }


    /**
     * Vkládá nová data z formuláře do databáze.
     */
    private void insertIntoDb(){
        if(name.getText().isEmpty()){
            message.setText("Zadejte jméno kávy!");
        }else{
            PreparedStatement preparedStatement = Database.getPrepStatement("INSERT into coffees (cafeId, name) VALUES (?, ?)");
            try {
                if (preparedStatement != null) {
                    preparedStatement.setInt(1, cafe.getId());
                    preparedStatement.setString(2, name.getText());
                    preparedStatement.executeUpdate();
                    cafeDetail.updateCenter();
                    System.out.println("Přidávám novou kávu "+name.getText()+" pro "+cafe.getNazev());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                message.setText("Něco se nepovedlo.");
            }
        }
    }
}
