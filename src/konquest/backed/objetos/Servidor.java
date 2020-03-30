/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.objetos;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author bryan
 */
public class Servidor extends Observable implements Runnable{
    
    private final int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket servidor;
        Socket socket;
        DataInputStream dataInputStream;
        System.out.println("Servidor Iniciado");
        try {
            servidor = new ServerSocket(puerto);
            while (true) {
                socket = servidor.accept();
                dataInputStream = new DataInputStream(socket.getInputStream());
                String flujo = dataInputStream.readUTF();
                
                System.out.println("Flujo Entrada");
                
                this.setChanged();
                this.notifyObservers(flujo);
                this.clearChanged();
                
                socket.close();
                System.out.println("Cliente Desconectado");
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
