package mx.edu.itspa.pw.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itspa.pw.webservice.dto.Equipo;
import mx.edu.itspa.pw.webservice.general.ConexionBD;
import mx.edu.itspa.pw.webservice.general.DAO;
import mx.edu.itspa.pw.webservice.general.DAOException;

public class EquipoDAO implements DAO<Equipo, String>{
	private final String INSERT = "INSERT INTO Equipo(NombreEquipo, InstitucionProcedencia, id_torneo) VALUES (?,?,?) ";
    private final String UPDATE = "UPDATE Equipo SET NombreEquipo=?, InstitucionProcedencia=?, id_torneo WHERE id_equipo = ?";
    private final String DELETE = "DELETE FROM Equipo WHERE id_equipo = ?";
    private final String GETALL = "SELECT * FROM Equipo JOIN Torneo on Equipo.id_torneo=Torneo.id_torneo";
    private final String GETONE = "SELECT * FROM Equipo JOIN Torneo on Equipo.id_torneo=Torneo.id_torneo WHERE id_equipo=?";
	@Override
	public Equipo obtenerModelo() {
		return new Equipo();
	}

	@Override
	public Object insertar(Equipo obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave=0;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNombreEquipo());
            stmt.setString(2, obj.getInstitucionProcedencia());
            stmt.setInt(3, obj.getId_torneo());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setId_equipo(clave);                
            } return clave;
        } catch (SQLException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}

	@Override
	public boolean modificar(Equipo obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, obj.getNombreEquipo());
            stmt.setString(2, obj.getInstitucionProcedencia());
            stmt.setInt(3, obj.getId_torneo());
            stmt.setInt(4, obj.getId_equipo());
            return stmt.executeUpdate() == 0;
        } catch (SQLException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return false;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}

	@Override
	public boolean eliminar(Equipo obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, obj.getId_equipo());
            return stmt.executeUpdate() == 0;
        } catch (SQLException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return false;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}

	@Override
	public List<Equipo> obtenerTodos() throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Equipo> equipos = new ArrayList<Equipo>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
               equipos.add((Equipo) convertir(rs, new Equipo()));
            }
            return equipos;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        } finally {
        	//alumnos.stream().forEach((e)->{System.out.println(e.getId());});
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}

	@Override
	public List<Equipo> obtenerTodos(String[] campos) throws DAOException {
		int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Equipo> equipos = new ArrayList<Equipo>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
                equipos.add((Equipo) convertir(rs, new Equipo(), campos));
            }
            return equipos;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}

	@Override
	public Equipo obtener(String id_equipo) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       Equipo equipo = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, id_equipo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                equipo = (Equipo) convertir(rs, new Equipo());
            } else {
                System.out.println("No se encontro el alumno en la base de datos");
            }
            return equipo;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}
	 private String prepararSelect(String campos[], int numero_campos){
	        String select = "SELECT ";
	        for (int i = 0; i < numero_campos - 1; i++) {
	            select = select.concat(campos[i]) + ", ";
	        }
	        select = select.concat(campos[numero_campos - 1]);
	        select = select.concat(" FROM Equipo;");
	        return select;
	    }

}
