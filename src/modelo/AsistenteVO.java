/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.PersonaVO;

/**
 *
 * @author SEBASTIAN
 */

     

public class AsistenteVO extends PersonaVO{
    
    String rutaFoto, idFunc;

    public AsistenteVO( String identificacion, String nombre, String apellido, String celular, String correo, String direccion,String rutaFoto, String idFunc, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
        this.rutaFoto = rutaFoto;
        this.idFunc = idFunc;
    }

    public AsistenteVO(String identificacion, String nombre, String apellido, String celular, String correo, String direccion, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(String idFunc) {
        this.idFunc = idFunc;
    }

    
    
    
}