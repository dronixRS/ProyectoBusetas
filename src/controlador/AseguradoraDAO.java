/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.AseguradoraVO;
import modelo.ConductorVO;

/**
 *
 * @author Usuario
 */
public class AseguradoraDAO {
    
     private Connection conexion;
    private Conexion conector;
    
        public AseguradoraDAO(){
        conector = new Conexion();
        try{
        conexion = conector.conexionSQL();
        }catch(Exception e){
            System.err.println(e);
        }
    }
        
        
        
        
        public ArrayList<String> buscarConductorID(){
        PreparedStatement pstmt;
        ResultSet resultado;
       ArrayList<String> iD;
       iD= new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement(
            "select codigo_aseg, nombre_aseg from aseguradora ");
            
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    iD.add(resultado.getString("codigo_aseg"));
            iD.add(resultado.getString("nombre_aseg"));
            
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
            pstm=conexion.prepareStatement("select codigo_aseg,nombre_aseg,estado_aseg  from aseguradora");
            
            
            resultado=pstm.executeQuery();
                            
            while (resultado.next() == true) {
                
	    funcionario.add(resultado.getString("codigo_aseg"));
            funcionario.add(resultado.getString("nombre_aseg"));
              funcionario.add(resultado.getString("estado_aseg"));
            
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
        
public boolean ingresarConductor(AseguradoraVO funcionario) {
        
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("insert into aseguradora (codigo_aseg,nombre_aseg,id_func,estado_aseg)"
                    +"values (?,?,?,?)");
       
        //asignar valores a los interrogantes
        
        pstmt.setInt(1, funcionario.getCodigo());
        pstmt.setString(2,funcionario.getNombre());
        pstmt.setString(3,funcionario.getId_func());
        pstmt.setBoolean(4, funcionario.isEstado());
        
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
    

   
    
    public boolean editarConductor(AseguradoraVO funcionario, String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE aseguradora SET codigo_aseg=?, nombre_aseg=?"
                    +" WHERE codigo_aseg= "+idd);
        
       
        //asignar valores a los interrogantes
        
        pstmt.setInt(1, funcionario.getCodigo());
        pstmt.setString(2,funcionario.getNombre());
        
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
            //codigo para el cambio de estados en MySQL (Eliminar)
            pstmt = conexion.prepareStatement("UPDATE aseguradora SET estado_aseg=0"
                    +" WHERE codigo_aseg="+idd);
        
     
        
        
        
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
