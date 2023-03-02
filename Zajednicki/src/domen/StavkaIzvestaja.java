/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class StavkaIzvestaja implements Serializable {
    
    private String reprezentacija;
    private int golovaDato;
    private int golovaPrimljeno;
    private int golRazlika;

    public StavkaIzvestaja() {
    }

    public StavkaIzvestaja(String reprezentacija, int golovaDato, int golovaPrimljeno, int golRazlika) {
        this.reprezentacija = reprezentacija;
        this.golovaDato = golovaDato;
        this.golovaPrimljeno = golovaPrimljeno;
        this.golRazlika = golRazlika;
    }

    public int getGolRazlika() {
        return golRazlika;
    }

    public void setGolRazlika(int golRazlika) {
        this.golRazlika = golRazlika;
    }

    public String getReprezentacija() {
        return reprezentacija;
    }

    public void setReprezentacija(String reprezentacija) {
        this.reprezentacija = reprezentacija;
    }

    public int getGolovaDato() {
        return golovaDato;
    }

    public void setGolovaDato(int golovaDato) {
        this.golovaDato = golovaDato;
    }

    public int getGolovaPrimljeno() {
        return golovaPrimljeno;
    }

    public void setGolovaPrimljeno(int golovaPrimljeno) {
        this.golovaPrimljeno = golovaPrimljeno;
    }
    
    
}
