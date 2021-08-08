package mx.edu.itspa.pw.webservice.services;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import mx.edu.itspa.pw.webservice.dto.Equipo;


@WebService
public interface EquiposService {
	 @WebMethod(operationName="getEquipos")
	 @WebResult(name="ResultadoOperacion") Equipo [] getEquipos();
}
