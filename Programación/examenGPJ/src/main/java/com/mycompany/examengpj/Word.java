/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examengpj;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Gonzalo
 */
public class Word implements Comparable<Word> {

    private String palabra, grafia, lema, definicion;
    private LocalDateTime ultimaConsulta;
    private static int numeroConsultas;

    public Word() {
    }

    public Word(String palabra, String grafia, String lema, String definicion) {
        this.palabra = palabra;
        this.grafia = grafia;
        this.lema = lema;
        this.definicion = definicion;
        this.ultimaConsulta = LocalDateTime.now();
        numeroConsultas++;
    }
    
    public Word(String palabra, String grafia, String lema, String definicion,  LocalDateTime ultimaConsulta) {
        this.palabra = palabra;
        this.grafia = grafia;
        this.lema = lema;
        this.definicion = definicion;
        this.ultimaConsulta = ultimaConsulta;
        numeroConsultas++;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getGrafia() {
        return grafia;
    }

    public void setGrafia(String grafia) {
        this.grafia = grafia;
    }

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    public String getDefinicion() {
        if (definicion == null) {
            return "";
        }
        return definicion.replace(".", ".\n");
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public LocalDateTime getUltimaConsulta() {
        return ultimaConsulta;
    }

    public void setUltimaConsulta(LocalDateTime ultimaConsulta) {
        this.ultimaConsulta = ultimaConsulta;
    }

    public void setUltimaConsulta() {
        this.ultimaConsulta = LocalDateTime.now();
    }

    public String getUltimaConsultaAsString() {
        if (ultimaConsulta == null) {
            return "";
        } else {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return ultimaConsulta.format(formato);
        }
    }

    public static int getNumeroConsultas() {
        return numeroConsultas;
    }

    public static void setNumeroConsultas(int numeroConsultas) {
        Word.numeroConsultas = numeroConsultas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(palabra.toLowerCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Word other = (Word) obj;
        return palabra.equalsIgnoreCase(other.palabra);
    }

    @Override
    public int compareTo(Word o) {
        return (palabra != null) ? this.palabra.compareToIgnoreCase(o.palabra) : -1;
    }

    @Override
    public String toString() {
        return grafia + "[" + lema + "] (" + getUltimaConsultaAsString() + "):\n" + getDefinicion();
    }

}
