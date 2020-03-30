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
public class Neutrales {
    private boolean mostrarNaves;
    private boolean mostrarEstadisticas;
    private int produccion;

    public Neutrales(boolean mostrarNaves, boolean mostrarEstadisticas, int produccion) {
        this.mostrarNaves = mostrarNaves;
        this.mostrarEstadisticas = mostrarEstadisticas;
        this.produccion = produccion;
    }

    public boolean isMostrarNaves() {
        return mostrarNaves;
    }

    public void setMostrarNaves(boolean mostrarNaves) {
        this.mostrarNaves = mostrarNaves;
    }

    public boolean isMostrarEstadisticas() {
        return mostrarEstadisticas;
    }

    public void setMostrarEstadisticas(boolean mostrarEstadisticas) {
        this.mostrarEstadisticas = mostrarEstadisticas;
    }

    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        this.produccion = produccion;
    }
    
    public String inf(){
        String salida = "";
        salida += ("\nInformacion Neutrales: ");
        salida += ("\nMostrar Naves: " + this.mostrarNaves);
        salida += ("\nMostrar Estaditicas: " + this.mostrarEstadisticas);
        salida += ("\nProduccion: " + this.produccion + "\n");
        return salida;
    }
}
