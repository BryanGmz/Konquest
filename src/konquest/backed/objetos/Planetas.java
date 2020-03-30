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
public class Planetas {
    private String nombre;
    private int naves;
    private int produccion;
    private double porcentajeMuertes;
    private Jugadores jugador;
    private int fila;
    private int columna;
    private int navesIniciales;
    private int cantidadProducida;
    private boolean filaExiste;
    private boolean columnaExiste;
    private boolean cantidadProducidaExiste;
    private boolean navesExiste;
    
    public Planetas() {}
    
    public Planetas(String nombre, int naves, int produccion, double porcentajeMuertes) {
        this.nombre = nombre.replaceAll("\"", "");
        this.naves = naves;
        this.navesIniciales = naves;
        this.produccion = produccion;
        this.porcentajeMuertes = porcentajeMuertes;
        this.cantidadProducida = 0;
    }

    public Jugadores getJugador() {
        return jugador;
    }

    public void setJugador(Jugadores jugador) {
        this.jugador = jugador;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.replaceAll("\"", "");
    }

    public int getNaves() {
        return naves;
    }

    public void setNaves(int naves) {
        this.naves = naves;
    }

    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        this.produccion = produccion;
    }

    public double getPorcentajeMuertes() {
        return porcentajeMuertes;
    }

    public void setPorcentajeMuertes(double porcentajeMuertes) {
        this.porcentajeMuertes = porcentajeMuertes;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getNavesIniciales() {
        return navesIniciales;
    }

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(int cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public boolean isFilaExiste() {
        return filaExiste;
    }

    public void setFilaExiste(boolean filaExiste) {
        this.filaExiste = filaExiste;
    }

    public boolean isColumnaExiste() {
        return columnaExiste;
    }

    public void setColumnaExiste(boolean columnaExiste) {
        this.columnaExiste = columnaExiste;
    }

    public boolean isCantidadProducidaExiste() {
        return cantidadProducidaExiste;
    }

    public void setCantidadProducidaExiste(boolean cantidadProducidaExiste) {
        this.cantidadProducidaExiste = cantidadProducidaExiste;
    }

    public boolean isNavesExiste() {
        return navesExiste;
    }

    public void setNavesExiste(boolean navesExiste) {
        this.navesExiste = navesExiste;
    }
    
    public int calcularDistancia(int filaS, int columnaS) {
        int distancia = 0;
        if (this.fila < filaS) {
            distancia += filaS - this.fila; 
        } else {
            distancia += this.fila - filaS;
        }
        if (this.columna < columnaS) {
            distancia += columnaS - this.columna; 
        } else {
            distancia += this.columna - columnaS;
        }
        return distancia;
    }
    
    public String inf(){
        String salida = "";
        salida += ("\nInformacion Planeta: ");
        if (this.nombre != null) {
            salida += ("\nNombre: " + this.nombre);
        }
        if (this.naves != 0) {
            salida += ("\nNaves: " + this.naves);
        }
        if (this.produccion != 0) {
            salida += ("\nProduccion: " + this.produccion);
        }
        if (this.porcentajeMuertes != 0.0) {
            salida += ("\nPorcentaje: " + this.porcentajeMuertes);
        }
        return salida;
    }
    
    public String infCarga(){
        String salida = "";
        salida += ("\nInformacion Planeta: ");
        if (this.nombre != null) {
            salida += ("\nNombre: " + this.nombre);
        }
        if (this.naves != 0) {
            salida += ("\nNaves: " + this.naves);
        }
        if (this.produccion != 0) {
            salida += ("\nProduccion: " + this.produccion);
        }
        if (this.porcentajeMuertes != 0.0) {
            salida += ("\nPorcentaje: " + this.porcentajeMuertes);
        }
        if (jugador != null) {
            salida += jugador.inf();
        }
        salida += "\nFila: " + fila;
        salida += "\nColumna: " + columna;
        salida += "\nCantidad Producida: " + cantidadProducida;
        return salida;
    }
    
    @Override
    public Planetas clone() throws CloneNotSupportedException {
        Planetas regresar = new Planetas(this.nombre, this.naves, this.produccion, this.porcentajeMuertes);
        regresar.setJugador(this.jugador);
        return regresar;
    }
}
