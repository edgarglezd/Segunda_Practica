
package server;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
import rmi.EchoInterface;
public class EchoObjectSkeleton implements EchoInterface {
    String myURL="192.168.0.26";
    //Constructor de la clase EchoObjectSkeleton
    public EchoObjectSkeleton()
    {
        try {
            myURL=InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) 
               {
               myURL="192.168.0.26";
              }
    }
// el Metodo Echo que es la implementacion de la interfaz EchoInterface

    public String echo(String input) throws java.rmi.RemoteException {
        String[] datos = input.split("-"); // 0 = tarjeta, 1 = cvv, 2 = costo
       
        //pago(datos[0],datos[1],Integer.parseInt(datos[2]));
        // escribe la fecha y la hora, nombre de compuadora y lo regresa
        Date h = new Date();
        // obtengo la fecha y hora actual 
        System.out.println("Atendiendo a un cliente.");
        String fecha = DateFormat.getTimeInstance(3,Locale.FRANCE).format(h);
        String ret = myURL + ":" + fecha + "> " + input;
        String ret2 = "Transaccion EXITOSA";
        String ret3 = "Transaccion NO FUE EXITOSA";
        String ret4 = "Transaccion no fue exitosa: Numero de tarjeta invalido";
        String ret5 = "Transaccion no fue exitosa: CVV invalido";
        System.out.println("Procesando: '" + input + "'");
        /*try {
            Thread.sleep(3000); // hilo actual
            ret = ret + " (retrasada 3 segundos)";
        } catch (InterruptedException e) {}
        System.out.println("Procesamiento de '"+ input +"'terminado.");*/

        if(pago(datos[0],datos[1],Integer.parseInt(datos[2])) == 1){
            
            System.out.println("En ejecucion... Esperando conexiones");
            return ret2;
            
        }else if(pago(datos[0],datos[1],Integer.parseInt(datos[2])) == 2){
            System.out.println("Numero de tarjeta invalido");
            System.out.println("En ejecucion... Esperando conexiones");
            return ret4;
        }else if(pago(datos[0],datos[1],Integer.parseInt(datos[2])) == 3){
            System.out.println("CVV invalido");
            System.out.println("En ejecucion... Esperando conexiones");
            return ret5;
        }
        System.out.println("Transaccion no fue exitosa");
        System.out.println("En ejecucion... Esperando conexiones");
        return ret3;
    }

    int pago(String tarjeta,String cvv,int cantidad){
        String tregistrada = "1234567891234";
        String cregistrado = "123";
        int resta=0;
        int disponible = 500-resta; 
        if(tarjeta.equals(tregistrada)){
            if(cvv.equals(cregistrado)){
                if(cantidad<=disponible){
                    resta = disponible-cantidad;

                    return 1;
                }
            }else{
                return 3;
            }
        }else{
            return 2;
        }
        return 0;
    }
    
   }
