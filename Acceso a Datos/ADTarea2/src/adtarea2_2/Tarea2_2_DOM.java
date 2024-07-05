/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package adtarea2_2;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 *
 * @author Gonzalo
 */
public class Tarea2_2_DOM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("libros.xml"));
            
            NodeList listaLibro = document.getElementsByTagName("libro");

            for (int i = 0; i < listaLibro.getLength(); i++) {
                Node hijosLibro = listaLibro.item(i);

                if (hijosLibro.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) hijosLibro;
                    System.out.println("Año: " + eElement.getAttribute("año"));
                    System.out.println("Titulo: " + eElement.getElementsByTagName("titulo").item(0).getTextContent());
                    System.out.println("Autor: " + eElement.getElementsByTagName("nombre").item(0).getTextContent()
                            + " " + eElement.getElementsByTagName("apellido").item(0).getTextContent());
                    System.out.println("Editorial: " + eElement.getElementsByTagName("editorial").item(0).getTextContent());
                    System.out.println("Precio: " + eElement.getElementsByTagName("precio").item(0).getTextContent());
                    System.out.println("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
