/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import konquest.backed.analizador.partida.LexerGuardarPartida;
import konquest.backed.analizador.partida.SintaxGuardarPartida;
import konquest.backed.analizador.replay.LexicoReplay;
import konquest.backed.analizador.replay.SintaxReplay;
import konquest.backed.objetos.*;
import konquest.gui.DialogoErrores;
import konquest.gui.Principal;

/**
 *
 * @author bryan
 */
public class ManejadorReplay {
    private final ManejadorEscrituraArchivo escrituraArchivo;
    private final ManejadorEscrituraReplay escrituraReplay = new ManejadorEscrituraReplay();
    
    public ManejadorReplay(ManejadorEscrituraArchivo escrituraArchivo) {
        this.escrituraArchivo = escrituraArchivo;
    }
    
    public void guardarArchivosReplay(Mapa mapa, List<Jugadores> listaJugadores, List<Planetas> listaPlanetas, String areaText, Replay replay){
        try {
            ArchivoReplay archivoReplay;
            ArchivoTXT archivoTXT;
            int contador = escrituraReplay.obtenerContador();
            String pathText = ManejadorEscrituraReplay.PATH_CARPETA_TEXT + ManejadorEscrituraReplay.NOMBRE_ARCHIVOS_TXT + contador + ManejadorEscrituraReplay.EXTENCION_TEXT;
            String pathGPReplay = ManejadorEscrituraReplay.PATH_CARPETA_REPLAY + ManejadorEscrituraReplay.NOMBRE_ARCHIVOS_PARTIDAS + contador + ManejadorEscrituraReplay.EXTENCION_REPLAY;
            escrituraArchivo.escrituraArchivoGuardarPartida(mapa, listaJugadores, listaPlanetas, pathGPReplay, false);
            escrituraReplay.escribirArchivoTXT(pathText, areaText);
            escrituraReplay.aumentarContador(contador + 1);
            archivoTXT = new ArchivoTXT(pathText);
            archivoReplay = new ArchivoReplay(archivoTXT, (ManejadorEscrituraReplay.NOMBRE_ARCHIVOS_PARTIDAS + contador), pathGPReplay);
            replay.getListaArchivosReplay().add(archivoReplay);
        } catch (IOException ex) {
            Logger.getLogger(ManejadorReplay.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void guadarReplay(Replay replay, String path){
        try {
            escrituraReplay.escrituraArchivoReplay(replay, path);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en la escritura del replay, vuelve a intentarlo más tarde.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void obtenerArchivo(Principal principal, FileNameExtensionFilter extensionFilter,  DialogoErrores errores) throws Exception{
        leerCargarReplay(extensionFilter, principal, principal, errores);
    }
    
    public void obtenerSiguienteCaptura(ArchivoReplay archivoReplay, JFrame component, JPanel panelPrincipal, JScrollPane scrollPane, Principal principal, boolean editar, boolean replay, DialogoErrores errores){
        try {
            principal.getTxtMensajes().setText(escrituraReplay.leerContadorYTXT(archivoReplay.getArchivoTXT().getUbicacion()));            
            leerCargaPartida(archivoReplay.getPath(), component, panelPrincipal, scrollPane, principal, editar, replay, errores);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String leerCargarReplay(FileNameExtensionFilter extensionFilter, JFrame component, Principal principal, DialogoErrores errores) throws FileNotFoundException, IOException, Exception{
        String path = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(extensionFilter);
        int opcion = chooser.showOpenDialog(component);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String docIde = "";
            File archivo = chooser.getSelectedFile();
            path = archivo.getAbsolutePath();
            FileInputStream entrada;
            entrada = new FileInputStream(archivo);
            int e;
            while ((e=entrada.read()) != -1) {
                char caracter = (char) e;
                docIde += caracter;
            }
            entrada.close();
            if (!errores.isVisible()) {
                errores.limpiarTXTArea();
                errores.setVisible(false);
            }
            LexicoReplay lexico = new LexicoReplay(new StringReader(docIde));
            lexico.setErrores(errores);
            SintaxReplay s = new SintaxReplay(lexico, principal, errores);
            s.parse();
            errores.setVisible(true);
        }
        return path;
    }
    
    public void leerCargaPartida(String pathArchivo, JFrame component, JPanel panelPrincipal, JScrollPane scrollPane, Principal principal, boolean editar, boolean replay, DialogoErrores errores) throws FileNotFoundException, IOException, Exception{
        String docIde = "";
        FileInputStream entrada;
        entrada = new FileInputStream(new File(pathArchivo));
        int e;
        while ((e = entrada.read()) != -1) {
            char caracter = (char) e;
            docIde += caracter;
        }
        entrada.close();
        if (!errores.isVisible()) {
            errores.limpiarTXTArea();
            errores.setVisible(false);
        }
        LexerGuardarPartida lexico = new LexerGuardarPartida(new StringReader(docIde));
        lexico.setErrores(errores);
        SintaxGuardarPartida s = new SintaxGuardarPartida(lexico, component, panelPrincipal, scrollPane, principal, editar, pathArchivo, replay, errores);
        s.parse();
        errores.setVisible(true);
    }
}
