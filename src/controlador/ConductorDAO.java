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
            pstm=conexion.prepareStatement("select id_cond,nombre_cond,apellido_cond,ruta_foto_cond,celular_cond,correo_cond,direccion_cond,categoria_licencia,vigencia_licencia,ciudad:licencia,restriccion_licencia,ruta_foto_licencia  from conductor");
            
            
            resultado=pstm.executeQuery();
                            
            while (resultado.next() == true) {
                
	    funcionario.add(resultado.getString("id_func"));
            funcionario.add(resultado.getString("nombre_func"));
            funcionario.add(resultado.getString("apellido_func"));
            funcionario.add(resultado.getString("celular_func"));
            funcionario.add(resultado.getString("correo_func"));
            funcionario.add(resultado.getString("direccion_func"));
            funcionario.add(resultado.getString("genero_func"));
            funcionario.add(resultado.getString("correo_func"));
            funcionario.add(resultado.getString("direccion_func"));
            funcionario.add(resultado.getString("categoria_licencia"));
            funcionario.add(resultado.getString("ciudad_licencia"));
            funcionario.add(resultado.getString("restirccion_licencia"));
            funcionario.add(resultado.getString("vigencia_licencia"));
            
            
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
                    + ",ciudad:licencia,restriccion_licencia,ruta_foto_licencia,id_func)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?)");
       
        //asignar valores a los interrogantes
        pstmt.setString(1, funcionario.getIdentificacion());
        pstmt.setString(2,funcionario.getNombre());
        pstmt.setString(3,funcionario.getApellido());
        pstmt.setString(4,funcionario.getCelular());
        pstmt.setString(5,funcionario.getCorreo());
        pstmt.setString(6,funcionario.getDireccion());
        pstmt.setString(7,funcionario.getCatLin());
        pstmt.setString(8,funcionario.getCiuLin());
        pstmt.setString(9,funcionario.getRestLin());
                //acomodar la fecha al formato de MySQL
        java.sql.Date sqlDate = new java.sql.Date(funcionario.getVigLin().getTime());
        pstmt.setDate(10,sqlDate);
        pstmt.setString(11,funcionario.getRutaFoto());
        pstmt.setString(12,funcionario.getRutaFotoLin());
//        pstmt.setString(13,funcionario.getRutaFotoLin());
//        pstmt.executeUpdate();

        
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