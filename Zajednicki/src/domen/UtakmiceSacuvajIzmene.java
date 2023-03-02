/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class UtakmiceSacuvajIzmene implements Serializable{
    
    private ArrayList<Utakmica> izmenjeneUtakmice;
    private ArrayList<Utakmica> obrisaneUtakmice;

    public UtakmiceSacuvajIzmene() {
    }

    public UtakmiceSacuvajIzmene(ArrayList<Utakmica> izmenjeneUtakmice, ArrayList<Utakmica> obrisaneUtakmice) {
        this.izmenjeneUtakmice = izmenjeneUtakmice;
        this.obrisaneUtakmice = obrisaneUtakmice;
    }

    public ArrayList<Utakmica> getObrisaneUtakmice() {
        return obrisaneUtakmice;
    }

    public void setObrisaneUtakmice(ArrayList<Utakmica> obrisaneUtakmice) {
        this.obrisaneUtakmice = obrisaneUtakmice;
    }

    public ArrayList<Utakmica> getIzmenjeneUtakmice() {
        return izmenjeneUtakmice;
    }

    public void setIzmenjeneUtakmice(ArrayList<Utakmica> izmenjeneUtakmice) {
        this.izmenjeneUtakmice = izmenjeneUtakmice;
    }
    
    
    
}
