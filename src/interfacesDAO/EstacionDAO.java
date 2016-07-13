package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import model.Bicicleta;
import model.Estacion;


public interface EstacionDAO extends GenericDAO<Estacion> {
	@Override
	public Estacion actualizar(Estacion estacion);
	@Override
	public void borrar(Estacion estacion);
	@Override
	public Estacion borrar(Serializable id);
	@Override
	public boolean existe(Serializable id);
	@Override
	public Estacion persistir(Estacion estacion);
	@Override
	public Estacion recuperar(Serializable id);

	public List<Bicicleta> recuperarBicicletas(Long estacion_id);
	
	public boolean existeConNombre(String nombre);
	
	public List<Estacion> getAllEstaciones();

	public List<Estacion> getAllEstacionesActivas();
	
	public List<Bicicleta> recuperarBicicletasDisponibles(Long estacion_id);
	
	public boolean existeNombreId(String nombre, Serializable id);

}
