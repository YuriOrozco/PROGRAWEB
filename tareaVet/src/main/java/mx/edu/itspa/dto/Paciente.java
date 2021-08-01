package mx.edu.itspa.dto;

public class Paciente {
	private Integer idMascota = null;
	private String aliasMascota;
	private String especie;
	private String raza;
	private String colorPelo;
	private String fechaNacimiento;
	private String vacunaciones;
	
	public Integer getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(Integer idMascota) {
		this.idMascota = idMascota;
	}
	public String getAliasMascota() {
		return aliasMascota;
	}
	public void setAliasMascota(String aliasMascota) {
		this.aliasMascota = aliasMascota;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getColorPelo() {
		return colorPelo;
	}
	public void setColorPelo(String colorPelo) {
		this.colorPelo = colorPelo;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getVacunaciones() {
		return vacunaciones;
	}
	public void setVacunaciones(String vacunaciones) {
		this.vacunaciones = vacunaciones;
	}
	
	
}
