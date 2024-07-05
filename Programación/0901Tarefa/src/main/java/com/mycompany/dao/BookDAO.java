package com.mycompany.dao;

import com.mycompany.model.Book;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Gonzalo
 */
public class BookDAO implements DAO<Book> {

    private Connection con;

    public BookDAO(Connection conexion) {
        this.con = conexion;
    }

    @Override
    public Book get(long idBook) {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Book WHERE idBook = ?")) {
            ps.setLong(1, idBook);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getLong("idBook"),
                        rs.getString("isbn"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getShort("anho"),
                        rs.getBoolean("disponible"),
                        rs.getBytes("portada"));
            }
        } catch (SQLException e) {
            System.err.println("Error en BookDAO.get(): " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Book> getAll() {
        List<Book> libros = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Book")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                libros.add(new Book(
                        rs.getLong("idBook"),
                        rs.getString("isbn"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getShort("anho"),
                        rs.getBoolean("disponible"),
                        rs.getBytes("portada")));
            }

        } catch (SQLException ex) {
            System.err.println("Error en BookDAO.getAll(): " + ex.getMessage());
        }
        return libros;
    }

    @Override
    public void save(Book book) {
        String consulta = "INSERT INTO Book (isbn, titulo, autor, anho, disponible, portada) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(consulta,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitulo());
            ps.setString(3, book.getAutor());
            ps.setShort(4, book.getAnho());
            ps.setBoolean(5, book.isDisponible());
            ps.setBytes(6, book.getPortada());

            int afectadas = ps.executeUpdate();

            if (afectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    book.setIdBook(rs.getLong(1));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en BookDAO.save(): " + ex.getMessage());
        }

    }

    @Override
    public void update(Book book) {
        String consulta = "UPDATE Book SET isbn = ?, titulo = ?, autor = ?, anho = ?, disponible = ?, portada = ? WHERE idBook = ? ";
        try (PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitulo());
            ps.setString(3, book.getAutor());
            ps.setShort(4, book.getAnho());
            ps.setBoolean(5, book.isDisponible());
            ps.setBytes(6, book.getPortada());
            ps.setLong(7, book.getIdBook());

            int afectadas = ps.executeUpdate();
            if (afectadas == 0) {
                System.out.println("No se actualizó ningún registro.");
            } else {
                System.out.println("Se actualizó el registro: " + book.getIdBook());
            }

        } catch (SQLException ex) {
            System.err.println("Error en BookDAO.update(): " + ex.getMessage());
        }
    }

    @Override
    public void delete(Book idBook) {
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM Book WHERE idBook = ?")) {
            ps.setLong(1, idBook.getIdBook());
            int afectadas = ps.executeUpdate();
            if (afectadas == 0) {
                System.out.println("No se eliminó ningún registro.");
            } else {
                System.out.println("Se eliminó el registro: " + idBook.getIdBook());
            }
        } catch (SQLException ex) {
            System.err.println("Error en BookDAO.delete(): " + ex.getMessage());
        }
    }

    @Override
    public boolean deleteById(long idBook) {
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM Book WHERE idBook = ?")) {
            ps.setLong(1, idBook);
            int afectadas = ps.executeUpdate();
            return afectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error en BookDAO.deleteById(): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Integer> getAllIds() {
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement("SELECT idBook FROM Book")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("idBook"));
            }
             Collections.sort(ids);
            System.out.println(ids);
        } catch (SQLException ex) {
            System.err.println("Error en BookDAO.getAllIds(): " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void updateLOB(Book b, String f) {

        try (PreparedStatement ps = con.prepareStatement("UPDATE Book b SET portada = ? WHERE idBook = ?")) {
            FileInputStream in = new FileInputStream(f);
            ps.setBinaryStream(1, in);
            ps.setLong(2, b.getIdBook());
            ps.executeUpdate();

            System.out.println("Se actualizó la portada del libro: " + b.getIdBook());

        } catch (SQLException | IOException ex) {
            System.err.println("Error en BookDAO.updateLOB(): " + ex.getMessage());
        }
    }

    @Override
    public void updateLOBById(long b, String f) {

        try (PreparedStatement ps = con.prepareStatement("UPDATE Book b SET portada = ? WHERE idBook = ?")) {
            FileInputStream in = new FileInputStream(f);
            ps.setBinaryStream(1, in);
            ps.setLong(2, b);
            ps.executeUpdate();

            System.out.println("Se actualizó la portada del libro: " + b);

        } catch (SQLException | IOException ex) {
            System.err.println("Error en BookDAO.updateLOBById(): " + ex.getMessage());

        }
    }

    @Override
    public void deleteAll() {
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM Book")) {
            ps.executeUpdate();
            System.out.println("Se han eliminado todos los libros.");

        } catch (SQLException ex) {
            System.err.println("Error en BookDAO.deleteAll(): " + ex.getMessage());
        }
    }

}
