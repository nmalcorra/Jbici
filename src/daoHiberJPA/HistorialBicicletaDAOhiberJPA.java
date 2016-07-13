package daoHiberJPA;


import interfacesDAO.HistorialBicicletaDAO;
import model.HistorialBicicleta;

public class HistorialBicicletaDAOhiberJPA extends GenericDAOhiberJPA<HistorialBicicleta> implements HistorialBicicletaDAO{
	public HistorialBicicletaDAOhiberJPA() {
		super(HistorialBicicleta.class);
	}



}
