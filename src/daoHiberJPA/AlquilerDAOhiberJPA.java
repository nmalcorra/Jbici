package daoHiberJPA;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import interfacesDAO.AlquilerDAO;
import model.Alquiler;


public class AlquilerDAOhiberJPA extends GenericDAOhiberJPA<Alquiler> implements AlquilerDAO {
	
	public AlquilerDAOhiberJPA(){
		super(Alquiler.class);
	}

	public void borrarDenuncia(Long id) {
		EntityManager em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
			Alquiler alquiler = em.find(Alquiler.class, id);
			alquiler.quitarDenuncia();
			em.merge(alquiler);
			em.flush();
		etx.commit();
		em.close();
	}
	

	public List<Alquiler> recuperarAlquileresActivosPorCliente(Long cliente_id) {
		EntityManager em = super.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
			Query q = em.createQuery("FROM Alquiler as a where a.cliente.id = :id_cli and a.fecha_devuelve = NULL");
			q.setParameter("id_cli", cliente_id);
			List<Alquiler> result = q.getResultList();
		etx.commit();
		em.close();
		return result;
	}
	

	public List<Alquiler> recuperarAlquileresPorCliente(Long cliente_id) {
		EntityManager em = super.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
			Query q = em.createQuery("FROM Alquiler as a where a.cliente.id = :id_cli and a.fecha_devuelve != NULL");
			q.setParameter("id_cli", cliente_id);
			List<Alquiler> result = q.getResultList();
		etx.commit();
		em.close();
		return result;
	}
	

	public List<Alquiler> recuperarAlquileresPorBicicleta(Long bicicleta_id) {
		EntityManager em = super.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
			Query q = em.createQuery("FROM Alquiler as a where a.bicicleta.idBicicleta = :id_bici");
			q.setParameter("id_bici", bicicleta_id);
			List<Alquiler> result = q.getResultList();
		etx.commit();
		em.close();
		return result;
	}


	
	
}
