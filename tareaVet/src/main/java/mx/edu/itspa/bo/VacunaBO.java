package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.PacienteDAO;
import mx.edu.itspa.dao.VacunaDAO;
import mx.edu.itspa.dto.Vacuna;
import mx.edu.itspa.general.DAOException;

@ManagedBean(name="vacunaBO")
@SessionScoped

public class VacunaBO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Vacuna vacuna;
	private VacunaDAO vacunaDAO;

	
	public VacunaBO() {
		vacunaDAO = new VacunaDAO();
		vacuna = new Vacuna();
	}
	
	public List<Vacuna> getVacunas(){
		try {
			return vacunaDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String idVacuna) {
		try {
			vacuna = vacunaDAO.obtener(idVacuna);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "addVacuna";
	}
	
	public String save() {
		try {
			if(vacuna.getIdVacuna()==null||vacuna.getIdVacuna()==0) {
				vacunaDAO.insertar(vacuna);
				
			}else {
				vacunaDAO.modificar(vacuna);
			}
			vacuna = new Vacuna();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addVacuna";
	}
	
	public String eliminar(String idVacuna) {
		try {
			vacuna = vacunaDAO.obtener(idVacuna);
			vacunaDAO.eliminar(vacuna);
			vacuna = new Vacuna();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "vacunas";
	}

	public Vacuna getVacuna() {
		return vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna= vacuna;
	}	
}
