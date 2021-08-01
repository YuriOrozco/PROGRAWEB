package mx.edu.itspa.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="user")
@SessionScoped

public class UserBean implements Serializable{
	
		private String nombre;
		private String contrasena;
		
		public String login() {
			String result="";
			
			if(nombre.length()>0 && contrasena.length()>0) {
				result = "/views/vacunas/vacunas";
			}else {
				result = "index";
			}
			return result;
		}
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getContrasena() {
			return contrasena;
		}
		public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
		}

}
