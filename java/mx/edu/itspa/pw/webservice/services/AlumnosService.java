package mx.edu.itspa.pw.webservice.services;
import mx.edu.itspa.pw.webservice.dto.Alumno;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.WebMethod;
@WebService
public interface AlumnosService {
 @WebMethod(operationName="getAlumnos")
 @WebResult(name="ResultadoOperacion") Alumno [] getAlumnos();
}
