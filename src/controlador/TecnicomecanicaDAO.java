/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.Conexion;
import controlador.FuncionarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.TecnicomecanicaVO;

/**
 *
 * @author SEBASTIAN
 */
public class TecnicomecanicaDAO {
    
    private Connection conexion;
    private Conexion conector;
    
        public TecnicomecanicaDAO(){
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
            "select codigo_tecno from tecnomecanica");
            
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    iD.add(resultado.getString("codigo_tecno"));
            
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
       public ArrayList<String> buscarCodigoTecnicomecanica(){
        PreparedStatement pstmt;
        ResultSet resultado;
       ArrayList<String> cod;
       cod= new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement(
            "select codigo_tecno from tecnicomecanica");
            
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    cod.add(resultado.getString("codigo_tecno"));
            
	}
        return cod;                   
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    } 
       
       public ArrayList<String> buscarTecnicomecanica(){
         PreparedStatement pstm;
         ResultSet resultado;
         ArrayList<String> Tecnomecanica= new ArrayList<>();
          try{
            pstm=conexion.prepareStatement("select codigo_tecno,centro_diagnostico_tecno,fecha_tecno,fecha_vigencia_tecno,ruta_foto_tecno,estado_tecno  from tecnomecanica");
            
            
            resultado=pstm.executeQuery();
                            
            while (resultado.next() == true) {
                
	    Tecnomecanica.add(resultado.getString("codigo_tecno"));
            Tecnomecanica.add(resultado.getString("centro_diagnostico_tecno"));
            Tecnomecanica.add(resultado.getString("fecha_tecno"));
            Tecnomecanica.add(resultado.getString("fecha_vigencia_tecno"));
            Tecnomecanica.add(resultado.getString("ruta_foto_tecno"));
            Tecnomecanica.add(resultado.getString("estado_tecno"));
            
            
	}
            return Tecnomecanica;
} catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
            }
       
       public boolean ingresarTecnicomecanica(TecnicomecanicaVO tecnicomecanica) {
        
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("insert into tecnomecanica (codigo_tecno,centro_diagnostico_tecno,fecha_tecno,fecha_vigencia_tecno,ruta_foto_tecno,id_func,estado_tecno)"
                    +"values (?,?,?,?,?,?,?)");
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, tecnicomecanica.getCodigo());
        pstmt.setString(2,tecnicomecanica.getCentroDiagnostico());
        
                
        java.sql.Date sqlDate = new java.sql.Date(tecnicomecanica.getFechaExpedicion().getTime());
        
        pstmt.setDate(3,sqlDate);
        
        java.sql.Date sqlDate1 = new java.sql.Date(tecnicomecanica.getFechaVigente().getTime());

        pstmt.setDate(4,sqlDate1);

        pstmt.setString(5,tecnicomecanica.getRutaFoto());
        pstmt.setString(6,tecnicomecanica.getId_func());
        pstmt.setBoolean(7, tecnicomecanica.isEstado());
        
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
       
       public boolean editarTecnomecanica(TecnicomecanicaVO tecnicomecanica, String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE tecnomecanica SET codigo_tecno=?,centro_diagnostico_tecno=?,fecha_tecno=?"
                    + ",fecha_vigencia_tecno=?,ruta_foto_tecno=?"+" WHERE codigo_tecno= "+idd);
                    
        
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, tecnicomecanica.getCodigo());
        pstmt.setString(2,tecnicomecanica.getCentroDiagnostico());
                       
        java.sql.Date sqlDate = new java.sql.Date(tecnicomecanica.getFechaExpedicion().getTime());
        
        pstmt.setDate(3,sqlDate);
        
        java.sql.Date sqlDate1 = new java.sql.Date(tecnicomecanica.getFechaVigente().getTime());

        pstmt.setDate(4,sqlDate1);
        
        pstmt.setString(5,tecnicomecanica.getRutaFoto());

        
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
       
       
       public boolean eliminarTecnicomecanica(String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE tecnomecanica SET estado_tecno=0"
                    +" WHERE codigo_tecno="+idd);
        
     
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
