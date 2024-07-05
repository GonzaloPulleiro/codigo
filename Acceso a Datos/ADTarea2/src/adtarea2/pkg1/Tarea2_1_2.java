
package adtarea2.pkg1;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Tarea2_1_2 {

    public static void main(String[] args) throws Exception {
        
        //añadimos el archivo empleados.dat creado en el apartado anterior del ejercicio
        File f = new File("C:\\Users\\gonza\\Desktop\\FP\\AD\\UD2\\Tarea\\ADTarea2.1\\EMPLEADOS.DAT");
        //accedemos a el 
        RandomAccessFile raf = new RandomAccessFile(f, "r");

        //establecemos los campos
        int codigo;
        String nombre, direccion;
        Float salario, comision;

        try {
            
            //creamos documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");

            
            //con el bucle for leeremos los datos del archivo
            for (;;) {
                codigo = raf.readInt();
                nombre = raf.readUTF();
                direccion = raf.readUTF();
                salario = raf.readFloat();
                comision = raf.readFloat();

                
                //establecemos elemento raíz Empleado
                Element raiz = document.createElement("Empleado");
                document.getDocumentElement().appendChild(raiz);
                //añadimos atributo código a empleado
                raiz.setAttribute("Código", Integer.toString(codigo));
                //ahora leemos los datos restantes y los vamos guardando
                crearElemento("Nombre", nombre.trim(), raiz, document);
                crearElemento("Dirección", direccion.trim(), raiz, document);
                crearElemento("Salario", Float.toString(salario), raiz, document);
                crearElemento("Comisión", Float.toString(comision), raiz, document);

                if (raf.getFilePointer() >= raf.length()) {
                    break;
                }
            }
            //creamos el archivo xml de salida
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("C:\\Users\\gonza\\Desktop\\FP\\AD\\UD2\\Tarea\\ADTarea2.1\\Empleados.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (EOFException eofe) {
            System.out.println("Error End Of File: " + eofe.getMessage());
        } catch (IOException e) {
            System.out.println("Error IO: " + e.getMessage());
        } catch (TransformerConfigurationException tce) {
            System.out.println("Error Transformer Configuration: " + tce.getMessage());
        } catch (TransformerException te) {
            System.out.println("Error Transformer: " + te.getMessage());
        } catch (ParserConfigurationException pce) {
            System.out.println("Error Parser Configuration: " + pce.getMessage());
        } finally {
            raf.close();
        }
    }

    static void crearElemento(String datoEmpleado, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmpleado);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

}
