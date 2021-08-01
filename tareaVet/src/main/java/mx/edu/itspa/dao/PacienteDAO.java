package mx.edu.itspa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itspa.dto.Paciente;
import mx.edu.itspa.dto.Vacuna;
import mx.edu.itspa.general.ConexionBD;
import mx.edu.itspa.general.DAO;
import mx.edu.itspa.general.DAOException;

public class PacienteDAO implements DAO<Paciente, String>{
	private final String INSERT = "INSERT INTO Pacientes(aliasMascota, especie, raza, colorPelo,"
			+ "fechaNacimiento, vacunaciones) VALUES (?,?,?,?,?,?) ";
    private final String UPDATE = "UPDATE Pacientes SET aliasMascota=?, "
    		+ "especie=?,raza=?, colorPelo=?, fechaNacimiento=?, vacunaciones=? WHERE idMascota = ?";
    private final String DELETE = "DELETE FROM Pacientes WHERE idMascota = ?";
    private final String GETALL = "SELECT * FROM Pacientes";
    private final String GETONE = "SELECT * FROM Pacientes WHERE idMascota=?";
	

	@Override
	public Integer insertar(Paciente obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave=0;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getAliasMascota());
            stmt.setString(2, obj.getEspecie());
            stmt.setString(3, obj.getRaza());
            stmt.setString(4, obj.getColorPelo());
            stmt.setString(5, obj.getFechaNacimiento());
            stmt.setString(6, obj.getVacunaciones());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setIdMascota(clave);                
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
	public boolean modificar(Paciente obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, obj.getAliasMascota());
            stmt.setString(2, obj.getEspecie());
            stmt.setString(3, obj.getRaza());
            stmt.setString(4, obj.getColorPelo());
            stmt.setString(5, obj.getFechaNacimiento());
            stmt.setString(6, obj.getVacunaciones());
            stmt.setInt(7, obj.getIdMascota());
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
	public Paciente obtenerModelo() {
		return new Paciente();
	}
	@Override
	public boolean eliminar(Paciente obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, obj.getIdMascota());
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
	public List<Paciente> obtenerTodos() throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> paciente = new ArrayList<Paciente>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                paciente.add((Paciente) convertir(rs, new Paciente()));
            }
            return paciente;
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
	public List<Paciente> obtenerTodos(String[] campos) throws DAOException {
		int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> paciente = new ArrayList<Paciente>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
                paciente.add((Paciente) convertir(rs, new Paciente(), campos));
            }
            return paciente;
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
	public Paciente obtener(String id) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Paciente paciente = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                paciente = (Paciente) convertir(rs, new Paciente());
            } else {
                System.out.println("No se encontro al paciente en la base de datos");
            }
            return paciente;
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
        select = select.concat(" FROM Pacientes;");
        return select;
    }
}
