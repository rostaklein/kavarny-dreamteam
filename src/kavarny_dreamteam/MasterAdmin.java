/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kavarny_dreamteam;

/**
 * Zvláštní třída uživatele pouze pro Master admina
 * důležitý je parametr signed, který je potvrzením, že
 * nenastala chyba a práva zůstanou pouze u master admina
 * přihlašovací údaje se nenacházejí v db, ale jsou napsány při
 * inicializaci jako parametry
 * @author rostaklein
 */
public class MasterAdmin {
    private String name;
    private String password;
    private boolean signed;

    /**
     * konstruktor, který vytvoří přihlašovací údaje pro master admina
     * @param name přihlašovací jméno
     * @param password přihlašovací heslo
     */
    public MasterAdmin(String name, String password) {
        this.name = name;
        this.password = password;
        signed = false;
    }

    /**
     * 
     * @return zda je Master admin přihlášen
     */
    public boolean isSigned() {
        return signed;
    }

    /**
     * nastaví, že se přihlásil masteradmin
     * @param signed true/false
     */
    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    /**
     * vrátí jméno masteradmina
     * @return masterAdmin name
     */
    public String getName() {
        return name;
    }

    /**
     * nastaví nové jméno pro masteradmina
     * @param name nové jméno
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return heslo masterAdmina
     */
    public String getPassword() {
        return password;
    }

    /**
     * nastaví nové heslo pro master admina
     * @param password nové heslo
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
