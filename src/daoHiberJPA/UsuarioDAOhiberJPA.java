package daoHiberJPA;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;



import interfacesDAO.UsuarioDAO;

import model.Usuario;

public class UsuarioDAOhiberJPA extends GenericDAOhiberJPA<Usuario> implements UsuarioDAO{
	
	public UsuarioDAOhiberJPA() {
		super(Usuario.class);
	}
	
	public List<Usuario> getAllUsers() {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<Usuario> result = null;
		etx.begin();
			Query q = em.createQuery("FROM Usuario");
			result = q.getResultList();
		etx.commit();
		
		return result;
	}


	public boolean verificarMail(String email, Long Id) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<Usuario> result = null;
		etx.begin();
			Query q = em.createQuery("FROM Usuario u WHERE u.email = :email and u.id != :id ");
			q.setParameter("email", email);
			q.setParameter("id", Id);
			result = q.getResultList();
		etx.commit();
		if (result.isEmpty()){
			return false;
		}
		return true; //Habia un usuario con ese mail y no puedes repetirlo.
	}
	
	public Usuario autenticacion(String email,String password){
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		List<Usuario> result = null;
		etx.begin();
			Query q = em.createQuery("FROM Usuario as u WHERE u.email = :email and u.password = :password");
			q.setParameter("email", email);
			q.setParameter("password", password);
			result = q.getResultList();
			etx.commit();
			if (result.isEmpty()){
				return null;
			}
			else{
				return result.get(0);
			}
			
	}
	
}
