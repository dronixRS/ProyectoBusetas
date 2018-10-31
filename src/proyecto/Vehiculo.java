/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

//import com.sun.xml.internal.fastinfoset.EncodingConstants;
import controlador.AsistenteDAO;
import controlador.ConductorDAO;
import controlador.Conexion;
import controlador.SOATDAO;
import controlador.TecnicomecanicaDAO;
import controlador.VehiculoDAO;
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
import modelo.ConductorVO;
import modelo.VehiculoVO;

/**
 *
 * @author Usuario
 */
public class Vehiculo extends javax.swing.JFrame {
private Connection conexion;
    private Conexion conector;
    
    /**
     * Creates new form Conductor
     */
  
    String placa, model, tipo_vehiculo, empresa, marca, conductor, asistente, soat, tecnoM, ruta_foto;
    int numero, capacidad;
    boolean estado;
    String id2;
     private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
    ImageIcon img;
    ArrayList<VehiculoVO> datosFuncionario;
    ArrayList<String> datosInicio;
    VehiculoVO transFuncionario;
    boolean bImg=false;
    String id_func="";
//    ArrayList<FuncionarioVO> datosFuncionario;
//    FuncionarioVO transFuncionario;
    DefaultTableModel modelo;
    int nivel,posicionUsuario = -1;
    boolean guardarEditar=false;
    VehiculoDAO BDConductor;
    ArrayList<String> datosFuncionarioTabla;
    ArrayList<String> conductores_id;
    ArrayList<String> asistentes_id;
    String fecha;
     String asis="", cond="";
    /**
     * Creates new form Vehiculo
     */
    
    public Vehiculo() {
        initComponents();

        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        
        bloquearCajas();
       
        BDConductor=new VehiculoDAO();
        
         AsistenteDAO asistentes= new AsistenteDAO();
        asistentes_id=asistentes.buscarConductorID();
        ConductorDAO conductores= new ConductorDAO();
        conductores_id=conductores.buscarConductorID();
        
        
        datosFuncionario=new ArrayList<VehiculoVO>();
        datosInicio=new ArrayList<String>();
        datosFuncionarioTabla=new ArrayList<String>();
        datosFuncionarioTabla=BDConductor.buscarConductor();
        datosInicio=BDConductor.buscarConductor();
       
        modelo = new DefaultTableModel();
        modelo.addColumn("Placa");
        modelo.addColumn("Numero");
        modelo.addColumn("Modulo");
        modelo.addColumn("Tipo Vehiculo");
        modelo.addColumn("Capacidad");
        modelo.addColumn("Empresa");
        modelo.addColumn("Marca");
        modelo.addColumn("Conductor");
        modelo.addColumn("Asistente");
        modelo.addColumn("Soat");
        modelo.addColumn("Tecnomecanica");
        //modelo.addColumn("Edad");
        this.jTVeh.setModel(modelo);
        this.jTVeh.getColumn(jTVeh.getColumnName(0)).setMaxWidth(100);
        this.jTVeh.getColumn(jTVeh.getColumnName(4)).setMaxWidth(100);
        this.jTVeh.getColumn(jTVeh.getColumnName(8)).setMaxWidth(100);
         BDConductor=new VehiculoDAO();
         cargarTablaInicio();
         cargarJCB();
    }
    public void cargarJCB(){
         for (int i = 0; i < asistentes_id.size(); i=i+2) {
            jCBAsistente.addItem((asistentes_id.get(i+1)));
        }
         for (int i = 0; i < conductores_id.size(); i=i+2) {
            jCBConductor.addItem((conductores_id.get(i+1)));
        }
    }
    public void obIDFunc(String id){
        id_func=id;
    }
    
    public void cargarTablaInicio(){
      String ver;
        
        for (int i = 0; i < datosFuncionarioTabla.size(); i=i+14) {
            ver=datosFuncionarioTabla.get(i+13);
            if (ver.equals("1")) {
               
                    
                     for (int k = 0; k < conductores_id.size(); k=k+3) {
                 if ((datosFuncionarioTabla.get(i+8)).equals(conductores_id.get(k))) {
                     cond=(conductores_id.get(k+1));
                 }
             }
                        for (int k = 0; k < asistentes_id.size(); k=k+3) {
                 if ((datosFuncionarioTabla.get(i+9)).equals(asistentes_id.get(k))) {
                     asis=(asistentes_id.get(k+1));
                 }
             }
            
              String[] datos=new String[11];
            datos[0]=datosFuncionarioTabla.get(i);
            datos[1]=datosFuncionarioTabla.get(i+1);
            datos[2]=datosFuncionarioTabla.get(i+2);
            datos[3]=datosFuncionarioTabla.get(i+3);
            datos[4]=datosFuncionarioTabla.get(i+4);
            datos[5]=datosFuncionarioTabla.get(i+5);
            datos[6]=datosFuncionarioTabla.get(i+6);;
            datos[7]=cond;
            datos[8]=asis;
            datos[9]=datosFuncionarioTabla.get(i+10);
            datos[10]=datosFuncionarioTabla.get(i+11);
            modelo.addRow(datos);  
            
            placa = datosFuncionarioTabla.get(i);
            numero =Integer.parseInt(datosFuncionarioTabla.get(i+1));
            model = datosFuncionarioTabla.get(i+2);
            tipo_vehiculo= datosFuncionarioTabla.get(i+3);
            capacidad = Integer.parseInt(datosFuncionarioTabla.get(i+4));
            empresa = datosFuncionarioTabla.get(i+5);
            marca = datosFuncionarioTabla.get(i+6);
            ruta_foto= datosFuncionarioTabla.get(i+7);
            
            soat=datosFuncionarioTabla.get(i+10);
            tecnoM= datosFuncionarioTabla.get(i+11);
           estado=true;
               
            
             
            
             
            transFuncionario = new VehiculoVO(placa,numero, model, tipo_vehiculo,capacidad, empresa, marca,ruta_foto, cond, asis, soat, tecnoM,id_func, estado);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTFPlaca = new javax.swing.JTextField();
        jTFNumero = new javax.swing.JTextField();
        jTFModelo = new javax.swing.JTextField();
        jCBTipoV = new javax.swing.JComboBox<>();
        jSCapacidad = new javax.swing.JSpinner();
        jTFEmpresa = new javax.swing.JTextField();
        jCBConductor = new javax.swing.JComboBox<>();
        jCBAsistente = new javax.swing.JComboBox<>();
        jCBMarca = new javax.swing.JComboBox<>();
        jTFTecnomecanica = new javax.swing.JTextField();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jBBuscarConduc = new javax.swing.JButton();
        jBBuscarAsis = new javax.swing.JButton();
        jBCargarSoat = new javax.swing.JButton();
        jBCargarTecno = new javax.swing.JButton();
        jLFoto = new javax.swing.JLabel();
        jBFoto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTVeh = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jTFSOAT = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso Vehiculo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Placa:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Numero:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Modelo:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Tipo Vehiculo:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Capacidad:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Empresa:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Marca:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Conductor:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Asistente:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("SOAT:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Tecnomecanica:");

        jCBTipoV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBTipoV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciones:", "Bus", "Buseta", "Micro-Bus" }));
        jCBTipoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBTipoVActionPerformed(evt);
            }
        });

        jCBConductor.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBConductor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciones:" }));
        jCBConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBConductorActionPerformed(evt);
            }
        });

        jCBAsistente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBAsistente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciones:" }));
        jCBAsistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBAsistenteActionPerformed(evt);
            }
        });

        jCBMarca.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciones:", "Renault", "Chevrolet", "International" }));

        jTFTecnomecanica.setEditable(false);

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

        jBBuscarConduc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBBuscarConduc.setText("Buscar");
        jBBuscarConduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarConducActionPerformed(evt);
            }
        });

        jBBuscarAsis.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBBuscarAsis.setText("Buscar");
        jBBuscarAsis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarAsisActionPerformed(evt);
            }
        });

        jBCargarSoat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBCargarSoat.setText("Cargar SOAT");
        jBCargarSoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarSoatActionPerformed(evt);
            }
        });

        jBCargarTecno.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBCargarTecno.setText("Cargar Tecnomecanica");
        jBCargarTecno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarTecnoActionPerformed(evt);
            }
        });

        jBFoto.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBFoto.setText("Foto");
        jBFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFotoActionPerformed(evt);
            }
        });

        jTVeh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTVeh);

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton11.setText("Salir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTFSOAT.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTFEmpresa, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCBMarca, javax.swing.GroupLayout.Alignment.LEADING, 0, 175, Short.MAX_VALUE)
                                            .addComponent(jCBConductor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jBBuscarConduc))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBTipoV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(103, 103, 103))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTFModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(jTFNumero)
                                    .addComponent(jTFPlaca))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jBFoto)
                                .addGap(67, 67, 67))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFTecnomecanica, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBCargarTecno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addComponent(jLabel10)
                                .addGap(77, 77, 77)
                                .addComponent(jTFSOAT, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBCargarSoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(55, 55, 55)
                                .addComponent(jCBAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBBuscarAsis)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTFPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTFModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jCBTipoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jSCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTFEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jCBMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jCBConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBBuscarConduc))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jCBAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBBuscarAsis))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jBCargarSoat)
                                    .addComponent(jTFSOAT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBFoto)
                                .addGap(234, 234, 234)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBCargarTecno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFTecnomecanica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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

    private void jBCargarSoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarSoatActionPerformed
        String so;
        
        so=JOptionPane.showInputDialog("Ingresar Codigo Soat:");
        buscarSoat(so);

    }//GEN-LAST:event_jBCargarSoatActionPerformed

    public void buscarSoat(String so){
     boolean verr=false;
        ArrayList<String> soatt;
        SOATDAO soo= new SOATDAO();
        soatt= soo.buscarConductorID();
        for (int i = 0; i < soatt.size(); i++) {
            if(so.equals(soatt.get(i))){
                jTFSOAT.setText(so);
                verr=true;
            }
        }
        if(verr==false){
           JOptionPane.showMessageDialog(null, "Codigo De Soat Incorrecto");
        }
    }
    
    
    
    private void jBCargarTecnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarTecnoActionPerformed
    String so;
        
        so=JOptionPane.showInputDialog("Ingresar Codigo Tecnomecanica:");
        
        buscarTecno(so);

    }//GEN-LAST:event_jBCargarTecnoActionPerformed
public void buscarTecno(String so){
    boolean verr=false;
        ArrayList<String> soatt;
        TecnicomecanicaDAO soo= new TecnicomecanicaDAO();
        soatt= soo.buscarConductorID();
        for (int i = 0; i < soatt.size(); i++) {
            if(so.equals(soatt.get(i))){
                jTFTecnomecanica.setText(so);
                verr=true;
            }
        }
        if(verr==false){
           JOptionPane.showMessageDialog(null, "Codigo De Tecnomecanica Incorrecto");
        }
    }
    private void jBFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFotoActionPerformed
       jLFoto.setIcon(cargarFoto());
    }//GEN-LAST:event_jBFotoActionPerformed

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

    private void jCBConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBConductorActionPerformed


        
    }//GEN-LAST:event_jCBConductorActionPerformed

    private void jCBAsistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBAsistenteActionPerformed

        
        
        
    }//GEN-LAST:event_jCBAsistenteActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed

  try {
            String buscaIdent = JOptionPane.showInputDialog("Ingresar placa para eliminar");
            //se llama el metodo buscarUsuarioEditar y se envia la variable buscaident la cual posee la identificacion
            buscarUsuarioElm(buscaIdent);
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jCBTipoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBTipoVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBTipoVActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
dispose();      
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jBBuscarConducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarConducActionPerformed
       
        String so;
        
        so=JOptionPane.showInputDialog("Ingresar Identificacion Conductor:");
        buscarConductor(so);
        
    }//GEN-LAST:event_jBBuscarConducActionPerformed

    private void jBBuscarAsisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarAsisActionPerformed
       String so;
        
        so=JOptionPane.showInputDialog("Ingresar Identificacion Asistente:");
        buscarAsistente(so);
    }//GEN-LAST:event_jBBuscarAsisActionPerformed
    public void buscarConductor(String so){
     boolean verr=false;
        ArrayList<String> soatt;
        ConductorDAO soo= new ConductorDAO();
        soatt= soo.buscarConductorID();
        for (int i = 0; i < soatt.size(); i=i+1) {
            if(so.equals(soatt.get(i))){
                jCBConductor.setSelectedItem(soatt.get(i+1));
                verr=true;
            }
        }
        if(verr==false){
           JOptionPane.showMessageDialog(null, "Identificacion Incorrecto");
        }
    }
    public void buscarAsistente(String so){
     boolean verr=false;
        ArrayList<String> soatt;
        AsistenteDAO soo= new AsistenteDAO();
        soatt= soo.buscarConductorID();
        for (int i = 0; i < soatt.size(); i=i+1) {
            if(so.equals(soatt.get(i))){
                jCBAsistente.setSelectedItem(soatt.get(i+1));
                verr=true;
            }
        }
        if(verr==false){
           JOptionPane.showMessageDialog(null, "Identificacion Incorrecto");
        }
    }
    public void buscarUsuarioElm(String busUsu) {
             boolean verificar = false;
        if (datosFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Usuarios Registrados");
        } else {
            
            for (int i = 0; i < datosFuncionario.size(); i++) {

                if (datosFuncionario.get(i).getPlaca().equals(busUsu)) {  
                     posicionUsuario = i;
                     id2=datosFuncionario.get(i).getPlaca();
                     System.out.println(id2);
                      BDConductor.eliminarConductor(id2);
                    datosFuncionario.remove(posicionUsuario);
                    verificar = true;
                     for (int j= jTVeh.getRowCount(); j >=0; j--) {
            modelo.removeRow(i);    
            }
                     
                    for (int j = 0; j < datosFuncionario.size(); j++) {
                        
                        
                        
             
             
                String[] datos = new String[11];
        datos[0] = datosFuncionario.get(j).getPlaca();
        datos[1] = Integer.toString(datosFuncionario.get(j).getNumero());
        datos[2] = datosFuncionario.get(j).getModelo();
        datos[3] = datosFuncionario.get(j).getTipo();
        datos[4] = Integer.toString(datosFuncionario.get(j).getCapacidad());
        datos[5] = datosFuncionario.get(j).getEmpresa();
        datos[6] = datosFuncionario.get(j).getMarca();
        
        for (int k = 0; k < conductores_id.size(); k=k+3) {
                 if ((datosFuncionario.get(j).getId_cond()).equals(conductores_id.get(k))) {
                     cond=(conductores_id.get(k+1));
                 }
             }
             
        datos[7] =cond; 
        
        for (int k = 0; k < asistentes_id.size(); k=k+3) {
                 if ((datosFuncionario.get(j).getId_asis()).equals(asistentes_id.get(k))) {
                     asis=(asistentes_id.get(k+1));
                 }
             }
        datos[8] = asis;
        datos[9] = datosFuncionario.get(j).getCod_SOAT();
        datos[10] = datosFuncionario.get(j).getCod_tecno();
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
                
                        for (int j = 0; j < asistentes_id.size(); j=j+3) {
                 if ((datosFuncionario.get(i).getId_asis()).equals(asistentes_id.get(j))) {
                     asis=(asistentes_id.get(j+1));
                 }
             }
             for (int k = 0; k < conductores_id.size(); k=k+3) {
                 if ((datosFuncionario.get(i).getId_cond()).equals(conductores_id.get(k))) {
                     cond=(conductores_id.get(k+1));
                 }
             }
                
                if (datosFuncionario.get(i).getPlaca().equals(busUsu)) {
                    verificarUsu = true;
                    JOptionPane.showMessageDialog(null,
                            "Información de Usuario:\nPlaca: "
                            +datosFuncionario.get(i).getPlaca()+"\nNumero: "
                            +datosFuncionario.get(i).getNumero()+"\nModelo: "
                            +datosFuncionario.get(i).getModelo()+"\nTipo De Vehiculo: "
                            +datosFuncionario.get(i).getTipo()+"\nCapacidad: "
                            +datosFuncionario.get(i).getCapacidad()+"\nEmpresa: "
                            +datosFuncionario.get(i).getEmpresa()+"\nMarca: "        
                            +datosFuncionario.get(i).getMarca()+"\nConductor: "
                            +cond+"\nAsistente: "
                            +asis+"\nCodigo Del Soat: "
                            +datosFuncionario.get(i).getCod_SOAT()+"\nCodigo De La Tecnomecanica: "
                            +datosFuncionario.get(i).getCod_tecno());        
                           
                    

                    
                }
            }

            if (verificarUsu == false) {
                JOptionPane.showMessageDialog(null, "Identificación Incorrecta");
            }

        }
    }
    
     public void buscarUsuarioEditar(String busUsu) {
        bImg=false;
        Licencia linc=new Licencia();
        boolean verificar = false;
        if (datosFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Usuarios Registrados");
        } else {
            for (int i = 0; i < datosFuncionario.size(); i++) {
                
                if (datosFuncionario.get(i).getPlaca().equals(busUsu)) {
                    posicionUsuario = i;
                    guardarEditar=true;
                    activarCajas();
                    
                    
                    verificar = true;
                    jTFPlaca.setText(datosFuncionario.get(i).getPlaca());
                    id2=datosFuncionario.get(i).getPlaca();
                    jTFNumero.setText(Integer.toString(datosFuncionario.get(i).getNumero()));
                    jTFModelo.setText(datosFuncionario.get(i).getModelo());
                    jTFEmpresa.setText(datosFuncionario.get(i).getEmpresa());
                    jTFSOAT.setText(datosFuncionario.get(i).getCod_SOAT());
                    jTFTecnomecanica.setText(datosFuncionario.get(i).getCod_tecno());
                    jCBConductor.setSelectedItem(datosFuncionario.get(i).getId_cond());
                    jCBAsistente.setSelectedItem(datosFuncionario.get(i).getId_asis());
                    jCBMarca.setSelectedItem(datosFuncionario.get(i).getMarca());
                    jCBTipoV.setSelectedItem(datosFuncionario.get(i).getTipo());
                    jSCapacidad.setValue(datosFuncionario.get(i).getCapacidad());
                    
                    
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
             placa = jTFPlaca.getText();
            numero = Integer.parseInt(jTFNumero.getText());
            model = jTFModelo.getText();
            tipo_vehiculo = (String)(jCBTipoV.getSelectedItem());
            capacidad = Integer.parseInt(jSCapacidad.getValue().toString());
            empresa = jTFEmpresa.getText();
            marca= (String)(jCBTipoV.getSelectedItem());
            conductor= (String)(jCBConductor.getSelectedItem());
            System.out.println(conductor);
            asistente= (String)(jCBAsistente.getSelectedItem());
            tecnoM=jTFTecnomecanica.getText();
            soat=jTFSOAT.getText();
            estado=true;
             
             for (int i = 0; i < asistentes_id.size(); i=i+3) {
                 if (asistente.equals(asistentes_id.get(i+1))) {
                     asis=(asistentes_id.get(i));
                 }
             }
             for (int i = 0; i < conductores_id.size(); i=i+3) {
                 if (conductor.equals(conductores_id.get(i+1))) {
                     cond=(conductores_id.get(i));
                 }
             }
             System.out.println("el Conductor es:" +cond);
              System.out.println("la Asistente es:" +asis);
            transFuncionario = new VehiculoVO(placa,numero,model, tipo_vehiculo,capacidad,empresa, marca,ruta_foto,cond, asis, soat, tecnoM,id_func, estado);
         
            datosFuncionario.set(posicionUsuario,transFuncionario);
                  
            BDConductor.editarConductor(transFuncionario,id2);
            for (int i = jTVeh.getRowCount()-1; i >=0; i--) {
            modelo.removeRow(i);    
            }
                           
            for (int i = 0; i < datosFuncionario.size(); i++) {
                String[] datos = new String[11];
        datos[0] = datosFuncionario.get(i).getPlaca();
        datos[1] =Integer.toString(datosFuncionario.get(i).getNumero());
        datos[2] = datosFuncionario.get(i).getModelo();
        datos[3] = datosFuncionario.get(i).getTipo();
        datos[4] = Integer.toString(datosFuncionario.get(i).getCapacidad());
        datos[5] = datosFuncionario.get(i).getEmpresa();
        datos[6] = datosFuncionario.get(i).getMarca();
        datos[7] = datosFuncionario.get(i).getId_cond();
        datos[8] = datosFuncionario.get(i).getId_asis();
        datos[9] = datosFuncionario.get(i).getCod_SOAT();
        datos[10] = datosFuncionario.get(i).getCod_tecno();
       
      
        modelo.addRow(datos);
            }
                  
                    
            
            return true;
        } else {
            return false;
        }
    }
    
     public void limpiarCajas() {
        jTFPlaca.setText("");
        jTFNumero.setText("");
        jTFModelo.setText("");
        jTFEmpresa.setText("");
        jTFSOAT.setText("");
        jTFTecnomecanica.setText("");
        jCBAsistente.setSelectedIndex(0);
        jCBConductor.setSelectedIndex(0);
        jCBMarca.setSelectedIndex(0);
        jCBTipoV.setSelectedIndex(0);
        jSCapacidad.setValue(0);
        jLFoto.setIcon(null);
    }
    
    
      public void cargarTabla() {
        String[] datos = new String[11];
        datos[0] = placa;
        datos[1] = Integer.toString(numero);
        datos[2] = model;
        datos[3] = tipo_vehiculo;
        datos[4] = Integer.toString(capacidad);
        datos[5] = empresa;
        datos[6] = marca;
        datos[7] = conductor;
        datos[8] =  asistente;
        datos[9] = soat;
        datos[10] =tecnoM;
        modelo.addRow(datos);
    }
    
    public boolean guardarDatos(){
         if (validarCajas() == true) {
            placa = jTFPlaca.getText();
            numero = Integer.parseInt(jTFNumero.getText());
            model = jTFModelo.getText();
            tipo_vehiculo = (String)(jCBTipoV.getSelectedItem());
            capacidad = Integer.parseInt((jSCapacidad.getValue().toString()));
            empresa = jTFEmpresa.getText();
            marca= (String)(jCBMarca.getSelectedItem());
            conductor= (String)(jCBConductor.getSelectedItem());
             System.out.println(conductor);
            asistente= (String)(jCBAsistente.getSelectedItem());
            tecnoM=jTFTecnomecanica.getText();
            soat=jTFSOAT.getText();
            estado=true;
            
           
             for (int i = 0; i < asistentes_id.size(); i=i+2) {
                 if (asistente.equals(asistentes_id.get(i+1))) {
                     asis=(asistentes_id.get(i));
                 }
             }
             for (int i = 0; i < conductores_id.size(); i=i+2) {
                 if (conductor.equals(conductores_id.get(i+1))) {
                     cond=(conductores_id.get(i));
                 }
             }
             
            transFuncionario = new VehiculoVO(placa,numero,model, tipo_vehiculo,capacidad,empresa, marca,ruta_foto,cond, asis, soat, tecnoM,id_func, estado);
            datosFuncionario.add(transFuncionario);
//            se envian los datos que se encuentran en funvionarioVO(transfuncionario) al metodo ingresarFuncionario que se encuentra en la clase FuncionarioDAO
            BDConductor.ingresarConductor(transFuncionario);
            return true;
        } else {
            return false;
        }
    
    }
  public boolean validarCajas() {
        
        if (jTFPlaca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Placa");
            return false;
        } else if (jTFNumero.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Numero");
            return false;
        } else if (jTFModelo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Modelo");
            return false;
        } else if (jTFEmpresa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingresar Empresa");
            return false;
        } else if (jCBAsistente.getSelectedItem().equals("Selecciones:")) {
            JOptionPane.showMessageDialog(null,
                    "Seleccionar Asistente");
            return false;
        } else if (jCBConductor.getSelectedItem().equals("Selecciones:")) {
            JOptionPane.showMessageDialog(null,
                    "Seleccionar Conductor");
            return false;
        } else if (jCBMarca.getSelectedItem().equals("Selecciones:")) {
            JOptionPane.showMessageDialog(null,
                    "Seleccionar Marca");
            return false;
        } else if (jCBTipoV.getSelectedItem().equals("Selecciones:")) {
            JOptionPane.showMessageDialog(null,
                    "Seleccionar Tipo De Vehiculo");
            return false;
        }else {
            return true;
        }
    }
  public void activarCajas(){
      
         jTFPlaca.setEnabled(true);
        jTFNumero.setEnabled(true);
        jTFModelo.setEnabled(true);
        jCBTipoV.setEnabled(true);
        jTFEmpresa.setEnabled(true);
        jCBMarca.setEnabled(true);
        jCBConductor.setEnabled(true);
        jCBAsistente.setEnabled(true);
        jTFSOAT.setEnabled(true);
        jSCapacidad.setEnabled(true);
        jTFTecnomecanica.setEnabled(true);
        jBFoto.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBBuscar.setEnabled(false);
        jBBuscarConduc.setEnabled(true);   
        jBBuscarAsis.setEnabled(true);
        jBCargarSoat.setEnabled(true);
        jBCargarTecno.setEnabled(true);
    }
  
      public void bloquearCajas(){
        jTFPlaca.setEnabled(false);
        jTFNumero.setEnabled(false);
        jTFModelo.setEnabled(false);
        jCBTipoV.setEnabled(false);
        jTFEmpresa.setEnabled(false);
        jCBMarca.setEnabled(false);
        jCBConductor.setEnabled(false);
        jCBAsistente.setEnabled(false);
        jTFSOAT.setEnabled(false);
        jTFTecnomecanica.setEnabled(false);
        jSCapacidad.setEnabled(false);
        jBFoto.setEnabled(false);
        jBGuardar.setEnabled(false);
        jBNuevo.setEnabled(true);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);
        jBBuscar.setEnabled(true);
        jBBuscarConduc.setEnabled(false);   
        jBBuscarAsis.setEnabled(false);
        jBCargarSoat.setEnabled(false);
        jBCargarTecno.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    public ImageIcon cargarFoto() {
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
            Image newImg = img.getScaledInstance(154, 154,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            //rutaImagen=file;
            ruta_foto = file;
            return newIcon;
        }
        return null;
    }
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
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBBuscarAsis;
    private javax.swing.JButton jBBuscarConduc;
    private javax.swing.JButton jBCargarSoat;
    private javax.swing.JButton jBCargarTecno;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBFoto;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jButton11;
    private javax.swing.JComboBox<String> jCBAsistente;
    private javax.swing.JComboBox<String> jCBConductor;
    private javax.swing.JComboBox<String> jCBMarca;
    private javax.swing.JComboBox<String> jCBTipoV;
    private javax.swing.JLabel jLFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSCapacidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFEmpresa;
    private javax.swing.JTextField jTFModelo;
    private javax.swing.JTextField jTFNumero;
    private javax.swing.JTextField jTFPlaca;
    private javax.swing.JTextField jTFSOAT;
    private javax.swing.JTextField jTFTecnomecanica;
    private javax.swing.JTable jTVeh;
    // End of variables declaration//GEN-END:variables
}
