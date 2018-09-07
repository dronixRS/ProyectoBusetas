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
    public ArrayList buscarIdFunc( String nom){

        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> funcionario = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement(
            "select id_func, nombre_func, apellido_func, celular_func, correo_func, direccion_func, usuario_func, clave_func from funcionario where id_func=?");
            pstmt.setString(1,nom);

            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    funcionario.add(resultado.getString("id_func"));
            
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

}
