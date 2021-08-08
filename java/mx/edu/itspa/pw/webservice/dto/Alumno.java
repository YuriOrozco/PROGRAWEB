package mx.edu.itspa.pw.webservice.dto;

public class Alumno extends Persona{
	private String matricula;
    private Integer carrera;
    private Integer semestre;
    
    public Alumno(){}
    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the carrera
     */
    public Integer getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(Integer carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the semestre
     */
    public Integer getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }
}
