package mx.edu.itspa.pw.webservice.services;

import java.util.List;

import javax.jws.WebService;

import mx.edu.itspa.pw.webservice.dao.AlumnoDAO;
import mx.edu.itspa.pw.webservice.general.DAOException;
import mx.edu.itspa.pw.webservice.dto.Alumno;

public class AlumnosServiceImpl implements AlumnosService {

	@Override
	public Alumno[] getAlumnos() {
		List <Alumno> alumnos = null;
		try {
			alumnos = new AlumnoDAO().obtenerTodos();
			} catch(DAOException e) {
				e.printStackTrace();
			}
		return alumnos.toArray(new Alumno[alumnos.size()]);
	}
	}
