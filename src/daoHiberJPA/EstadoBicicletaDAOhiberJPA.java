package daoHiberJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import interfacesDAO.EstadoBicicletaDAO;

import model.EstadoBicicleta;

public class EstadoBicicletaDAOhiberJPA extends GenericDAOhiberJPA<EstadoBicicleta> implements EstadoBicicletaDAO {
	
	public EstadoBicicletaDAOhiberJPA(){
		super(EstadoBicicleta.class);
	}


	@Override
	public List<EstadoBicicleta> getAllEstadoBicicleta() {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<EstadoBicicleta> result = null;
		etx.begin();
			Query q = em.createQuery("FROM EstadoBicicleta");
			result = q.getResultList();
		etx.commit();
		return result;		

	}
	

}
