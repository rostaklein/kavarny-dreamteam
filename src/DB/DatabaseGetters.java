package DB;

import kavarny_dreamteam.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseGetters {

    /**
     * Vrací ArrayList všech kaváren.
     * @return všechny kavárny z databáze
     */
    public ArrayList<Cafes> getAllCafes(){
        ArrayList<Cafes> seznamKavaren = new ArrayList<>();
        ResultSet kavarny;
        try {
            kavarny= Database.getPrepStatement("SELECT * FROM kavarny").executeQuery();
            while(kavarny.next()){
                seznamKavaren.add(
                        new Cafes(
                                kavarny.getInt("id"),
                                kavarny.getString("name"),
                                kavarny.getString("adress"),
                                kavarny.getString("description"),
                                kavarny.getTimestamp("added"),
                                new DatabaseGetters().getUserById(kavarny.getInt("userId"))
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seznamKavaren;
    }

    /**
     * @param value kus textu, který vyhledáváme ve jméně kavárny
     * @return list kaváren, které vyhovují vyhledávacímu kritériu
     */
    public ArrayList<Cafes> findCafeByName(String value){
        ArrayList<Cafes> seznamKavaren = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getPrepStatement("SELECT * FROM kavarny where name like ?");
            if (ps != null) {
                ps.setString(1, "%"+value+"%");
            }
            ResultSet kavarny = ps.executeQuery();
            while(kavarny.next()){
                seznamKavaren.add(
                        new Cafes(
                                kavarny.getInt("id"),
                                kavarny.getString("name"),
                                kavarny.getString("adress"),
                                kavarny.getString("description"),
                                kavarny.getTimestamp("added"),
                                new DatabaseGetters().getUserById(kavarny.getInt("userId"))
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seznamKavaren;
    }

    /**
     * Připraveno, zatím nepoužito.
     * @param value minimální hodnocení kavárny
     * @return list kaváren, které vyhovují kritériu
     */
    public ArrayList<Cafes> findCafeByRating(Float value){
        ArrayList<Cafes> seznamKavaren = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getPrepStatement("select * from kavarny join caferating on(kavarny.id=caferating.cafeId) group by kavarny.id having avg(ratingInt)>?");
            if (ps != null) {
                ps.setFloat(1, value);
            }
            ResultSet kavarny = ps.executeQuery();
            while(kavarny.next()){
                seznamKavaren.add(
                        new Cafes(
                                kavarny.getInt("id"),
                                kavarny.getString("name"),
                                kavarny.getString("adress"),
                                kavarny.getString("description"),
                                kavarny.getTimestamp("added"),
                                new DatabaseGetters().getUserById(kavarny.getInt("userId"))
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seznamKavaren;
    }

    /**
     * Pro ostatní třídy vrací uživatele podle jeho id,
     * @param id
     * @return uživatel dle zadaného id
     */
    public User getUserById(int id){
        try {
            ResultSet rs = Database.getPrepStatement("SELECT * from users where id="+id).executeQuery();
            while(rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getBoolean("admin"),
                        rs.getBoolean("wantsToBeAdmin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Pro ostatní třídy vrací kavárnu dle jejího id.
     * @param id id kavárny
     * @return kavárna dle zadaného id
     */
    public Cafes getCafeById(int id){
        try {
            ResultSet rs = Database.getPrepStatement("SELECT * from kavarny where id="+id).executeQuery();
            while(rs.next()){
                return new Cafes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("adress"),
                        rs.getString("description"),
                        rs.getTimestamp("added"),
                        new DatabaseGetters().getUserById(rs.getInt("userId"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param cafe pro jakou kavárnu hledám hodnocení
     * @return vrací list všech hodnocení k zadané kavárně
     */
    public ArrayList<CafeRating> getAllRatingsByCafe(Cafes cafe){
        ArrayList<CafeRating> ratingsFor = new ArrayList<>();
        try {
            ResultSet rs = Database.getPrepStatement("SELECT * from caferating where cafeid="+cafe.getId()).executeQuery();
            while(rs.next()) {
                ratingsFor.add(new CafeRating(
                        rs.getInt("id"),
                        rs.getString("ratingText"),
                        rs.getInt("ratingInt"),
                        new DatabaseGetters().getUserById(rs.getInt("userId")),
                        cafe,
                        rs.getTimestamp("added")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratingsFor;
    }

    /**
     * @param cafe pro jakou kavárnu chci zobrazit kávy
     * @return vrací list všech hodnocení k dané kavárně
     */
    public ArrayList<SpecialOffer> getAllSpecialOfffersByCafe(Cafes cafe){
        ArrayList<SpecialOffer> offersFor = new ArrayList<>();
        try {
            ResultSet rs = Database.getPrepStatement("SELECT * from special_offers where cafeid="+cafe.getId()).executeQuery();
            while(rs.next()) {
                offersFor.add(new SpecialOffer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("start"),
                        rs.getTimestamp("until")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offersFor;
    }

    /**
     * @param cafe pro jakou kavárnu chci zobrazit kávy
     * @return vrací list všech káv nabízených v dané kavárně
     */
    public ArrayList<Coffees> getAllCoffesByCafe(Cafes cafe){
        ArrayList<Coffees> coffees = new ArrayList<>();
        try {
            ResultSet rs = Database.getPrepStatement("SELECT * from coffees where cafeid="+cafe.getId()).executeQuery();
            while(rs.next()) {
                coffees.add(new Coffees(
                        rs.getInt("id"),
                        rs.getString("name"),
                        cafe
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coffees;
    }

    /**
     * Vrací hodnocení dané kavárny
     * @param cafe kavárna, u které zjišťujeme hodnocení
     * @return hodnocení kavárny
     */
    public Float getOverallRating(Cafes cafe){
        Float rating = 0f;
        try {
            ResultSet rs = Database.getPrepStatement("select avg(ratingInt) as rating from kavarny join caferating on(kavarny.id=caferating.cafeId) where kavarny.id="+cafe.getId()+" group by kavarny.id").executeQuery();
            while(rs.next()) {
                rating=rs.getFloat("rating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rating;
    }
}
