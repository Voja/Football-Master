/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.Reprezentacija;
import domen.StavkaIzvestaja;
import domen.Utakmica;
import domen.UtakmiceSacuvajIzmene;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBBroker {

    public ArrayList<Object> vrati() {
        ArrayList<Object> lista = new ArrayList<>();
        String upit = "";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;

    }

    public boolean cuvajIzmeniBrisi() throws Exception {
        String upit = "";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public ArrayList<Utakmica> vratiUtakmice() {
        ArrayList<Utakmica> lista = new ArrayList<>();
        String upit = "SELECT * FROM UTAKMICA U "
                + "JOIN REPREZENTACIJA D ON (U.DOMACINID = D.REPREZENTACIJAID) "
                + "JOIN REPREZENTACIJA G ON (U.GOSTID = G.REPREZENTACIJAID)";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Reprezentacija domacin = new Reprezentacija(rs.getInt(7), rs.getString(8));

                Reprezentacija gost = new Reprezentacija(rs.getInt(9), rs.getString(10));

                Utakmica u = new Utakmica(rs.getInt("UtakmicaID"), rs.getString("Grupa"),
                        domacin, gost, rs.getInt("GolovaDomacin"), rs.getInt("GolovaGost"));

                lista.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Reprezentacija> vratiReprezentacije() {
        ArrayList<Reprezentacija> lista = new ArrayList<>();
        String upit = "SELECT * FROM REPREZENTACIJA";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Reprezentacija reprezentacija
                        = new Reprezentacija(rs.getInt("ReprezentacijaID"), rs.getString("Naziv"));

                lista.add(reprezentacija);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean izmeniUtakmice(UtakmiceSacuvajIzmene usi) throws SQLException {
        String upit = "UPDATE UTAKMICA SET GRUPA = ?, DOMACINID = ?, GOSTID = ?, "
                + "GOLOVADOMACIN = ?, GOLOVAGOST = ? WHERE UTAKMICAID = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            if (!usi.getIzmenjeneUtakmice().isEmpty()) {

                for (Utakmica u : usi.getIzmenjeneUtakmice()) {
                    ps.setString(1, u.getGrupa());
                    ps.setInt(2, u.getDomacin().getReprezentacijaID());
                    ps.setInt(3, u.getGost().getReprezentacijaID());
                    ps.setInt(4, u.getGolovaDomacin());
                    ps.setInt(5, u.getGolovaGost());
                    ps.setInt(6, u.getUtakmicaID());

                    ps.addBatch();
                }

                ps.executeBatch();

                if (obrisiUtakmice(usi)) {
                    Konekcija.getInstance().getConnection().commit();
                    return true;
                } else {
                    Konekcija.getInstance().getConnection().rollback();
                    return false;
                }

            }
            
            return obrisiUtakmice(usi);

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private boolean obrisiUtakmice(UtakmiceSacuvajIzmene usi) throws SQLException {
        String upit = "DELETE FROM UTAKMICA WHERE UTAKMICAID = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            if (!usi.getObrisaneUtakmice().isEmpty()) {

                for (Utakmica u : usi.getObrisaneUtakmice()) {
                    ps.setInt(1, u.getUtakmicaID());

                    ps.addBatch();
                }

                ps.executeBatch();
                Konekcija.getInstance().getConnection().commit();
                
                return true;

            }
            
            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList<StavkaIzvestaja> vratiIzvestaj(String dodatniUpit) {
        ArrayList<StavkaIzvestaja> lista = new ArrayList<>();
        String upit = "SELECT r.naziv AS Naziv, "
                + "SUM(CASE WHEN r.reprezentacijaid = u.domacinid "
                + "THEN u.golovadomacin ELSE u.golovagost END) AS GolovaDato, "
                + "SUM(CASE WHEN r.reprezentacijaid = u.domacinid "
                + "THEN u.golovagost ELSE u.golovadomacin END) AS GolovaPrimljeno, "
                + "SUM(CASE WHEN r.reprezentacijaid = u.domacinid "
                + "THEN u.golovadomacin ELSE u.golovagost END) "
                + "- SUM(CASE WHEN r.reprezentacijaid = u.domacinid "
                + "THEN u.golovagost ELSE u.golovadomacin END) AS GolRazlika "
                + "FROM reprezentacija r JOIN utakmica u "
                + "ON (r.reprezentacijaid = u.domacinid OR r.reprezentacijaid = u.gostid)"
                + dodatniUpit
                + "GROUP BY naziv "
                + "ORDER BY GolRazlika DESC";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                
                StavkaIzvestaja si = new StavkaIzvestaja(rs.getString("Naziv"),
                        rs.getInt("GolovaDato"), 
                                rs.getInt("GolovaPrimljeno"), 
                                        rs.getInt("GolRazlika"));
                
                
                
                lista.add(si);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
}
