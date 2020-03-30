/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bryan
 */
public class Jugadores {
    private String nombre;
    private String tipoString;
    private List<Planetas> planetas;
    private List<String> planetasCadena;
    private int tipo;
    private List<Ataque> listaAtaques;
    
    public Jugadores(){}
    
    public Jugadores(String nombre, String tipoCadena, List<String> planetasCadena) {
        this.nombre = nombre.replaceAll("\"", "");
        this.planetas = new ArrayList<>();
        this.planetasCadena = planetasCadena;
        this.listaAtaques = new ArrayList<>();
        this.setTipo(tipoCadena.replaceAll("\"", ""));
    }

    public List<String> getPlanetasCadena() {
        return planetasCadena;
    }

    public void setPlanetasCadena(List<String> planetasCadena) {
        this.planetasCadena = planetasCadena;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.replaceAll("\"", "");
    }

    public List<Planetas> getPlanetas() {
        return planetas;
    }

    public void setPlanetas(List<Planetas> planetas) {
        this.planetas = planetas;
    }

    public int getTipo() {
        return tipo;
    }

    public List<Ataque> getListaAtaques() {
        return listaAtaques;
    }

    public void setListaAtaques(List<Ataque> listaAtaques) {
        this.listaAtaques = listaAtaques;
    }

    public String getTipoString() {
        switch (this.tipo) {
            case 1:
                return "HUMANO";
            case 2:
                return "FACIL";
            case 3:
                return "DIFICIL";
            default:
                return "";
        }
    }

    public void setTipoString(String tipoString) {
        this.tipoString = tipoString.replaceAll("\"", "");
    }
    
    public String tipoCadena() {
        switch (this.tipo) {
            case 1:
                return "HUMANO";
            case 2:
                return "FACIL";
            case 3:
                return "DIFICIL";
            default:
                return "";
        }
    }
    
    public void setTipo(String tipo) {
        switch (tipo.replaceAll("\"", "")) {
            case "HUMANO":
                this.tipo = 1;
                break;
            case "FACIL":
                this.tipo = 2;
                break;
            case "DIFICIL":
                this.tipo = 3;
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public String inf(){
        String salida = "";
        salida += ("\nInformacion Jugador: ");
        if (nombre != null) {
            salida += ("\nNombre: " + this.nombre);
        }
        if (planetasCadena != null) {
            salida += ("\nPlanetas: \n");
            for (int i = 0; i < this.planetasCadena.size(); i++) {
                salida += ("\nPlaneta -> " + this.planetasCadena.get(i));
            }
        }
        if (tipoCadena() != null) {
            salida += ("\n\nTipo: " + this.tipoCadena());
        }
        return salida;
    }
    
    @Override
    public Jugadores clone() throws CloneNotSupportedException {
        Jugadores jugador = new Jugadores(this.nombre, this.tipoCadena(), this.planetasCadena);
        jugador.setPlanetas(this.planetas);
        return jugador;
    }
}
