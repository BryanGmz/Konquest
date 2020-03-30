/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import konquest.backed.objetos.*;

/**
 *
 * @author bryan
 */
public class ManejadorEscrituraArchivo {
    private final EstructuraKonquest estructuraKonquest;
    private final List<Planetas> planetasNeutrales = new ArrayList<>();
    private final List<Planetas> planetasJugadores = new ArrayList<>();
    
    public ManejadorEscrituraArchivo(EstructuraKonquest estructuraKonquest) {
        this.estructuraKonquest = estructuraKonquest;
    }
    
    public void escribirArchivoSalida(String path) throws IOException {
        File chooser = new File(path);
        try (FileOutputStream salida = new FileOutputStream(chooser)) {
            byte[] byteTxt = construirFlujoDeSalida().getBytes();
            salida.write(byteTxt);
        }
    }
    
    public void escrituraArchivoGuardarPartida(Mapa mapa, List<Jugadores> lista, List<Planetas> planetas, String path, boolean mostrar) throws FileNotFoundException, IOException {
        File chooser = new File(path);
        try (FileOutputStream salida = new FileOutputStream(chooser)) {
            byte[] byteTxt = construirGuardarPartida(mapa, lista, planetas).getBytes();
            salida.write(byteTxt);
        } 
        if (mostrar) {
            JOptionPane.showMessageDialog(null, "Guardado");
        } else {
            System.out.println("Guardado");
        }
    }
    
    public String construirGuardarPartida(Mapa mapa, List<Jugadores> lista, List<Planetas> planetas) {
        separarLista(planetas);
        String salida;
        salida = "{";
        salida += construccionGuardarMapa(mapa);
        salida += estructuraPlanetasGuardar(false, planetasJugadores);
        salida += estructuraPlanetasGuardar(true, planetasNeutrales);
        salida += estructuraJugadores(lista, true);
        salida += estructuraAtaqueGuardar(lista);
        salida += "\n}";
        return salida;
    }
    
    public String construirFlujoDeSalida() {
        String salida;
        salida = "{";
        salida += construccionFlujoMapa(estructuraKonquest.getMapa());
        salida += estructuraPlanetas(false, estructuraKonquest.getEspacioPlanetas().getListaPlanetas());
        salida += estructuraPlanetas(true, estructuraKonquest.getEspacioPlanetasNeutrales().getListaPlanetas());
        salida += estructuraJugadores(estructuraKonquest.getJugadores(), false);
        salida += "\n}";
        return salida;
    }
    
    public String construccionFlujoMapa(Mapa mapa) {
        String salida;
        salida = "\n\tMAPA: {";
        salida += "\n\t\tid: \"" + mapa.getAtributtosMapa().getiD() + "\",";
        salida += "\n\t\ttamaño: {";
        salida += "\n\t\t\tfilas: " + mapa.getAtributtosMapa().getTamaño().getFilas() + ",";
        salida += "\n\t\t\tcolumnas: " + mapa.getAtributtosMapa().getTamaño().getColumnas();
        salida += "\n\t\t},";
        salida += "\n\t\talAzar: " + mapa.getAtributtosMapa().isAlAzar() + ",";
        if (mapa.getAtributtosMapa().isAlAzar()) {
            salida += "\n\t\tplanetasNeutrales: " + mapa.getAtributtosMapa().getPlanetasNeutrales() + ",";
        }
        salida += "\n\t\tmapaCiego: " + mapa.getAtributtosMapa().isMapaCiego()+ ",";
        salida += "\n\t\tacumular: " + mapa.getAtributtosMapa().isAcumular()+ ",";
        salida += "\n\t\tNEUTRALES: {";
        salida += "\n\t\t\tmostrarNaves: " + mapa.getAtributtosMapa().getNeutrales().isMostrarNaves() + ",";
        salida += "\n\t\t\tmostrarEstadisticas: " + mapa.getAtributtosMapa().getNeutrales().isMostrarEstadisticas() + ",";
        salida += "\n\t\t\tproduccion: " + mapa.getAtributtosMapa().getNeutrales().getProduccion();
        salida += "\n\t\t}";
        if (mapa.getAtributtosMapa().getFinalizacion() != 0) {
            salida += ",\n\t\tfinalizacion: " + mapa.getAtributtosMapa().getFinalizacion();
        }
        salida += "\n\t},";
        return salida;
    }
    
    public String construccionGuardarMapa(Mapa mapa) {
        String salida;
        salida = "\n\tMAPA: {";
        salida += "\n\t\ttamaño: {";
        salida += "\n\t\t\tfilas: " + mapa.getAtributtosMapa().getTamaño().getFilas() + ",";
        salida += "\n\t\t\tcolumnas: " + mapa.getAtributtosMapa().getTamaño().getColumnas();
        salida += "\n\t\t},";
        salida += "\n\t\tmapaCiego: " + mapa.getAtributtosMapa().isMapaCiego()+ ",";
        salida += "\n\t\tturno: " + mapa.getTurnoActual()+ ",";
        salida += "\n\t\tcantidadPlanetas: " + mapa.getCantidadPlanetas() + ",";
        salida += "\n\t\tjugador: \"" + mapa.getJugadorEnTurno().getNombre() + "\"";
        if (mapa.getAtributtosMapa().getFinalizacion() != 0) {
            salida += ",\n\t\tfinalizacion: " + mapa.getAtributtosMapa().getFinalizacion();
        }
        salida += "\n\t},";
        return salida;
    }
    
    public String estructuraPlanetas(boolean neutrales, List<Planetas> planetas){
        String salida;
        if (neutrales) {
            salida = "\n\tPLANETAS_NEUTRALES: [";
        } else {
            salida = "\n\tPLANETAS: [";
        }
        for (int i = 0; i < planetas.size(); i++) {
            if (i == (planetas.size() - 1)) {
                salida += construccionFlujoPlanetas(planetas.get(i), true);
            } else {
                salida += construccionFlujoPlanetas(planetas.get(i), false);
            }
        }
        salida += "\n\t],";
        return salida;
    }
    
    public String estructuraPlanetasGuardar(boolean neutrales, List<Planetas> planetas){
        String salida;
        if (neutrales) {
            salida = "\n\tPLANETAS_NEUTRALES: [";
        } else {
            salida = "\n\tPLANETAS: [";
        }
        for (int i = 0; i < planetas.size(); i++) {
            if (i == (planetas.size() - 1)) {
                salida += construccionGuardarPlanetas(planetas.get(i), true);
            } else {
                salida += construccionGuardarPlanetas(planetas.get(i), false);
            }
        }
        salida += "\n\t],";
        return salida;
    }
    
    public String estructuraAtaqueGuardar(List<Jugadores> listaJugadores){
        String salida;
        salida = "\n\tATAQUE: [";
        for (int i = 0; i < listaJugadores.size(); i++) {
            for (int j = 0; j < listaJugadores.get(i).getListaAtaques().size(); j++) {
                if (i == (listaJugadores.size()-1) && (j == listaJugadores.get(i).getListaAtaques().size()-1)) {
                    salida += construccionGuardarAtaques(listaJugadores.get(i).getListaAtaques().get(j), true);
                } else {
                    salida += construccionGuardarAtaques(listaJugadores.get(i).getListaAtaques().get(j), false);
                }
            }
        }
        salida += "\n\t]";
        return salida;
    }
    
    /* ONLINE */
    public String estructuraOnlineAtaque(Jugadores jugador) {
        String salida;
        salida = "\nATAQUE: [";
        for (int j = 0; j < jugador.getListaAtaques().size(); j++) {
            if ((j == (jugador.getListaAtaques().size()-1))) {
                salida += construccionGuardarAtaques(jugador.getListaAtaques().get(j), true);
            } else {
                salida += construccionGuardarAtaques(jugador.getListaAtaques().get(j), false);
            }
        }
        salida += "\n]";
        return salida;
    }
    
    public String estructuraJugadores(List<Jugadores> jugadores, boolean cargar) {
        String salida;
        salida = "\n\tJUGADORES: [";
        for (int i = 0; i < jugadores.size(); i++) {
            if (i == (jugadores.size() - 1)) {
                salida += construccionFlujoJugadores(jugadores.get(i), true);
            } else {
                salida += construccionFlujoJugadores(jugadores.get(i), false);
            }
        }
        if (cargar) {
            salida += "\n\t],";
        } else {
            salida += "\n\t]";
        }
        return salida;
    }
    
    public String construccionFlujoPlanetas(Planetas planeta, boolean fin) {
        String salida;
        salida = "\n\t\t{";
        salida += "\n\t\t\tnombre: \"" + planeta.getNombre() + "\",";
        salida += "\n\t\t\tnaves: " + planeta.getNaves() + ",";
        if (planeta.getProduccion() != 0) {
            salida += "\n\t\t\tproduccion: " + planeta.getProduccion() + ",";
        } 
        salida += "\n\t\t\tporcentajeMuertes: " + planeta.getPorcentajeMuertes() + "";
        if (fin) {
            salida += "\n\t\t}";
        } else {
            salida += "\n\t\t},";
        }
        return salida;
    }
    
    public String construccionGuardarAtaques(Ataque ataque, boolean fin) {
        String salida;
        salida = "\n\t\t{";
        salida += "\n\t\t\tplanetaDestino: \"" + ataque.getPlanetaDestino().getNombre() + "\",";
        salida += "\n\t\t\tplanetaOrigen: \"" + ataque.getPlanetaOrigen().getNombre() + "\",";
        salida += "\n\t\t\tjugador: \"" + ataque.getPlanetaOrigen().getJugador().getNombre() + "\",";
        salida += "\n\t\t\tporcentajeMuertes: " + ataque.getPorcentajeMuertes() + ",";
        salida += "\n\t\t\tnaves: " + ataque.getNumeroDeNaves() + ",";
        salida += "\n\t\t\tturno: " + ataque.getTurnoLlegada();
        if (fin) {
            salida += "\n\t\t}";
        } else {
            salida += "\n\t\t},";
        }
        return salida;
    }
    
    public String construccionGuardarPlanetas(Planetas planeta, boolean fin) {
        String salida;
        salida = "\n\t\t{";
        salida += "\n\t\t\tnombre: \"" + planeta.getNombre() + "\",";
        salida += "\n\t\t\tnaves: " + planeta.getNaves() + ",";
        if (planeta.getProduccion() != 0) {
            salida += "\n\t\t\tproduccion: " + planeta.getProduccion() + ",";
        } 
        salida += "\n\t\t\tporcentajeMuertes: " + planeta.getPorcentajeMuertes() + ",";
        salida += "\n\t\t\tfila: " + planeta.getFila() + ",";
        salida += "\n\t\t\tcolumna: " + planeta.getColumna() + ",";
        salida += "\n\t\t\tcantidadProducida: " + planeta.getCantidadProducida();
        if (fin) {
            salida += "\n\t\t}";
        } else {
            salida += "\n\t\t},";
        }
        return salida;
    }
    
    public String construccionFlujoJugadores(Jugadores jugadores, boolean fin) {
        String salida;
        salida = "\n\t\t{";
        salida += "\n\t\t\tnombre: \"" + jugadores.getNombre() + "\",";
        salida += "\n\t\t\tplanetas: [" + construccionCadenaPlanetas(jugadores.getPlanetas());
        salida += "\n\t\t\t],";
        salida += "\n\t\t\ttipo: " + jugadores.tipoCadena();
        if (fin) {
            salida += "\n\t\t}";
        } else {
            salida += "\n\t\t},";
        }
        return salida;
    }
    
    
    public String construccionCadenaPlanetas(List<Planetas> planetas){ 
        String salida = "";
        for (int i = 0; i < planetas.size(); i++) {
            if (i == (planetas.size() - 1)) {
                salida += "\n\t\t\t\t\"" + planetas.get(i).getNombre() + "\"";
            } else {
                salida += "\n\t\t\t\t\"" + planetas.get(i).getNombre() + "\",";
            }
        }
        return salida;
    }

    private void separarLista(List<Planetas> lista){
        planetasJugadores.clear();
        planetasNeutrales.clear();
        lista.forEach((planetas) -> {
            if (planetas.getJugador() != null) {
                planetasJugadores.add(planetas);
            } else {
                planetasNeutrales.add(planetas);
            }
        });
    }
}
