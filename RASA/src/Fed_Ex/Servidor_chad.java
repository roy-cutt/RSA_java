/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fed_Ex;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author Administrador
 */
public class Servidor_chad {
     //Inicializamos el puerto y el numero maximo de conexciones que acepta el servidor
    private final int puerto = 35556;
    private final int noConexiones = 10;
    //private final String ip = "192.168.0.1";
    static InetAddress dirServidor = null;
    //Creamos una lista de sockets, donde guardaremos los sockets que se vayan conectando
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
       
   //Funcion para que el servidor empieze a recibir conexiones de clientes
    public void escuchar(){
        try {
            //Creamos el socket servidor
            ServerSocket servidor = new ServerSocket(puerto,noConexiones);
            //Ciclo infinito para estar escuchando por nuevos clientes
            while(true){
                System.out.println("Servidor en linea");
                //Cuando un cliente se conecte guardamos el socket en nuestra lista
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
                //Instanciamos un hilo que estara atendiendo al cliente y lo ponemos a escuchar
                Runnable  run = new Hilo_servidor(cliente,usuarios);
                Thread hilo = new Thread(run);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //Funcion main para correr el servidor
    public static void main(String[] args) {
        Servidor_chad servidor= new Servidor_chad();
        servidor.escuchar();
    }
}
