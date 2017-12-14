package GUI;

import DB.DatabaseGetters;
import GUI.forms.CafeRating;
import GUI.forms.SpecialOffer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import kavarny_dreamteam.Cafes;
import kavarny_dreamteam.Main;

import java.util.ArrayList;

/**
 * Vytváří detail kavárny. Pouze vypisuje na samotném screenu informace o ní.
 */
public class CafeDetail extends BorderPane{
    private Cafes cafe;
    private VBox ratingListView;
    private VBox specialOffersView;
    private Main main;
    private HBox coffeesAndOffers;
    /**
     * Vytvoři VBox s informacemi o dané kavárně.
     * @param cafe
     */
    public CafeDetail(Cafes cafe, Main main){
        this.cafe = cafe;
        this.main = main;
        Label nazev = new Label(cafe.getNazev());
        nazev.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nazev.setPadding(new Insets(0, 0, 5, 0));
        nazev.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: #5c5c5c;");

        HBox added = new HBox();
        added.setSpacing(20);
        Label addedOnValue = new Label(cafe.getFormatedAdded());
        Label addedBy = new Label(cafe.getUser().getEmail());
        addedOnValue.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        addedBy.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label labelAddedOn = new Label("Přidáno: ");
        Label labelAddedBy =  new Label("Přidal uživatel: ");

        added.getChildren().addAll(labelAddedOn, addedOnValue, labelAddedBy, addedBy);
        added.setStyle("-fx-opacity: 0.4");
        added.setPadding(new Insets(15, 0, 0, 0));
        this.setPadding(new Insets(10, 20, 10, 20));

        VBox topBox = new VBox();
        topBox.getChildren().addAll(nazev, added);
        this.setTop(topBox);

        updateCenter();
        updateRatingListView();
    }

    public VBox getCenterInfo(){
        VBox centerInfo = new VBox();
        Label labelAdresa = new Label("Najdete nás na:");
        labelAdresa.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        labelAdresa.setPadding(new Insets(20, 0, 0, 0));
        Label adresa = new Label(cafe.getAdresa());

        Label labelDescription = new Label("O nás:");
        labelDescription.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        labelDescription.setPadding(new Insets(20, 0, 0, 0));
        Label description = new Label(cafe.getPopis());

        centerInfo.getChildren().setAll(labelAdresa, adresa, labelDescription, description);

        return centerInfo;
    }

    public void updateCenter(){
        coffeesAndOffers = new HBox();
        SpecialOffer specOffers = new SpecialOffer(cafe, main, this);
        coffeesAndOffers.getChildren().addAll(specOffers);
        VBox centerBox = new VBox();
        centerBox.getChildren().addAll(getCenterInfo(), getSpecialOffersView(), coffeesAndOffers);
        this.setCenter(centerBox);
    }



    public void updateRatingListView(){
        this.ratingListView = new VBox();
        ArrayList<kavarny_dreamteam.CafeRating> ratingList = new DatabaseGetters().getAllRatingsByCafe(this.cafe);
        ratingList.forEach((rating)->{
            VBox topLine = new VBox();
            Label label = new Label(rating.getUser().getEmail());
            Label time = new Label(main.getFormatedDate(rating.getAdded(), "dd. MM. YYYY k:m"));
            Label ratingLabel = new Label(rating.getRatingInt()+" *");
            label.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            time.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
            ratingLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 14));
            topLine.getChildren().addAll(label, time);
            Text text = new Text(rating.getRatingText());
            text.setWrappingWidth(200);
            if(text.getText().isEmpty()){
                text.setText("--- bez vzkazu ---");
                text.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
                text.setStyle("-fx-opacity: 0.4;");
            }

            topLine.setStyle("-fx-opacity: 0.4;");

            HBox flow = new HBox();
            flow.setPrefWidth(Main.WINDOW_WIDTH/7);
            flow.setStyle("-fx-border-width: 1 0 0 0; -fx-border-color: #d4d4d4;");
            flow.setSpacing(20);
            flow.getChildren().addAll(ratingLabel, topLine);
            flow.setAlignment(Pos.CENTER_LEFT);
            this.ratingListView.getChildren().addAll(flow, text);
        });
        this.ratingListView.setPadding(new Insets(10, 0, 0, 10));
        this.ratingListView.setSpacing(10);

        Label labelRating = new Label("Hodnocení a vzkazy:");
        labelRating.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        labelRating.setPadding(new Insets(20, 0, 0, 0));

        VBox rightBox = new VBox();
        rightBox.setSpacing(10);
        rightBox.setAlignment(Pos.TOP_LEFT);
        rightBox.getChildren().setAll(labelRating, ratingListView, new CafeRating(cafe, main, this));

        this.setRight(rightBox);
    }

    public VBox getSpecialOffersView(){
        VBox specialOffersView = new VBox();
        ArrayList<kavarny_dreamteam.SpecialOffer> offersList = new DatabaseGetters().getAllSpecialOfffersByCafe(this.cafe);

        Label labelOffers = new Label("Speciální nabídky:");
        labelOffers.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        labelOffers.setPadding(new Insets(20, 0, 0, 0));
        specialOffersView.getChildren().add(labelOffers);

        if(offersList.size()==0){
            Label label = new Label("Tato kavárna zatím nemá žádnou speciální nabídku;");
            label.setPadding(new Insets(20));
            label.setStyle("-fx-opacity: 0.4");
            specialOffersView.getChildren().add(label);
        }else{
            offersList.forEach((offer)->{
                FlowPane flow = new FlowPane();
                Label name = new Label(offer.getName());
                name.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                name.setAlignment(Pos.CENTER_LEFT);
                Label duration = new Label("Trvá od "
                        +main.getFormatedDate(offer.getStart(), "dd. MM. YYYY")
                        +" do "
                        +main.getFormatedDate(offer.getUntil(), "dd. MM. YYYY"));
                duration.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
                duration.setStyle("-fx-opacity: 0.4;");
                duration.setAlignment(Pos.CENTER_LEFT);
                duration.setPadding(new Insets(0, 0, 0, 20));
                flow.getChildren().setAll(name, duration);
                flow.setStyle("-fx-border-width: 1 0 0 0; -fx-border-color: #d4d4d4;");
                flow.setPadding(new Insets(10, 0, 10, 0));
                flow.setAlignment(Pos.CENTER_LEFT);
                specialOffersView.getChildren().addAll(flow);
            });
        }
        specialOffersView.setSpacing(5);

        return specialOffersView;

    }
}
