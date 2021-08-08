package mx.edu.itspa.pw.webservice.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mx.edu.itspa.pw.webservice.dto.Persona;
import mx.edu.itspa.pw.webservice.general.ConexionBD;
import mx.edu.itspa.pw.webservice.general.DAO;
import mx.edu.itspa.pw.webservice.general.DAOException;

public class PersonaDAO implements DAO<Persona, String> {
	private final String INSERT = "INSERT INTO Persona(nombre, apellido_paterno, apellido_materno, "
			+ "curp, asentamiento, domicilio, email, "
			+ "telefono_fijo, telefono_celular) VALUES (?,?,?,?,?,?,?,?,?) ";
    private final String UPDATE = "UPDATE Persona SET nombre=?, apellido_paterno=?, "
    		+ "apellido_materno=?,curp=?, asentamiento=?, domicilio=?, email=?,telefono_fijo=?, "
    		+ "telefono_celular=? WHERE id = ?";
    private final String DELETE = "DELETE FROM Persona WHERE id = ?";
    private final String GETALL = "SELECT * FROM Persona";
    private final String GETONE = "SELECT * FROM Persona WHERE id=?";
    
	@Override
	public Persona obtenerModelo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer insertar(Persona obj) throws DAOException {
		// TODO Auto-generated method stub
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave=0;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getApellido_paterno());
            stmt.setString(3, obj.getApellido_materno());
            stmt.setString(4, obj.getCurp());
            stmt.setInt(5, obj.getAsentamiento());
            stmt.setString(6, obj.getDomicilio());
            stmt.setString(7, obj.getEmail());
            stmt.setString(8, obj.getTelefono_fijo());
            stmt.setString(9, obj.getTelefono_celular());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setId(clave);                
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
	public boolean modificar(Persona obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getApellido_paterno());
            stmt.setString(3, obj.getApellido_paterno());
            stmt.setString(4, obj.getCurp());
            stmt.setInt(5, obj.getAsentamiento());
            stmt.setString(6, obj.getDomicilio());
            stmt.setString(7, obj.getEmail());
            stmt.setString(8, obj.getTelefono_fijo());
            stmt.setString(9, obj.getTelefono_celular());
            stmt.setInt(10, obj.getId());
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
	public boolean eliminar(Persona obj) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Persona> obtenerTodos() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Persona> obtenerTodos(String[] campos) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Persona obtener(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
