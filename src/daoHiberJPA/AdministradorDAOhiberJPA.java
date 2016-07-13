package daoHiberJPA;


import interfacesDAO.AdministradorDAO;
import model.Administrador;

public class AdministradorDAOhiberJPA extends GenericDAOhiberJPA<Administrador> implements AdministradorDAO {

	public AdministradorDAOhiberJPA() {
		super(Administrador.class);
	}


	
	
}
