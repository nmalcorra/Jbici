package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario autenticacion(String email,String password);

	public List<Usuario> getAllUsers();
	
	public boolean verificarMail(String email,Long Id);
	
	
}
