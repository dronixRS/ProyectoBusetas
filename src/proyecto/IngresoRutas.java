/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

//importaciones de otros paquetes del proyecto
import controlador.Conexion;
import controlador.RutaDAO;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.RutaVO;

/**
 *
 * @author Usuario
 */
public class IngresoRutas extends javax.swing.JFrame {

    //se llama de sql para crear la conexion 
    private Connection conexion;
    
    //llamamos la conexion que se encuentra en controlador
    private Conexion conector;
    
    //inicializamos las variables que vamos a necesitar 
    String codigo,periodo,zona,vehiculoPlaca,conductor,asistente,recorrido,horaInicio,horaFin,tRecorrido,cuposAsignados,cuposDisponibles,numeroParadas;
    boolean estado;
    
    String id_func;
    String id2;
    
    //creamos un arraylist que contenga los datos que vamos a cargar a la base de datos y otro con los ismos datos pero para cargar en la tabla 
    ArrayList<RutaVO> datosruta;
    ArrayList<String> datosRutaTabla;
    
    //trans ruta nos va a servir para cargar los datos que almacenamos a la base de datos 
    RutaVO transRuta;
    
    //creamos el modelo de la tabla 
    DefaultTableModel modelo;
    
    
    int posicionRuta = -1;
    
    //llamamos rutadao que es quien ejecuta la gestion de los datos en mysql
    RutaDAO BDRuta;
    
    //usamos esta variable para verificar cuando un dato se va a guardar despues de editar
    boolean guardarEditar=false;
    
    
    /**
     * Creates new form IngresoRutas
     */
    public IngresoRutas() {
        initComponents();
        setLocationRelativeTo(null);
        //llamamos el metodo bloquearCajas para que las casillas aparezcan bloqueaddas
        bloquearCajas();
        
        
        BDRuta= new RutaDAO();
        datosruta= new ArrayList<RutaVO>();
        datosRutaTabla= new ArrayList<String>();
        datosRutaTabla=BDRuta.buscarRuta();
        
        //le damos las columnas a la tabla
        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("periodo");
        modelo.addColumn("zona");
        modelo.addColumn("vehiculo placa");
        modelo.addColumn("conductor");
        modelo.addColumn("asistente");
        modelo.addColumn("recorrido");
        modelo.addColumn("hora I");
        modelo.addColumn("hora F");
        modelo.addColumn("T recorrido");
        modelo.addColumn("cup. asignados");
        modelo.addColumn("cup. disponibles");
        modelo.addColumn("paradas");
       
        //se asignan los espacios para el modelo de la tabla
        this.jTRuta.setModel(modelo);
        this.jTRuta.getColumn(jTRuta.getColumnName(0)).setMaxWidth(80);
        this.jTRuta.getColumn(jTRuta.getColumnName(2)).setMaxWidth(80);
        this.jTRuta.getColumn(jTRuta.getColumnName(4)).setMaxWidth(80);
        this.jTRuta.getColumn(jTRuta.getColumnName(5)).setMaxWidth(50);
        
        BDRuta=new RutaDAO();
        
        //cargarTablaInicio();
    }

    
    
    public void bloquearCajas(){
    
        //se ponen algunas casillas en falso para que queden bloqueadas
        jTFCodigo.setEnabled(false);
        jCBPeriodo.setEnabled(false);
        jCBZona.setEnabled(false);
        jCBVehiculoPlaca.setEnabled(false);
        jCBRecorrido.setEnabled(false);
        jCBHoraInicio.setEnabled(false);
        jCBHoraFinal.setEnabled(false);
        jCBCuposAsignados.setEnabled(false);
        jTFNumeroParadas.setEnabled(false);


        jBGuardar.setEnabled(false);
        jBBuscar.setEnabled(true);
        jBNuevo.setEnabled(true);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);
    
    
    
    }
    
    public void obtenerIdFunc(String id){
        //este metodo captura el id enviado desde el menu de inicio 
        id_func=id;
    }
    
    
     public void activarCajas(){
    
        //se ponen algunas casillas en verdadero para que queden desbloqueadas
        jTFCodigo.setEnabled(true);
        jCBPeriodo.setEnabled(true);
        jCBZona.setEnabled(true);
        jCBVehiculoPlaca.setEnabled(true);
        jCBRecorrido.setEnabled(true);
        jCBHoraInicio.setEnabled(true);
        jCBHoraFinal.setEnabled(true);
        jCBCuposAsignados.setEnabled(true);
        jTFNumeroParadas.setEnabled(true);


        jBGuardar.setEnabled(true);
        jBBuscar.setEnabled(false);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
    
    
    
    }
    
     
     public void limpiarCajas(){
     
        jTFCodigo.setText("");
        jCBPeriodo.setSelectedIndex(0);
        jCBZona.setSelectedIndex(0);
        jCBVehiculoPlaca.setSelectedIndex(0);
        jTFConductor.setText("");
        jTFAsistente.setText("");
        jCBRecorrido.setSelectedIndex(0);
        jCBHoraInicio.setSelectedIndex(0);
        jCBHoraFinal.setSelectedIndex(0);
        jCBCuposAsignados.setSelectedItem(0);
        jTFRecorrido.setText("");
        jTFDisponibles.setText("");
        jTFNumeroParadas.setText("");

     
     
     }
     
     
     
     
     public void cargarTabla(){
     
      String[] datos = new String[13];
        datos[0] = codigo;
        datos[1] = periodo;
        datos[2] = zona;
        datos[3] = vehiculoPlaca;
        datos[4] = conductor;
        datos[5] = asistente;
        datos[6] = recorrido;
        datos[7] = horaInicio;
        datos[8] =  horaFin;
        datos[9] = tRecorrido;
        datos[10] =cuposAsignados;
        datos [11]= cuposDisponibles;
        datos [12]= numeroParadas;
        modelo.addRow(datos);
     
     
     }
     
     
     public boolean modificarDatos(){
     
         if(validarCajas()==true){
         
         codigo=jTFCodigo.getText();
         periodo=(String)(jCBPeriodo.getSelectedItem());
         zona=(String)(jCBZona.getSelectedItem());
         vehiculoPlaca=(String)(jCBVehiculoPlaca.getSelectedItem());
         conductor=jTFConductor.getText();
         asistente=jTFAsistente.getText();
         recorrido=(String)(jCBRecorrido.getSelectedItem());
         horaInicio=(String)(jCBHoraInicio.getSelectedItem());
         horaFin=(String)(jCBHoraFinal.getSelectedItem());
         tRecorrido=jTFRecorrido.getText();
         cuposAsignados=(String)(jCBCuposAsignados.getSelectedItem());
         cuposDisponibles=jTFDisponibles.getText();
         numeroParadas=jTFNumeroParadas.getText();
         estado=true;
         
         transRuta = new RutaVO(codigo, periodo, zona, vehiculoPlaca, recorrido, horaInicio, horaFin, cuposAsignados, cuposDisponibles, conductor, asistente, tRecorrido, numeroParadas, id_func, estado);
         
         datosruta.set(posicionRuta,transRuta);
         
         BDRuta.editarRuta(transRuta, id2);
         
         
             for (int i = jTRuta.getRowCount()-1; i >= 0; i--) {
                 modelo.removeRow(i);
             }
         
             for (int i = 0; i < datosruta.size(); i++) {
                 
                 String[] datos = new String[13];
                 
                 datos[0]=datosruta.get(i).getCodigo();
                 datos[1]=datosruta.get(i).getPeriodo();
                 datos[2]=datosruta.get(i).getZona();
                 datos[3]=datosruta.get(i).getVehiculoPlaca();
                 datos[4]=datosruta.get(i).getConductor();
                 datos[5]=datosruta.get(i).getAsistente();
                 datos[6]=datosruta.get(i).getRecorrido();
                 datos[7]=datosruta.get(i).getHoraInicio();
                 datos[8]=datosruta.get(i).getHoraFin();
                 datos[9]=datosruta.get(i).getRecorrido();
                 datos[10]=datosruta.get(i).getCuposAsignados();
                 datos[11]=datosruta.get(i).getCuposDisponibles();
                 datos[12]=datosruta.get(i).getNumeroParadas();
                
                 modelo.addRow(datos);
             }
 
             return true;
             
             
             
         }else{
         
         return false;
         }
         
         
         
     }
     
     
     
     
     
     
     
     public boolean guardarDatos() {
    
        if(validarCajas()==true){
        codigo=jTFCodigo.getText();
        periodo= (String)(jCBPeriodo.getSelectedItem());
        zona= (String)(jCBZona.getSelectedItem());
        vehiculoPlaca= (String)(jCBVehiculoPlaca.getSelectedItem());
        recorrido= (String)(jCBRecorrido.getSelectedItem());
        horaInicio= (String)(jCBHoraInicio.getSelectedItem());
        horaFin= (String)(jCBHoraFinal.getSelectedItem());
        cuposAsignados= (String)(jCBCuposAsignados.getSelectedItem());
        cuposDisponibles= jTFDisponibles.getText();        
        conductor= jTFConductor.getText();
        asistente= jTFAsistente.getText();
        
        tRecorrido= jTFRecorrido.getText();
        
        numeroParadas= jTFNumeroParadas.getText();
        
        estado=true;
        
        transRuta = new RutaVO(codigo, periodo, zona, vehiculoPlaca, recorrido, horaInicio, horaFin, cuposAsignados, cuposDisponibles, conductor, asistente, tRecorrido, numeroParadas, id_func, estado);
        datosruta.add(transRuta); 
        BDRuta.ingresarRuta(transRuta);
        return true;
        } else{
        return false;
        }
    }
     
     public boolean validarCajas(){
    
         //se hace la validacion para segurarse de que los campos necesarios estan llenos podiendo guardar unicamente si retorna true
        if(jTFCodigo.getText().isEmpty()){
        
            JOptionPane.showMessageDialog(null, "ingresar codigo");
            return false;
        } else if(jCBPeriodo.getSelectedItem().equals("Seleccionar Periodo:")){
            
            JOptionPane.showMessageDialog(null, "ingresar periodo");
            return false;
        }else if(jCBZona.getSelectedItem().equals("Seleccionar Zona:")){
            
            JOptionPane.showMessageDialog(null, "ingresar zona");
            return false;
        }else if(jCBVehiculoPlaca.getSelectedItem().equals("Seleccionar Placa:")){
            
            JOptionPane.showMessageDialog(null, "ingresar placa");
            return false;
        }else if(jCBRecorrido.getSelectedItem().equals("Seleccionar Hora:")){
            
            JOptionPane.showMessageDialog(null, "ingresar recorrido");
            return false;
        }else if(jCBHoraInicio.getSelectedItem().equals("Seleccionar Hora:")){
            
            JOptionPane.showMessageDialog(null, "ingresar hora de inicio");
            return false;
        }else if(jCBHoraFinal.getSelectedItem().equals("Seleccionar Hora:")){
            
            JOptionPane.showMessageDialog(null, "ingresar hora final");
            return false;
        }else if(jCBCuposAsignados.getSelectedItem().equals("cupos")){
            
            JOptionPane.showMessageDialog(null, "ingresar cupos");
            return false;
        
        }else{
        return true;
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
        jTFCodigo = new javax.swing.JTextField();
        jCBVehiculoPlaca = new javax.swing.JComboBox<>();
        jTFAsistente = new javax.swing.JTextField();
        jCBHoraInicio = new javax.swing.JComboBox<>();
        jCBRecorrido = new javax.swing.JComboBox<>();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTRuta = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jCBPeriodo = new javax.swing.JComboBox<>();
        jCBZona = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        jTFConductor = new javax.swing.JTextField();
        jCBCuposAsignados = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jCBHoraFinal = new javax.swing.JComboBox<>();
        jTFRecorrido = new javax.swing.JTextField();
        jTFDisponibles = new javax.swing.JTextField();
        jTFNumeroParadas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso Rutas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Codigo:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Periodo:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Zona:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Vehiculo Placa:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Conductor:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Asistente:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Recorrido:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Hora Inicio:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Hora Final:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Cupos Asignados:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Num. Paradas");

        jTFCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCodigoKeyTyped(evt);
            }
        });

        jCBVehiculoPlaca.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBVehiculoPlaca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecccionar Placa", "1" }));

        jTFAsistente.setText("234");
        jTFAsistente.setEnabled(false);

        jCBHoraInicio.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBHoraInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Hora", "1" }));
        jCBHoraInicio.setToolTipText("");
        jCBHoraInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBHoraInicioActionPerformed(evt);
            }
        });

        jCBRecorrido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBRecorrido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Horario:", "Mañana", "Tarde", "Noche", " " }));
        jCBRecorrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBRecorridoActionPerformed(evt);
            }
        });

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

        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N

        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N

        jTRuta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTRuta);

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton11.setText("Salir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jCBPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Periodo:", "2017", "2018", "2019", "2020", "2021" }));

        jCBZona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Zona:", "1.Sur", "2.Norte", "3.Centro", "4.Este", "5.Oeste", "6.Otro" }));

        jButton12.setText("Buscar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTFConductor.setText("123");
        jTFConductor.setEnabled(false);

        jCBCuposAsignados.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBCuposAsignados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cupos", "1" }));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Disponibles:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("T. Recorrido:");

        jCBHoraFinal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jCBHoraFinal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Hora", "2" }));
        jCBHoraFinal.setToolTipText("");

        jTFRecorrido.setText("32");
        jTFRecorrido.setEnabled(false);

        jTFDisponibles.setText("43");
        jTFDisponibles.setEnabled(false);
        jTFDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFDisponiblesActionPerformed(evt);
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
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCBVehiculoPlaca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFAsistente)
                            .addComponent(jCBRecorrido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBHoraInicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFConductor)
                            .addComponent(jCBHoraFinal, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jTFRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton12))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBZona, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(245, 245, 245)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBCuposAsignados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNumeroParadas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jCBPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jCBZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jCBVehiculoPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTFConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTFAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jCBRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jCBHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14)
                            .addComponent(jCBHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jCBCuposAsignados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jTFDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTFNumeroParadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))))
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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jCBHoraInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBHoraInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBHoraInicioActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
dispose();    }//GEN-LAST:event_jButton11ActionPerformed

    private void jCBRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBRecorridoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBRecorridoActionPerformed

    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed
        //llama al metodo activar cajas
        activarCajas();
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        if (guardarEditar==false){
            
        if(guardarDatos()==true){
            
        JOptionPane.showMessageDialog(null,"guardado");
        cargarTabla();
        limpiarCajas();        
        posicionRuta=-1;
        bloquearCajas();
        }else{
        JOptionPane.showMessageDialog(null,"error de datos");
        }
 
        }else{
        
            if(modificarDatos()==true){
            JOptionPane.showMessageDialog(null,"modificado con exito");       
        limpiarCajas();        
        bloquearCajas();
        guardarEditar=false;
            }else{
            JOptionPane.showMessageDialog(null,"error datos");

            }
        }
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jTFDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFDisponiblesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFDisponiblesActionPerformed

    private void jTFCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCodigoKeyTyped
 char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCodigoKeyTyped

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
            java.util.logging.Logger.getLogger(IngresoRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoRutas().setVisible(true);
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
    private javax.swing.JButton jButton12;
    private javax.swing.JComboBox<String> jCBCuposAsignados;
    private javax.swing.JComboBox<String> jCBHoraFinal;
    private javax.swing.JComboBox<String> jCBHoraInicio;
    private javax.swing.JComboBox<String> jCBPeriodo;
    private javax.swing.JComboBox<String> jCBRecorrido;
    private javax.swing.JComboBox<String> jCBVehiculoPlaca;
    private javax.swing.JComboBox<String> jCBZona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFAsistente;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JTextField jTFConductor;
    private javax.swing.JTextField jTFDisponibles;
    private javax.swing.JTextField jTFNumeroParadas;
    private javax.swing.JTextField jTFRecorrido;
    private javax.swing.JTable jTRuta;
    // End of variables declaration//GEN-END:variables
}
