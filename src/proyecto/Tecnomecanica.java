package proyecto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controlador.Conexion;
import controlador.TecnicomecanicaDAO;
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
import modelo.TecnicomecanicaVO;

/**
 *
 * @author Usuario
 */
public class Tecnomecanica extends javax.swing.JFrame {

   private Connection conexion;
    private Conexion conector;   
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date sqlDate;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
    java.sql.Date dateDB;
    java.sql.Date dateDBE;
    
    boolean bImg=false;

    String codigo,centroDiagnostico,rutaFoto,fechaDC,fechaDC2;
    boolean estado;
    Date vigLin,expedicion;
    Calendar vigLin1,expedicion1;
    ImageIcon img;
    String id_func="";
    String id2;
    DefaultTableModel modelo;
    int posicionUsuario=-1;
    boolean guardarEditar=false;
   
    TecnicomecanicaDAO BDTecnicomecanica;
    ArrayList<String> datosTecnomecanicaTabla;
    ArrayList<String> datosInicio;

    ArrayList<TecnicomecanicaVO>datosTecnomecanica;
    TecnicomecanicaVO transTecnomecanica;
    
    String fecha;
    
    public Tecnomecanica() {
        initComponents();
        setLocationRelativeTo(null);
        bloquearCajas();       
        
        BDTecnicomecanica = new TecnicomecanicaDAO();
        
        datosTecnomecanica=new ArrayList<TecnicomecanicaVO>();
        datosInicio=new ArrayList<String>();
        datosTecnomecanicaTabla=new ArrayList<String>();
        datosTecnomecanicaTabla=BDTecnicomecanica.buscarTecnicomecanica();
        datosInicio=BDTecnicomecanica.buscarTecnicomecanica();
        
        modelo = new DefaultTableModel();
        modelo.addColumn("codigo");
        modelo.addColumn("centro diag.");
        modelo.addColumn("expedicion");
        modelo.addColumn("vigente");
        
        this.jTTecnomecanica.setModel(modelo);
        this.jTTecnomecanica.getColumn(jTTecnomecanica.getColumnName(0)).setMaxWidth(80);
        this.jTTecnomecanica.getColumn(jTTecnomecanica.getColumnName(2)).setMaxWidth(80);
        
        cargarTablaInicio();
        
    }

    public void cargarTablaInicio(){
      String ver;
        
        for (int i = 0; i < datosTecnomecanicaTabla.size(); i=i+6) {
            ver=datosTecnomecanicaTabla.get(i+5);
           if (ver.equals("1")) {
              String[] datos=new String[4];
            datos[0]=datosTecnomecanicaTabla.get(i);
            datos[1]=datosTecnomecanicaTabla.get(i+1);
            datos[2]=datosTecnomecanicaTabla.get(i+2);
            datos[3]=datosTecnomecanicaTabla.get(i+3);
            
            modelo.addRow(datos);  
            
            codigo = datosTecnomecanicaTabla.get(i);
            centroDiagnostico = datosTecnomecanicaTabla.get(i+1);
            
            fechaDC=datosTecnomecanicaTabla.get(i+2);
                try {
                    vigLin=formato.parse(fechaDC);
                            
                } catch (ParseException ex) {
                    Logger.getLogger(Tecnomecanica.class.getName()).log(Level.SEVERE, null, ex);
                }
            fechaDC=formato.format(vigLin);
            String date = fechaDC;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr;
             try {
                 dateStr = formatter.parse(date);
                 dateDB = new java.sql.Date(dateStr.getTime());
                 System.out.print(dateDB);

             } catch (ParseException ex) {
                 Logger.getLogger(Tecnomecanica.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             
            fechaDC2=datosTecnomecanicaTabla.get(i+3);
            try {
                    expedicion=formato.parse(fechaDC2);

                } catch (ParseException ex) {
                    Logger.getLogger(Tecnomecanica.class.getName()).log(Level.SEVERE, null, ex);
                }    
            
            fechaDC2=formato.format(expedicion);
            String date1 = fechaDC2;
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr1;

            
            try {
                 dateStr1 = formatter1.parse(date);
                 dateDBE = new java.sql.Date(dateStr1.getTime());
                 System.out.print(dateDBE);
             } catch (ParseException ex) {
                 Logger.getLogger(Tecnomecanica.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             
           rutaFoto= datosTecnomecanicaTabla.get(i+4);  
           estado=true;
             
            
             
            transTecnomecanica = new TecnicomecanicaVO(codigo, centroDiagnostico,dateDB,dateDBE, rutaFoto, id_func, estado);
            
            datosTecnomecanica.add(transTecnomecanica);
            
            
        }
        }
       
 }
    
    
    
    
    public void activarCajas(){

        jTFCodigo.setEnabled(true);
        jTFCentroDiagnostico.setEnabled(true);
        dateChooserComboExpedicion.setEnabled(true);
        dateChooserComboVigente.setEnabled(true);
        jBImgTecnomecanica.setEnabled(true);
        jBVerTecno.setEnabled(true);
        

        jBGuardar.setEnabled(true);
        jBBuscar.setEnabled(false);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);

}
    
    
    public void bloquearCajas(){

        jTFCodigo.setEnabled(false);
        jTFCentroDiagnostico.setEnabled(false);
        dateChooserComboExpedicion.setEnabled(false);
        dateChooserComboVigente.setEnabled(false);
        jBImgTecnomecanica.setEnabled(false);
        jBVerTecno.setEnabled(false);
       

        jBGuardar.setEnabled(false);
        jBBuscar.setEnabled(true);
        jBNuevo.setEnabled(true);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);

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
        jTFCodigo = new javax.swing.JTextField();
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
        jTTecnomecanica = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jBImgTecnomecanica = new javax.swing.JButton();
        jTFCentroDiagnostico = new javax.swing.JTextField();
        dateChooserComboExpedicion = new datechooser.beans.DateChooserCombo();
        dateChooserComboVigente = new datechooser.beans.DateChooserCombo();
        jBVerTecno = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso Tecnomecanica", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Codigo:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Centro Diagnostico:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Fecha de expedicion:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Imagen Tecnomecanica:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Fecha Vigente:");

        jBNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        jBNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNuevoActionPerformed(evt);
            }
        });

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar.png"))); // NOI18N
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });

        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        jTTecnomecanica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTTecnomecanica);

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton11.setText("Salir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jBImgTecnomecanica.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBImgTecnomecanica.setText("Cargar");
        jBImgTecnomecanica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImgTecnomecanicaActionPerformed(evt);
            }
        });

        jBVerTecno.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBVerTecno.setText("Ver");
        jBVerTecno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerTecnoActionPerformed(evt);
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
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTFCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jTFCentroDiagnostico, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateChooserComboExpedicion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBImgTecnomecanica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBVerTecno, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(dateChooserComboVigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTFCentroDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(dateChooserComboExpedicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(dateChooserComboVigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jBImgTecnomecanica)
                            .addComponent(jBVerTecno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))))
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
        }
//                 
    }//GEN-LAST:event_jBGuardarActionPerformed

    }
    
    public boolean guardarDatos(){
         if (validarCajas() == true) {
            codigo = jTFCodigo.getText();
            centroDiagnostico = jTFCentroDiagnostico.getText();            
            
            
            
            
            obtenerVigLin();
            vigLin1=(dateChooserComboVigente.getSelectedDate());
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
            
            obtenerExpedicion();
            expedicion1=(dateChooserComboExpedicion.getSelectedDate());
            expedicion=expedicion1.getTime();
            fechaDC2=formato.format(expedicion);
            String date1 = fechaDC2;
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy"); // your template here
            java.util.Date dateStr1;
             try {
                 dateStr1 = formatter.parse(date1);
                 dateDBE = new java.sql.Date(dateStr1.getTime());
             } catch (ParseException ex) {
                 Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             
             
           estado=true;
             
           id_func="323"; 
             
           transTecnomecanica = new TecnicomecanicaVO(codigo, centroDiagnostico, dateDBE,dateDB, rutaFoto, id_func, estado);
            datosTecnomecanica.add(transTecnomecanica);
//            se envian los datos que se encuentran en funvionarioVO(transfuncionario) al metodo ingresarFuncionario que se encuentra en la clase FuncionarioDAO
            BDTecnicomecanica.ingresarTecnicomecanica(transTecnomecanica);
            return true;
        } else {
            return false;
       }
    
    }
    
    
    public boolean validarCajas() {
        
        if (jTFCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar codigo");
            return false;
        } else if (jTFCentroDiagnostico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar centro de diagnostico");
            return false;
        } else {
            return true;
        }
    }
    
    public void obtenerVigLin(){
           
           int dia, mes, anio;
           
           
           vigLin1=dateChooserComboVigente.getSelectedDate();
            dia=vigLin1.get(Calendar.DAY_OF_MONTH);
            mes=vigLin1.get(Calendar.MONTH);
            anio=vigLin1.get(Calendar.YEAR);
            fecha= dia+"/"+(mes+1)+"/"+anio;
            
       }
    
    public void obtenerExpedicion(){
           
           int dia, mes, anio;
           
           
           expedicion1=dateChooserComboExpedicion.getSelectedDate();
            dia=expedicion1.get(Calendar.DAY_OF_MONTH);
            mes=expedicion1.get(Calendar.MONTH);
            anio=expedicion1.get(Calendar.YEAR);
            fecha= dia+"/"+(mes+1)+"/"+anio;
            
       }
    
    
    public void limpiarCajas(){
    
        jTFCodigo.setText("");
        jTFCentroDiagnostico.setText("");
        dateChooserComboExpedicion.setCurrent(Calendar.getInstance());
        dateChooserComboVigente.setCurrent(Calendar.getInstance());

    }
    
    
    public void cargarTabla(){
    
        String[] datos = new String[4];
        datos[0] = codigo;
        datos[1] = centroDiagnostico;
        datos[2] = fechaDC2;
        datos[3] = fechaDC;
    
       modelo.addRow(datos);
    }
    
    
    public boolean modificarDatos() {
        
        if (validarCajas() == true) {
            codigo = jTFCodigo.getText();
            centroDiagnostico = jTFCentroDiagnostico.getText();           
            vigLin1=dateChooserComboVigente.getSelectedDate();           
            fechaDC=formato.format(vigLin);
            expedicion1=dateChooserComboExpedicion.getSelectedDate();
            fechaDC2=formato.format(expedicion);
            
            estado=true;
            
            transTecnomecanica= new TecnicomecanicaVO(codigo, centroDiagnostico, expedicion, vigLin, rutaFoto, id_func, estado);
            
            datosTecnomecanica.set(posicionUsuario,transTecnomecanica);
                      
            BDTecnicomecanica.editarTecnomecanica(transTecnomecanica,id2);
            for (int i = jTTecnomecanica.getRowCount()-1; i >=0; i--) {
            modelo.removeRow(i);    
            }
                           
            for (int i = 0; i < datosTecnomecanica.size(); i++) {
                String[] datos = new String[4];
        datos[0] = datosTecnomecanica.get(i).getCodigo();
        datos[1] = datosTecnomecanica.get(i).getCentroDiagnostico();
        
        fechaDC2=formato.format(datosTecnomecanica.get(i).getFechaExpedicion());
        datos[2] = fechaDC2;

        fechaDC=formato.format(datosTecnomecanica.get(i).getFechaVigente());
        datos[3] = fechaDC;        
 
      
        modelo.addRow(datos);
            }
                  
                    
            
            return true;
        } else {
            return false;
        }
    }
    
    
    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed

    try {
            String buscaCodigo = JOptionPane.showInputDialog(
                    "Ingresar Identificación para editar");
            buscarUsuarioEditar(buscaCodigo);
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }//GEN-LAST:event_jBEditarActionPerformed

    
    public void buscarUsuarioEditar(String busTec) {
        bImg=false;
        VerTecnomecanica tecno=new VerTecnomecanica();
        boolean verificar = false;
        if (datosTecnomecanica.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay tecnomecanicas Registradas");
        } else {
            for (int i = 0; i < datosTecnomecanica.size(); i++) {
                
                if (datosTecnomecanica.get(i).getCodigo().equals(busTec)) {
                    posicionUsuario = i;
                    guardarEditar=true;
                    activarCajas();
                    jBVerTecno.setEnabled(true);
                    
                    verificar = true;
                    jTFCodigo.setText(datosTecnomecanica.get(i).getCodigo());
                    id2=datosTecnomecanica.get(i).getCodigo();
                    jTFCentroDiagnostico.setText(datosTecnomecanica.get(i).getCentroDiagnostico());
                    
                    
                    img=(buscImgTec(datosTecnomecanica.get(i).getRutaFoto()));
                    
                    
                    
                    
                }
            }

            if (verificar == false) {
                JOptionPane.showMessageDialog(null, "Identificación Incorrecta");
            }
            verificar = false;
        }
    }
    
    public ImageIcon buscImgTec(String fil){
        
        
        ImageIcon icon = new ImageIcon(fil);
            //extrae la imagen del icono
            Image img = icon.getImage();
            //cambiando el tamaño a la imagen
            Image newImg = img.getScaledInstance(485, 220,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            //rutaImagen=file;
            return newIcon;
    }
    
    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed

        activarCajas();
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jBImgTecnomecanicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImgTecnomecanicaActionPerformed
        
        img=cargarFotoTecnicomecanica();
        jBVerTecno.setEnabled(true);
    }//GEN-LAST:event_jBImgTecnomecanicaActionPerformed

    private void jBVerTecnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerTecnoActionPerformed

    verTecnomecanica();
    
    }//GEN-LAST:event_jBVerTecnoActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed

    try {
    String buscaCodigo = JOptionPane.showInputDialog(
       "Ingresar codigo para buscar");
       buscarTecnomecanica(buscaCodigo);
        } catch (Exception e) {
         System.err.println(e);
        }
        
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed

    try {
            String buscaIdent = JOptionPane.showInputDialog("Ingresar identificacion para eliminar");
            //se llama el metodo buscarUsuarioEditar y se envia la variable buscaident la cual posee la identificacion
            buscarTecnomecanicaElm(buscaIdent);
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_jBEliminarActionPerformed

    
    public void buscarTecnomecanicaElm(String busTec) {
             boolean verificar = false;
        if (datosTecnomecanica.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay tecnomecanica Registrada");
        } else {
            
            for (int i = 0; i < datosTecnomecanica.size(); i++) {

                if (datosTecnomecanica.get(i).getCodigo().equals(busTec)) {  
                     posicionUsuario = i;
                     id2=datosTecnomecanica.get(i).getCodigo();
                     System.out.println(id2);
                      BDTecnicomecanica.eliminarTecnicomecanica(id2);
                    datosTecnomecanica.remove(posicionUsuario);
                    verificar = true;
                     for (int j= jTTecnomecanica.getRowCount(); j >=0; j--) {
            modelo.removeRow(i);    
            }
                     
                    for (int j = 0; j < datosTecnomecanica.size(); j++) {
                        
        String[] datos = new String[2];
        datos[0] = datosTecnomecanica.get(j).getCodigo();
        datos[1] = datosTecnomecanica.get(j).getCentroDiagnostico();
        
        modelo.addRow(datos);
        
       }
                  
                }
            }

            if (verificar == false) {
                JOptionPane.showMessageDialog(null, "codigo Incorrecto");
            }
            verificar = false;
        }
                 
          
      }
    
    
    
    
    
    
    
    
    public void buscarTecnomecanica(String busUsu) {
        boolean verificarUsu = false;
        if (datosTecnomecanica.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                    "No hay Usuarios tecnomecanica");
        } else {
            for (int i = 0; i < datosTecnomecanica.size(); i++) {

                if (datosTecnomecanica.get(i).getCodigo().equals(busUsu)) {
                    verificarUsu = true;
                    JOptionPane.showMessageDialog(null,
                            "Información de tecnomecanica:\nCodigo: "
                            +datosTecnomecanica.get(i).getCodigo()+"\nCentro de diagnostico: "
                            +datosTecnomecanica.get(i).getCentroDiagnostico()+"\nFecha de expedicion: "
                            +datosTecnomecanica.get(i).getFechaExpedicion()+"\nFecha Vigencia: "
                            +datosTecnomecanica.get(i).getFechaVigente());        
    
                }
            }

            if (verificarUsu == false) {
                JOptionPane.showMessageDialog(null, "codigo Incorrecto");
            }

        }
    }
    
    
    
    public void verTecnomecanica(){
        VerTecnomecanica linc=new VerTecnomecanica();
        if (bImg==false) {
        linc.cargarImg(img);
        linc.setVisible(true);  
        }else if (bImg==true) {
        linc.setVisible(true);
        bImg=false;
        }
        
    }
    
    public ImageIcon cargarFotoTecnicomecanica() {
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
            rutaFoto = file;
            return newIcon;
        }
        return null;
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
            java.util.logging.Logger.getLogger(Tecnomecanica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tecnomecanica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tecnomecanica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tecnomecanica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tecnomecanica().setVisible(true);
            }
        });
    }
    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserComboExpedicion;
    private datechooser.beans.DateChooserCombo dateChooserComboVigente;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBImgTecnomecanica;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jBVerTecno;
    private javax.swing.JButton jButton11;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCentroDiagnostico;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JTable jTTecnomecanica;
    // End of variables declaration//GEN-END:variables
}
