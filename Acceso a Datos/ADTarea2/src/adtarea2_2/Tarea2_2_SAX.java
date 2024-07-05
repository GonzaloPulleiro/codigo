package adtarea2_2;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Tarea2_2_SAX extends DefaultHandler {

    private StringBuilder valor;

    public Tarea2_2_SAX() {
        this.valor = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.valor.setLength(0);

        if (qName.equals("libro")) {
            String año = attributes.getValue("año");
            System.out.println("Año: " + año);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        this.valor.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "libro":
                System.out.println("");
                break;
            case "titulo":
                System.out.println("Titulo: " + this.valor.toString());
                break;
            case "nombre":
                System.out.println("Nombre: " + this.valor.toString());
                break;
            case "apellido":
                System.out.println("Apellido: " + this.valor.toString());
                break;
            case "editorial":
                System.out.println("Editorial: " + this.valor.toString());
                break;
            case "precio":
                System.out.println("Precio: " + this.valor.toString());
                break;
        }
    }

    public static void main(String[] args) throws IOException {

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            Tarea2_2_SAX doc = new Tarea2_2_SAX();
            parser.parse("libros.xml", doc);
        }
        catch (ParserConfigurationException | SAXException  ex) {
            System.out.println(ex.getMessage());
    }

}
}
