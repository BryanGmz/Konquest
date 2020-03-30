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
public class Mapa {
    private AtributtosMapa atributtosMapa;
    private String areaText;
    private int turnoActual;
    private int cantidadPlanetas;
    private Jugadores jugadorEnTurno;
    
    public Mapa() {}
    
    public Mapa(AtributtosMapa atributtosMapa) {
        this.atributtosMapa = atributtosMapa;
    }

    public AtributtosMapa getAtributtosMapa() {
        return atributtosMapa;
    }

    public void setAtributtosMapa(AtributtosMapa atributtosMapa) {
        this.atributtosMapa = atributtosMapa;
    }

    public String getAreaText() {
        return areaText;
    }

    public void setAreaText(String areaText) {
        this.areaText = areaText;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    public int getCantidadPlanetas() {
        return cantidadPlanetas;
    }

    public void setCantidadPlanetas(int cantidadPlanetas) {
        this.cantidadPlanetas = cantidadPlanetas;
    }

    public Jugadores getJugadorEnTurno() {
        return jugadorEnTurno;
    }

    public void setJugadorEnTurno(Jugadores jugadorEnTurno) {
        this.jugadorEnTurno = jugadorEnTurno;
    }
    
    public String inf() {
        String salida = "";
        salida += ("\n\nInformacion Mapa: ");
        if (atributtosMapa != null) {
            salida += atributtosMapa.inf();
        }
        return salida;
    }
    
    public String infCagar() {
        String salida = "";
        salida += ("\n\nInformacion Mapa: ");
        if (atributtosMapa != null) {
            salida += atributtosMapa.inf();
        }
        salida += "\nTurno: " + turnoActual;
        salida += "\nCantidad Planetas: " + cantidadPlanetas;
        if (jugadorEnTurno != null) {
            salida += jugadorEnTurno.inf();
        }
        return salida;
    }
}
