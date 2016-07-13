package aspects;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import daoHiberJPA.GenericDAOhiberJPA;

/**
 * 
 * @author Pravisani Santiago & Malcorra Nicolas
 * La siguiente clase se encargar� de generar un Log en la DB hereda los comportamientos de GenericDAO.
 *
 */
public class LoggerDAOhiberJPA extends GenericDAOhiberJPA<Logger> {
	
	/**
	 * Retorna una lista de todos los Logs de la DB.
	 * @return List<Logger> lista_logs
	 */
	public List<Logger> getLogs() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<Logger> result = null;
		etx.begin();
			Query q = em.createQuery("FROM Logger");
			result =  q.getResultList();
		etx.commit();
		
		return result;
	}
	
	/**
	 * Retorna una lista de los logs de determinada operacion en una entidad especifica.
	 * @return List<Logger> lista_logs
	 */
	public List<Logger> getLogsFromOperationAndEntity(String operacion,String entidad) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<Logger> result = null;
		etx.begin();
			Query q = em.createQuery("FROM Logger as l WHERE l.operation =:operation and l.class_name =:class");
			q.setParameter("operation", operacion);
			q.setParameter("class", entidad);
			result =  q.getResultList();
		etx.commit();
		
		return result;
	}

}
