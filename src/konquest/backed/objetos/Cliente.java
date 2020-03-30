/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.objetos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bryan
 */
public class Cliente implements Runnable{
    
    private final int puerto;
    private final String flujoSalida;

    public Cliente(int puerto, String flujoSalida) {
        this.puerto = puerto;
        this.flujoSalida = flujoSalida;
    }
    
    @Override
    public void run() {
        final String HOST = "localhost";
        //Puerto del servidor
        DataOutputStream out;

        try {
            try ( 
                //Creo el socket para conectarme con el cliente    
                Socket sc = new Socket(HOST, puerto)) {
                out = new DataOutputStream(sc.getOutputStream());
                //Envio un mensaje al cliente
                out.writeUTF(flujoSalida);
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
}
