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
import modelo.ConductorVO;

/**
 *
 * @author Usuario
 */
public class ConductorDAO {
      private Connection conexion;
    private Conexion conector;
    
        public ConductorDAO(){
        conector = new Conexion();
        try{
        conexion = conector.conexionSQL();
        }catch(Exception e){
            System.err.println(e);
        }
    }
        public ArrayList<String> buscarConductor(){
         PreparedStatement pstm;
         ResultSet resultado;
         ArrayList<String> funcionario= new ArrayList<>();
          try{
            pstm=conexion.prepareStatement("select id_cond,nombre_cond,apellido_cond,ruta_foto_cond,celular_cond,correo_cond,direccion_cond,categoria_licencia,vigencia_licencia,ciudad_licencia,restriccion_licencia,ruta_foto_licencia  from conductor");
            
            
            resultado=pstm.executeQuery();
                            
            while (resultado.next() == true) {
                
	    funcionario.add(resultado.getString("id_cond"));
            funcionario.add(resultado.getString("nombre_cond"));
            funcionario.add(resultado.getString("apellido_cond"));
            funcionario.add(resultado.getString("ruta_foto_cond"));
            funcionario.add(resultado.getString("celular_cond"));
            funcionario.add(resultado.getString("correo_cond"));
            funcionario.add(resultado.getString("direccion_cond"));
            funcionario.add(resultado.getString("categoria_licencia"));
            funcionario.add(resultado.getString("vigencia_licencia"));
            funcionario.add(resultado.getString("ciudad_licencia"));
            funcionario.add(resultado.getString("restriccion_licencia"));
            funcionario.add(resultado.getString("ruta_foto_licencia"));
            
            
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
        
public boolean ingresarConductor(ConductorVO funcionario) {
        
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("insert into conductor (id_cond,nombre_cond,apellido_cond"
                    + ",ruta_foto_cond,celular_cond,correo_cond,direccion_cond,categoria_licencia,vigencia_licencia"
                    + ",ciudad_licencia,restriccion_licencia,ruta_foto_licencia)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?)");
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getIdentificacion());
        pstmt.setString(2,funcionario.getNombre());
        pstmt.setString(3,funcionario.getApellido());
        pstmt.setString(4,funcionario.getRutaFoto());
        pstmt.setString(5,funcionario.getCelular());
        pstmt.setString(6,funcionario.getCorreo());
        pstmt.setString(7,funcionario.getDireccion());
        pstmt.setString(8,funcionario.getCatLin());
         java.sql.Date sqlDate = new java.sql.Date(funcionario.getVigLin().getTime());
        pstmt.setDate(9,sqlDate);
        pstmt.setString(10,funcionario.getCiuLin());
        pstmt.setString(11,funcionario.getRestLin());
        pstmt.setString(12,funcionario.getRutaFotoLin());
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
    
   
    
    public boolean editarConductor(ConductorVO funcionario) {
        
         String idc;
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            idc=funcionario.getIdentificacion();
            pstmt = conexion.prepareStatement("UPDATE conductor \n SET (id_cond=?,nombre_cond=?,apellido_cond=?"
                    + ",ruta_foto_cond=?,celular_cond=?,correo_cond=?,direccion_cond=?,categoria_licencia=?,vigencia_licencia=?"
                    + ",ciudad_licencia=?,restriccion_licencia=?,ruta_foto_licencia=?)"
                    +"\n WHERE id_cond= "+idc);
        
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getIdentificacion());
        pstmt.setString(2,funcionario.getNombre());
        pstmt.setString(3,funcionario.getApellido());
        pstmt.setString(4,funcionario.getRutaFoto());
        pstmt.setString(5,funcionario.getCelular());
        pstmt.setString(6,funcionario.getCorreo());
        pstmt.setString(7,funcionario.getDireccion());
        pstmt.setString(8,funcionario.getCatLin());
         java.sql.Date sqlDate = new java.sql.Date(funcionario.getVigLin().getTime());
        pstmt.setDate(9,sqlDate);
        pstmt.setString(10,funcionario.getCiuLin());
        pstmt.setString(11,funcionario.getRestLin());
        pstmt.setString(12,funcionario.getRutaFotoLin());
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