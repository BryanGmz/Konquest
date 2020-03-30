/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.objetos;

/**
 *
 * @author bryan
 */
public class ArchivoTXT {
    private String areaTexto;
    private String ubicacion;
    
    public ArchivoTXT() {}

    public ArchivoTXT(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public ArchivoTXT(String areaTexto, String ubicacion) {
        this.areaTexto = areaTexto.replaceAll("\"", "");
        this.ubicacion = ubicacion.replaceAll("\"", "");
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion.replaceAll("\"", "");
    }

    public String getAreaTexto() {
        return areaTexto;
    }

    public void setAreaTexto(String areaTexto) {
        this.areaTexto = areaTexto.replaceAll("\"", "");
    }
}
