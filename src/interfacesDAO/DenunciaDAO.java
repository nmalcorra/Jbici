package interfacesDAO;

import java.io.Serializable;

import model.Alquiler;
import model.Denuncia;

public interface DenunciaDAO extends GenericDAO<Denuncia>{
	@Override
	public Denuncia actualizar(Denuncia denuncia);
	@Override
	public void borrar(Denuncia denuncia);
	@Override
	public Denuncia borrar(Serializable id);
	@Override
	public boolean existe(Serializable id);
	@Override
	public Denuncia persistir(Denuncia denuncia);
	@Override
	public Denuncia recuperar(Serializable id);
	
}
