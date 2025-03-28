package local.gonzalo.exame.examefinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author dammdprog1
 */
public class Examen {

    private String enunciado, descripcion;
    private int idExamen, idPregunta, idOpcion, numero;
    private double puntos;
    private LocalDateTime fecha;
    private List<Pregunta> preguntas;

    public Examen() {
        this.descripcion = "Programación";
        this.fecha = LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 20));
    }

    public Examen(String descripcion) {
        this.descripcion = descripcion;
        this.preguntas = new ArrayList<>();
        this.fecha = LocalDateTime.now();
    }

    public Examen(String descripcion, int ano, int mes, int dia, int hora, int minuto) {
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.of(ano, mes, dia, hora, minuto);
        this.preguntas = new ArrayList<>();
    }

    public Examen(String descripcion, int idExamen, LocalDateTime fecha) {
        this.descripcion = descripcion;
        this.idExamen = idExamen;
        this.fecha = fecha;
    }
    
    

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void addPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    public void removePregunta(Pregunta pregunta) {
        preguntas.remove(pregunta);
    }

    public void ordenar() {
       Collections.sort(preguntas);
    }

    @Override
    public int hashCode() {
        return 59 * 7 + this.idExamen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Examen other = (Examen) obj;
        return this.idExamen == other.idExamen;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        return sb.append(descripcion) + " " + sb.append(Character.LINE_SEPARATOR)
                + "( " + sb.append(fecha)
                + " ): " + sb.append(Character.LINE_SEPARATOR)
                + " " + sb.append(preguntas);
    }

}
