package daoHiberJPA;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import interfacesDAO.ClienteDAO;
import model.Cliente;
import model.Usuario;

public class ClienteDAOhiberJPA extends GenericDAOhiberJPA<Cliente> implements ClienteDAO{
	public ClienteDAOhiberJPA() {
		super(Cliente.class);
	}


	@Override
	public List<Usuario> getAllClients() {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<Usuario> result = null;
		etx.begin();
			Query q = em.createQuery("FROM Cliente");
			result = q.getResultList();
		etx.commit();
		return  result;
	}

	@Override
	public boolean existeConEmail(String email) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<Cliente> result = null;
		etx.begin();
			Query q = em.createQuery("FROM Usuario as u WHERE u.email= :email");
			q.setParameter("email", email);
			result = q.getResultList();
			etx.commit();
			if(result.isEmpty()){
				return false;
			}
			
			return true;
			
	}


	
}
