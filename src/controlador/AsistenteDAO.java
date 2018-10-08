/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.Conexion;
import controlador.Conexion;
import controlador.FuncionarioDAO;
import controlador.FuncionarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.AsistenteVO;
import modelo.EstudianteVO;

/**
 *
 * @author SEBASTIAN
 */
public class AsistenteDAO {
    
    private Connection conexion;
    
    private Conexion conector;

    public AsistenteDAO(){
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
            "select id_asis from asistente");
            
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
              
	    iD.add(resultado.getString("id_asis"));
            
            
            
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
    public ArrayList<String> buscarAsistente(){
         PreparedStatement pstm;
         ResultSet resultado;
         ArrayList<String> asistente= new ArrayList<>();
    
    try{
        
        
         pstm=conexion.prepareStatement("select id_asis,nombre_asis,apellido_asis,celular_asis,correo_asis,direccion_asis,ruta_foto_asis,estado_asis  from asistente");

         resultado=pstm.executeQuery();

    
         while(resultado.next()==true){
         
            asistente.add(resultado.getString("id_asis"));            
            asistente.add(resultado.getString("nombre_asis"));
            asistente.add(resultado.getString("apellido_asis"));
            asistente.add(resultado.getString("celular_asis"));
            asistente.add(resultado.getString("correo_asis"));
            asistente.add(resultado.getString("direccion_asis"));
            asistente.add(resultado.getString("ruta_foto_asis"));
            asistente.add(resultado.getString("estado_asis"));

         
         }
         return asistente;
         
    }catch (SQLException e){
                    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);

                    JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
    
    }
    return null;
}
    
    public boolean ingresarAsistente(AsistenteVO asistente)  {
    
        
            PreparedStatement pstmt;
            
            try {
                   pstmt = conexion.prepareStatement("insert into asistente (id_asis,nombre_asis,apellido_asis,celular_asis,correo_asis,direccion_asis,ruta_foto_asis,id_func,estado_asis)"
                    +"values (?,?,?,?,?,?,?,?,?)");
                   
        pstmt.setString(1,asistente.getIdentificacion());
        pstmt.setString(2,asistente.getNombre());
        pstmt.setString(3,asistente.getApellido());
        pstmt.setString(4,asistente.getCelular());
        pstmt.setString(5,asistente.getCorreo());
        pstmt.setString(6,asistente.getDireccion());
        pstmt.setString(7,asistente.getRutaFoto());
        pstmt.setString(8,asistente.getIdFunc());       
        pstmt.setBoolean(9,asistente.isEstado());
        pstmt.executeUpdate();
    
        System.out.print(pstmt);
        
    }catch( SQLException e){
    
    
        Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
             JOptionPane.showMessageDialog(null,"No se guardo la información \n" + e,"mensaje Error", JOptionPane.ERROR_MESSAGE);
             
			return false;
    }
    return true;
    }
    
    public boolean modificarAsistente(AsistenteVO asistente, String idd)  {
    
        
            PreparedStatement pstmt;
            
            try {
                   pstmt = conexion.prepareStatement("update asistente set id_asis=?,nombre_asis=?"
                    + ",apellido_asis=?,celular_asis=?,correo_asis=?,direccion_asis=?,ruta_foto_asis=?"
                    + ",id_func=? where id_asis="+idd);
                    
                   
        
        pstmt.setString(1,asistente.getIdentificacion());
        pstmt.setString(2,asistente.getNombre());
        pstmt.setString(3,asistente.getApellido());
        pstmt.setString(4,asistente.getCelular());
        pstmt.setString(5,asistente.getCorreo());
        pstmt.setString(6,asistente.getDireccion()); 
        pstmt.setString(7,asistente.getRutaFoto()); 
        pstmt.setString(8,asistente.getIdFunc());
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
    
    public boolean eliminarAsistente(String cod)  {
    
        
            PreparedStatement pstmt;
            
            try {
                   pstmt = conexion.prepareStatement("update asistente set estado_asis=0"+" where id_asis="+cod);
                    
                   
        
        
        pstmt.executeUpdate();
    
        System.out.print(pstmt);
        
        JOptionPane.showMessageDialog(null, "eliminacion en base con exito");
        
    }catch( SQLException e){
    
    
        Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
             JOptionPane.showMessageDialog(null,
					"No se modifico la información\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
    }
    return true;
    }
    
}
