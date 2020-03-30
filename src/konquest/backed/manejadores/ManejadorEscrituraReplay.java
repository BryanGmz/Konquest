/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import konquest.backed.objetos.*;

/**
 *
 * @author bryan
 */
public class ManejadorEscrituraReplay {
    public static final String NOMBRE_ARCHIVOS_TXT = "AreaTexto";
    public static final String NOMBRE_ARCHIVOS_PARTIDAS = "GPReplay";
    public static final String PATH_CARPETA_REPLAY = "./replay/";
    public static final String PATH_CARPETA_TEXT = "./text/";
    public static final String EXTENCION_TEXT = ".txt";
    public static final String EXTENCION_REPLAY = ".gpr";
    public static final String EXTENCION_ARCHIVO_REPLAY = ".rply";
    
    public void escribirArchivoTXT(String path, String textoSalida) throws IOException {
        File chooser = new File(path);
        try (FileOutputStream salida = new FileOutputStream(chooser)) {
            byte[] byteTxt = textoSalida.getBytes();
            salida.write(byteTxt);
        }
    }
    
    public void escrituraArchivoReplay(Replay replay, String path) throws IOException{
        String salida;
        salida = "\n<REPLAY nombre=\"" + replay.getNombre() + "\" ubicacion=\"" + replay.getPath() + "\">";
        salida = replay.getListaArchivosReplay().stream().map((archivoReplay) -> escrituraArchivoCPK(archivoReplay)).reduce(salida, String::concat);
        salida += "\n</REPLAY>";
        escribirArchivoTXT(path, salida);
    }
    
    public String escrituraArchivoCPK(ArchivoReplay archivoReplay){
        String salida;
        salida = "\n\t<ARCHIVO nombre=\"" + archivoReplay.getNombre() + "\" ubicacion=\"" + archivoReplay.getPath() + "\">"; 
        salida += escrituraArchivoTXT(archivoReplay.getArchivoTXT());
        salida += "\n\t</ARCHIVO>";
        return salida;
    }
    
    public String escrituraArchivoTXT(ArchivoTXT archivoTXT){
        return "\n\t\t\t<TEXTO ubicacion=\"" + archivoTXT.getUbicacion() + "\"/>";
    }
    
    public void crearCarpetasYContador() throws IOException {
        File carpetaReplay = new File("./replay");
        File carpetaText = new File("./text");
        File contador = new File("./contador.cnt");
        if (!carpetaReplay.exists()) {
            carpetaReplay.mkdir();
        }
        if (!carpetaText.exists()) {
            carpetaText.mkdir();
        }
        if (!contador.exists()) {
            escribirArchivoTXT("./contador.cnt", Integer.toString(0));
        }
        
    }
    
    public String leerContadorYTXT(String path) throws FileNotFoundException, IOException {
        BufferedReader bf = new BufferedReader(new FileReader(path));
        String entrada = "";
        String bfRead;
        while ((bfRead = bf.readLine()) != null) {
            entrada += bfRead + "\n";
        } return entrada;
    }
    
    public int obtenerContador() throws IOException{
        return Integer.parseInt(leerContadorYTXT("./contador.cnt").replaceAll("\n", "").replaceAll("\"", ""));
    }
    
    public void aumentarContador(int contador) throws IOException{
        escribirArchivoTXT("./contador.cnt", Integer.toString(contador));
    }
    
}
