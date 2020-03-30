/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bryan
 */
public class Replay {
    private String nombre;
    private String path;
    private List<ArchivoReplay> listaArchivosReplay;

    public Replay() {
        this.listaArchivosReplay = new ArrayList<>();
    }

    public Replay(String nombre, String path) {
        this.nombre = nombre.replaceAll("\"", "");
        this.path = path.replaceAll("\"", "");
        this.listaArchivosReplay = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.replaceAll("\"", "");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path.replaceAll("\"", "");
    }

    public List<ArchivoReplay> getListaArchivosReplay() {
        return listaArchivosReplay;
    }

    public void setListaArchivosReplay(List<ArchivoReplay> listaArchivosReplay) {
        this.listaArchivosReplay = listaArchivosReplay;
    }
}
