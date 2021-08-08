package mx.edu.itspa.pw.webservice.dto;

public class Equipo extends Torneo{
 private Integer id_equipo;
 private String NombreEquipo;
 private String InstitucionProcedencia;
 private Integer id_torneo;
 
public Integer getId_equipo() {
	return id_equipo;
}
public void setId_equipo(Integer id_equipo) {
	this.id_equipo = id_equipo;
}
public String getNombreEquipo() {
	return NombreEquipo;
}
public void setNombreEquipo(String nombreEquipo) {
	NombreEquipo = nombreEquipo;
}
public String getInstitucionProcedencia() {
	return InstitucionProcedencia;
}
public void setInstitucionProcedencia(String institucionProcedencia) {
	InstitucionProcedencia = institucionProcedencia;
}
public Integer getId_torneo() {
	return id_torneo;
}
public void setId_torneo(Integer id_torneo) {
	this.id_torneo = id_torneo;
}
 
 
}
