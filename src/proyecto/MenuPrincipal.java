/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author Usuario
 */
public class MenuPrincipal extends javax.swing.JFrame {
String idFu;
    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMIFuncionario = new javax.swing.JMenuItem();
        jMIAseguradora = new javax.swing.JMenuItem();
        jMIEstudiante = new javax.swing.JMenuItem();
        jMIConductor = new javax.swing.JMenuItem();
        jMIAsistente = new javax.swing.JMenuItem();
        jMIVehiculo = new javax.swing.JMenuItem();
        jMRuta = new javax.swing.JMenu();
        jMConsulta = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Registros");

        jMIFuncionario.setText("Ingreso Funcionario");
        jMIFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIFuncionarioActionPerformed(evt);
            }
        });
        jMenu1.add(jMIFuncionario);

        jMIAseguradora.setText("Ingreso Aseguradora");
        jMIAseguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAseguradoraActionPerformed(evt);
            }
        });
        jMenu1.add(jMIAseguradora);

        jMIEstudiante.setText("Ingreso Estudiante");
        jMIEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEstudianteActionPerformed(evt);
            }
        });
        jMenu1.add(jMIEstudiante);

        jMIConductor.setText("Ingreso Conductor");
        jMIConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIConductorActionPerformed(evt);
            }
        });
        jMenu1.add(jMIConductor);

        jMIAsistente.setText("Ingreso Asistente");
        jMIAsistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAsistenteActionPerformed(evt);
            }
        });
        jMenu1.add(jMIAsistente);

        jMIVehiculo.setText("Ingreso Vehiculo");
        jMenu1.add(jMIVehiculo);

        jMenuBar1.add(jMenu1);

        jMRuta.setText("Ruta");
        jMenuBar1.add(jMRuta);

        jMConsulta.setText("Consulta");
        jMenuBar1.add(jMConsulta);

        jMenu6.setText("Ayuda");
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMIAseguradoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIAseguradoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMIAseguradoraActionPerformed

    private void jMIEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMIEstudianteActionPerformed

    private void jMIAsistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIAsistenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMIAsistenteActionPerformed

    private void jMIConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIConductorActionPerformed
        Conductor miUsuario = new Conductor();
        miUsuario.setVisible(true);
        miUsuario.obIDFunc(idFu);
        
    }//GEN-LAST:event_jMIConductorActionPerformed

    public void obIdFunc(String id){
     idFu=id;   
    }
        
    private void jMIFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIFuncionarioActionPerformed
       
    }//GEN-LAST:event_jMIFuncionarioActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
public void cerrarConductor(){
    Conductor miUsuario = new Conductor();
        miUsuario.setVisible(false);
}
public void cerrarLicencia(){
    Licencia miUsuario = new Licencia();
        miUsuario.setVisible(false);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenu jMConsulta;
    private javax.swing.JMenuItem jMIAseguradora;
    private javax.swing.JMenuItem jMIAsistente;
    private javax.swing.JMenuItem jMIConductor;
    private javax.swing.JMenuItem jMIEstudiante;
    private javax.swing.JMenuItem jMIFuncionario;
    private javax.swing.JMenuItem jMIVehiculo;
    private javax.swing.JMenu jMRuta;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    // End of variables declaration//GEN-END:variables
}
