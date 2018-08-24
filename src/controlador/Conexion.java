/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Conexion {
     Connection conectar = null; 
    
     public Conexion(){
        conexionSQL();
    }
    
    public Connection conexionSQL(){
        try{
            
            //cargar el driver MySQL
            Class.forName("com.mysql.jdbc.Driver");
//            conectar=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3307/bibliteca1","root","root");
//puerto y ubicacion de la base de datos
       conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/transporte","root","root");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error "+e);
        }
        
        return conectar;
    }
}
