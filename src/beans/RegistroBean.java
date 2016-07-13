package beans;
import interfacesDAO.ClienteDAO;
import interfacesDAO.FactoryDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Cliente;

@ManagedBean(name="registroBean")
@RequestScoped
public class RegistroBean {

	private FactoryDAO factory;
	public String nombre;
	public String apellido;
	public Integer dni;
	public String domicilio;
	public char sexo;
	public String fechan;
	public String email;
	public String password;
	public String message;
	
	public RegistroBean(){
		super();
		this.factory = new FactoryDAO();
	}
	
    public String registrar(){    	
    	ClienteDAO clientedao = factory.getClienteDAO();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/mm/yy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(this.fechan);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        boolean existe = clientedao.existeConEmail(this.email);
        
        if(! existe){
        //	mailContrasena env = new mailContrasena();
        //	env.enviarMail();
            //Si no existe
            this.password="1234";
            Cliente user = new Cliente(nombre, apellido, dni, domicilio,sexo, fecha,
        			email,password);
        	clientedao.persistir(user);
        	LoginBean bean = new LoginBean(this.email,this.password,
        			"Su clave por defecto es: 1234, para cambiarla acceda a Modificar datos");
    
        	return bean.login();
        }
        else{
    		message ="<div class='alert alert-danger'>Ya existe un usuario con ese ese email</div>";
    		return "registro";
        }

    }
    

	public FactoryDAO getFactory() {
		return factory;
	}

	public void setFactory(FactoryDAO factory) {
		this.factory = factory;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechan() {
		return fechan;
	}

	public void setFechan(String fechan) {
		this.fechan = fechan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
    
	
}