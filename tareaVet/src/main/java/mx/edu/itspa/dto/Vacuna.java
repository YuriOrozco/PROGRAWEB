package mx.edu.itspa.dto;

public class Vacuna extends Paciente{
	private Integer idVacuna=null;
	private Integer idMascota;
	private String fecha;
	private String enfermedad;
	private String fechaproxima;
	
	public Integer getIdVacuna() {
		return idVacuna;
	}
	public void setIdVacuna(Integer idVacuna) {
		this.idVacuna = idVacuna;
	}
	public Integer getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(Integer idMascota) {
		this.idMascota = idMascota;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEnfermedad() {
		return enfermedad;
	}
	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}
	public String getFechaProxima() {
		return fechaproxima;
	}
	public void setFechaProxima(String fechaProxima) {
		this.fechaproxima = fechaProxima;
	}
	
}
