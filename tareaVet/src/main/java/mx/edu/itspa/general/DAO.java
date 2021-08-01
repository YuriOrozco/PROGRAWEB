package mx.edu.itspa.general;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @param <T> Modelo de la interface DAO
 * @param <K> Tipo de Dato para el ID del modelo la interface DAO
 */
public interface DAO<T, K> {
	T obtenerModelo();

	/**
	 *
	 * @param obj Objeto de modelo para insertar en la base de datos
	 * @return
	 * @throws DAOException
	 */
	Object insertar(T obj) throws DAOException;

	/**
	 *
	 * @param obj Objeto de modelo para modificar en la base de datos
	 * @return
	 * @throws DAOException
	 */
	boolean modificar(T obj) throws DAOException;

	/**
	 *
	 * @param obj Objeto de modelo para eliminar en la base de datos
	 * @return
	 * @throws DAOException
	 */
	boolean eliminar(T obj) throws DAOException;

	/**
	 *
	 * @return Regresa una lista de todos los registros del modelo en la base de
	 *         datos
	 * @throws DAOException
	 */
	List<T> obtenerTodos() throws DAOException;

	/**
	 *
	 * @param campos Indicar los campos que se quieren recuperar del modelo en la
	 *               base de datos
	 * @return Regresa una lista de todos los registros del modelo en la base de
	 *         datos con los campos especificados
	 * @throws DAOException
	 */
	List<T> obtenerTodos(String campos[]) throws DAOException;

	/**
	 *
	 * @param id ID del modelo en la base de datos
	 * @return Regresa una lista de todos los registros del modelo en la base de
	 *         datos
	 * @throws DAOException
	 */
	T obtener(K id) throws DAOException;

	/**
	 *
	 * @param rs     Contiene todos los valores recuperados del modelo de la base de
	 *               datos
	 * @param obj    Objeto del modelo del que se llenara sus propiedades con el
	 *               resltado de la consulta (ResultSet)
	 * @param campos Contiene los campos del mdelo que se quieren recuperar en el
	 *               objeto del modelo
	 * @return Regresa el objeto del modelo con los valores recuperados de la base
	 *         de datos
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws DAOException
	 */
	default Object convertir(ResultSet rs, Object obj, String campos[])
			throws SQLException, IllegalArgumentException, IllegalAccessException, DAOException {
		if (obj == null || rs == null) {
			throw new DAOException("Error al convertir, se recibió objeto nulo o ResultSet nulo");
		}
		Class<? extends Object> c;/// Class c;
		c = obj.getClass();

		for (Field f : c.getDeclaredFields()) {
			for (String campo : campos) {
				if (campo.equalsIgnoreCase(f.getName())) {
					f.setAccessible(true);
					f.set(obj, rs.getObject(campo));
					break;
				}
			}
		}

		// Campos de superclase
		/*
		 * for(Field f : c.getGenericSuperclass().getClass().getFields()){ for(String
		 * campo : campos){ if(campo.equalsIgnoreCase(f.getName())){
		 * f.setAccessible(true); System.out.println(campo + ": " +
		 * rs.getObject(campo)); f.set(obj, rs.getObject(campo)); break; } } }
		 */

		return obj;
	}

	/**
	 *
	 * @param rs  Contiene todos los valores recuperados del modelo de la base de
	 *            datos
	 * @param obj Objeto del modelo del que se llenara sus propiedades con el
	 *            resltado de la consulta (ResultSet)
	 * @return Regresa el objeto del modelo con los valores recuperados de la base
	 *         de datos
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws DAOException
	 */
	default Object convertir(ResultSet rs, Object obj)
			throws SQLException, IllegalArgumentException, IllegalAccessException, DAOException {
		if (obj == null || rs == null) {
			throw new DAOException("Error al convertir, se recibió objeto nulo o ResultSet nulo");
		}
		Class<? extends Object> c;
		c = obj.getClass();
		for (Field f : c.getDeclaredFields()) {
			f.setAccessible(true);
			//System.out.println("campo del objeto: " + f.getName());
			f.set(obj, rs.getObject(f.getName()));
		}
		// Campos de superclase
		if(!c.getSuperclass().getSimpleName().equalsIgnoreCase("Object")) {
			for (Field f : c.getSuperclass().getDeclaredFields()) {			
				f.setAccessible(true);
				//System.out.println("campo del objeto: " + f.getName());
				f.set(obj, rs.getObject(f.getName()));
			}
		}		
		return obj;
	}

	/**
	 *
	 * @param obj Modelo del que se quiere recuperar su campos
	 * @return Regresa la lista de campos que tiene el modelo
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws DAOException
	 */
	default String[] obtenerCampos(Object obj) throws IllegalArgumentException, IllegalAccessException, DAOException {
		if (obj == null) {
			throw new DAOException("Error al convertir, se recibió objeto nulo");
		}
		String campos[];
		Class<? extends Object> c;
		c = obj.getClass();
		Field fields[] = c.getDeclaredFields();
		campos = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			campos[i] = fields[i].getName();
		}
		return campos;
	}
	
	default void cerrarConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error causado por: " + ex.getMessage());
            }
        }
    }
    
	default void cerrarResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error causado por: " + ex.getMessage());
            }
        }
    }

	default void cerrarStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error causado por: " + ex.getMessage());
                //Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
