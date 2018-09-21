/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ConductorVO;
import modelo.FuncionarioVO;


/**
 *
 * @author Usuario
 */
public class FuncionarioDAO {
    
    private Connection conexion;
    private Conexion conector;
    
    public FuncionarioDAO(){
        conector = new Conexion();
        try{
        conexion = conector.conexionSQL();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    
    public ArrayList<String> buscarFuncionarioLogin(String idUsu, String clave){
        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> funcionario = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement(
            "select id_func, nombre_func, apellido_func, celular_func, correo_func, direccion_func, usuario_func, clave_func from funcionario where usuario_func=? and clave_func=?");
            pstmt.setString(1,idUsu);
            pstmt.setString(2,clave);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    funcionario.add(resultado.getString("usuario_func"));
            funcionario.add(resultado.getString("clave_func"));
            
            
	}
        return funcionario;                   
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    }
    public String buscarFuncionarioID(String idUsu, String clave){
        PreparedStatement pstmt;
        ResultSet resultado;
       String iD="";
        try {
            pstmt=conexion.prepareStatement(
            "select id_func from funcionario where usuario_func=? and clave_func=?");
            pstmt.setString(1,idUsu);
            pstmt.setString(2,clave);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    iD=(resultado.getString("id_func"));
            
            
            
	}
        return iD;                   
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    }

     public ArrayList<String> buscarConductor(){
         PreparedStatement pstm;
         ResultSet resultado;
         ArrayList<String> funcionario= new ArrayList<>();
          try{
            pstm=conexion.prepareStatement("select id_func,nombre_func,apellido_func,celular_func,correo_func,direccion_func,usuario_func, clave_func ,estado_func  from funcionario");
            
            
            resultado=pstm.executeQuery();
                            
            while (resultado.next() == true) {
                
	    funcionario.add(resultado.getString("id_func"));
            funcionario.add(resultado.getString("nombre_func"));
            funcionario.add(resultado.getString("apellido_func"));
            funcionario.add(resultado.getString("celular_func"));
            funcionario.add(resultado.getString("correo_func"));
            funcionario.add(resultado.getString("direccion_func"));
            funcionario.add(resultado.getString("usuario_func"));
            funcionario.add(resultado.getString("clave_func"));
            funcionario.add(resultado.getString("estado_func"));
            
	}
            return funcionario;
} catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
            }
        
public boolean ingresarConductor(FuncionarioVO funcionario) {
        
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("insert into funcionario (id_func,nombre_func,apellido_func"
                    + ",celular_func,correo_func,direccion_func,usuario_func,clave_func,estado_func)"
                    +"values (?,?,?,?,?,?,?,?,?)");
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getIdentificacion());
        pstmt.setString(2,funcionario.getNombre());
        pstmt.setString(3,funcionario.getApellido());
        pstmt.setString(4,funcionario.getCelular());
        pstmt.setString(5,funcionario.getCorreo());
        pstmt.setString(6,funcionario.getDireccion());
        pstmt.setString(7,funcionario.getUsuario());
        pstmt.setString(8,funcionario.getClave());
        pstmt.setBoolean(9, funcionario.isEstado());
        
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
    

   
    
    public boolean editarConductor(FuncionarioVO funcionario, String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE funcionario SET id_func=?,nombre_func=?,apellido_func=?"
                    + ",celular_func=?,correo_func=?,direccion_func=?,usuario_func=?, clave_func=?"
                    +" WHERE id_func= "+idd);
        
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getIdentificacion());
        pstmt.setString(2,funcionario.getNombre());
        pstmt.setString(3,funcionario.getApellido());
        pstmt.setString(4,funcionario.getCelular());
        pstmt.setString(5,funcionario.getCorreo());
        pstmt.setString(6,funcionario.getDireccion());
        pstmt.setString(7,funcionario.getUsuario());
        pstmt.setString(8,funcionario.getClave());
        
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
    
     public boolean eliminarConductor(String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE funcionario SET estado_func=0"
                    +" WHERE id_func="+idd);
        
     
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
