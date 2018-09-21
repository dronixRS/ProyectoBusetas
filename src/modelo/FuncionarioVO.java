/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class FuncionarioVO extends PersonaVO{
    String usuario, clave;

    public FuncionarioVO( String identificacion, String nombre, String apellido, String celular, String correo, String direccion, String usuario, String clave, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
        this.usuario = usuario;
        this.clave = clave;
    }

    public FuncionarioVO(String identificacion, String nombre, String apellido, String celular, String correo, String direccion, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
    
}
