package mx.edu.itspa.pw.webservice.dto;

public class Persona {
	private Integer id = null;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String curp;
    private Integer asentamiento;
    private String domicilio;
    private String email;
    private String telefono_fijo;
    private String telefono_celular;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido_paterno
     */
    public String getApellido_paterno() {
        return apellido_paterno;
    }

    /**
     * @param apellido_paterno the apellido_paterno to set
     */
    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    /**
     * @return the apellido_materno
     */
    public String getApellido_materno() {
        return apellido_materno;
    }

    /**
     * @param apellido_materno the apellido_materno to set
     */
    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    /**
     * @return the curp
     */
    public String getCurp() {
        return curp;
    }

    /**
     * @param curp the curp to set
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * @return the asentamiento
     */
    public Integer getAsentamiento() {
        return asentamiento;
    }

    /**
     * @param asentamiento the asentamiento to set
     */
    public void setAsentamiento(Integer asentamiento) {
        this.asentamiento = asentamiento;
    }

    /**
     * @return the domicilio
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * @param domicilio the domicilio to set
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefono_fijo
     */
    public String getTelefono_fijo() {
        return telefono_fijo;
    }

    /**
     * @param telefono_fijo the telefono_fijo to set
     */
    public void setTelefono_fijo(String telefono_fijo) {
        this.telefono_fijo = telefono_fijo;
    }

    /**
     * @return the telefono_celular
     */
    public String getTelefono_celular() {
        return telefono_celular;
    }

    /**
     * @param telefono_celular the telefono_celular to set
     */
    public void setTelefono_celular(String telefono_celular) {
        this.telefono_celular = telefono_celular;
    }
}
