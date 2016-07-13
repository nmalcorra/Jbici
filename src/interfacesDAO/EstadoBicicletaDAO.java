package interfacesDAO;


import java.util.List;

import model.EstadoBicicleta;

public interface EstadoBicicletaDAO extends GenericDAO<EstadoBicicleta>{

	List<EstadoBicicleta> getAllEstadoBicicleta();

	

}
