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
import modelo.FuncionarioVO;
import modelo.VehiculoVO;

/**
 *
 * @author Usuario
 */
public class VehiculoDAO {
    private Connection conexion;
    private Conexion conector;
    
    
    public VehiculoDAO(){
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
            pstm=conexion.prepareStatement("select placa_veh, numero_veh, modelo_veh, tipo_veh, capacidad_veh, empresa_veh, marca_veh, ruta_foto_veh, id_cond, id_asis, cod_soat, cod_tecno, id_func, estado_veh  from vehiculo");
            
            
            resultado=pstm.executeQuery();
                            
            while (resultado.next() == true) {
                //14
	    funcionario.add(resultado.getString("placa_veh"));
            funcionario.add(resultado.getString("numero_veh"));
            funcionario.add(resultado.getString("modelo_veh"));
            funcionario.add(resultado.getString("tipo_veh"));
            funcionario.add(resultado.getString("capacidad_veh"));
            funcionario.add(resultado.getString("empresa_veh"));
            funcionario.add(resultado.getString("marca_veh"));
            funcionario.add(resultado.getString("ruta_foto_veh"));
            funcionario.add(resultado.getString("id_cond"));
            funcionario.add(resultado.getString("id_asis"));
            funcionario.add(resultado.getString("cod_soat"));
            funcionario.add(resultado.getString("cod_tecno"));
            funcionario.add(resultado.getString("id_func"));
            funcionario.add(resultado.getString("estado_veh"));
            
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
        
public boolean ingresarConductor(VehiculoVO funcionario) {
        
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("insert into vehiculo (placa_veh, numero_veh, modelo_veh, tipo_veh, capacidad_veh, empresa_veh, marca_veh, ruta_foto_veh, id_cond, id_asis, cod_soat, cod_tecno, id_func, estado_veh)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getPlaca());
        pstmt.setInt(2,funcionario.getNumero());
        pstmt.setString(3,funcionario.getModelo());
        pstmt.setString(4,funcionario.getTipo());
        pstmt.setInt(5,funcionario.getCapacidad());
        pstmt.setString(6,funcionario.getEmpresa());
        pstmt.setString(7,funcionario.getMarca());
        pstmt.setString(8,funcionario.getRuta_foto());
        pstmt.setString(9,funcionario.getId_cond());
        pstmt.setString(10,funcionario.getId_asis());
        pstmt.setString(11,funcionario.getCod_SOAT());
        pstmt.setString(12,funcionario.getCod_tecno());
        pstmt.setString(13,funcionario.getId_func());
        pstmt.setBoolean(14,funcionario.isEstado());
        
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
    

   
    
    public boolean editarConductor(VehiculoVO funcionario, String idd) {
        
         
        PreparedStatement pstmt;
        try {
            //codigo para el ingreso de datos en MySQL
            pstmt = conexion.prepareStatement("UPDATE vehiculo SET placa_veh=?, numero_veh=?,"
                    + " modelo_veh=?, tipo_veh=?, capacidad_veh=?, empresa_veh=?, marca_veh=?, ruta_foto_veh=?,"
                    + " id_cond=?, id_asis=?, cod_soat=?, cod_tecno=?"
                    +" WHERE placa_veh= "+idd);
        
       
        //asignar valores a los interrogantes
        
        pstmt.setString(1, funcionario.getPlaca());
        pstmt.setInt(2,funcionario.getNumero());
        pstmt.setString(3,funcionario.getModelo());
        pstmt.setString(4,funcionario.getTipo());
        pstmt.setInt(5,funcionario.getCapacidad());
        pstmt.setString(6,funcionario.getEmpresa());
        pstmt.setString(7,funcionario.getMarca());
        pstmt.setString(8,funcionario.getRuta_foto());
        pstmt.setString(9,funcionario.getId_cond());
        pstmt.setString(10,funcionario.getId_asis());
        pstmt.setString(11,funcionario.getCod_SOAT());
        pstmt.setString(12,funcionario.getCod_tecno());
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
            pstmt = conexion.prepareStatement("UPDATE vehiculo SET estado_veh=0"
                    +" WHERE placa_veh="+idd);
        
     
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
