/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class ConductorVO extends PersonaVO{
    String catLin, ciuLin, restLin, rutaFoto, rutaFotoLin;
    Date vigLin;

    public ConductorVO( String identificacion, String nombre, String apellido,String rutaFoto, String celular, String correo, String direccion, String catLin,Date vigLin, String ciuLin, String restLin,  String rutaFotoLin, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
        this.catLin = catLin;
        this.ciuLin = ciuLin;
        this.restLin = restLin;
        this.rutaFoto = rutaFoto;
        this.rutaFotoLin = rutaFotoLin;
        this.vigLin = vigLin;
    }

    public ConductorVO(String identificacion, String nombre, String apellido, String celular, String correo, String direccion, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
    }

    public String getCatLin() {
        return catLin;
    }

    public void setCatLin(String catLin) {
        this.catLin = catLin;
    }

    public String getCiuLin() {
        return ciuLin;
    }

    public void setCiuLin(String ciuLin) {
        this.ciuLin = ciuLin;
    }

    public String getRestLin() {
        return restLin;
    }

    public void setRestLin(String restLin) {
        this.restLin = restLin;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getRutaFotoLin() {
        return rutaFotoLin;
    }

    public void setRutaFotoLin(String rutaFotoLin) {
        this.rutaFotoLin = rutaFotoLin;
    }

    public Date getVigLin() {
        return vigLin;
    }

    public void setVigLin(Date vigLin) {
        this.vigLin = vigLin;
    }

 
    
}
