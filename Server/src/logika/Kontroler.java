/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import domen.Reprezentacija;
import domen.StavkaIzvestaja;
import domen.Utakmica;
import domen.UtakmiceSacuvajIzmene;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Kontroler {

    private static Kontroler instance;
    private DBBroker dbb;

    private Kontroler() {
        dbb = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<Utakmica> vratiUtakmice() {
        return dbb.vratiUtakmice();
    }

    public ArrayList<Reprezentacija> vratiReprezentacije() {
        return dbb.vratiReprezentacije();
    }

    public boolean sacuvajIzmene(UtakmiceSacuvajIzmene usi) {
        try {
            return dbb.izmeniUtakmice(usi);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<StavkaIzvestaja> vratiIzvestaj(String dodatniUpit) {
        return dbb.vratiIzvestaj(dodatniUpit);
    }

}
