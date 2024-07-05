package com.mycompany.examengpj;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Gonzalo
 */
public class WordDAO implements DictionaryDAO<Word, String> {

    private static final String DICCIONARIO = "C:\\Users\\gonza\\Desktop\\palabras.csv";
    private final Connection CONEXION;
    private final File FICHERO;

    public WordDAO(Connection CONEXION) {
        this.CONEXION = CONEXION;
        this.FICHERO = new File(DICCIONARIO);
    }

    public WordDAO(Connection CONEXION, String fichero) {
        this.CONEXION = CONEXION;
        this.FICHERO = new File(fichero);
    }

    @Override
    public List<Word> getAll() {
        List<Word> lista = new ArrayList<>();
        String consulta = "SELECT palabra, grafia, lema, definicion FROM Palabra";

        try (Statement st = CONEXION.createStatement(); ResultSet rs = st.executeQuery(consulta)) {

            while (rs.next()) {
                Word palabra = new Word(
                        rs.getString("palabra"),
                        rs.getString("grafica"),
                        rs.getString("lema"),
                        rs.getString("definicion"));
                //palabra.setUltimaConsulta();
                lista.add(palabra);

            }

        } catch (SQLException ex) {
            System.err.println("Error en getAll() : " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public List<Word> getAllFromFile() {
        List<Word> lista = new ArrayList<>();

        if (!FICHERO.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campo = linea.split("\\|");
                if (campo.length == 4) {
                    Word palabra = new Word(campo[0], campo[1], campo[2], campo[3]);
                    palabra.setUltimaConsulta();
                    lista.add(palabra);
                }
            }
        } catch (IOException ex) {
            System.err.println("Error en getAllFromFile() : " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Word get(String palabra) {
        String consulta = "SELECT palabra, grafia, lema, definicion FROM Palabra WHERE palabra = ?";

        try (PreparedStatement ps = CONEXION.prepareStatement(consulta)) {
            ps.setString(1, palabra);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Word word = new Word(
                            rs.getString("palabra"),
                            rs.getString("grafica"),
                            rs.getString("lema"),
                            rs.getString("definicion"));
                    word.setUltimaConsulta();
                    return word;

                }
            }

        } catch (SQLException ex) {
            System.err.println("Error en get() : " + ex.getMessage());
        }

        return null;
    }

    @Override
    public boolean save(Word palabra) {
        String consulta = "INSERT INTO Palabra (palabra, grafia, lema, definicion) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = CONEXION.prepareStatement(consulta)) {

            ps.setString(1, palabra.getPalabra());
            ps.setString(2, palabra.getGrafia());
            ps.setString(3, palabra.getLema());
            ps.setString(4, palabra.getDefinicion());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error en save() : " + ex.getMessage());
        }
        return false;
    }

}
