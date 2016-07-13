package interfacesDAO;

import daoHiberJPA.*;


public class FactoryDAO {
	/**
	 * El patron factory se encargara de devolver las clases concretas.
	 * 
	 */
	
	public AdministradorDAO getAdministradorDAO(){
		return new AdministradorDAOhiberJPA();
	}
	
	public AlquilerDAO getAlquilerDAO(){
		return new AlquilerDAOhiberJPA();
	}
	
	public BicicletaDAO getBicicletaDAO(){
		return new BicicletaDAOhiberJPA();
	}

	public EstadoBicicletaDAO getEstadoBicicletaDAO() {
		return new EstadoBicicletaDAOhiberJPA();
	}
	
	public ClienteDAO getClienteDAO(){
		return new ClienteDAOhiberJPA();
	}

	public DenunciaDAO getDenunciaDAO() {
		return new DenunciaDAOhiberJPA();
	}

	public EstacionDAO getEstacionDAO() {
		return new EstacionDAOhiberJPA();
	}

	public UbicacionDAO getUbicacionDAO() {
		return new UbicacionDAOhiberJPA();
	}

	public EstadoEstacionDAO getEstadoEstacion() {
		return new EstadoEstacionDAOhiberJPA();
	}

	public HistorialBicicletaDAO getHistorialBicicleta() {
		return new HistorialBicicletaDAOhiberJPA();
	}
	
	public UsuarioDAO getUsuarioDAO(){
		return new UsuarioDAOhiberJPA();
	}
	
}

