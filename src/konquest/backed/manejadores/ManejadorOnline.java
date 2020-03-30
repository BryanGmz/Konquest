/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import konquest.backed.analizador.partida.LexerGuardarPartida;
import konquest.backed.analizador.partida.SintaxGuardarPartida;
import konquest.backed.objetos.Ataque;
import konquest.backed.objetos.Jugadores;
import konquest.backed.objetos.Mapa;
import konquest.backed.objetos.Planetas;
import konquest.gui.DialogoErrores;
import konquest.gui.Principal;

/**
 *
 * @author bryan
 */
public class ManejadorOnline {
    private ManejadorEscrituraArchivo escrituraArchivo;
    
    public ManejadorOnline() {}
        
    public ManejadorOnline(ManejadorEscrituraArchivo escrituraArchivo) {
        this.escrituraArchivo = escrituraArchivo;
    }
    
    //para obtener el ataque
    public void obtenerAtaque(String flujoEntrada, JFrame component, JPanel panelPrincipal, JScrollPane scrollPane, Principal principal, DialogoErrores errores) throws FileNotFoundException, IOException, Exception{
        if (!errores.isVisible()) {
            errores.limpiarTXTArea();
            errores.setVisible(false);
        }
        LexerGuardarPartida lexico = new LexerGuardarPartida(new StringReader(flujoEntrada));
        lexico.setErrores(errores);
        SintaxGuardarPartida s = new SintaxGuardarPartida(lexico, component, panelPrincipal, scrollPane, principal, false, "", false, errores);
        s.parse();
        errores.setVisible(true);
    }
    
    //Para recibir flujo de ataque
    public void agregarAtaques(Principal principal, List<Ataque> lista){
        if (lista != null && !lista.isEmpty()) {
            int indice = 0;
            boolean banderaEncontrado = false;
            String nombreJugadorFlujo = lista.get(0).getJugador().getNombre();
            for (int i = 0; i < principal.getListaJugadores().size(); i++) {
                if (principal.getListaJugadores().get(i).getNombre().equalsIgnoreCase(nombreJugadorFlujo)) {
                    principal.getListaJugadores().get(i).setListaAtaques(lista);
                    banderaEncontrado = true;
                    indice = i;
                    System.out.println("Agregado");
                }
            }
            if (banderaEncontrado) {
                for (Ataque ataque : principal.getListaJugadores().get(indice).getListaAtaques()) {
                    asignarAtributosPlanetas(principal, ataque);
                }
            }
            
        }
    }
    
    private void asignarAtributosPlanetas(Principal principal, Ataque ataque) {
        principal.getTodosLosPlanetas().stream().map((todosLosPlaneta) -> {
            if (ataque.getPlanetaDestino().getNombre().equalsIgnoreCase(todosLosPlaneta.getNombre())) {
                ataque.setPlanetaDestino(todosLosPlaneta);
            }
            return todosLosPlaneta;
        }).filter((todosLosPlaneta) -> (ataque.getPlanetaOrigen().getNombre().equalsIgnoreCase(todosLosPlaneta.getNombre()))).forEachOrdered((todosLosPlaneta) -> {
            ataque.setPlanetaOrigen(todosLosPlaneta);
        });
    }
    
    //Para generar flujo de salida
    public String enviarAtaque(Jugadores jugadores){
        return escrituraArchivo.estructuraOnlineAtaque(jugadores);
    }
    
    
    //Para leer el flujo del mapa
    public void leerMapaFlujo(String flujo, JFrame component, JPanel panelPrincipal, JScrollPane scrollPane, Principal principal, DialogoErrores errores) throws FileNotFoundException, IOException, Exception{
        if (!errores.isVisible()) {
            errores.limpiarTXTArea();
            errores.setVisible(false);
        }
        LexerGuardarPartida lexico = new LexerGuardarPartida(new StringReader(flujo));
        lexico.setErrores(errores);
        SintaxGuardarPartida s = new SintaxGuardarPartida(lexico, component, panelPrincipal, scrollPane, principal, false, "", false, errores);
        s.parse();
        errores.setVisible(true);
    }
    
    public boolean isAtaque(String entrada) {
        return entrada.startsWith("\nATAQUE:");
    }
    
    //Para generar flujo de salida mapa
    public String sincronizacionMapa(Mapa mapa, List<Jugadores> lista, List<Planetas> planetas){
        if (lista.size() == 2) {
            return escrituraArchivo.construirGuardarPartida(mapa, lista, planetas);
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento debes de cargar un mapa con unicamente dos jugadores.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }
}
