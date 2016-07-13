package interfacesDAO;

import java.util.List;

import model.Alquiler;

public interface AlquilerDAO extends GenericDAO<Alquiler>{
	
	public void borrarDenuncia(Long id);
	
	List<Alquiler> recuperarAlquileresPorCliente(Long cliente_id);
	
	List<Alquiler> recuperarAlquileresActivosPorCliente(Long cliente_id);
	
	List<Alquiler> recuperarAlquileresPorBicicleta(Long bicicleta_id);


}
