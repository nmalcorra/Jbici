package interfacesDAO;
import java.io.Serializable;

import model.Administrador;

public interface AdministradorDAO extends GenericDAO<Administrador>{
	@Override
	public Administrador actualizar(Administrador admin);
	@Override
	public void borrar(Administrador admin);
	@Override
	public Administrador borrar(Serializable id);
	@Override
	public boolean existe(Serializable id);
	@Override
	public Administrador persistir(Administrador admin);
	@Override
	public Administrador recuperar(Serializable id);

}
