package APIRest;

import java.util.ArrayList;
import java.util.List;

import model.Estacion;
import daoHiberJPA.EstacionDAOhiberJPA;

public class EstacionService {

	EstacionDAOhiberJPA EstacionDao;
	
	public EstacionService() {
		EstacionDao = new EstacionDAOhiberJPA();
	}
	
	
	public List<Estacion> getEstacionesAsList() {
		return this.EstacionDao.getAllEstaciones();

	}

}
