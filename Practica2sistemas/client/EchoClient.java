
package client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    //definimos el Stub del cliente
    private static EchoObjectStub ss;
    
    public static void main(String[] args) 
    {
        Scanner leer = new Scanner(System.in);
        String tarjeta="";
        String cvv="";
        String costo="";
        if (args.length<2) {
            System.out.println("Para ejecutar , hazlo en este formato: Echo <nombre o IP del Equipo> <numero de puerto>");
            System.exit(1);
        }
        //instanciamos el STUB
        ss = new EchoObjectStub();
        //Puente de comunicacion
        ss.setHostAndPort(args[0],Integer.parseInt(args[1]));
        String input,output;
        
        System.out.println("\t---SHOP---\t\n");
        int respuesta;
        System.out.println("Metodos de pago: \n");
        System.out.println("1.-Tarjeta\n");
        System.out.println("2.-Salir\n");
        respuesta = leer.nextInt();
        switch(respuesta){
            case 1:
                try {  
                    while(true){
                        
                    //preparo "apuntador" que es el lector de flujo para el teclado
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
               
                    // asigno a una variable y leo una linea del lector de flujo que leyo del teclado
                    System.out.println("Numero de tarjeta: ");
                    tarjeta=in.readLine();
                    System.out.println("Ingrese su CVV");
                    cvv=in.readLine();
                    System.out.println("Ingrese el monto: ");
                    costo=in.readLine();
                    String datos = tarjeta + "-" + cvv + "-" + costo;
                    //Invocar el stub con el metodo remoto echo e Imprimir .. 
                    //por pantalla lo que regreso el metodo remoto echo
                    System.out.println(ss.echo(datos));
                    //break;
                }
                }catch (IOException e) {
                    System.err.println("Falla conexion de E/S con el host:"+args[0]);
                }   
            
            
            
            case 2:

            break;
        }


        
        //catch (UnknownHostException e) {
            //System.err.println("Don't know about host: "+ args[0]);
        //} 
        /*catch (IOException e) {
            System.err.println("Falla conexion de E/S con el host:"+args[0]);
        }*/
    }
}
