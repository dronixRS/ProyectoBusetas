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
import modelo.SOATVO;

/**
 *
 * @author Usuario
 */
public class SOATDAO {
    private Connection conexion;
    private Conexion conector;
            public SOATDAO(){
        conector = new Conexion();
        try{
        conexion = conector.conexionSQL();
        }catch(Exception e){
            System.err.println(e);
      }}
            
            
            public ArrayList<String> buscarConductorID(){
        PreparedStatement pstmt;
        ResultSet resultado;
       ArrayList<String> iD;
       iD= new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement(
            "select codigo_soat from soat");
            
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    iD.add(resultado.getString("codigo_soat"));
            
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
            
            
      public boolean ingresarSOAT(SOATVO funcionario) {
        
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("insert into soat (codigo_soat,codigo_aseg_soat,fecha_soat"
                    + ",fecha_vigencia_soat,ruta_foto_soat,id_func,estado_soat"
                    +"values (?,?,?,?,?,?,?)");
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getCodigoSoat());
        pstmt.setInt(2,funcionario.getAseguradora());
        java.sql.Date sqlDate = new java.sql.Date(funcionario.getFechaSoat().getTime());
        pstmt.setDate(3,sqlDate);
        java.sql.Date sqlDate2 = new java.sql.Date(funcionario.getFechaVigenciaSoat().getTime());
        pstmt.setDate(4,sqlDate2);
        pstmt.setString(5,funcionario.getRutaFoto());
        pstmt.setString(6,funcionario.getId_func());
        pstmt.setBoolean(7,funcionario.isEstado());
     

        
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
            
        public boolean editarConductor(SOATVO funcionario, String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE soat SET codigo_soat=?,codigo_aseg_soat=?,fecha_soat=?"
                    + ",fecha_vigencia_soat=?,ruta_foto_soat=?,id_func=?,estado_soat=?"
                    +" WHERE codigo_soat= "+idd);
        
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getCodigoSoat());
        pstmt.setInt(2,funcionario.getAseguradora());
        java.sql.Date sqlDate = new java.sql.Date(funcionario.getFechaSoat().getTime());
        pstmt.setDate(3,sqlDate);
        java.sql.Date sqlDate1 = new java.sql.Date(funcionario.getFechaVigenciaSoat().getTime());
        pstmt.setDate(4,sqlDate1);
        pstmt.setString(5, funcionario.getRutaFoto());
        pstmt.setString(6,funcionario.getId_func());
        pstmt.setBoolean(7,funcionario.isEstado());
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
            pstmt = conexion.prepareStatement("UPDATE soat SET estado_soat=0"
                    +" WHERE id_soat="+idd);
        
     
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
        
        
         public ArrayList<String> buscarConductor(){
         PreparedStatement pstm;
             ResultSet resultado;
         ArrayList<String> funcionario= new ArrayList<>();
          try{
            pstm=conexion.prepareStatement("select codigo_soat,codigo_aseg_soat,fecha_soat, fecha_vigencia_soat, ruta_foto_soat, estado_soat  from soat");
            
            
            resultado=pstm.executeQuery();
                            
            while (resultado.next() == true) {
                
	    funcionario.add(resultado.getString("codigo_soat"));
            funcionario.add(resultado.getString("codigo_aseg_soat"));
            funcionario.add(resultado.getString("fecha_soat"));
            funcionario.add(resultado.getString("fecha_vigencia_soat"));
            funcionario.add(resultado.getString("ruta_foto_soat"));
            funcionario.add(resultado.getString("estado_soat"));;
            
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
