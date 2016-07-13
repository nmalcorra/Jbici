package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import model.EstadoEstacion;

public interface EstadoEstacionDAO extends GenericDAO<EstadoEstacion>{
	@Override
	public EstadoEstacion actualizar(EstadoEstacion estado);
	@Override
	public void borrar(EstadoEstacion estado);
	@Override
	public EstadoEstacion borrar(Serializable id);
	@Override
	public boolean existe(Serializable id);
	@Override
	public EstadoEstacion persistir(EstadoEstacion estado);
	@Override
	public EstadoEstacion recuperar(Serializable id);
	
	public List<EstadoEstacion> getAllEstadoEstacion();

}
