package pruebamatriculabean;

import alumnobean.*;
/**
 *
 * @author Gonzalo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        PruebaMatricula pm = new PruebaMatricula();
        AccesoBD ab = new AccesoBD();
        
        System.out.println("---ALUMNOS---");
        System.out.println("Listado: ");
        ab.listado();
        
        System.out.println("Engade: ");
        ab.engade();
        
        System.out.println("----------------------------------------------------------------");
        
        System.out.println("---MATRICULA---");
        
        System.out.println("Listado: ");
        pm.listado();
        
        System.out.println("Engade: ");
        pm.engade();
        
        System.out.println("Consulta Listado: ");
        pm.consultaListado();
        
        
         System.out.println("----------------------------------------------------------------");
    }
    
}
