package daoHiberJPA;

import interfacesDAO.EstadoEstacionDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.EstadoEstacion;

public class EstadoEstacionDAOhiberJPA extends GenericDAOhiberJPA<EstadoEstacion> implements EstadoEstacionDAO{
	public EstadoEstacionDAOhiberJPA() {
		super(EstadoEstacion.class);
	}
	
	@Override
	public List<EstadoEstacion> getAllEstadoEstacion(){
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<EstadoEstacion> result = null;
		etx.begin();
			Query q = em.createQuery("FROM EstadoEstacion");
			result = q.getResultList();
		etx.commit();
		return result;
	}
	
	
}
