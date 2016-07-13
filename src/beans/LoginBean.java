package beans;
import interfacesDAO.FactoryDAO;
import interfacesDAO.UsuarioDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Usuario;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {

	public String email;
	public String password;
	public String message;
	private FactoryDAO factory = new FactoryDAO();
    public String logout;
	
	public LoginBean(){
		
	}
	
	public LoginBean(String email,String password,String mensaje){
		this.email = email;
		this.password = password;
		this.message=mensaje;
   	}
	
    public String login(){

    	
    	UsuarioDAO userdao = factory.getUsuarioDAO();
    	Usuario user = userdao.autenticacion(email, password);
    	if(user != null) {
    		if(user instanceof model.Cliente && ((model.Cliente) user).getEstado()==false){
        		message ="<div class='alert alert-danger'>Usuario inhabilitado, contactese con Administrador</div>";
        		return "login";
    		}
            //Http session
    		HttpSession session = (HttpSession)
    		          FacesContext.
    		          getCurrentInstance().
    		          getExternalContext().
    		          getSession(true);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("userid", user.getIdUsuario());
            
            if(user instanceof model.Cliente){
            	session.setAttribute("role", "client");
        		return "/user/home"+"?faces-redirect=true";
    		}
    		else{
    			session.setAttribute("role", "admin");
        		return "admin/admin"+"?faces-redirect=true";
    		}

    	} else {
    		message ="<div class='alert alert-danger'>Nombre de usuario o Contraseña invalidos</div>";
    		return "login";
    	}
    }
    
    public String logout(){
    	HttpSession session = (HttpSession)
		          FacesContext.
		          getCurrentInstance().
		          getExternalContext().
		          getSession(false);
        session.invalidate();
        
        return "/login.xhtml"+"?faces-redirect=true";
    	
    }
    
    
    
    public String getMessage() {
	return message;
    }
    public void setMessage(String message) {
	this.message = message;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
