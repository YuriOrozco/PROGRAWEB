package mx.edu.itspa.pw.webservice.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itspa.pw.webservice.dto.Alumno;
import mx.edu.itspa.pw.webservice.general.*;


public class AlumnoDAO implements DAO<Alumno, String> {
	private final String INSERT = "INSERT INTO Alumno(matricula, carrera, semestre, persona_id) VALUES (?,?,?,?) ";
    private final String UPDATE = "UPDATE Alumno SET carrera = ?, semestre = ? WHERE matricula = ?";
    private final String DELETE = "DELETE FROM Alumno WHERE matricula = ?";
    //private final String GETALL = "SELECT * FROM Alumno";
    private final String GETALL = "SELECT * FROM Alumno INNER JOIN Persona ON Alumno.persona_id = Persona.id";
    private final String GETONE = "SELECT * FROM Alumno INNER JOIN Persona ON Alumno.persona_id = Persona.id "
    		+ "AND Alumno.matricula=?";
    
    private PersonaDAO personaDAO;
    
    public AlumnoDAO() {
    	personaDAO = new PersonaDAO();
    }

    public String insertar(Alumno obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String clave = null;
        try {
            conn = ConexionBD.obtenerConexion();
            if(personaDAO.insertar(obj)<=0) {
            	return "0";
            }
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getMatricula());
            stmt.setInt(2, obj.getCarrera());
            stmt.setInt(3, obj.getSemestre());
            stmt.setInt(4, obj.getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                clave = rs.getString(1);
                obj.setMatricula(clave);                
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

    public boolean modificar(Alumno obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setInt(1, obj.getCarrera());
            stmt.setInt(2, obj.getSemestre());
            stmt.setString(3, obj.getMatricula());
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

    public boolean eliminar(Alumno obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, obj.getMatricula());
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
    
    public List<Alumno> obtenerTodos() throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Alumno> alumnos = new ArrayList<Alumno>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                alumnos.add((Alumno) convertir(rs, new Alumno()));
            }
            return alumnos;
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

    public List<Alumno> obtenerTodos(String campos[]) throws DAOException {
        int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Alumno> alumnos = new ArrayList<Alumno>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
                alumnos.add((Alumno) convertir(rs, new Alumno(), campos));
            }
            return alumnos;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public Alumno obtener(String matricula) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Alumno alumno = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, matricula);
            rs = stmt.executeQuery();
            if (rs.next()) {
                alumno = (Alumno) convertir(rs, new Alumno());
            } else {
                System.out.println("No se encontro el alumno en la base de datos");
            }
            return alumno;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public Alumno obtenerModelo() {
        return new Alumno();
    }
    
    private String prepararSelect(String campos[], int numero_campos){
        String select = "SELECT ";
        for (int i = 0; i < numero_campos - 1; i++) {
            select = select.concat(campos[i]) + ", ";
        }
        select = select.concat(campos[numero_campos - 1]);
        select = select.concat(" FROM Alumno;");
        return select;
    }
}
