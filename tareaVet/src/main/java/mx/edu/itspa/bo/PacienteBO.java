package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.PacienteDAO;
import mx.edu.itspa.dto.Paciente;
import mx.edu.itspa.general.DAOException;


@ManagedBean(name="pacienteBO")
@SessionScoped
public class PacienteBO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private PacienteDAO pacienteDAO;

	public PacienteBO() {
		paciente = new Paciente();
		pacienteDAO = new PacienteDAO();
	}
	
	public List<Paciente> getPacientes(){
		try {
			return pacienteDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String idMascota) {
		try {
			paciente = pacienteDAO.obtener(idMascota);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "add";
	}
	
	public String save() {
		try {
			if(paciente.getIdMascota()==null ||paciente.getIdMascota()==0) {
				pacienteDAO.insertar(paciente);
			}else {
				pacienteDAO.modificar(paciente);
				}
			paciente = new Paciente();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add";
	}
	
	public String eliminar(String idMascota) {
		try {
			paciente = pacienteDAO.obtener(idMascota);
			pacienteDAO.eliminar(paciente);
			paciente = new Paciente();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "pacientes";
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente= paciente;
	}	

}
