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
public class Ataque {
    private Planetas planetaDestino; 
    private Planetas planetaOrigen;
    private double porcentajeMuertes;
    private int numeroDeNaves;
    private int turnoLlegada;
    private Jugadores jugador;
    
    public Ataque() {}
    
    public Ataque(Planetas planetaDestino, Planetas planetaOrigen, int numeroDeNaves, int turnoLlegada) {
        this.planetaDestino = planetaDestino;
        this.planetaOrigen = planetaOrigen;
        this.numeroDeNaves = numeroDeNaves;
        this.turnoLlegada = turnoLlegada;
        this.porcentajeMuertes = planetaOrigen.getPorcentajeMuertes();
    }

    @Override
    public Ataque clone() throws CloneNotSupportedException{
        return new Ataque(this.planetaDestino, this.planetaOrigen, this.numeroDeNaves, this.turnoLlegada);
    }

    public Jugadores getJugador() {
        return jugador;
    }

    public void setJugador(Jugadores jugador) {
        this.jugador = jugador;
    }
    
    public Planetas getPlanetaDestino() {
        return planetaDestino;
    }

    public void setPlanetaDestino(Planetas planetaDestino) {
        this.planetaDestino = planetaDestino;
    }

    public Planetas getPlanetaOrigen() {
        return planetaOrigen;
    }

    public void setPlanetaOrigen(Planetas planetaOrigen) {
        this.planetaOrigen = planetaOrigen;
    }

    public double getPorcentajeMuertes() {
        return porcentajeMuertes;
    }

    public void setPorcentajeMuertes(double porcentajeMuertes) {
        this.porcentajeMuertes = porcentajeMuertes;
    }

    public int getNumeroDeNaves() {
        return numeroDeNaves;
    }

    public void setNumeroDeNaves(int numeroDeNaves) {
        this.numeroDeNaves = numeroDeNaves;
    }

    public int getTurnoLlegada() {
        return turnoLlegada;
    }

    public void setTurnoLlegada(int turnoLlegada) {
        this.turnoLlegada = turnoLlegada;
    }
    
    public void cancelarAtaque() {
        this.planetaOrigen.setNaves(this.planetaOrigen.getNaves() + this.numeroDeNaves);
    }
    
    public void recompensa() {
        if (this.planetaDestino.getJugador() != null) {
            this.planetaDestino.getJugador().getPlanetas().remove(this.planetaDestino);
            for (int i = 0; i < this.planetaDestino.getJugador().getListaAtaques().size(); i++) {
                Ataque ataque = this.planetaDestino.getJugador().getListaAtaques().get(i);
                if (ataque.getPlanetaOrigen().getNombre().equals(this.planetaDestino.getNombre())) {
                    this.planetaDestino.getJugador().getListaAtaques().remove(ataque);
                    ataque.cancelarAtaque();
                }
            }
        }
        this.planetaDestino.setJugador(this.planetaOrigen.getJugador());
        this.planetaOrigen.getJugador().getPlanetas().add(this.planetaDestino);
    }
    
    public boolean comprobarAtaque(){
        double ataque = this.planetaOrigen.getPorcentajeMuertes() * this.numeroDeNaves;
        double defensa = this.planetaDestino.getNaves() * this.planetaDestino.getPorcentajeMuertes();
        if (ataque >= defensa) {
             this.planetaDestino.setNaves((int)((ataque-defensa)));
            return true;
        } else {
            if (this.planetaDestino.getNaves() >= this.numeroDeNaves) {
                this.planetaDestino.setNaves((int)((defensa-ataque)));
            } else {
                this.planetaDestino.setNaves(0);
            }
            return false;
        }
    }
    
    public String inf(){
        String salida = "\nAtaque \n";
        if (planetaDestino != null) {
            salida += "\nPlaneta Destino: " + planetaDestino.inf();
        }
        if (planetaOrigen != null) {
            salida += "\nPlaneta Origen: " + planetaOrigen.inf();
        }
        if (jugador != null) {
            salida += "\nJugador: " + jugador.inf();
        }
        salida += "\n% Muertes: " + porcentajeMuertes;
        salida += "\n# Naves: " + numeroDeNaves;
        salida += "\n# Turno de Llegada: " + turnoLlegada;
        return salida;
    }
}
