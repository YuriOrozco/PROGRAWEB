package mx.edu.itspa.pw.webservice.dto;

import java.sql.Date;

public class Torneo {
private Integer id_torneo;
private String NombreTorneo;
private Date FechaTorneo;

public Integer getId_torneo() {
	return id_torneo;
}
public void setId_torneo(Integer id_torneo) {
	this.id_torneo = id_torneo;
}
public String getNombreTorneo() {
	return NombreTorneo;
}
public void setNombreTorneo(String nombreTorneo) {
	NombreTorneo = nombreTorneo;
}
public Date getFechaTorneo() {
	return FechaTorneo;
}
public void setFechaTorneo(Date fechaTorneo) {
	FechaTorneo = fechaTorneo;
}


}
