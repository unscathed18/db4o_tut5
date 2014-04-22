/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db4o_congreso;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
/**
 *
 * @author hugo
 */
public class Metodos {
     /**
     * Permite cerrar la conexion a la base de datos que se esta utilizando
     * @param baseDatos el ObjectContainer de la base de datos
     */
    public static void cerrarConexion(ObjectContainer baseDatos){
        try{
            baseDatos.close();
        }catch(Exception e){System.out.println("error al cerrar la conexion");}
    }
    
    /**
     * Permite almacenar un Ponente en la base de datos
     * @param baseDatos el objeto que representa la base de datos en la que se almacenara el ponente
     * @param ponente el ponente que se desea almacear en la base de datos
     */
    public static void almacenarPonente(ObjectContainer baseDatos, Ponente ponente){
    try{
        baseDatos.store(ponente);
        System.out.println("Se ha almacenado correctamente el ponente");
    }catch(Exception e){System.out.println("Se ha porducido un error en la insercion");}
    }
    
      /**
     * Imprime por pantalla el resultado de una consulta sin importar el metodo
     * de consulta
     *
     * @param resultado el objeto en el que se ha almacenado el resultado de la
     * consulta
     */
    public static void imprimirResultadoConsulta(ObjectSet resultado) {
        System.out.println("Recuperados " + resultado.size() + " Objetos");
        while (resultado.hasNext()) {
            System.out.println(resultado.next());
        }

    }
    
        /**
     * Permite hacer una consulta mediante Query-by-Example a partir de un dni e
     * imprimirla por pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param dni el dni del ponente que se quiere buscar
     */
    public static void consultarQBEPonentesDni(ObjectContainer baseDatos, String dni) {
        Ponente ponente = new Ponente(dni, null, null, 0.0f);
        ObjectSet resultado = baseDatos.queryByExample(ponente);
        imprimirResultadoConsulta(resultado);

    }
    
      /**
     * Permite hacer una consulta mediante Query-by-Example a partir de un
     * nombre e imprimirla por pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param nombre el nombre del ponente que se desea encontrar
     */
    public static void consultarQBEPonentesNombre(ObjectContainer baseDatos, String nombre) {
        Ponente ponente = new Ponente(null, nombre, null, 0.0f);
        ObjectSet resultado = baseDatos.queryByExample(ponente);
        imprimirResultadoConsulta(resultado);

    }
    
     /**
     * Permite hacer una consulta mediante Query-by-Example a partir de un cache
     * imprimirla por pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param cache el cache del ponente que se desea encontrar
     */
    public static void consultarQBEPonentesCache(ObjectContainer baseDatos, float cache) {
        Ponente ponente = new Ponente(null, null, null, cache);
        ObjectSet resultado = baseDatos.queryByExample(ponente);
        imprimirResultadoConsulta(resultado);

    }
    
    /**
     * Permite realizar una consulta Nativa a partir del dni e imprimirla en
     * pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param dni el dni del ponente que se desea encontrar
     */
    public static void consultarNatPonentesDni(ObjectContainer baseDatos, final String dni) {
        List res = baseDatos.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente ponente) {
                return ponente.getDNI().equalsIgnoreCase(dni);
            }

            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        imprimirResultadoConsulta((ObjectSet) res);
    }
    /**
     * Permite realizar una consulta Nativa y obtener a todos los ponentes que
     * tengan un determinado cache
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param cache el cacha que ha de tener el ponente
     */
    public static void consulrtarNatPonentesCacheIgualA(ObjectContainer baseDatos, final float cache) {

        List res = baseDatos.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente ponente) {
                return ponente.getCache() == cache;
            }

            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        imprimirResultadoConsulta((ObjectSet) res);
    }

    /**
     * Permite realizar una consulta Nativa y obtener todos los ponentes que
     * tengan un cache superior al indicado
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param cacheBase el cache base de los ponentes
     */
    public static void consultarNatPonentesCacheSuperiorA(ObjectContainer baseDatos, final float cacheBase) {

        List res = baseDatos.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente ponente) {
                return ponente.getCache() >= cacheBase;
            }

            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        imprimirResultadoConsulta((ObjectSet) res);
    }
    
    /**
     * Permite realizar una consulta SODA cuyo resultado sean todos los ponentes
     *
     * @param baseDatos la base de datos desde la que se va a operar
     */
    public static void consultaSODAponentes(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);//declara las restricciones
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }

    /**
     * Permite realizar una consulta SODA cuyo resultado sea el ponente cuyo
     * nombre se ha introducido por parametro
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param nombre el nombre del ponente que se quiere recuperar
     */
   public static void consultaSODAPonentesNombre(ObjectContainer baseDatos, String nombre) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la restricion es el parametro nombre
        Constraint constraint = query.descend("nombre").constrain(nombre);
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);


    } 
   
     /**
     * Permite realizar una consulta SODA que recupera los ponentes cuyo cache
     * esta entre los indicados por parametro
     *
     * @param baseDatosla base de datos desde la que se va a operar
     * @param cacheInferior el cache que marca limite inferior
     * @param CacheSuperior el cache que marcha el limite superior
     */
    public static void consultaSODAPonentesCacheEntre(ObjectContainer baseDatos, float cacheInferior, float CacheSuperior) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        //creamos el primer constraint diciendole que el cache ha de ser menor del superior
        Constraint constraint = query.descend("cache").constrain(CacheSuperior).smaller();
        //se enlazan las dos restricciones a aplicar
        query.descend("cache").constrain(cacheInferior).greater().and(constraint);
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }

    /**
     * Consulta SODA que permite recuperar ordenados por cache todos los
     * ponentes
     *
     * @param baseDatos la base de datos desde la que se va a operar
     */
    public static void consultaSODAPonentesOrdenadosCache(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        query.descend("cache").orderDescending();
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }
    
    public static void imprimirResultadosConsulta(ObjectSet resultado){
        System.out.println("Recuperados " + resultado.size()+ " Objetos");
        while(resultado.hasNext()){
            System.out.println(resultado.next());
        }
    }
    
    public static void almacenarCharla(ObjectContainer baseDatos, Charla charla){
        try{
            baseDatos.store(charla);
            System.out.println("Se ha almacenado correctamente la charla.");
        }catch(Exception e){
            System.out.println("Se ha producido un error en la insercion.");
        }
    }
    
    public static void actualizarCachePonente(ObjectContainer baseDatos, String nombrePonente, float nuevoCache){
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        //Creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la restriccion es el parametro
        Constraint constraint = query.descend("nombre").constrain(nombrePonente);
        ObjectSet resultado = query.execute();
        Ponente p = (Ponente)resultado.get(0);
        p.setCache(nuevoCache);
        baseDatos.store(p);
    }
}