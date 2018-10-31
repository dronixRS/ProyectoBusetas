package proyecto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controlador.AsistenteDAO;
import controlador.Conexion;
import controlador.EstudianteDAO;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.AsistenteVO;
import modelo.EstudianteVO;

/**
 *
 * @author SEBASTIAN
 */
public class Asistente extends javax.swing.JFrame {

    private Connection conexion;
    private Conexion conector;
    
    String identificacion,nombre,apellido,celular,mail,direccion,rutaFoto;
    boolean bImg=false;
    String id_func="";
    String id2;
    boolean estado;
    ArrayList<AsistenteVO> datosAsistente;
    ArrayList<String> datosAsistenteTabla;
    AsistenteVO transAsistente;
    
    DefaultTableModel modelo;
    int posicionAsistente = -1;
    AsistenteDAO BDAsistente;
    boolean guardarEditar=false;

    
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
    
    public Asistente() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);        
        setLocationRelativeTo(null);
        bloquearCajas();
        
        BDAsistente= new AsistenteDAO();
        datosAsistente= new ArrayList<AsistenteVO>();
        datosAsistenteTabla= new ArrayList<String>();
        datosAsistenteTabla=BDAsistente.buscarAsistente();
        
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Identificacion");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("celular");
        modelo.addColumn("Mail");
        modelo.addColumn("Direccion");
        
        this.jTAsistente.setModel(modelo);
        this.jTAsistente.getColumn(jTAsistente.getColumnName(0)).setMaxWidth(80);
        this.jTAsistente.getColumn(jTAsistente.getColumnName(2)).setMaxWidth(80);
        this.jTAsistente.getColumn(jTAsistente.getColumnName(4)).setMaxWidth(80);
        this.jTAsistente.getColumn(jTAsistente.getColumnName(5)).setMaxWidth(50);
        
        BDAsistente= new AsistenteDAO();
        
        cargarTablaInicio();

    }
    
    public void obtenerIdFunc(String id){
        id_func=id;
    }

    
    public void cargarTablaInicio(){
        
        String ver;
        
        for (int i = 0; i < datosAsistenteTabla.size();i=i+8) {
            
            ver=datosAsistenteTabla.get(i+7);
            
            if(ver.equals("1")){
                
            String[] datos=new String[6];
            datos[0]=datosAsistenteTabla.get(i);
            datos[1]=datosAsistenteTabla.get(i+1);
            datos[2]=datosAsistenteTabla.get(i+2);
            datos[3]=datosAsistenteTabla.get(i+3);
            datos[4]=datosAsistenteTabla.get(i+4);
            datos[5]=datosAsistenteTabla.get(i+5);
            
            
            modelo.addRow(datos);
            
            
        identificacion= datosAsistenteTabla.get(i);
        nombre= datosAsistenteTabla.get(i+1);
        apellido= datosAsistenteTabla.get(i+2);
        celular= datosAsistenteTabla.get(i+3);
        mail= datosAsistenteTabla.get(i+4);
        direccion= datosAsistenteTabla.get(i+5);
        rutaFoto=datosAsistenteTabla.get(i+6);
        
        estado=true;
        
        transAsistente = new AsistenteVO(identificacion,nombre,apellido,celular,mail,direccion,rutaFoto,id_func,estado);
        datosAsistente.add(transAsistente); 
            
        
                System.out.println("la id es"+identificacion);
            
        }
        }               
 }
    
     public void activarCajas(){

        jTFIdentificacionAsistente.setEnabled(true);
        jTFNombreAsistente.setEnabled(true);
        jTFApellidoAsistente.setEnabled(true);
        jTFCelularAsistente.setEnabled(true);
        jTFMailAsistente.setEnabled(true);
        jTFDireccionAsistente.setEnabled(true);
        jBFotoAsistente.setEnabled(true);


        jBGuardar.setEnabled(true);
        jBBuscar.setEnabled(false);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);

} 
     
     public void bloquearCajas(){

        jTFIdentificacionAsistente.setEnabled(false);
        jTFNombreAsistente.setEnabled(false);
        jTFApellidoAsistente.setEnabled(false);
        jTFCelularAsistente.setEnabled(false);
        jTFMailAsistente.setEnabled(false);
        jTFDireccionAsistente.setEnabled(false);
        jBFotoAsistente.setEnabled(false);

        jBGuardar.setEnabled(false);
        jBBuscar.setEnabled(true);
        jBNuevo.setEnabled(true);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);

}
    
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
            rutaFoto = file;
            return newIcon;
        }
        return null;
    }
    
     
     public void limpiarCajas() {
        jTFIdentificacionAsistente.setText("");
        jTFNombreAsistente.setText("");
        jTFApellidoAsistente.setText("");
        jTFCelularAsistente.setText("");
        jTFMailAsistente.setText("");
        jTFDireccionAsistente.setText("");       
        jLabel12.setIcon(null);
    }
     
     public void cargarTabla(){
    
        String[] datos = new String[6];
        
        datos[0] = identificacion;
        datos[1] = nombre;
        datos[2] = apellido;
        datos[3] = celular;
        datos[4] = mail;
        datos[5] = direccion;
        
       
        modelo.addRow(datos);

    }
     
     public boolean guardarDatos() {
    
        if(validarCajas()==true){
        identificacion= jTFIdentificacionAsistente.getText();
        nombre= jTFNombreAsistente.getText();
        apellido= jTFApellidoAsistente.getText();
        celular= jTFCelularAsistente.getText();
        mail= jTFMailAsistente.getText();
        direccion= jTFDireccionAsistente.getText();
        
        
        estado=true;
        
        transAsistente = new AsistenteVO(identificacion,nombre,apellido,celular,mail,direccion,rutaFoto,id_func,estado);
        datosAsistente.add(transAsistente); 
        BDAsistente.ingresarAsistente(transAsistente);
        return true;
        } else{
        return false;
        }
    }
     
     public boolean validarCajas(){
    
        if(jTFIdentificacionAsistente.getText().isEmpty()){
        
            JOptionPane.showMessageDialog(null, "ingresar identificacion");
            return false;
        } else if(jTFNombreAsistente.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar nombre");
            return false;
        }else if(jTFApellidoAsistente.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar apellido");
            return false;
        }else if(jTFCelularAsistente.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar celular");
            return false;
        }else if(jTFMailAsistente.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar mail");
            return false;
        }else if(jTFDireccionAsistente.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "ingresar direccion");
            return false;
        }else{
        return true;
        }
        
    }
     
     public boolean modificarDatos(){

        if(validarCajas()==true){
        
            identificacion=jTFIdentificacionAsistente.getText();
            nombre=jTFNombreAsistente.getText();
            apellido=jTFApellidoAsistente.getText();
            celular=jTFCelularAsistente.getText();
            mail=jTFMailAsistente.getText();
            direccion=jTFDireccionAsistente.getText();
            
            
            estado=true;
            transAsistente = new AsistenteVO(identificacion, nombre, apellido, celular, mail, direccion,rutaFoto,id_func,estado);
            datosAsistente.set(posicionAsistente, transAsistente);
            BDAsistente.modificarAsistente(transAsistente,id2);
            
            for (int i = jTAsistente.getRowCount()-1; i >= 0; i--) {
                modelo.removeRow(i);
            }
            
            for (int i = 0; i < datosAsistente.size(); i++) {
                
                String[] datos = new String[6];
        
        datos[0] = datosAsistente.get(i).getIdentificacion();
        datos[1] = datosAsistente.get(i).getNombre();
        datos[2] = datosAsistente.get(i).getApellido();
        datos[3] = datosAsistente.get(i).getCelular();
        datos[4] = datosAsistente.get(i).getCorreo();
        datos[5] = datosAsistente.get(i).getDireccion();
        
       
        modelo.addRow(datos);
       
            }
            
            return true;
            
        }else{
        return false;
        }
}
     
     public void buscarAsistenteEditar(String busAsis){
        boolean verificar=false;
    
        if(datosAsistente.isEmpty()){
        JOptionPane.showMessageDialog(null, "no hay asistentes registrados");
        }else{
        
            for (int i = 0; i < datosAsistente.size(); i++) {
                
                if(datosAsistente.get(i).getIdentificacion().equals(busAsis)){
                
                posicionAsistente=i; 
                verificar=true;
                    
                jTFIdentificacionAsistente.setText(datosAsistente.get(i).getIdentificacion());
                id2=datosAsistente.get(i).getIdentificacion();
                jTFNombreAsistente.setText(datosAsistente.get(i).getNombre());
                jTFApellidoAsistente.setText(datosAsistente.get(i).getApellido());
                jTFCelularAsistente.setText(datosAsistente.get(i).getCelular());
                jTFMailAsistente.setText(datosAsistente.get(i).getCorreo());
                jTFDireccionAsistente.setText(datosAsistente.get(i).getDireccion());
                jLabel12.setIcon(buscImg(datosAsistente.get(i).getRutaFoto()));

                
                activarCajas();
                guardarEditar=true;
                }
            }
            
            if(verificar==false){
                
        JOptionPane.showMessageDialog(null, "identificacion incorrecta");
            }
        verificar=false;
        }
    }
     
     
     
     
public ImageIcon buscImg(String fil){
        
        
        ImageIcon icon = new ImageIcon(fil);
            //extrae la imagen del icono
            Image img = icon.getImage();
            //cambiando el tamaño a la imagen
            Image newImg = img.getScaledInstance(154, 154,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            //rutaImagen=file;
            return newIcon;
    }
             
     
     public void buscarCodigo(String busId){
    
    boolean verificarCodigo=false;
    
    if(datosAsistente.isEmpty()){
                JOptionPane.showMessageDialog(null, "No hay identificacion Registrados");
    }else{
    
        for (int i = 0; i < datosAsistente.size(); i++) {
            
            if(datosAsistente.get(i).getIdentificacion().equals(busId)){
            
                        verificarCodigo=true;
                
                        JOptionPane.showMessageDialog(null, "informacion asistente: \n identificacion: "
                        +datosAsistente.get(i).getIdentificacion()+"\n nombre: "
                        +datosAsistente.get(i).getNombre()+"\n apellido: "
                        +datosAsistente.get(i).getApellido()+"\n celular: "
                        +datosAsistente.get(i).getCelular()+"\n mail: "
                        +datosAsistente.get(i).getCorreo()+"\n direccion: "
                        +datosAsistente.get(i).getDireccion());
                
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
        if (datosAsistente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Usuarios Registrados");
        } else {
            
            for (int i = 0; i < datosAsistente.size(); i++) {

                if (datosAsistente.get(i).getIdentificacion().equals(busCodi)) {  
                     posicionAsistente = i;
                     id2=datosAsistente.get(i).getIdentificacion();
                     BDAsistente.eliminarAsistente(id2);
                    datosAsistente.remove(posicionAsistente);
                    verificar = true;
                     for (int j= jTAsistente.getRowCount(); j >=0; j--) {
            modelo.removeRow(i);    
            }
                     
                    for (int j = 0; j < datosAsistente.size(); j++) {
                String[] datos = new String[6];
        datos[0] = datosAsistente.get(j).getIdentificacion();
        datos[1] = datosAsistente.get(j).getNombre();
        datos[2] = datosAsistente.get(j).getApellido();
        datos[3] = datosAsistente.get(j).getCelular();
        datos[4] = datosAsistente.get(j).getCorreo();
        datos[5] = datosAsistente.get(j).getDireccion();
        

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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFIdentificacionAsistente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFNombreAsistente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFApellidoAsistente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFDireccionAsistente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFMailAsistente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFCelularAsistente = new javax.swing.JTextField();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAsistente = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jBFotoAsistente = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton12.setText("Salir");

        jButton13.setText("Foto");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asistente");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso Asistente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

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

        jTAsistente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTAsistente);

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton11.setText("Salir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jBFotoAsistente.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBFotoAsistente.setText("Foto");
        jBFotoAsistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFotoAsistenteActionPerformed(evt);
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
                        .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTFNombreAsistente, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(jTFIdentificacionAsistente)
                                    .addComponent(jTFApellidoAsistente)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTFDireccionAsistente, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(jTFMailAsistente)
                                    .addComponent(jTFCelularAsistente))))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jBFotoAsistente)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTFIdentificacionAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTFNombreAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTFApellidoAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTFCelularAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jTFMailAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTFDireccionAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jBFotoAsistente)))
                        .addGap(0, 0, Short.MAX_VALUE))))
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

    if (guardarEditar==false){
            
        if(guardarDatos()==true){
            
        JOptionPane.showMessageDialog(null,"guardado");
        cargarTabla();
        limpiarCajas();        
        posicionAsistente=-1;
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
           
               String buscarid = JOptionPane.showInputDialog("ingrese identificacion para editar");
               buscarAsistenteEditar(buscarid);
           }catch (Exception p){
               System.err.println(p);
           }
        
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed

    try  {
                String buscarIdAsisElimi = JOptionPane.showInputDialog("ingrese id para eliminar");
                buscarCodigoEliminar(buscarIdAsisElimi);
           }catch(Exception n){
                       System.err.println(n);

           }
        
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed

    activarCajas();
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jBFotoAsistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFotoAsistenteActionPerformed

             jLabel12.setIcon(cargarFoto()); 
    }//GEN-LAST:event_jBFotoAsistenteActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed

    try  {
                String buscarIdAsis = JOptionPane.showInputDialog("ingrese codigo para buscar");
                buscarCodigo(buscarIdAsis);
           }catch(Exception n){
                       System.err.println(n);

           }
        
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

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
            java.util.logging.Logger.getLogger(Asistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asistente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBFotoAsistente;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTAsistente;
    private javax.swing.JTextField jTFApellidoAsistente;
    private javax.swing.JTextField jTFCelularAsistente;
    private javax.swing.JTextField jTFDireccionAsistente;
    private javax.swing.JTextField jTFIdentificacionAsistente;
    private javax.swing.JTextField jTFMailAsistente;
    private javax.swing.JTextField jTFNombreAsistente;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
