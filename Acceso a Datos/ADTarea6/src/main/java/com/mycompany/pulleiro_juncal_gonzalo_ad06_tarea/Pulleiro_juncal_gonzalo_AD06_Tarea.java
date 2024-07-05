package com.mycompany.pulleiro_juncal_gonzalo_ad06_tarea;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XQueryService;

/**
 *
 * @author Gonzalo
 */
public class Pulleiro_juncal_gonzalo_AD06_Tarea {

    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static String COLECCION = "/db/ejercicios";
    private static String CONSULTA = "for $libro in /bib/libro return $libro/titulo";

    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 3.");

        final String driver = "org.exist.xmldb.DatabaseImpl";

        //inicializar driver db
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        Collection col = null;

        try {
            col = DatabaseManager.getCollection(URI + COLECCION);
            XQueryService xqs = (XQueryService) col.getService("XQueryService", "1.0");
            xqs.setProperty("indent", "yes");

            CompiledExpression compiled = xqs.compile(CONSULTA);
            ResourceSet result = xqs.execute(compiled);
            ResourceIterator i = result.getIterator();
            Resource res = null;
            System.out.println("Libros presentes en el fichero libros.xml");

            while (i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    System.out.println("\t" + res.getContent());
                } finally {
                    try {
                        ((EXistResource) res).freeResources();
                    } catch (XMLDBException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

}
