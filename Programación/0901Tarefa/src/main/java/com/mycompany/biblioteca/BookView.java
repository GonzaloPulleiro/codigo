package com.mycompany.biblioteca;

import com.mycompany.dao.BookDAO;
import com.mycompany.model.Book;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Gonzalo
 */
public class BookView extends JFrame {

    public static ImageIcon icon;

    static {
        try {
            icon = new ImageIcon(BookView.class.getResource("/images/biblio.png"));

        } catch (NullPointerException | ExceptionInInitializerError e) {
            System.err.println("Erro ó cargar o icono");
        }
    }

    private BookDAO libroDAO;
    private List<Book> libros;
    private int indice = 0;
    private final JLabel portada, id;
    private final JLabel title;
    private final JTextField nome, autor, anho, isbn;
    private final JCheckBox disponible;
    private final JButton actualizar, eliminar, anterior, siguiente;

    public BookView(Connection con, String titulo) {
        super(titulo);
        this.id = new JLabel("1");

        this.libroDAO = new BookDAO(con);
        this.libros = libroDAO.getAll();

        try {
            if (icon != null) {
                setIconImage(icon.getImage());
            }
        } catch (Exception e) {
            System.err.println("Non se pudo cargar o icono: " + e.getMessage());
        }

        setSize(500, 800);
        setLayout(new BorderLayout(1, 3));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        portada = new JLabel();
        portada.setHorizontalAlignment(SwingConstants.CENTER);
        portada.setPreferredSize(new Dimension(450, 600));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        panel.add(portada, gbc);

        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        title = new JLabel();
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Título: "), gbc);
        nome = new JTextField(20);
        gbc.gridx = 2;
        panel.add(nome, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(new JLabel("Autor: "), gbc);
        autor = new JTextField(20);
        gbc.gridx = 2;
        panel.add(autor, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(new JLabel("Año: "), gbc);
        anho = new JTextField(20);
        gbc.gridx = 2;
        panel.add(anho, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(new JLabel("ISBN: "), gbc);
        isbn = new JTextField(20);
        gbc.gridx = 2;
        panel.add(isbn, gbc);

        JPanel pButton = new JPanel();
        actualizar = new JButton("Actualizar");
        eliminar = new JButton("Eliminar");
        pButton.add(actualizar);
        pButton.add(eliminar);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(pButton, gbc);

        JPanel bottom = new JPanel();
        disponible = new JCheckBox("Disponible");
        anterior = new JButton("< Anterior");
        siguiente = new JButton("Siguiente >");
        bottom.add(disponible);
        bottom.add(anterior);
        bottom.add(siguiente);
        bottom.add(id);

        add(panel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        if (!libros.isEmpty()) {
            cargarLibro(libros.get(indice));
        }

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        actualizar.addActionListener((ActionEvent e) -> {
            actualizarLibro();
        });

        eliminar.addActionListener((ActionEvent e) -> {
            eliminarLibro();
        });

        anterior.addActionListener((ActionEvent e) -> {
            if (indice > 0) {
                indice--;
                cargarLibro(libros.get(indice));
            }
        });

        siguiente.addActionListener((ActionEvent e) -> {
            if (indice < libros.size() - 1) {
                indice++;
                cargarLibro(libros.get(indice));
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void sair() {
        int value = JOptionPane.showConfirmDialog(this, "Queres sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (value == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void cargarLibro(Book book) {
        title.setText(book.getTitulo());
        nome.setText(book.getTitulo());
        autor.setText(book.getAutor());
        anho.setText(String.valueOf(book.getAnho()));
        isbn.setText(book.getIsbn());
        disponible.setSelected(book.isDisponible());
        id.setText(String.valueOf((int) book.getIdBook()));
        
        if (book.getPortada() != null) {
            ImageIcon imaxe = new ImageIcon(book.getPortada());
            portada.setIcon(imaxe);
        } else {
            portada.setIcon(null);
        }
    }

    private void actualizarLibro() {
        Book book = libros.get(indice);
        book.setTitulo(nome.getText());
        book.setAutor(autor.getText());
        book.setAnho(Short.parseShort(anho.getText()));
        book.setIsbn(isbn.getText());
        book.setDisponible(disponible.isSelected());

        libroDAO.update(book);

        JOptionPane.showMessageDialog(this, "Actualizado");

    }

    private void eliminarLibro() {
        Book book = libros.get(indice);
        int value = JOptionPane.showConfirmDialog(this, "Eliminar libro?", "Eliminar libro.", JOptionPane.YES_NO_OPTION);
        if (value == JOptionPane.YES_OPTION) {
            libroDAO.delete(book);
            libros.remove(indice);

        }

        if (indice >= libros.size()) {
            indice = libros.size() - 1;

        }

        if (!libros.isEmpty()) {
            cargarLibro(libros.get(indice));
        } else {
            clean();
        }

    }

    private void clean() {
        nome.setText("");
        autor.setText("");
        anho.setText("");
        isbn.setText("");
        disponible.setSelected(false);
        portada.setIcon(null);
    }

}
