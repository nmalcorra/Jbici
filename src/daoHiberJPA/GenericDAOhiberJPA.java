package daoHiberJPA;
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;









import interfacesDAO.GenericDAO;

public class GenericDAOhiberJPA<T> implements GenericDAO<T> {

		protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jyaa");
		private Class<T> persistentClass;
		
		/**
		 * 
		 * Constructores
		 */
		//Constructor vacio
		public GenericDAOhiberJPA() {
			super();
		}
		
		//Constructor que recibe la clase persistente para poder retornar un tipo especifico.
		public GenericDAOhiberJPA(Class<T> persistentClass) {
			super();
			this.persistentClass = persistentClass;
		}

		/**
		 * Getter y setter
		 */
		private Class<T> getPersistentClass() {
			return persistentClass;
		}

		private void setPersistentEntity(Class<T> persistentClass) {
			this.persistentClass = persistentClass;
		}
		

		@Override
		public T actualizar(T entity) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
				em.merge(entity);
				em.flush();
			etx.commit();
			em.close();
			return entity;
		}

		@Override
		public void borrar(T entity) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
				em.remove(entity);
				em.flush();
			etx.commit();
			em.close();			
		}

		@Override
		public T borrar(Serializable id) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
				T entity = em.find(getPersistentClass(), id);
				em.remove(entity);
				em.flush();
			etx.commit();
			em.close();
			return entity;
		}

		@Override
		public boolean existe(Serializable id) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
				boolean flag = (em.find(persistentClass, id) != null); //Si la encontré true.
			etx.commit();
			em.close();
			return flag;
		}

		@Override
		public T persistir(T entity) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
				em.persist(entity);
				em.flush();
			etx.commit();
			em.close();
			return entity;

		}

		@Override
		public T recuperar(Serializable id) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
				T entity = em.find(getPersistentClass(), id); 
			etx.commit();
			em.close();
			return entity;
		}
}

