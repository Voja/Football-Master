/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Reprezentacija;
import domen.Utakmica;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleKlijent extends AbstractTableModel {

    ArrayList<Utakmica> lista;
    String[] kolone = {"ID", "Grupa", "Domacin", "Gost", "Golova domacin", "Golova gost"};

    public ModelTabeleKlijent() {
        lista = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Utakmica u = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                // STAVIO SAM I ID RADI LAKSE PROVERE
                return u.getUtakmicaID();
            case 1:
                return u.getGrupa();
            case 2:
                return u.getDomacin();
            case 3:
                return u.getGost();
            case 4:
                return u.getGolovaDomacin();
            case 5:
                return u.getGolovaGost();

            default:
                return "return!";
        }
    }

    public ArrayList<Utakmica> getLista() {
        return lista;
    }

    public void dodajUtakmice(ArrayList<Utakmica> utakmice) {
        lista = utakmice;
        fireTableDataChanged();
    }

    public Utakmica vratiIzabranuUtakmicu(int row) {
        return lista.get(row);
    }

    public void izmeniUtakmicu(int selectedRow, Utakmica u) {
        lista.set(selectedRow, u);
        fireTableDataChanged();
    }

    public boolean postojiParDomacinGost(int utakmicaID, Reprezentacija domacin, Reprezentacija gost) {
        for (Utakmica utakmica : lista) {
            if (utakmica.getDomacin().getReprezentacijaID() == domacin.getReprezentacijaID()
                    && utakmica.getGost().getReprezentacijaID() == gost.getReprezentacijaID()
                    && utakmica.getUtakmicaID() != utakmicaID) {
                return true;
            }
        }
        return false;
    }

    public void obrisiUtakmicu(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

}
