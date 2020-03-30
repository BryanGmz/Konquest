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
public class ArchivoReplay extends Replay {
    private ArchivoTXT archivoTXT;

    public ArchivoReplay() {}

    public ArchivoReplay(ArchivoTXT archivoTXT, String nombre, String path) {
        super(nombre, path);
        this.archivoTXT = archivoTXT;
    }

    public ArchivoTXT getArchivoTXT() {
        return archivoTXT;
    }

    public void setArchivoTXT(ArchivoTXT archivoTXT) {
        this.archivoTXT = archivoTXT;
    }
}
