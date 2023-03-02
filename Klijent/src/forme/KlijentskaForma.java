/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Reprezentacija;
import domen.Utakmica;
import domen.UtakmiceSacuvajIzmene;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import konstante.Operacije;
import modeli.ModelTabeleKlijent;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class KlijentskaForma extends javax.swing.JFrame {

    ArrayList<Utakmica> izmenjeneUtakmice = new ArrayList<>();
    ArrayList<Utakmica> obrisaneUtakmice = new ArrayList<>();

    /**
     * Creates new form Forma1
     */
    public KlijentskaForma() {
        initComponents();
        setLocationRelativeTo(null);
        tblUtakmice.setModel(new ModelTabeleKlijent());
        popuniUtakmice();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUtakmice = new javax.swing.JTable();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Utakmice"));

        tblUtakmice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblUtakmice);

        btnIzmeni.setText("Izmeni utakmicu");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi utakmicu");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnSacuvaj.setText("Sacuvaj izmene");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 116, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIzmeni)
                    .addComponent(btnObrisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSacuvaj)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed

        int row = tblUtakmice.getSelectedRow();

        if (row != -1) {
            ModelTabeleKlijent mt = (ModelTabeleKlijent) tblUtakmice.getModel();
            Utakmica u = mt.vratiIzabranuUtakmicu(row);

            new IzmeniForma(this, true, u).setVisible(true);
        }


    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed

        int row = tblUtakmice.getSelectedRow();

        if (row != -1) {
            ModelTabeleKlijent mt = (ModelTabeleKlijent) tblUtakmice.getModel();

            Utakmica u = mt.vratiIzabranuUtakmicu(row);
            obrisaneUtakmice.add(u);

            mt.obrisiUtakmicu(row);
        }

    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed

        UtakmiceSacuvajIzmene usi = new UtakmiceSacuvajIzmene(izmenjeneUtakmice, obrisaneUtakmice);

        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.SACUVAJ_IZMENE);
        kz.setParametar(usi);

        Komunikacija.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();

        boolean uspesno = (boolean) so.getOdgovor();

        if (uspesno) {
            popuniUtakmice();
            JOptionPane.showMessageDialog(this, "Uspesno sacuvane izmene!");
        } else {
            JOptionPane.showMessageDialog(this, "Doslo je do greske...");
        }


    }//GEN-LAST:event_btnSacuvajActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KlijentskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KlijentskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KlijentskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KlijentskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KlijentskaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUtakmice;
    // End of variables declaration//GEN-END:variables

    private void popuniUtakmice() {

        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.POPUNI_UTAKMICE);

        Komunikacija.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();

        ArrayList<Utakmica> utakmice = (ArrayList<Utakmica>) so.getOdgovor();

        ModelTabeleKlijent mt = (ModelTabeleKlijent) tblUtakmice.getModel();
        mt.dodajUtakmice(utakmice);

    }

    void izmeniUtakmicu(Utakmica u) {
        ModelTabeleKlijent mt = (ModelTabeleKlijent) tblUtakmice.getModel();
        mt.izmeniUtakmicu(tblUtakmice.getSelectedRow(), u);

        izmenjeneUtakmice.add(u);

    }

    boolean validiraj(int utakmicaID, Reprezentacija domacin, Reprezentacija gost) {
        ModelTabeleKlijent mt = (ModelTabeleKlijent) tblUtakmice.getModel();

        if (mt.postojiParDomacinGost(utakmicaID, domacin, gost)) {
            return false;
        }

        return true;
    }

}
