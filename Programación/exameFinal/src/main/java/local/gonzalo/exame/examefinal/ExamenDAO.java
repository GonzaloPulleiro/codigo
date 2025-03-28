package local.gonzalo.exame.examefinal;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dammdprog1
 */
public class ExamenDAO {

    public Connection conexion;

    public ExamenDAO(Connection conexion) {
        this.conexion = new ConnectionManager().getInstance().getConnection();
    }

    public List<Integer> getExamenIds() {
        List<Integer> lista = new ArrayList<>();

        String consulta = "SELECT idExamen FROM Examen";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(rs.getInt(1));

            }

        } catch (SQLException ex) {
            System.err.println("Error en getExamenIds(): " + ex.getMessage());
        }
        return lista;
    }

//    public Examen getExamenById(int idExamen) {
//        Examen exame = null;
//        String consulta = "SELECT idExamen, descripcion, fecha FROM Examen WHERE idExamen = ?";
//
//        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
//            ps.setInt(1, idExamen);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){

                //            LocalDateTime fecha = LocalDateTime.parse("fecha");
                //            exame = new Examen(rs.getInt("idExamen"),
                //                    rs.getString("descripcion"),
                ////                    rs.getString(fecha));
//            }
//
//        } catch (SQLException ex) {
//            System.err.println("Error en getExamenById(): " + ex.getMessage());
//        }
//        return exame;
//    }

//    public void savePregunta(int idExamen, Pregunta pregunta) {
//        String consulta = "INSERT INTO Pregunta (idExamen, enunciado, puntos) VALUES(?,?,?)";
//        String consulta2 = "INSERT INTO Opcion (idPregunta, enunciado, correcta) VALUES(?,?,?)";
//
//        try (PreparedStatement ps = conexion.prepareStatement(consulta); PreparedStatement ps2 = conexion.prepareStatement(consulta2, Statement.getGeneratedKeys())) {
//            try {
//                ps.setInt(1, getExamenById(idExamen).getIdExamen());
//                ps.setString(2, getExamenById(idExamen).getEnunciado());
//                ps.setDouble(3, getExamenById(idExamen).getPuntos());
//
//                int value = ps.executeUpdate();
//
//                ps2.setInt(1, pregunta.getIdPregunta());
//                ps2.setString(2, pregunta.getEnunciado());
//
//                int value2 = ps2.executeUpdate();
//
//                if (value2 > 0) {
//                    ps2.setInt(idExamen, ps2.getGeneratedKeys());
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(ExamenDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ExamenDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    public void savePreguntasToFile(Examen ex, File archivo) throws IOException {
        
        ObjectOutputStream op = new ObjectOutputStream() {
        };
    }

    public void getPreguntasFromFile(File archivo) {
        
    }
}
