/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db4o_congreso;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 *
 * @author hugo
 */
public class DB4O_Congreso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // https://www.youtube.com/watch?v=fUD_tqwGbC0&list=PLOXABFmJCH7kCJaDI-1HPplBaZY_eAJn1
        // TODO code application logic here
        //es necesario realizar los 2 imports anteriores, ya que si no es ponible que automaticamente no los haga produciendose un error.
     ObjectContainer baseDatos=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"congreso_tut5.db4o");
    //creamos un ponente
     Ponente ponente1=new Ponente("123654278F","ponente1","email@email.com",10.0f);
    
       
    Ponente  p2 = new Ponente("1234567Z", "ponente2", "email12@email.com", 11.0f);
    Ponente  p3 = new Ponente("23456Z", "ponente3", "email13@email.com", 12.0f);
    Ponente  p4 = new Ponente("56567Z", "ponente4", "email14@email.com", 15.0f);
    Ponente  p5 = new Ponente("8887Z", "ponente5", "email15@email.com", 25.0f);
    Ponente  p6 = new Ponente("94567Z", "ponente6", "email16@email.com", 19.0f);
    Ponente  p7 = new Ponente("111987Z", "ponente7", "email17@email.com", 250.0f);
    
    //Charlas
    
    Charla c1 = new Charla("charla1", 1f,p2);
    Charla c2 = new Charla("charla2", 3f,p7);
    Charla c3 = new Charla("charla3", 2f);
    c3.setPonente((new Ponente("99999F","ponente8", "email8@email.com",9.0f)));
    Charla c4 = new Charla("charla4", 5f, p3);
    Charla c5 = new Charla("charla5", 6f, p2);
    
     try{
         /*Metodos.almacenarPonente(baseDatos, ponente1);
         Metodos.almacenarPonente(baseDatos, p2);
         Metodos.almacenarPonente(baseDatos, p3);
         Metodos.almacenarPonente(baseDatos, p4);
         Metodos.almacenarPonente(baseDatos, p5);
         Metodos.almacenarPonente(baseDatos, p6);
         Metodos.almacenarPonente(baseDatos, p7);*/
         
         /*Metodos.consultarQBEPonentesDni(baseDatos, "8887Z");
         System.out.println("**************************");
         System.out.println("Todos los ponentes");
         Metodos.consultarQBEPonentesDni(baseDatos, null);
         System.out.println("**************************");
         System.out.println("ponenete de nombre ponente4");
         Metodos.consultarQBEPonentesNombre(baseDatos, "ponente4");
         Metodos.consultarQBEPonentesDni(baseDatos, "111987Z");*/
         /*Metodos.almacenarCharla(baseDatos, c1);
         Metodos.almacenarCharla(baseDatos, c2);
         Metodos.almacenarCharla(baseDatos, c3);
         Metodos.almacenarCharla(baseDatos, c4);
         Metodos.almacenarCharla(baseDatos, c5);*/
         
         //Ejemplos de actualizacion
         System.out.println("Actualizar cache de un ponente");
         Metodos.actualizarCachePonente(baseDatos, "ponente3", 987.0f);
         Metodos.consultaSODAPonentesNombre(baseDatos, "ponente3");
         System.out.println("**********************************");
         
     }finally{
         Metodos.cerrarConexion(baseDatos);
     }
     
    }
        
    }

