package mx.edu.itspa.pw.webservice.services;

import java.util.List;

import mx.edu.itspa.pw.webservice.dao.EquipoDAO;
import mx.edu.itspa.pw.webservice.dto.Equipo;
import mx.edu.itspa.pw.webservice.general.DAOException;

public class EquiposServiceImpl implements EquiposService{

	@Override
	public Equipo[] getEquipos() {
		List <Equipo> equipos = null;
		try {
			equipos = new EquipoDAO().obtenerTodos();
			} catch(DAOException e) {
				e.printStackTrace();
			}
		return equipos.toArray(new Equipo[equipos.size()]);
	}
	}


