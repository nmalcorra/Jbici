package daoHiberJPA;

import java.io.Serializable;

import interfacesDAO.DenunciaDAO;
import model.Denuncia;

public class DenunciaDAOhiberJPA extends GenericDAOhiberJPA<Denuncia> implements DenunciaDAO{
	public DenunciaDAOhiberJPA() {
		super(Denuncia.class);
	}

	@Override
	public Denuncia actualizar(Denuncia entity) {
		// TODO Auto-generated method stub
		return super.actualizar(entity);
	}

	@Override
	public void borrar(Denuncia entity) {
		// TODO Auto-generated method stub
		super.borrar(entity);
	}

	@Override
	public Denuncia borrar(Serializable id) {
		// TODO Auto-generated method stub
		return super.borrar(id);
	}

	@Override
	public boolean existe(Serializable id) {
		// TODO Auto-generated method stub
		return super.existe(id);
	}

	@Override
	public Denuncia persistir(Denuncia entity) {
		// TODO Auto-generated method stub
		return super.persistir(entity);
	}

	@Override
	public Denuncia recuperar(Serializable id) {
		// TODO Auto-generated method stub
		return super.recuperar(id);
	}
	
	
	
}