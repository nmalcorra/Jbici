package beans;
import interfacesDAO.ClienteDAO;
import interfacesDAO.FactoryDAO;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Cliente;
import model.Usuario;

@ManagedBean(name="adminBean")
@SessionScoped
public class AdministradorUsuariosBean {
	
	private FactoryDAO factory = new FactoryDAO();
	
	
	private ArrayList<Usuario> users = getAllUsers(); 


	public AdministradorUsuariosBean(){

	}
	
    public ArrayList<Usuario> getAllUsers() {
        ClienteDAO clientedao = factory.getClienteDAO();
        this.users = (ArrayList<Usuario>) clientedao.getAllClients();
        return users;
    }

    public String habilitar(Long id){
    	ClienteDAO userdao = factory.getClienteDAO();
    	Cliente cliente = userdao.recuperar(id);
    	cliente.setEstado(true);
    	userdao.actualizar(cliente);
    	this.users=getAllUsers();
    	return "administrar_usuarios";
    }
    public String deshabilitar(Long id){
    	ClienteDAO userdao = factory.getClienteDAO();
    	Cliente cliente = userdao.recuperar(id);
    	cliente.setEstado(false);
    	userdao.actualizar(cliente);
    	this.users=getAllUsers();
    	return "administrar_usuarios";
    } 
    

    
	public ArrayList<Usuario> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Usuario> users) {
		this.users = users;
	}
	
}
