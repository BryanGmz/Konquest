/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest;

import konquest.backed.manejadores.ManejadorEscrituraReplay;
import konquest.gui.Principal;

/**
 *
 * @author bryan
 */
public class Konquest {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        ManejadorEscrituraReplay escrituraReplay = new ManejadorEscrituraReplay();
        escrituraReplay.crearCarpetasYContador();
        Principal principal = new Principal();
        principal.setVisible(true);
        
        GeneradorArchivos generadorArchivos = new GeneradorArchivos();
//        generadorArchivos.generador();
    
    }
}
