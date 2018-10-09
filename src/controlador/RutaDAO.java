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
import modelo.RutaVO;

/**
 *
 * @author SEBASTIAN
 */
public class RutaDAO {
    
    private Connection conexion;
    private Conexion conector;
    
        public RutaDAO(){
        conector = new Conexion();
        try{
        conexion = conector.conexionSQL();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    
    public ArrayList<String> buscarRutaID(){
        PreparedStatement pstmt;
        ResultSet resultado;
       ArrayList<String> iD;
       iD= new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement(
            "select codigo_ruta from ryta");
            
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    iD.add(resultado.getString("codigo_ruta"));
            
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
    
    
    public ArrayList<String> buscarRuta(){
         PreparedStatement pstm;
         ResultSet resultado;
         ArrayList<String> ruta= new ArrayList<>();
          try{
            pstm=conexion.prepareStatement("select codigo_ruta,periodo_ruta,zona_ruta,placa_veh,recorrido_veh,hora_inicio_ruta,hora_final_ruta,cupos_asignados_ruta,cupos_disponibles_ruta,tiempo_recorrido_ruta,num_paradas_ruta,estado_ruta  from ruta");
            
            
            resultado=pstm.executeQuery();
                            
            while (resultado.next() == true) {
                
	    ruta.add(resultado.getString("codigo_ruta"));
            ruta.add(resultado.getString("periodo_ruta"));
            ruta.add(resultado.getString("zona_ruta"));
            ruta.add(resultado.getString("placa_veh"));
            ruta.add(resultado.getString("recorrido_veh"));
            ruta.add(resultado.getString("hora_inicio_ruta"));
            ruta.add(resultado.getString("hora_final_ruta"));
            ruta.add(resultado.getString("cupos_asignados_ruta"));
            ruta.add(resultado.getString("cupos_disponibles_ruta"));
            ruta.add(resultado.getString("tiempo_recorrido_ruta"));
            ruta.add(resultado.getString("num_paradas_ruta"));
            ruta.add(resultado.getString("estado_ruta"));
            
	}
            return ruta;
} catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
            }
    
    
    public boolean ingresarRuta(RutaVO funcionario) {
        
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("insert into conductor (codigo_ruta,periodo_ruta,zona_ruta,placa_veh,recorrido_veh,hora_inicio_ruta,hora_final_ruta,cupos_asignados_ruta,cupos_disponibles_ruta,tiempo_recorrido_ruta,num_paradas_ruta,id_func,estado_ruta)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getCodigo());
        pstmt.setString(2,funcionario.getPeriodo());
        pstmt.setString(3,funcionario.getZona());
        pstmt.setString(4,funcionario.getVehiculoPlaca());
        pstmt.setString(5,funcionario.getConductor());
        pstmt.setString(6,funcionario.getAsistente());
        pstmt.setString(7,funcionario.getRecorrido());
        pstmt.setString(8,funcionario.getHoraInicio());
        pstmt.setString(9,funcionario.getHoraFin());
        pstmt.setString(10,funcionario.gettRecorrido());
        pstmt.setString(11,funcionario.getCuposAsignados());
        pstmt.setString(12,funcionario.getCuposDisponibles());
        pstmt.setString(13,funcionario.getNumeroParadas());      
        pstmt.setString(12,funcionario.getId_func());
        pstmt.setBoolean(13,funcionario.isEstado());  
        
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
    
    
    public boolean editarRuta(RutaVO funcionario, String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE ruta SET codigo_ruta=?,periodo_ruta=?,zona_ruta=?,placa_veh=?,recorrido_veh=?,hora_inicio_ruta=?,hora_final_ruta=?,cupos_asignados_ruta=?,cupos_disponibles_ruta=?,tiempo_recorrido_ruta=?,num_paradas_ruta=?"
                    +" WHERE codigo_ruta= "+idd);
        
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getCodigo());
        pstmt.setString(2,funcionario.getPeriodo());
        pstmt.setString(3,funcionario.getZona());
        pstmt.setString(4,funcionario.getVehiculoPlaca());
        pstmt.setString(5,funcionario.getConductor());
        pstmt.setString(6,funcionario.getAsistente());
        pstmt.setString(7,funcionario.getRecorrido());
        pstmt.setString(8,funcionario.getHoraInicio());
        pstmt.setString(9,funcionario.getHoraFin());
        pstmt.setString(10,funcionario.gettRecorrido());
        pstmt.setString(11,funcionario.getCuposAsignados());
        pstmt.setString(12,funcionario.getCuposDisponibles());
        pstmt.setString(13,funcionario.getNumeroParadas());      
      
        
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
    
    
     public boolean eliminarRuta(String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE ruta SET estado_ruta=0"
                    +" WHERE codigo_ruta"+idd);
        
     
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
