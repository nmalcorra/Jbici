package interfacesDAO;

import java.io.Serializable;

import model.Ubicacion;

public interface UbicacionDAO extends GenericDAO<Ubicacion>{
	@Override
	public Ubicacion actualizar(Ubicacion ubicacion);
	@Override
	public void borrar(Ubicacion ubicacion);
	@Override
	public Ubicacion borrar(Serializable id);
	@Override
	public boolean existe(Serializable id);
	@Override
	public Ubicacion persistir(Ubicacion ubicacion);
	@Override
	public Ubicacion recuperar(Serializable id);


}
