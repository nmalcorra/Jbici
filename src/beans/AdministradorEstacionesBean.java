package beans;
import interfacesDAO.EstacionDAO;
import interfacesDAO.FactoryDAO;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Estacion;

@ManagedBean(name="adminEstacionesBean")
@RequestScoped
public class AdministradorEstacionesBean {
	
	private FactoryDAO factory = new FactoryDAO();
	
	
	private ArrayList<Estacion> estaciones;


	public AdministradorEstacionesBean(){
		this.estaciones = this.getAllEstaciones();
	}
	
    public ArrayList<Estacion> getAllEstaciones() {
        EstacionDAO estaciondao = factory.getEstacionDAO();
        this.estaciones = (ArrayList<Estacion>) estaciondao.getAllEstaciones();
        return this.estaciones;
    }

    public String eliminar(Long id){
    	EstacionDAO estaciondao = factory.getEstacionDAO();
    	estaciondao.borrar(id);
    	this.estaciones = getAllEstaciones();
    	return "administrar_estaciones";
    } 
    
	public ArrayList<Estacion> getEstaciones() {
		return estaciones;
	}

	public void setEstaciones(ArrayList<Estacion> estaciones) {
		this.estaciones = estaciones;
	}
	
}