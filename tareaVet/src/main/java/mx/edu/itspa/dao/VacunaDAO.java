package mx.edu.itspa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itspa.dto.Vacuna;
import mx.edu.itspa.general.ConexionBD;
import mx.edu.itspa.general.DAO;
import mx.edu.itspa.general.DAOException;

public class VacunaDAO implements DAO<Vacuna, String> {
	private final String INSERT = "INSERT INTO Vacunas(idMascota, fecha, enfermedad, fechaproxima) VALUES (?,?,?,?) ";
    private final String UPDATE = "UPDATE Vacunas SET idMascota=?, fecha = ?, enfermedad = ?, fechaproxima=? WHERE idVacuna = ?";
    private final String DELETE = "DELETE FROM Vacunas WHERE idVacuna = ?";
    private final String GETALL = "SELECT * FROM Vacunas INNER JOIN Pacientes ON Vacunas.idMascota = Pacientes.idMascota";
    private final String GETONE = "SELECT * FROM Vacunas INNER JOIN Pacientes ON Vacunas.idMascota = Pacientes.idMascota "
    		+ "AND Vacunas.idVacuna=?";
 
    
	
	@Override
	public Object insertar(Vacuna obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getIdMascota());
            stmt.setString(2, obj.getFecha());
            stmt.setString(3, obj.getEnfermedad());
            stmt.setString(4, obj.getFechaProxima());       
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setIdVacuna(clave);                
            }
            return clave;
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
	public boolean modificar(Vacuna obj) throws DAOException {
		 Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        try {
	            conn = ConexionBD.obtenerConexion();
	            stmt = conn.prepareStatement(UPDATE);
	            stmt.setInt(1, obj.getIdMascota());
	            stmt.setString(2, obj.getFecha());
	            stmt.setString(3, obj.getEnfermedad());
	            stmt.setString(4, obj.getFechaProxima());
	            stmt.setInt(5, obj.getIdVacuna());
	            return stmt.executeUpdate() == 0;
	        }catch (SQLException ex) {
	            System.out.println("Error causado por: " + ex.getMessage());
	            return false;
	        } finally {
	            cerrarConnection(conn);
	            cerrarResultSet(rs);
	            cerrarStatement(stmt);
	        }
	}

	@Override
	public boolean eliminar(Vacuna obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, obj.getIdVacuna());
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
	public Vacuna obtenerModelo() {
		return new Vacuna();
	}


	@Override
	public List<Vacuna> obtenerTodos() throws DAOException {
		 Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        List<Vacuna> vacuna = new ArrayList<Vacuna>();
	        try {
	            conn = ConexionBD.obtenerConexion();
	            stmt = conn.prepareStatement(GETALL);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	                vacuna.add((Vacuna) convertir(rs, new Vacuna()));
	            }
	            return vacuna;
	        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
	            System.out.println("Error causado por: " + ex.getMessage());
	            ex.printStackTrace();
	            return null;
	        } finally {
	        	
	            cerrarConnection(conn);
	            cerrarResultSet(rs);
	            cerrarStatement(stmt);
	        }
	}

	@Override
	public List<Vacuna> obtenerTodos(String[] campos) throws DAOException {
		int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vacuna> vacuna = new ArrayList<Vacuna>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
                vacuna.add((Vacuna) convertir(rs, new Vacuna(), campos));
            }
            return vacuna;
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
	public Vacuna obtener(String idVacuna) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vacuna vacuna = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, idVacuna);
            rs = stmt.executeQuery();
            if (rs.next()) {
                vacuna = (Vacuna) convertir(rs, new Vacuna());
            } else {
                System.out.println("No se encontro la vacuna en la base de datos");
            }
            return vacuna;
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
        select = select.concat(" FROM Vacunas;");
        return select;
    }



}
