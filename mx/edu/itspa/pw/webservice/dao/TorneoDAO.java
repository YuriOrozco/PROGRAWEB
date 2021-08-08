package mx.edu.itspa.pw.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import mx.edu.itspa.pw.webservice.dto.Torneo;
import mx.edu.itspa.pw.webservice.general.ConexionBD;
import mx.edu.itspa.pw.webservice.general.DAO;
import mx.edu.itspa.pw.webservice.general.DAOException;

public class TorneoDAO implements DAO<Torneo, String> {
	private final String INSERT = "INSERT INTO Torneo(NombreTorneo, FechaTorneo) VALUES (?,?) ";
    private final String UPDATE = "UPDATE Torneo SET NombreTorneo=?, FechaTorneo=? WHERE id_torneo = ?";
    private final String DELETE = "DELETE FROM Torneo WHERE id_torneo = ?";
    private final String GETALL = "SELECT * FROM Torneo";
    private final String GETONE = "SELECT * FROM Torneo WHERE id_torneo=?";
	@Override
	public Torneo obtenerModelo() {

		return new Torneo();
	}

	@Override
	public Object insertar(Torneo obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave=0;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNombreTorneo());
            stmt.setDate(2, obj.getFechaTorneo());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setId_torneo(clave);                
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
	public boolean modificar(Torneo obj) throws DAOException {
		 Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        try {
	            conn = ConexionBD.obtenerConexion();
	            stmt = conn.prepareStatement(UPDATE);
	            stmt.setString(1, obj.getNombreTorneo());
	            stmt.setDate(2, obj.getFechaTorneo());
	            stmt.setInt(3, obj.getId_torneo());
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
	public boolean eliminar(Torneo obj) throws DAOException {
		 Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        try {
	            conn = ConexionBD.obtenerConexion();
	            stmt = conn.prepareStatement(DELETE);
	            stmt.setInt(1, obj.getId_torneo());
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
	public List<Torneo> obtenerTodos() throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Torneo> torneos = new ArrayList<Torneo>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                torneos.add((Torneo) convertir(rs, new Torneo()));
            }
            return torneos;
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
	public List<Torneo> obtenerTodos(String[] campos) throws DAOException {
		int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Torneo> torneos = new ArrayList<Torneo>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
                torneos.add((Torneo) convertir(rs, new Torneo(), campos));
            }
            return torneos;
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
	public Torneo obtener(String id_torneo) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Torneo torneo = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, id_torneo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                torneo = (Torneo) convertir(rs, new Torneo());
            } else {
                System.out.println("No se encontro el alumno en la base de datos");
            }
            return torneo;
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
	        select = select.concat(" FROM Torneo;");
	        return select;
	    }
}
