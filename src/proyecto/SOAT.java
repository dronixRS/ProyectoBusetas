/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;
import controlador.AseguradoraDAO;
import controlador.AsistenteDAO;
import controlador.ConductorDAO;
import controlador.SOATDAO;
import controlador.Conexion;
import modelo.SOATVO;
import controlador.FuncionarioDAO;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.AseguradoraVO;
import modelo.ConductorVO;
        
/**
 *
 * @author Usuario
 */
public class SOAT extends javax.swing.JFrame {
private Connection conexion;
    private Conexion conector;
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
         boolean guardarEditar=false;
     Date vigLin, vigLin2;
    String id2;
    Calendar vigLin1,vigLin12;
     String id_func="", fechaDC,fechaDC2, rutaFotoSoat;
     String fecha,fecha2;
    java.sql.Date dateDB, dateDB2;
      boolean estado;  
      int nivel,posicionUsuario = -1;
      SOATVO transFuncionario;
      ArrayList<SOATVO> datosFuncionario;
      SOATDAO BDConductor;
       ImageIcon img;
       boolean bImg=false;
       String codigo,aseguradora;
        Date fechaexpedicion,fechavigente;
            DefaultTableModel modelo;
         ArrayList<String> datosFuncionarioTabla;
        SOATDAO BDsoat; 
         ArrayList<String> datosInicio;
         ArrayList<String> asegurador;
    /**
     * Creates new form SOAT
     */
    public SOAT() {
        initComponents();
                this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        bloquearCajas();
        BDsoat=new SOATDAO();
         BDConductor=new SOATDAO();
         AseguradoraDAO aseguradoors= new AseguradoraDAO();
        asegurador=aseguradoors.buscarConductor();

        
        datosFuncionario=new ArrayList<SOATVO>();
        datosInicio=new ArrayList<String>();
        datosFuncionarioTabla=new ArrayList<String>();
        datosFuncionarioTabla=BDConductor.buscarConductor();
        datosInicio=BDConductor.buscarConductor();
                 modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Aseguradora");
        modelo.addColumn("Fecha de expedicion");
        modelo.addColumn("fecha vigente");
                this.jTSoat.setModel(modelo);
        this.jTSoat.getColumn(jTSoat.getColumnName(0)).setMaxWidth(100);
        this.jTSoat.getColumn(jTSoat.getColumnName(2)).setMaxWidth(100);
        this.jTSoat.getColumn(jTSoat.getColumnName(3)).setMaxWidth(100);
        BDConductor=new SOATDAO();
         cargarTablaInicio();
         cargarJCB();
    }
      public void cargarJCB(){
     
          for (int i = 0; i < asegurador.size(); i=i+3) {
            jCBAseguradora.addItem(asegurador.get(i+1));
        }
       
 }
public void cargarTablaInicio(){
      String ver;
        
        for (int i = 0; i < datosFuncionarioTabla.size(); i=i+6) {
            ver=datosFuncionarioTabla.get(i+5);
            System.out.println(ver);
            if (ver.equals("1")) {
              String[] datos=new String[6];
            datos[0]=datosFuncionarioTabla.get(i);
            datos[1]=datosFuncionarioTabla.get(i+1);
            datos[2]=datosFuncionarioTabla.get(i+2);
            datos[3]=datosFuncionarioTabla.get(i+3);
           modelo.addRow(datos); 
            codigo = datosFuncionarioTabla.get(i);
            aseguradora = datosFuncionarioTabla.get(i+1);
           
            
            fechaDC=datosFuncionarioTabla.get(i+2);
            String date = fechaDC;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr;
             try {
                 dateStr = formatter.parse(date);
                 dateDB = new java.sql.Date(dateStr.getTime());
             } catch (ParseException ex) {
                 Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
             }
           
            fechaDC2=datosFuncionarioTabla.get(i+3);
            String date2 = fechaDC2;
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr2;
             try {
                 dateStr2 = formatter.parse(date2);
                 dateDB2 = new java.sql.Date(dateStr2.getTime());
             } catch (ParseException ex) {
                 Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            rutaFotoSoat=datosFuncionarioTabla.get(i+4);
            
//            java.sql.Date sqlDate = new java.sql.Date(vigLin.getTime());
            
           estado=true;
             
             int aseg=0;
             for (int j = 0; j < asegurador.size(); j=j+3) {
                 if (aseguradora.equals(asegurador.get(j+1))) {
                     aseg=Integer.parseInt(asegurador.get(j));
                 }
             }
                
            transFuncionario = new SOATVO(codigo,aseg,dateDB, dateDB2,rutaFotoSoat,id_func, estado);
            datosFuncionario.add(transFuncionario);
            
            }
            
        }
       
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
        jLabel1 = new javax.swing.JLabel();
        jTfCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTSoat = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jCBAseguradora = new javax.swing.JComboBox<>();
        jBCargar = new javax.swing.JButton();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jBVer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SOAT");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso SOAT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Codigo:");

        jTfCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTfCodigoKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Aseguradora:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Fecha de expedicion:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Imagen SOAT:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Fecha Vigente:");

        jBNuevo.setBackground(new java.awt.Color(255, 255, 255));
        jBNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        jBNuevo.setBorder(null);
        jBNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNuevoActionPerformed(evt);
            }
        });

        jBGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        jBGuardar.setBorder(null);
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar.png"))); // NOI18N
        jBEditar.setBorder(null);
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });

        jBBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jBBuscar.setBorder(null);
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jBEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        jBEliminar.setBorder(null);
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        jTSoat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTSoat.setEnabled(false);
        jScrollPane1.setViewportView(jTSoat);

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton11.setText("Salir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jCBAseguradora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Empresa De Seguros" }));
        jCBAseguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBAseguradoraActionPerformed(evt);
            }
        });

        jBCargar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBCargar.setText("Cargar");
        jBCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarActionPerformed(evt);
            }
        });

        jBVer.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBVer.setText("Ver");
        jBVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jBCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBVer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(54, 54, 54))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTfCodigo)
                            .addComponent(jCBAseguradora, 0, 200, Short.MAX_VALUE)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 592, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jCBAseguradora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jBCargar)
                            .addComponent(jBVer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed
       activarCajas();
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
if(guardarEditar==false){
        
        if (guardarDatos() == true) {
            JOptionPane.showMessageDialog(null,
                    "Guardado con exito...");

            posicionUsuario=-1;
            cargarTabla();
            limpiarCajas();
            bloquearCajas();
           
        
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error de datos");
        }
        
       }else{
           
           if(modificarDatos()==true){
               JOptionPane.showMessageDialog(null,
                    "Modificado con exito...");
            limpiarCajas();
            bloquearCajas();
            guardarEditar=false;
           }else {
            JOptionPane.showMessageDialog(null,
                    "Error de datos");
           }}    
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jCBAseguradoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBAseguradoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBAseguradoraActionPerformed
  
    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
         try {
            String buscaIdent = JOptionPane.showInputDialog(
                    "Ingresar Identificación para editar");
            buscarUsuarioEditar(buscaIdent);
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarActionPerformed

        img=cargarFotoLicencia();
        jBVer.setEnabled(true);        
        
    }//GEN-LAST:event_jBCargarActionPerformed

    private void jBVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerActionPerformed
    verLicencia();
    }//GEN-LAST:event_jBVerActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed

    try {
    String buscaIdent = JOptionPane.showInputDialog(
       "Ingresar Identificación para editar");
       buscarUsuario(buscaIdent);
        } catch (Exception e) {
         System.err.println(e);
        }

    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed

try {
            String buscaIdent = JOptionPane.showInputDialog("Ingresar identificacion para eliminar");
            //se llama el metodo buscarUsuarioEditar y se envia la variable buscaident la cual posee la identificacion
            buscarUsuarioElm(buscaIdent);
        } catch (Exception e) {
            System.err.println(e);
        }

    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTfCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfCodigoKeyTyped
 char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTfCodigoKeyTyped
     
    public void buscarUsuarioElm(String busUsu) {
             boolean verificar = false;
        if (datosFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Usuarios Registrados");
        } else {
            
            for (int i = 0; i < datosFuncionario.size(); i++) {

                if (datosFuncionario.get(i).getCodigoSoat().equals(busUsu)) {  
                     posicionUsuario = i;
                     id2=datosFuncionario.get(i).getCodigoSoat();
                     System.out.println(id2);
                      BDConductor.eliminarConductor(id2);
                    datosFuncionario.remove(posicionUsuario);
                    verificar = true;
                     for (int j= jTSoat.getRowCount(); j >=0; j--) {
            modelo.removeRow(i);    
            }
                     
                    for (int j = 0; j < datosFuncionario.size(); j++) {
                String[] datos = new String[2];
        datos[0] = datosFuncionario.get(j).getCodigoSoat();
        String aseg="";
                for (int k = 0; k < asegurador.size(); k=k+3) {
                 if ((datosFuncionario.get(j).getAseguradora()+"").equals(asegurador.get(k))) {
                     aseg=(asegurador.get(k+1));
                 }
             }
        datos[1] = aseg;
        
        modelo.addRow(datos);
        
       }
                  
                }
            }

            if (verificar == false) {
                JOptionPane.showMessageDialog(null, "Cedula Incorrecta");
            }
            verificar = false;
        }
                 
          
      }
    
    
    
    
    public void buscarUsuario(String busUsu) {
        boolean verificarUsu = false;
        if (datosFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                    "No hay Usuarios Registrados");
        } else {
            for (int i = 0; i < datosFuncionario.size(); i++) {
                
                if (datosFuncionario.get(i).getCodigoSoat().equals(busUsu)) {
                    String aseg="";
                for (int j = 0; j < asegurador.size(); j=j+3) {
                 if ((datosFuncionario.get(i).getAseguradora()+"").equals(asegurador.get(j))) {
                     aseg=(asegurador.get(j+1));
                 }
             }
                    verificarUsu = true;
                    JOptionPane.showMessageDialog(null,
                            "Información de Usuario:\nCodigo Soat: "
                            +datosFuncionario.get(i).getCodigoSoat()+"\nAseguradora: "
                            +aseg+"\nFecha Soat: "
                            +datosFuncionario.get(i).getFechaSoat()+"\nFecha Vigencia Soat: "
                            +datosFuncionario.get(i).getFechaVigenciaSoat());        
                          
                }
            }

            if (verificarUsu == false) {
                JOptionPane.showMessageDialog(null, "Identificación Incorrecta");
            }

        }
    }
    
    
    
    public void verLicencia(){
        ImgSOAT linc=new ImgSOAT();
        if (bImg==false) {
        linc.cargarImg(img);
        linc.setVisible(true);  
        }else if (bImg==true) {
        linc.setVisible(true);
        bImg=false;
        }
        
    }
 
    public ImageIcon cargarFotoLicencia() {
        //crea un objeto filechooser
        JFileChooser dlg = new JFileChooser();
        //del objeto creado vamos a llamar el metodo setFileFilter
        dlg.setFileFilter(filter);
        //abrimos la ventana de dialogo para escoger imagenes
        int opcion = dlg.showOpenDialog(this);
        //si hacemos clic en boton abrir
        if (opcion == JFileChooser.APPROVE_OPTION) {
            //obtener el nombre del archivo que hemos seleccionado
            String fil = dlg.getSelectedFile().getPath();
            //obtener la direccion donde se guarda la imagen
            String file = dlg.getSelectedFile().toString();
            //modiicamos la imagen~~~~~~~~
            ImageIcon icon = new ImageIcon(fil);
            //extrae la imagen del icono
            Image img = icon.getImage();
            //cambiando el tamaño a la imagen
            Image newImg = img.getScaledInstance(450, 150,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            //rutaImagen=file;
            rutaFotoSoat = file;
            return newIcon;
        }
        return null;
    }
    
    public void buscarUsuarioEditar(String busUsu) {
        bImg=false;
        boolean verificar = false;
        if (datosFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Usuarios Registrados");
        } else {
            for (int i = 0; i < datosFuncionario.size(); i++) {
                
                if (datosFuncionario.get(i).getCodigoSoat().equals(busUsu)) {
                    posicionUsuario = i;
                    guardarEditar=true;
                    activarCajas();
                    jBVer.setEnabled(true);
                    
                    verificar = true;
                    jTfCodigo.setText(datosFuncionario.get(i).getCodigoSoat());
                    id2=datosFuncionario.get(i).getCodigoSoat();
                     String aseg="";
                for (int j = 0; j < asegurador.size(); j=j+3) {
                 if ((datosFuncionario.get(i).getAseguradora()+"").equals(asegurador.get(j))) {
                     aseg=(asegurador.get(j+1));
                 }
             }
                    jCBAseguradora.setSelectedItem(aseg); 
                    
                }
            }

            if (verificar == false) {
                JOptionPane.showMessageDialog(null, "Identificación Incorrecta");
            }
            verificar = false;
        }
    }





    public boolean modificarDatos() {
        
        if (validarCajas() == true) {
           codigo = jTfCodigo.getText();
            
           aseguradora = String.valueOf(jCBAseguradora.getSelectedItem());
           
            vigLin1= (dateChooserCombo1.getSelectedDate());
            vigLin=vigLin1.getTime();
            fechaDC=formato.format(vigLin);
            String date = fechaDC;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr;
             try {
                 dateStr = formatter.parse(date);
                 dateDB = new java.sql.Date(dateStr.getTime());
             } catch (ParseException ex) {
                 Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
             }
                         vigLin12= (dateChooserCombo2.getSelectedDate());
            vigLin2=vigLin12.getTime();
            fechaDC2=formato.format(vigLin2);
            String date2 = fechaDC2;
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr2;
             try {
                 dateStr2 = formatter.parse(date2);
                 dateDB2 = new java.sql.Date(dateStr2.getTime());
             } catch (ParseException ex) {
                 Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            int aseg=0;
             for (int i = 0; i < asegurador.size(); i=i+3) {
                 if (aseguradora.equals(asegurador.get(i+1))) {
                     aseg=Integer.parseInt(asegurador.get(i));
                 }
             }
            transFuncionario = new SOATVO(codigo, aseg, dateDB,  dateDB2,rutaFotoSoat,id_func,estado);
            
            datosFuncionario.set(posicionUsuario,transFuncionario);
//                       
            BDsoat.editarConductor(transFuncionario,id2);
            for (int i = jTSoat.getRowCount()-1; i >=0; i--) {
            modelo.removeRow(i);    
            }
                           
            for (int i = 0; i < datosFuncionario.size(); i++) {
                String[] datos = new String[4];
        datos[0] = datosFuncionario.get(i).getCodigoSoat();
        datos[1] = datosFuncionario.get(i).getAseguradora()+"";
        datos[2] = fechaDC;
        datos[3] = fechaDC2;

        modelo.addRow(datos);
            }
                  
                    
            
            return true;
        } else {
            return false;
        }
    }
    public void cargarTabla() {
        String[] datos = new String[4];
        datos[0] = codigo;
        datos[1] = aseguradora;
        datos[2] = fechaDC;
        datos[3] = fechaDC2;
        
        
       
        modelo.addRow(datos);
    }
         
         public void limpiarCajas() {
        jTfCodigo.setText("");
        jCBAseguradora.setSelectedIndex(0);
        dateChooserCombo1.setCurrent(Calendar.getInstance());
        dateChooserCombo2.setCurrent(Calendar.getInstance());
        
    }
         
    public void obIDFunc(String id){
        id_func=id;
    }
    
    public boolean validarCajas() {
        
        if (jTfCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Identificación");

            return false;
        }else if (jCBAseguradora.getSelectedItem().equals("Seleccione Empresa De Soat")){
            JOptionPane.showMessageDialog(null,
                    "Ingresar Empresa Soat");

            return false;
        }else {
            return true;
        }
    }
         
    
    public boolean guardarDatos(){
         if (validarCajas() == true) {
           codigo = jTfCodigo.getText();
            aseguradora = (String)(jCBAseguradora.getSelectedItem());
           
            vigLin1= (dateChooserCombo1.getSelectedDate());
            vigLin=vigLin1.getTime();
            fechaDC=formato.format(vigLin);
            String date = fechaDC;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr;
             try {
                 dateStr = formatter.parse(date);
                 dateDB = new java.sql.Date(dateStr.getTime());
             } catch (ParseException ex) {
                 Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
             }
           
            vigLin12= (dateChooserCombo2.getSelectedDate());
            vigLin2=vigLin12.getTime();
            fechaDC2=formato.format(vigLin2);
            String date2 = fechaDC2;
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr2;
             try {
                 dateStr2 = formatter.parse(date2);
                 dateDB2 = new java.sql.Date(dateStr2.getTime());
             } catch (ParseException ex) {
                 Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
             }
            
//            java.sql.Date sqlDate = new java.sql.Date(vigLin.getTime());
            
           estado=true;
             int aseg=0;
             for (int i = 0; i < asegurador.size(); i=i+3) {
                 if (aseguradora.equals(asegurador.get(i+1))) {
                     aseg=Integer.parseInt(asegurador.get(i));
                 }
             }
             
             
            transFuncionario = new SOATVO(codigo, aseg, dateDB, dateDB2,rutaFotoSoat , id_func, estado);
            datosFuncionario.add(transFuncionario);
//            se envian los datos que se encuentran en funvionarioVO(transfuncionario) al metodo ingresarFuncionario que se encuentra en la clase FuncionarioDAO
             if (BDConductor.ingresarSOAT(transFuncionario)==true) {
                 return true;
             }else{
             return false;
             }

         
        } else {
            return false;
        }
    
    }
    public void activarCajas(){
        
       
        jTfCodigo.setEnabled(true);
        jCBAseguradora.setEnabled(true);
        dateChooserCombo1.setEnabled(true);
        dateChooserCombo2.setEnabled(true);
        jBCargar.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBBuscar.setEnabled(false);
        jBVer.setEnabled(false);
    }
public void bloquearCajas(){
        
       
        jTfCodigo.setEnabled(false);
        jCBAseguradora.setEnabled(false);
        dateChooserCombo1.setEnabled(false);
        dateChooserCombo2.setEnabled(false);
        jBCargar.setEnabled(false);
        jBGuardar.setEnabled(false);
        jBNuevo.setEnabled(true);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);
        jBBuscar.setEnabled(true);
        jBVer.setEnabled(false);
        
    }
    
 
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
            java.util.logging.Logger.getLogger(SOAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SOAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SOAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SOAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SOAT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBCargar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jBVer;
    private javax.swing.JButton jButton11;
    private javax.swing.JComboBox<String> jCBAseguradora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTSoat;
    private javax.swing.JTextField jTfCodigo;
    // End of variables declaration//GEN-END:variables
}
