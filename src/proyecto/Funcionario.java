/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import controlador.ConductorDAO;
import controlador.Conexion;
import controlador.FuncionarioDAO;
import java.awt.Color;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.ConductorVO;
import modelo.FuncionarioVO;

/**
 *
 * @author Usuario
 */
public class Funcionario extends javax.swing.JFrame {
private Connection conexion;
    private Conexion conector;
   
    /**
     * Creates new form Conductor
     */
   
    String  ident, nombre, apellido, celular, direccion, correo, usuario, clave,id2;
    boolean estado;
    ArrayList<FuncionarioVO> datosFuncionario;
    ArrayList<String> datosInicio;
    FuncionarioVO transFuncionario;
    boolean bImg=false;
    DefaultTableModel modelo;
    int nivel,posicionUsuario = -1;
    boolean guardarEditar=false;
    FuncionarioDAO BDConductor;
    ArrayList<String> datosFuncionarioTabla;
    
    /**
     * Creates new form Funcionario
     */
    public Funcionario() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        bloquearCajas();
       
        BDConductor=new FuncionarioDAO();
        

        
        datosFuncionario=new ArrayList<FuncionarioVO>();
        datosInicio=new ArrayList<String>();
        datosFuncionarioTabla=new ArrayList<String>();
        datosFuncionarioTabla=BDConductor.buscarConductor();
        datosInicio=BDConductor.buscarConductor();
       
        modelo = new DefaultTableModel();
        modelo.addColumn("Ident.");
        modelo.addColumn("Nombre");
        modelo.addColumn("Celular");
        modelo.addColumn("correo");
        modelo.addColumn("Dirección");
        modelo.addColumn("Usuario");
        modelo.addColumn("Clave");
        
        this.jTFunc.setModel(modelo);
        this.jTFunc.getColumn(jTFunc.getColumnName(0)).setMaxWidth(100);
        this.jTFunc.getColumn(jTFunc.getColumnName(4)).setMaxWidth(100);
        this.jTFunc.getColumn(jTFunc.getColumnName(6)).setMaxWidth(100);
         BDConductor=new FuncionarioDAO();
         cargarTablaInicio();
    }
 public void bloquearCajas(){
         jTFIdent.setEnabled(false);
        jTFNombre.setEnabled(false);
        jTFApellido.setEnabled(false);
        jTFCelular.setEnabled(false);
        jTFCorreo.setEnabled(false);
        jTFDireccion.setEnabled(false);
        jTFUsuario.setEnabled(false);
        jTFClave.setEnabled(false);
        
        jBGuardar.setEnabled(false);
        jBNuevo.setEnabled(true);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);
        jBBuscar.setEnabled(true);
        
    }
  public void cargarTablaInicio(){
      String ver;
        
        for (int i = 0; i < datosFuncionarioTabla.size(); i=i+9) {
            ver=datosFuncionarioTabla.get(i+8);
            
            if (ver.equals("1")) {
              String[] datos=new String[7];
            datos[0]=datosFuncionarioTabla.get(i);
            datos[1]=datosFuncionarioTabla.get(i+1)+" "+datosFuncionarioTabla.get(i+2);
            datos[2]=datosFuncionarioTabla.get(i+3);
            datos[3]=datosFuncionarioTabla.get(i+4);
            datos[4]=datosFuncionarioTabla.get(i+5);
            datos[5]=datosFuncionarioTabla.get(i+6);
            datos[6]=datosFuncionarioTabla.get(i+7);
            modelo.addRow(datos);  
            
            ident = datosFuncionarioTabla.get(i);
            nombre = datosFuncionarioTabla.get(i+1);
            apellido = datosFuncionarioTabla.get(i+2);
            celular = datosFuncionarioTabla.get(i+3);
            correo = datosFuncionarioTabla.get(i+4);
            direccion = datosFuncionarioTabla.get(i+5);
            usuario= datosFuncionarioTabla.get(i+6);
            clave= datosFuncionarioTabla.get(i+7);
 
            
           estado=true;
             
            
             
            transFuncionario = new FuncionarioVO(ident, nombre, apellido, celular, correo, direccion,usuario,clave, estado);
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
        jTFIdent = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFCorreo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFCelular = new javax.swing.JTextField();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTFunc = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFUsuario = new javax.swing.JTextField();
        jTFClave = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionario");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso Funcionario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Identificacion:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Apellido:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Direccion:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("E-Mail:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Celular:");

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

        jTFunc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTFunc.setEnabled(false);
        jScrollPane1.setViewportView(jTFunc);

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton11.setText("Salir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Usuario:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Contraseña:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jTFClave)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jTFCorreo)
                            .addComponent(jTFCelular)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jTFIdent)
                            .addComponent(jTFApellido))))
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
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFIdent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTFCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTFClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        }
       }  
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
 try {
            String buscaIdent = JOptionPane.showInputDialog(
                    "Ingresar Identificación para editar");
            buscarUsuarioEditar(buscaIdent);
        } catch (Exception e) {
            System.err.println(e);
        }    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
 try {
    String buscaIdent = JOptionPane.showInputDialog(
       "Ingresar Identificación para editar");
       buscarUsuario(buscaIdent);
        } catch (Exception e) {
         System.err.println(e);
        }    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
 try {
            String buscaIdent = JOptionPane.showInputDialog("Ingresar identificacion para eliminar");
            //se llama el metodo buscarUsuarioEditar y se envia la variable buscaident la cual posee la identificacion
            buscarUsuarioElm(buscaIdent);
        } catch (Exception e) {
            System.err.println(e);
        }    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTFIdentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFIdentKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFIdentKeyTyped

    private void jTFCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCelularKeyTyped
 char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCelularKeyTyped
     
    public void buscarUsuarioElm(String busUsu) {
             boolean verificar = false;
        if (datosFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Usuarios Registrados");
        } else {
            
            for (int i = 0; i < datosFuncionario.size(); i++) {

                if (datosFuncionario.get(i).getIdentificacion().equals(busUsu)) {  
                     posicionUsuario = i;
                     id2=datosFuncionario.get(i).getIdentificacion();
                     System.out.println(id2);
                      BDConductor.eliminarConductor(id2);
                    datosFuncionario.remove(posicionUsuario);
                    verificar = true;
                     for (int j= jTFunc.getRowCount(); j >=0; j--) {
            modelo.removeRow(i);    
            }
                     
                    for (int j = 0; j < datosFuncionario.size(); j++) {
                String[] datos = new String[7];
        datos[0] = datosFuncionario.get(j).getIdentificacion();
        datos[1] = datosFuncionario.get(j).getNombre()+" "+datosFuncionario.get(j).getApellido();
        datos[2] = datosFuncionario.get(j).getCelular();
        datos[3] = datosFuncionario.get(j).getCorreo();
        datos[4] = datosFuncionario.get(j).getDireccion();
        datos[5] = datosFuncionario.get(j).getUsuario();
         datos[6] = datosFuncionario.get(j).getClave();
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

                if (datosFuncionario.get(i).getIdentificacion().equals(busUsu)) {
                    verificarUsu = true;
                    JOptionPane.showMessageDialog(null,
                            "Información de Usuario:\nIdentificación: "
                            +datosFuncionario.get(i).getIdentificacion()+"\nNombre: "
                            +datosFuncionario.get(i).getNombre()+"\nApellido: "
                            +datosFuncionario.get(i).getApellido()+"\nCelular: "
                            +datosFuncionario.get(i).getCelular()+"\nCorreo: "
                            +datosFuncionario.get(i).getCorreo()+"\nDireccion: "
                            +datosFuncionario.get(i).getDireccion()+"\nUsuario: "        
                            +datosFuncionario.get(i).getUsuario()+"\nClave: "
                            +datosFuncionario.get(i).getClave());       
                           
                    

                    
                }
            }

            if (verificarUsu == false) {
                JOptionPane.showMessageDialog(null, "Identificación Incorrecta");
            }

        }
    }
    
    
    public void buscarUsuarioEditar(String busUsu) {
        
       
        boolean verificar = false;
        if (datosFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Usuarios Registrados");
        } else {
            for (int i = 0; i < datosFuncionario.size(); i++) {
                
                if (datosFuncionario.get(i).getIdentificacion().equals(busUsu)) {
                    posicionUsuario = i;
                    guardarEditar=true;
                    activarCajas();
                    
                    
                    verificar = true;
                    jTFIdent.setText(datosFuncionario.get(i).getIdentificacion());
                    id2=datosFuncionario.get(i).getIdentificacion();
                    jTFNombre.setText(datosFuncionario.get(i).getNombre());
                    jTFApellido.setText(datosFuncionario.get(i).getApellido());
                    jTFCelular.setText(datosFuncionario.get(i).getCelular());
                    jTFCorreo.setText(datosFuncionario.get(i).getCorreo());
                    jTFDireccion.setText(datosFuncionario.get(i).getDireccion());
                    jTFUsuario.setText(datosFuncionario.get(i).getUsuario());
                    jTFClave.setText(datosFuncionario.get(i).getClave());
                    
                    
                    
                    
                    
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
            ident = jTFIdent.getText();
            nombre = jTFNombre.getText();
            apellido = jTFApellido.getText();
            celular = jTFCelular.getText();
            correo = jTFCorreo.getText();
            direccion = jTFDireccion.getText();
          usuario = jTFUsuario.getText();
            clave = jTFClave.getText();
            
            estado=true;
            transFuncionario = new FuncionarioVO(
                    ident, nombre, apellido,  celular, correo, direccion,usuario,clave,estado);
            
            datosFuncionario.set(posicionUsuario,transFuncionario);
//                       
            BDConductor.editarConductor(transFuncionario,id2);
            for (int i = jTFunc.getRowCount()-1; i >=0; i--) {
            modelo.removeRow(i);    
            }
                           
            for (int i = 0; i < datosFuncionario.size(); i++) {
                String[] datos = new String[7];
        datos[0] = datosFuncionario.get(i).getIdentificacion();
        datos[1] = datosFuncionario.get(i).getNombre()+" "+datosFuncionario.get(i).getApellido();
        datos[2] = datosFuncionario.get(i).getCelular();
        datos[3] = datosFuncionario.get(i).getCorreo();
        datos[4] = datosFuncionario.get(i).getDireccion();
        datos[5] = datosFuncionario.get(i).getUsuario();
        datos[6] = datosFuncionario.get(i).getClave();
        
       
      
        modelo.addRow(datos);
            }
                  
                    
            
            return true;
        } else {
            return false;
        }
    }
    
    public void cargarTabla() {
        String[] datos = new String[9];
        datos[0] = ident;
        datos[1] = nombre + " " + apellido;
        datos[2] = celular;
        datos[3] = correo;
        datos[4] = direccion;
        datos[5] =  usuario;
        datos[6] = clave;
      
        
        //datos[6] = edad + "";
        modelo.addRow(datos);
    }
     public void limpiarCajas() {
        jTFIdent.setText("");
        jTFNombre.setText("");
        jTFApellido.setText("");
        jTFCelular.setText("");
        jTFCorreo.setText("");
        jTFDireccion.setText("");
        jTFUsuario.setText("");
        jTFClave.setText("");
    }
    public boolean guardarDatos(){
         if (validarCajas() == true) {
            ident = jTFIdent.getText();
            nombre = jTFNombre.getText();
            apellido = jTFApellido.getText();
            celular = jTFCelular.getText();
            correo = jTFCorreo.getText();
            direccion = jTFDireccion.getText();
            usuario= jTFUsuario.getText();
            clave= jTFClave.getText();
           estado=true;
             
            
             
            transFuncionario = new FuncionarioVO(ident, nombre, apellido, celular, correo, direccion,usuario,clave, estado);
            datosFuncionario.add(transFuncionario);
//            se envian los datos que se encuentran en funvionarioVO(transfuncionario) al metodo ingresarFuncionario que se encuentra en la clase FuncionarioDAO
            BDConductor.ingresarConductor(transFuncionario);
            return true;
        } else {
            return false;
        }
    
    }
    
    public void activarCajas(){
         jTFIdent.setEnabled(true);
        jTFNombre.setEnabled(true);
        jTFApellido.setEnabled(true);
        jTFCelular.setEnabled(true);
        jTFCorreo.setEnabled(true);
        jTFDireccion.setEnabled(true);
        jTFUsuario.setEnabled(true);
        jTFClave.setEnabled(false);
        
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBBuscar.setEnabled(false);
        
    }
     public boolean validarCajas() {
        
        if (jTFIdent.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Identificación");
            return false;
        } else if (jTFNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Nombre");
            return false;
        } else if (jTFApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Apellido");
            return false;
        } else if (jTFCelular.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar celular");
            return false;
        } else if (jTFCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar correo");
            return false;
        }else if (jTFDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Dirección");
            return false;
        }else if (jTFUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Restriccion de Licencia");
            return false;
        }else if (jTFClave.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Restriccion de Licencia");
            return false;
        }else {
            return true;
        }
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
            java.util.logging.Logger.getLogger(Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Funcionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jButton11;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFApellido;
    private javax.swing.JTextField jTFCelular;
    private javax.swing.JPasswordField jTFClave;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFIdent;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFUsuario;
    private javax.swing.JTable jTFunc;
    // End of variables declaration//GEN-END:variables
}
