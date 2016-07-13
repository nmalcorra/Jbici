package daoHiberJPA;



import interfacesDAO.UbicacionDAO;
import model.Ubicacion;

public class UbicacionDAOhiberJPA extends GenericDAOhiberJPA<Ubicacion> implements UbicacionDAO{
	public UbicacionDAOhiberJPA() {
		super(Ubicacion.class);
	}


	
	
	
	
}
