/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.util.ArrayList;
import java.util.List;
import konquest.backed.objetos.ArchivoReplay;
import konquest.backed.objetos.ArchivoTXT;
import konquest.backed.objetos.Replay;

/**
 *
 * @author bryan
 */
public class ManejadorSintacticoReplay {
    
    public ArchivoTXT agregarAtributosArchivoTXT(Object path) {
        ArchivoTXT archivoTXT = new ArchivoTXT();
        archivoTXT.setUbicacion(path.toString());
        return archivoTXT;
    }
    
    public ArchivoReplay argregarAtributosArchivoReplay(Object nombre, Object path){
        ArchivoReplay archivoReplay = new ArchivoReplay();
        archivoReplay.setNombre(nombre.toString());
        archivoReplay.setPath(path.toString());
        return archivoReplay;
    }
    
    public ArchivoReplay argregarArearTXTArchivoReplay(Object agregar, Object archivoTXT){
        ArchivoReplay archivoReplay = (ArchivoReplay)agregar;
        archivoReplay.setArchivoTXT((ArchivoTXT)archivoTXT);
        return archivoReplay;
    }
    
    public Replay agregarAtributosReplay(Object nombre, Object path){
        return new Replay(nombre.toString(), path.toString());
    }
    
    public List<ArchivoReplay> listaArchivos(Object lista, Object agregar){
        if (lista instanceof ArchivoReplay) {
            List<ArchivoReplay> listaRegresar = new ArrayList<>();
            listaRegresar.add((ArchivoReplay)lista);
            listaRegresar.add((ArchivoReplay)agregar);
            return listaRegresar;
        } else {
            ((List<ArchivoReplay>)lista).add((ArchivoReplay)agregar);
            return ((List<ArchivoReplay>)lista);
        }
    }
    
    public Replay agregarLista(Object replay, Object lista){
        Replay replayRegresar = (Replay)replay;
        replayRegresar.setListaArchivosReplay((List<ArchivoReplay>)lista);
        return replayRegresar;
    }
}
