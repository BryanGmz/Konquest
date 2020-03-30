/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.objetos;

import java.util.List;

/**
 *
 * @author bryan
 */
public class EstructuraKonquest {
    private Mapa mapa;
    private Espacio espacioPlanetas;
    private Espacio espacioPlanetasNeutrales;
    private List<Planetas> planetas;
    private List<Planetas>  planetasNeutrales;
    private List<Jugadores>  jugadores;
    private List<Ataque> ataques;
    
    public EstructuraKonquest() {}

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public List<Planetas> getPlanetas() {
        return planetas;
    }

    public void setPlanetas(List<Planetas> planetas) {
        this.planetas = planetas;
    }

    public List<Planetas> getPlanetasNeutrales() {
        return planetasNeutrales;
    }

    public void setPlanetasNeutrales(List<Planetas> planetasNeutrales) {
        this.planetasNeutrales = planetasNeutrales;
    }

    public List<Jugadores> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    public Espacio getEspacioPlanetas() {
        return espacioPlanetas;
    }

    public Espacio getEspacioPlanetasNeutrales() {
        return espacioPlanetasNeutrales;
    }

    public void setEspacioPlanetas(Espacio espacioPlanetas) {
        this.espacioPlanetas = espacioPlanetas;
    }

    public void setEspacioPlanetasNeutrales(Espacio espacioPlanetasNeutrales) {
        this.espacioPlanetasNeutrales = espacioPlanetasNeutrales;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    public void setAtaques(List<Ataque> ataques) {
        this.ataques = ataques;
    }
    
    public String inf(){
        String salida = "\nInformacion Estructura";
        if (mapa != null) {
            salida += mapa.inf();
        }
        if (espacioPlanetas != null) {
            salida += " \n\nPLANETAS: \n";
            for (int i = 0; i < espacioPlanetas.getListaPlanetas().size(); i++) {
                salida += espacioPlanetas.getListaPlanetas().get(i).inf();
            }
        }
        if (espacioPlanetasNeutrales != null) {
            salida += " \n\nPLANETAS NEUTRALES: \n";
            for (int i = 0; i < espacioPlanetasNeutrales.getListaPlanetas().size(); i++) {
                salida += espacioPlanetasNeutrales.getListaPlanetas().get(i).inf();
            }
        }
        if (jugadores != null) {
            salida += " \n\nJUGADORES: \n";
            for (int i = 0; i < jugadores.size(); i++) {
                salida += jugadores.get(i).inf();
            }
        }
        if (ataques != null) {
            salida += " \n\nATAQUES: \n";
            for (int i = 0; i < ataques.size(); i++) {
                salida += ataques.get(i).inf();
            }
        }
        return salida;
    }
}
