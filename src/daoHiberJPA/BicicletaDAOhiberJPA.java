package daoHiberJPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import interfacesDAO.BicicletaDAO;
import model.Alquiler;
import model.Bicicleta;
import model.Estacion;
import model.HistorialBicicleta;

public class BicicletaDAOhiberJPA extends GenericDAOhiberJPA<Bicicleta> implements BicicletaDAO {
	
	public BicicletaDAOhiberJPA() {
		super(Bicicleta.class);
	}



	@Override
	public Bicicleta recuperarconHistorial(Long id_bici) {
		EntityManager em = super.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
			Bicicleta entity = em.find(Bicicleta.class, id_bici);
			if(entity!= null){
				entity.getHistorial().size();
			}
			etx.commit();
		em.close();
		
		return entity;
	}



	@Override
	public List<Bicicleta> getAllBicicletas() {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<Bicicleta> result = null;
		etx.begin();
			Query q = em.createQuery("FROM Bicicleta");
			result = q.getResultList();
		etx.commit();
		return result;
	}
	
	
}
