package proyecto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controlador.Conexion;
import controlador.EstudianteDAO;
import controlador.FuncionarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.EstudianteVO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Estudiante  extends javax.swing.JFrame{

    private Connection conexion;
    private Conexion conector;
    
    String codigo,identificacion,nombre,apellido,celular,mail,direccion,grado,ciudad,nombreAcudiente,celularAcudiente,mailAcudiente,direccionAcudiente;

    String id_func = "";
    String id2;
    boolean estado;
    ArrayList<EstudianteVO> datosEstudiante;
    ArrayList<String> datosEstudiantesTabla;
    
    EstudianteVO transEstudiante;
    
    DefaultTableModel modelo;
    
    int posicionEstudiante = -1;
    EstudianteDAO BDEstudiante;
    boolean guardarEditar=false;
    /**
     * Creates new form Estudiante
     */
    public Estudiante() {
        initComponents();
        setLocationRelativeTo(null);
        bloquearCajas();
        
        BDEstudiante= new EstudianteDAO();
        datosEstudiante= new ArrayList<EstudianteVO>();
        datosEstudiantesTabla= new ArrayList<String>();
        datosEstudiantesTabla=BDEstudiante.buscarEstudiante();
    
        
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Identificacion");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("celular");
        modelo.addColumn("Mail");
        modelo.addColumn("Direccion");
        modelo.addColumn("Grado");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Nom.Acudi");
        modelo.addColumn("Cel.Acudi");
        modelo.addColumn("Mail.Acudi");
        modelo.addColumn("Direc.Acudiente");
       
        this.jTEstudiantes.setModel(modelo);
        this.jTEstudiantes.getColumn(jTEstudiantes.getColumnName(0)).setMaxWidth(80);
        this.jTEstudiantes.getColumn(jTEstudiantes.getColumnName(2)).setMaxWidth(80);
        this.jTEstudiantes.getColumn(jTEstudiantes.getColumnName(4)).setMaxWidth(80);
        this.jTEstudiantes.getColumn(jTEstudiantes.getColumnName(5)).setMaxWidth(50);
        
        BDEstudiante=new EstudianteDAO();
        cargarTablaInicio();

    }
    
    public void obtenerIdFunc(String id){
        id_func=id;
    }
    

    public void activarCajas(){

        jTFIdentificacionEstudiante.setEnabled(true);
        jTFCodigoEstudiante.setEnabled(true);
        jTFNombreEstudiante.setEnabled(true);
        jTFApellidosEstudiante.setEnabled(true);
        jTFCelularEstudiante.setEnabled(true);
        jTFMailEstudiante.setEnabled(true);
        jTFDireccionEstudiante.setEnabled(true);
        jTFGradoEstudiante.setEnabled(true);
        jTFCiudadEstudiante.setEnabled(true);
        jTFNombreAcudienteEstu.setEnabled(true);
        jTFCelularAcudienteEstu.setEnabled(true);
        jTFMailAcudienteEstu.setEnabled(true);
        jTFDireccionAcudienteEstu.setEnabled(true);

        jBGuardar.setEnabled(true);
        jBBuscar.setEnabled(false);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);

}
    
    
    
    public void cargarTablaInicio(){
        
        String ver;
        
        for (int i = 0; i < datosEstudiantesTabla.size();i=i+14) {
            
            ver=datosEstudiantesTabla.get(i+13);
            
            if(ver.equals("1")){
                
            String[] datos=new String[13];
            datos[0]=datosEstudiantesTabla.get(i);
            datos[1]=datosEstudiantesTabla.get(i+1);
            datos[2]=datosEstudiantesTabla.get(i+2);
            datos[3]=datosEstudiantesTabla.get(i+3);
            datos[4]=datosEstudiantesTabla.get(i+4);
            datos[5]=datosEstudiantesTabla.get(i+5);
            datos[6]=datosEstudiantesTabla.get(i+6);
            datos[7]=datosEstudiantesTabla.get(i+7);
            datos[8]=datosEstudiantesTabla.get(i+8);
            datos[9]=datosEstudiantesTabla.get(i+9);
            datos[10]=datosEstudiantesTabla.get(i+10);
            datos[11]=datosEstudiantesTabla.get(i+11);
            datos[12]=datosEstudiantesTabla.get(i+12);
            
            modelo.addRow(datos);
            
            
        codigo=datosEstudiantesTabla.get(i);
        identificacion= datosEstudiantesTabla.get(i+1);
        nombre= datosEstudiantesTabla.get(i+2);
        apellido= datosEstudiantesTabla.get(i+3);
        celular= datosEstudiantesTabla.get(i+4);
        mail= datosEstudiantesTabla.get(i+5);
        direccion= datosEstudiantesTabla.get(i+6);
        grado= datosEstudiantesTabla.get(i+7);
        ciudad= datosEstudiantesTabla.get(i+8);
        nombreAcudiente= datosEstudiantesTabla.get(i+9);
        celularAcudiente= datosEstudiantesTabla.get(i+10);
        mailAcudiente= datosEstudiantesTabla.get(i+11);
        direccionAcudiente= datosEstudiantesTabla.get(i+12);
        
        estado=true;
        
        transEstudiante = new EstudianteVO(codigo,identificacion,nombre,apellido,celular,mail,direccion,grado,ciudad,nombreAcudiente,celularAcudiente,mailAcudiente,direccionAcudiente,id_func,estado);
        datosEstudiante.add(transEstudiante); 
            
            
        }
        }               
 }
    
    
    public void bloquearCajas(){

        jTFIdentificacionEstudiante.setEnabled(false);
        jTFCodigoEstudiante.setEnabled(false);
        jTFNombreEstudiante.setEnabled(false);
        jTFApellidosEstudiante.setEnabled(false);
        jTFCelularEstudiante.setEnabled(false);
        jTFMailEstudiante.setEnabled(false);
        jTFDireccionEstudiante.setEnabled(false);
        jTFGradoEstudiante.setEnabled(false);
        jTFCiudadEstudiante.setEnabled(false);
        jTFNombreAcudienteEstu.setEnabled(false);
        jTFCelularAcudienteEstu.setEnabled(false);
        jTFMailAcudienteEstu.setEnabled(false);
        jTFDireccionAcudienteEstu.setEnabled(false);

        jBGuardar.setEnabled(false);
        jBBuscar.setEnabled(true);
        jBNuevo.setEnabled(true);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);

}
    
    
    public boolean guardarDatos() {
    
        if(validarCajas()==true){
        codigo=jTFCodigoEstudiante.getText();
        identificacion= jTFIdentificacionEstudiante.getText();
        nombre= jTFNombreEstudiante.getText();
        apellido= jTFApellidosEstudiante.getText();
        celular= jTFCelularEstudiante.getText();
        mail= jTFMailEstudiante.getText();
        direccion= jTFDireccionEstudiante.getText();
        grado= jTFGradoEstudiante.getText();
        ciudad= jTFCiudadEstudiante.getText();
        nombreAcudiente= jTFNombreAcudienteEstu.getText();
        celularAcudiente= jTFCelularAcudienteEstu.getText();
        mailAcudiente= jTFMailAcudienteEstu.getText();
        direccionAcudiente= jTFDireccionAcudienteEstu.getText();
        
       //obtenerIdFunc(id_func);
        estado=true;
        
        transEstudiante = new EstudianteVO(codigo,identificacion,nombre,apellido,celular,mail,direccion,grado,ciudad,nombreAcudiente,celularAcudiente,mailAcudiente,direccionAcudiente,id_func,estado);
        datosEstudiante.add(transEstudiante); 
        BDEstudiante.ingresarEstudiante(transEstudiante);
        return true;
        } else{
        return false;
        }
    }
    
    public void cargarTabla(){
    
        String[] datos = new String[13];
        
        datos[0] = codigo;
        datos[1] = identificacion;
        datos[2] = nombre;
        datos[3] = apellido;
        datos[4] = celular;
        datos[5] = mail;
        datos[6] = direccion;
        datos[7] = grado;
        datos[8] = ciudad;
        datos[9] = nombreAcudiente;
        datos[10] = celularAcudiente;
        datos[11] = mailAcudiente;
        datos[12] = direccionAcudiente;
       
        modelo.addRow(datos);

    }
    
    public void limpiarCajas(){
    
    jTFIdentificacionEstudiante.setText("");
        jTFCodigoEstudiante.setText("");
        jTFNombreEstudiante.setText("");
        jTFApellidosEstudiante.setText("");
        jTFCelularEstudiante.setText("");
        jTFMailEstudiante.setText("");
        jTFDireccionEstudiante.setText("");
        jTFGradoEstudiante.setText("");
        jTFCiudadEstudiante.setText("");
        jTFNombreAcudienteEstu.setText("");
        jTFCelularAcudienteEstu.setText("");
        jTFMailAcudienteEstu.setText("");
        jTFDireccionAcudienteEstu.setText("");
    
    
    }
    
    public boolean validarCajas(){
    
        if(jTFCodigoEstudiante.getText().isEmpty()){
        
            JOptionPane.showMessageDialog(null, "ingresar codigo");
            return false;
        } else if(jTFIdentificacionEstudiante.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar identificacion");
            return false;
        }else if(jTFNombreEstudiante.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar nombre");
            return false;
        }else if(jTFApellidosEstudiante.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar apellido");
            return false;
        }else if(jTFCelularEstudiante.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar celular");
            return false;
        }else if(jTFMailEstudiante.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar mail");
            return false;
        }else if(jTFDireccionEstudiante.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar direccion");
            return false;
        }else if(jTFGradoEstudiante.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar grado");
            return false;
        }else if(jTFCiudadEstudiante.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar ciudad");
            return false;
        }else if(jTFNombreAcudienteEstu.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar nombre acudiente");
            return false;
        }else if(jTFCelularAcudienteEstu.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar celular acudiente");
            return false;
        }else if(jTFMailAcudienteEstu.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar mail acudiente");
            return false;
        }else if(jTFDireccionAcudienteEstu.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar direccion acudiente");
            return false;
        }else{
        return true;
        }
    }
    
    public boolean modificarDatos(){

        if(validarCajas()==true){
        
            codigo=jTFCodigoEstudiante.getText();
            identificacion=jTFIdentificacionEstudiante.getText();
            nombre=jTFNombreEstudiante.getText();
            apellido=jTFApellidosEstudiante.getText();
            celular=jTFCelularEstudiante.getText();
            mail=jTFMailEstudiante.getText();
            direccion=jTFDireccionEstudiante.getText();
            grado=jTFGradoEstudiante.getText();
            ciudad=jTFCiudadEstudiante.getText();
            nombreAcudiente=jTFNombreAcudienteEstu.getText();
            celularAcudiente=jTFCelularAcudienteEstu.getText();
            mailAcudiente=jTFMailAcudienteEstu.getText();
            direccionAcudiente=jTFDireccionAcudienteEstu.getText();
            estado=true;
            transEstudiante = new EstudianteVO(codigo, identificacion, nombre, apellido, celular, mail, direccion, grado, ciudad, nombreAcudiente, celularAcudiente, mailAcudiente, direccionAcudiente,id_func,estado);
            datosEstudiante.set(posicionEstudiante, transEstudiante);
             BDEstudiante.modificarEstudiante(transEstudiante,id2);

            
            for (int i = jTEstudiantes.getRowCount()-1; i >= 0; i--) {
                modelo.removeRow(i);
            }
            
            for (int i = 0; i < datosEstudiante.size(); i++) {
                
                String[] datos = new String[13];
        
        datos[0] = datosEstudiante.get(i).getCodigo();
        datos[1] = datosEstudiante.get(i).getIdentificacion();
        datos[2] = datosEstudiante.get(i).getNombre();
        datos[3] = datosEstudiante.get(i).getApellido();
        datos[4] = datosEstudiante.get(i).getCelular();
        datos[5] = datosEstudiante.get(i).getCorreo();
        datos[6] = datosEstudiante.get(i).getDireccion();
        datos[7] = datosEstudiante.get(i).getGrado();
        datos[8] = datosEstudiante.get(i).getCiudad();
        datos[9] = datosEstudiante.get(i).getNombreAcudiente();
        datos[10] = datosEstudiante.get(i).getCelularAcudiente();
        datos[11] = datosEstudiante.get(i).getMailAcudiente();
        datos[12] = datosEstudiante.get(i).getDireccionAcudiente();
       
        modelo.addRow(datos);
       
            }
            
            return true;
            
        }else{
        return false;
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
        jTFCodigoEstudiante = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFIdentificacionEstudiante = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFNombreEstudiante = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFMailEstudiante = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFCelularEstudiante = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFApellidosEstudiante = new javax.swing.JTextField();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTEstudiantes = new javax.swing.JTable();
        jBSalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTFDireccionEstudiante = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTFNombreAcudienteEstu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTFCelularAcudienteEstu = new javax.swing.JTextField();
        jTFMailAcudienteEstu = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTFDireccionAcudienteEstu = new javax.swing.JTextField();
        jTFGradoEstudiante = new javax.swing.JTextField();
        jTFCiudadEstudiante = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso Estudiante", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Codigo:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Identificacion:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Nombres:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("E-Mail:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Celular:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Apellidos:");

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

        jTEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTEstudiantes);

        jBSalir.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Direccion:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Grado:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Ciudad:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Nombre Acudiente:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Celular Acudiente:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("E-Mail Acudiente:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Direccion Acudiente:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFIdentificacionEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFDireccionAcudienteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFMailAcudienteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFCelularAcudienteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNombreAcudienteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFDireccionEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFApellidosEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFCiudadEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFGradoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFMailEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCelularEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFNombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFCodigoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFCodigoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTFIdentificacionEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTFNombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTFApellidosEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTFCelularEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTFMailEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTFDireccionEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTFGradoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTFCiudadEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTFNombreAcudienteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFCelularAcudienteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFMailAcudienteEstu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTFDireccionAcudienteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed

        try  {
                String buscarCodigoEstuElimi = JOptionPane.showInputDialog("ingrese codigo para eliminar");
                buscarCodigoEliminar(buscarCodigoEstuElimi);
           }catch(Exception n){
                       System.err.println(n);

           }
        
        
        
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed
        activarCajas();
    }//GEN-LAST:event_jBNuevoActionPerformed

    
    
   
    
    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed

               
        if (guardarEditar==false){
            
        if(guardarDatos()==true){
            
        JOptionPane.showMessageDialog(null,"guardado");
        cargarTabla();
        limpiarCajas();        
        posicionEstudiante=-1;
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

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed

           try{
           
               String buscarCodigo = JOptionPane.showInputDialog("ingrese codigo para editar");
               buscarEstudianteEditar(buscarCodigo);
           }catch (Exception p){
               System.err.println(p);
           }    
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed

           try  {
                String buscarCodigoEstu = JOptionPane.showInputDialog("ingrese codigo para buscar");
                buscarCodigo(buscarCodigoEstu);
           }catch(Exception n){
                       System.err.println(n);

           }
        
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jBSalirActionPerformed

    public void buscarEstudianteEditar(String busEstu){
        boolean verificar=false;
    
        if(datosEstudiante.isEmpty()){
        JOptionPane.showMessageDialog(null, "no hay estudiantes registrados");
        }else{
        
            for (int i = 0; i < datosEstudiante.size(); i++) {
                
                if(datosEstudiante.get(i).getCodigo().equals(busEstu)){
                
                posicionEstudiante=i; 
                verificar=true;
                    
                jTFCodigoEstudiante.setText(datosEstudiante.get(i).getCodigo());
                id2=datosEstudiante.get(i).getCodigo();
                jTFIdentificacionEstudiante.setText(datosEstudiante.get(i).getIdentificacion());
                jTFNombreEstudiante.setText(datosEstudiante.get(i).getNombre());
                jTFApellidosEstudiante.setText(datosEstudiante.get(i).getApellido());
                jTFCelularEstudiante.setText(datosEstudiante.get(i).getCelular());
                jTFMailEstudiante.setText(datosEstudiante.get(i).getCorreo());
                jTFDireccionEstudiante.setText(datosEstudiante.get(i).getDireccion());
                jTFGradoEstudiante.setText(datosEstudiante.get(i).getGrado());
                jTFCiudadEstudiante.setText(datosEstudiante.get(i).getCiudad());
                jTFNombreAcudienteEstu.setText(datosEstudiante.get(i).getNombreAcudiente());
                jTFCelularAcudienteEstu.setText(datosEstudiante.get(i).getCelularAcudiente());
                jTFMailAcudienteEstu.setText(datosEstudiante.get(i).getMailAcudiente());
                jTFDireccionAcudienteEstu.setText(datosEstudiante.get(i).getDireccionAcudiente());
                
                activarCajas();
                guardarEditar=true;
                }
            }
            
            if(verificar==false){
                
        JOptionPane.showMessageDialog(null, "codigo incorrecto");
            
            }
        verificar=false;
        }
    }
    
    
    public void buscarCodigo(String busCodi){
    
    boolean verificarCodigo=false;
    
    if(datosEstudiante.isEmpty()){
                JOptionPane.showMessageDialog(null, "No hay codigo Registrados");
    }else{
    
        for (int i = 0; i < datosEstudiante.size(); i++) {
            
            if(datosEstudiante.get(i).getCodigo().equals(busCodi)){
            
                        verificarCodigo=true;
                
                        JOptionPane.showMessageDialog(null, "informacion estudiante: \n codigo: "
                        +datosEstudiante.get(i).getCodigo()+"\n identificacion: "
                        +datosEstudiante.get(i).getIdentificacion()+"\n nombre: "
                        +datosEstudiante.get(i).getNombre()+"\n apellido: "
                        +datosEstudiante.get(i).getApellido()+"\n celular: "
                        +datosEstudiante.get(i).getCelular()+"\n mail: "
                        +datosEstudiante.get(i).getCorreo()+"\n direccion: "
                        +datosEstudiante.get(i).getDireccion()+"\n grado: "
                        +datosEstudiante.get(i).getGrado()+"\n ciudad: "
                        +datosEstudiante.get(i).getCiudad()+"\n nombre acudiente: "
                        +datosEstudiante.get(i).getNombreAcudiente()+"\n celular acudiente: "
                        +datosEstudiante.get(i).getCelularAcudiente()+"\n mail acudiente: "
                        +datosEstudiante.get(i).getMailAcudiente()+"\n direccion acudiente: "
                        +datosEstudiante.get(i).getDireccionAcudiente());
                
                guardarEditar=true;
                
            }
            
        }
        
        if(verificarCodigo==false){
                JOptionPane.showMessageDialog(null, "codigo Incorrecto");
        }
        verificarCodigo=false;
        
    }
        
        
    }
    
    
    public void buscarCodigoEliminar(String busCodi) {
             boolean verificar = false;
        if (datosEstudiante.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Usuarios Registrados");
        } else {
            
            for (int i = 0; i < datosEstudiante.size(); i++) {

                if (datosEstudiante.get(i).getCodigo().equals(busCodi)) {  
                     posicionEstudiante = i;
                     id2=datosEstudiante.get(i).getCodigo();
                     //System.out.println(id2);
                      BDEstudiante.eliminarEstudiante(id2);
                    datosEstudiante.remove(posicionEstudiante);
                    verificar = true;
                     for (int j= jTEstudiantes.getRowCount(); j >=0; j--) {
            modelo.removeRow(i);    
            }
                     
                    for (int j = 0; j < datosEstudiante.size(); j++) {
                String[] datos = new String[13];
        datos[0] = datosEstudiante.get(j).getCodigo();
        datos[1] = datosEstudiante.get(j).getIdentificacion();
        datos[2] = datosEstudiante.get(j).getNombre();
        datos[3] = datosEstudiante.get(j).getApellido();
        datos[4] = datosEstudiante.get(j).getCelular();
        datos[5] = datosEstudiante.get(j).getCorreo();
        datos[6] = datosEstudiante.get(j).getDireccion();
        datos[7] = datosEstudiante.get(j).getGrado();
        datos[8] = datosEstudiante.get(j).getCiudad();
        datos[9] = datosEstudiante.get(j).getNombreAcudiente();
        datos[10] = datosEstudiante.get(j).getCelularAcudiente();
        datos[11] = datosEstudiante.get(j).getMailAcudiente();
        datos[12] = datosEstudiante.get(j).getDireccionAcudiente();

        modelo.addRow(datos);
        
       }
                  
                }
            }

            if (verificar == false) {
                JOptionPane.showMessageDialog(null, "Codigo Incorrecta");
            }
            verificar = false;
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
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jBSalir;
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
    private javax.swing.JTable jTEstudiantes;
    private javax.swing.JTextField jTFApellidosEstudiante;
    private javax.swing.JTextField jTFCelularAcudienteEstu;
    private javax.swing.JTextField jTFCelularEstudiante;
    private javax.swing.JTextField jTFCiudadEstudiante;
    private javax.swing.JTextField jTFCodigoEstudiante;
    private javax.swing.JTextField jTFDireccionAcudienteEstu;
    private javax.swing.JTextField jTFDireccionEstudiante;
    private javax.swing.JTextField jTFGradoEstudiante;
    private javax.swing.JTextField jTFIdentificacionEstudiante;
    private javax.swing.JTextField jTFMailAcudienteEstu;
    private javax.swing.JTextField jTFMailEstudiante;
    private javax.swing.JTextField jTFNombreAcudienteEstu;
    private javax.swing.JTextField jTFNombreEstudiante;
    // End of variables declaration//GEN-END:variables

    
}
