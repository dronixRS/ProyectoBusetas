/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.Conexion;
import controlador.FuncionarioDAO;
import modelo.PersonaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.EstudianteVO;

/**
 *
 * @author LENOVO PC
 */
public class EstudianteDAO {
    
    private Connection conexion;
    
    private Conexion conector;

    public EstudianteDAO(){
        conector = new Conexion();
        try{
        conexion = conector.conexionSQL();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public ArrayList<String> buscarEstudiante(){
         PreparedStatement pstm;
         ResultSet resultado;
         ArrayList<String> estudiante= new ArrayList<>();
    
    try{
        
        
         pstm=conexion.prepareStatement("select codigo_est,id_est,nombre_est,apellido_est,celular_est,correo_est,direccion_est,grado_est,ciudad_est,acudiente_est,celular_acu_est,correo_acu_est,direccion_acu_est,estado_est  from estudiante");

         resultado=pstm.executeQuery();

    
         while(resultado.next()==true){
         
            estudiante.add(resultado.getString("codigo_est"));
            estudiante.add(resultado.getString("id_est"));            
            estudiante.add(resultado.getString("nombre_est"));
            estudiante.add(resultado.getString("apellido_est"));
            estudiante.add(resultado.getString("celular_est"));
            estudiante.add(resultado.getString("correo_est"));
            estudiante.add(resultado.getString("direccion_est"));
            estudiante.add(resultado.getString("grado_est"));
            estudiante.add(resultado.getString("ciudad_est"));
            estudiante.add(resultado.getString("acudiente_est"));            
            estudiante.add(resultado.getString("celular_acu_est"));
            estudiante.add(resultado.getString("correo_acu_est"));
            estudiante.add(resultado.getString("direccion_acu_est"));
            estudiante.add(resultado.getString("estado_est"));
         
         }
         return estudiante;
         
    }catch (SQLException e){
                    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);

                    JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
    
    }
    return null;
}
    
    public boolean ingresarEstudiante(EstudianteVO estudiante)  {
    
        
            PreparedStatement pstmt;
            
            try {
                   pstmt = conexion.prepareStatement("insert into estudiante (codigo_est,id_est,nombre_est"
                    + ",apellido_est,celular_est,correo_est,direccion_est,grado_est,ciudad_est"
                    + ",acudiente_est,celular_acu_est,correo_acu_est,direccion_acu_est,id_func,estado_est)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                   
        pstmt.setString(1,estudiante.getCodigo());
        pstmt.setString(2,estudiante.getIdentificacion());
        pstmt.setString(3,estudiante.getNombre());
        pstmt.setString(4,estudiante.getApellido());
        pstmt.setString(5,estudiante.getCelular());
        pstmt.setString(6,estudiante.getCorreo());
        pstmt.setString(7,estudiante.getDireccion());
        pstmt.setString(8,estudiante.getGrado());
        pstmt.setString(9,estudiante.getCiudad());
        pstmt.setString(10,estudiante.getNombreAcudiente());
        pstmt.setString(11,estudiante.getCelularAcudiente());
        pstmt.setString(12,estudiante.getMailAcudiente());
        pstmt.setString(13,estudiante.getDireccionAcudiente());
        pstmt.setString(14,estudiante.getIdFunc());
        pstmt.setBoolean(15,estudiante.isEstado());
        pstmt.executeUpdate();
    
        System.out.print(pstmt);
        
    }catch( SQLException e){
    
    
        Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
             JOptionPane.showMessageDialog(null,"No se guardo la información \n" + e,"mensaje Error", JOptionPane.ERROR_MESSAGE);
             
			return false;
    }
    return true;
    }
    
    
    public boolean modificarEstudiante(EstudianteVO estudiante, String idd)  {
    
        
            PreparedStatement pstmt;
            
            try {
                   pstmt = conexion.prepareStatement("update estudiante set codigo_est=?,id_est=?,nombre_est=?"
                    + ",apellido_est=?,celular_est=?,correo_est=?,direccion_est=?,grado_est=?,ciudad_est=?"
                    + ",acudiente_est=?,celular_acu_est=?,correo_acu_est=?,direccion_acu_est=?,id_func=? where codigo_est="+idd);
                    
                   
        pstmt.setString(1,estudiante.getCodigo());
        pstmt.setString(2,estudiante.getIdentificacion());
        pstmt.setString(3,estudiante.getNombre());
        pstmt.setString(4,estudiante.getApellido());
        pstmt.setString(5,estudiante.getCelular());
        pstmt.setString(6,estudiante.getCorreo());
        pstmt.setString(7,estudiante.getDireccion());
        pstmt.setString(8,estudiante.getGrado());
        pstmt.setString(9,estudiante.getCiudad());
        pstmt.setString(10,estudiante.getNombreAcudiente());
        pstmt.setString(11,estudiante.getCelularAcudiente());
        pstmt.setString(12,estudiante.getMailAcudiente());
        pstmt.setString(13, estudiante.getDireccionAcudiente());
        // id funcionario
        pstmt.setString(14, estudiante.getIdFunc());
       // pstmt.setString(14, estudiante.geti());
        pstmt.executeUpdate();
    
        System.out.print(pstmt);
        
        JOptionPane.showMessageDialog(null, "edicion en base con exito");
        
    }catch( SQLException e){
    
    
        Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
             JOptionPane.showMessageDialog(null,
					"No se modifico la información\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
    }
    return true;
    }
    
    public boolean eliminarEstudiante(String cod) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("update estudiante set estado_est=0"+" where codigo_est="+cod);
        
     
        //asignar valores a los interrogantes
        
        
        pstmt.executeUpdate();

        
         } catch (SQLException ex) {
             //guarda el error en una carpeta
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se guardo la información\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    
    }
    
    
    
    
}