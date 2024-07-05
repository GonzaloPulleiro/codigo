package com.mycompany.model;

import java.awt.Image;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 *
 * @author Gonzalo
 */
public class Book implements Serializable {
    
    // Atributos de la clase Book
    private long idBook;
    private String isbn, titulo, autor;
    private short anho;
    private boolean disponible;
    private byte[] portada;
    private LocalDate dataPublicacion;

    // Constructores 
    
    // Constructor vacío
    public Book() {

    }

    // Constructor con argumentos 1
    public Book(String isbn, String titulo, String autor, short anho, boolean disponible) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anho = anho;
        this.disponible = disponible;
    }

     // Constructor con argumentos 2
    public Book(long idBook, String isbn, String titulo, String autor, short anho, boolean disponible, byte[] portada) {
        this.idBook = idBook;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anho = anho;
        this.disponible = disponible;
        this.portada = portada;
    }

    // Métodos de la clase 
    
    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public short getAnho() {
        return anho;
    }

    public void setAnho(short anho) {
        this.anho = anho;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public byte[] getPortada() {
        return portada;
    }

    public void setPortada(byte[] portada) {
        this.portada = portada;
    }

    public LocalDate getDataPublicacion() {
        return dataPublicacion;
    }

    public void setDataPublicacion(LocalDate dataPublicacion) {
        this.dataPublicacion = dataPublicacion;
    }
    
    // Asigna una portada recogiendo una referencia File
    public Book setPortada(File f) {
        try {
            this.portada = Files.readAllBytes(f.toPath());
        } catch (IOException ex) {
            System.out.println("Dirección non atopada: " + ex.getMessage());
        }

        return this;
    }

    // Recoge la ruta al archivo
    public Book setPortada(String f) {
        return setPortada(new File(f));
    }

    // Si la portada no es nula, crea un objeto Image. 
    public Image getImage() throws IOException {
        if (portada != null) {
            ByteArrayInputStream flujo = new ByteArrayInputStream(portada);
            return ImageIO.read(flujo);
        }
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public boolean equals(Object isbn) {
        if (this == isbn) {
            return true;
        }
        if (isbn == null || getClass() != isbn.getClass()) {
            return false;
        }
        Book libro = (Book) isbn;
        return Objects.equals(this.isbn, isbn);
    }

    @Override
    public String toString() {

        return "Libro [" + "titulo= " + titulo + ", autor= " + autor + ", anho= " + anho + (disponible ? "" : " *") +  " ]";

    }

}
