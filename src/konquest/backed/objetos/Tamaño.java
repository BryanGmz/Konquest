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
public class Tamaño {
    private int filas;
    private int columnas;

    public Tamaño(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
    public String inf(){
        String salida = "";
        salida += ("\nInformacion Tamaño: ");
        salida += ("\nFilas: " + this.filas);
        salida += ("\nColumnas: " + this.columnas + "\n");
        return salida;
    }
}
