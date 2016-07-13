package interfacesDAO;

import java.io.Serializable;

import model.HistorialBicicleta;

public interface HistorialBicicletaDAO extends GenericDAO<HistorialBicicleta>{	
	@Override
	public HistorialBicicleta actualizar(HistorialBicicleta historial);
	@Override
	public void borrar(HistorialBicicleta historial);
	@Override
	public HistorialBicicleta borrar(Serializable id);
	@Override
	public boolean existe(Serializable id);
	@Override
	public HistorialBicicleta persistir(HistorialBicicleta historial);
	@Override
	public HistorialBicicleta recuperar(Serializable id);

}
