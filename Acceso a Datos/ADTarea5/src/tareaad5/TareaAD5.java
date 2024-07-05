package tareaad5;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Gonzalo
 */
public class TareaAD5 {

    public static void main(String[] args) {

        File fichero = new File("BDJefeHijo");
        fichero.delete();
        /*Este código anterior lo ponemos por si la base de datos ya existiera y quisiéramos empezar desde el principio.*/
        ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo");

        try {
            baseDatos.store(new Jefe("Ángel", 5, 53, new Hijo("Gustavo", 7)));
            baseDatos.store(new Jefe("Nieves", 3, 45, new Hijo("Iván", 3)));
            baseDatos.store(new Jefe("Jesús", 3, 5, new Hijo("Noelia", 3)));
            baseDatos.store(new Jefe("Dolores", 5, 63, new Hijo("Sergio", 7)));
            baseDatos.store(new Jefe("Vicki", 3, 5, null));
            baseDatos.store(new Jefe("Fátima", 5, 63, new Hijo("Lidia", 27)));
            baseDatos.store(new Jefe("Juan Luís", 3, 5, null));
            baseDatos.store(new Jefe("Elena", 1, 42, new Hijo("David", 19)));
            baseDatos.store(new Jefe("Miguel", 20, 45, new Hijo("Paula", 3)));
            baseDatos.store(new Jefe("Jesús", 19, 44, new Hijo("Rubén", 12)));

            System.out.println("Base de datos creada con éxito");
            Scanner sc = new Scanner(System.in);
            //Crearemos un while para escoller a según os exercicios propostos
            boolean salir = false;
            int opcion;

            while (!salir) {
                System.out.println("ESCOLLE UNHA OPCIÓN:");
                System.out.println("1. VISUALIZA OS XEFES QUE TEÑAN MÁIS DE 55 ANOS");
                System.out.println("2. MODIFICA A IDADE DE MIGUEL INCREMENTANDOA NUN ANO MÁIS");
                System.out.println("3. BORRA OS XEFES QUE LEVAN MÁIS DE 6 ANOS NA EMPRESA");
                System.out.println("4. VISUALIZA TODOS OS XEFES QUE QUEDAN, INCLUIDOS OS SEUS FILLOS, QUE NON FORON BORRADOS ANTERIORMENTE");
                System.out.println("5. SAIR");
                opcion = sc.nextInt();
                System.out.println("");

                
                switch (opcion) {

                    case 1: //visualiza os xefes que teñan máis de 55 anos
                        consultaEdad(baseDatos);
                        break;
                    case 2: //modifica a idade de Miguel incrementandoa nun ano máis
                        incrementaMiguel(baseDatos, 1);
                        //en este caso opto por un continue en lugar de un break, para poder seguir
                        //utilizando la aplicación una vez ejecute esto.
                        continue;
                    case 3: //borra os xefes que levan máis de 6 anos na empresa
                        eliminaJefes(baseDatos);
                        break;
                    case 4: //visualiza todos os xefes que quedan, incluidos os seus fillos, que non foron borrados anteriormente
                        mostrarJefes(baseDatos);
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                        break;

                }

            }
        } catch (Exception e) {

        } finally {
            baseDatos.close();
        }
    }
    //facemos uso do método empregado nos exemplos facilitados nos apuntes
    //amosa a cantidade de resultados que se obteñen da consulta 
    //e tamén os propios datos

    public static void mostrarConsulta(ObjectSet resul) {
        System.out.println("Recuperados " + resul.size() + " Objetos");
        while (resul.hasNext()) {
            System.out.println(resul.next());
        }
    }

    public static void consultaEdad(ObjectContainer baseDatos) {
        //creamos el objeto query()
        Query query = baseDatos.query();
        //establecemos la clase en la que se aplicará
        query.constrain(Jefe.class);
        //declaramos unha restricción con constraint, neste caso idade ata 100 anos
        Constraint constraint = query.descend("edad").constrain(100).smaller();
        //agora declaramos a segunda, con 50 anos, así mostrará os xefes entre 55 e 100 anos
        //enlazamos as dúas restriccións
        query.descend("edad").constrain(55).greater().and(constraint);
        ObjectSet res = query.execute();
        //sacamos por pantalla chamando ao método anterior
        mostrarConsulta(res);

    }
    //introducimos a cantidade que queremos aumentar na idade como segundo parámetro
    public static void incrementaMiguel(ObjectContainer baseDatos, int aumenta){
        //seleccionamos o obxecto Miguel da base de datos
        ObjectSet res = baseDatos.queryByExample(new Jefe("Miguel"));
        Jefe j = (Jefe) res.next();
        //establecemos a idade, para iso obtemos a idade que ten Miguel e sumamoslle a cantidade introducida por parámetro
        j.setEdad(j.getEdad() + aumenta);
        //actualizamos os datos
        baseDatos.store(j);

    }
    //elaboramos un método similar ao de consultaEdad().
    public static void eliminaJefes(ObjectContainer baseDatos){
        //creamos un obxeto query().
        Query query = baseDatos.query();
        //establecemos a clase na que se aplicara a restricción.
        query.constrain(Jefe.class);
        //declaramos as restriccións, de 6 a 25 anos(por poñer un num), xa que hai que eliminar os que 
        // teñen antigüedade superior a 6 anos
        //enlazamos as restriccións
        Constraint constraint = query.descend("antig").constrain(25).smaller();
        query.descend("antig").constrain(7).greater().and(constraint);
        //executamos a consulta
        ObjectSet res = query.execute();
        while(res.hasNext()){
            Jefe j = (Jefe) res.next();
            System.out.println(j);
            baseDatos.delete(j);
        }
    }

    public static void mostrarJefes(ObjectContainer baseDatos){
        Jefe j = new Jefe(null);
        ObjectSet res = baseDatos.queryByExample(j);
        mostrarConsulta(res);
    }


}
