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
public class AtributtosMapa {
    private String  iD;
    private Tamaño tamaño;
    private Neutrales neutrales;
    private boolean alAzar;
    private boolean mapaCiego;
    private boolean acumular;
    private int planetasNeutrales;
    private int finalizacion;
    
    private boolean cAlAzar;
    private boolean cMapaCiego;
    private boolean cAcumular;
    private boolean cPlanetasNeutrales;
    private boolean cFinalizacion;

    public AtributtosMapa() {}
    
    public AtributtosMapa(String iD, Tamaño tamaño, Neutrales neutrales, boolean alAzar, boolean mapaCiego, boolean acumular, int planetasNeutrales, int finalizacion) {
        this.iD = iD;
        this.tamaño = tamaño;
        this.neutrales = neutrales;
        this.alAzar = alAzar;
        this.mapaCiego = mapaCiego;
        this.acumular = acumular;
        this.planetasNeutrales = planetasNeutrales;
        this.finalizacion = finalizacion;
    }
    
    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD.replace("\"", "");
    }

    public Tamaño getTamaño() {
        return tamaño;
    }

    public void setTamaño(Tamaño tamaño) {
        this.tamaño = tamaño;
    }

    public Neutrales getNeutrales() {
        return neutrales;
    }

    public void setNeutrales(Neutrales neutrales) {
        this.neutrales = neutrales;
    }

    public boolean isAlAzar() {
        return alAzar;
    }

    public void setAlAzar(boolean alAzar) {
        this.alAzar = alAzar;
    }

    public boolean isMapaCiego() {
        return mapaCiego;
    }

    public void setMapaCiego(boolean mapaCiego) {
        this.mapaCiego = mapaCiego;
    }

    public boolean isAcumular() {
        return acumular;
    }

    public void setAcumular(boolean acumular) {
        this.acumular = acumular;
    }

    public int getPlanetasNeutrales() {
        return planetasNeutrales;
    }

    public void setPlanetasNeutrales(int planetasNeutrales) {
        this.planetasNeutrales = planetasNeutrales;
    }

    public int getFinalizacion() {
        return finalizacion;
    }

    public void setFinalizacion(int finalizacion) {
        this.finalizacion = finalizacion;
    }

    public boolean iscAlAzar() {
        return cAlAzar;
    }

    public void setcAlAzar(boolean cAlAzar) {
        this.cAlAzar = cAlAzar;
    }

    public boolean iscMapaCiego() {
        return cMapaCiego;
    }

    public void setcMapaCiego(boolean cMapaCiego) {
        this.cMapaCiego = cMapaCiego;
    }

    public boolean iscAcumular() {
        return cAcumular;
    }

    public void setcAcumular(boolean cAcumular) {
        this.cAcumular = cAcumular;
    }

    public boolean iscPlanetasNeutrales() {
        return cPlanetasNeutrales;
    }

    public void setcPlanetasNeutrales(boolean cPlanetasNeutrales) {
        this.cPlanetasNeutrales = cPlanetasNeutrales;
    }

    public boolean iscFinalizacion() {
        return cFinalizacion;
    }

    public void setcFinalizacion(boolean cFinalizacion) {
        this.cFinalizacion = cFinalizacion;
    }   
    
    public String inf(){
        String salida = "";
        if (iD != null) {
            salida += "\nID: " + iD;
        }
        if (tamaño != null) {
            salida += tamaño.inf();
        } 
        salida += ("\nPlanetas Neutrales: " + planetasNeutrales);
        salida += ("\nAl Azar: " + alAzar);
        salida += ("\nMapa Ciego: " + mapaCiego);
        salida += ("\nAcumular: " + acumular);
        if (neutrales != null) {
            salida += neutrales.inf();
        }
        salida += ("\nFinalizacion: " + finalizacion);
        salida += ("\n\n");
        return salida;
    }
}
