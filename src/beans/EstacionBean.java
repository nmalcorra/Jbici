package beans;

import interfacesDAO.EstacionDAO;
import interfacesDAO.EstadoEstacionDAO;
import interfacesDAO.FactoryDAO;
import interfacesDAO.UbicacionDAO;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Estacion;
import model.EstadoEstacion;
import model.Ubicacion;

@ManagedBean(name="EstacionBean")
@SessionScoped
public class EstacionBean {
	
	Estacion estacion;
	public String message;
	Ubicacion ubicacion;
	Long estado_estacion;
	
	private FactoryDAO factory = new FactoryDAO();
	
	public EstacionBean(){
		super();
		this.estacion = new Estacion();
		this.ubicacion = new Ubicacion();
	}
	
	public void borrarCampos(){
	//	this.message= null;
		this.estacion = new Estacion();
		this.ubicacion = new Ubicacion();
	}
	
	
	public String agregar_estacion(){
		EstacionDAO estacionDAO = this.factory.getEstacionDAO();
		EstadoEstacionDAO estadoEstacionDAO = this.factory.getEstadoEstacion();
		UbicacionDAO ubicacionDAO = this.factory.getUbicacionDAO();
		boolean existe = estacionDAO.existeConNombre(getNombre());
		if (!existe){
			EstadoEstacion estado = estadoEstacionDAO.recuperar(this.estado_estacion);
			ubicacionDAO.persistir(this.ubicacion);
			this.estacion.setUbicacionEstacion(ubicacion);
			this.estacion.setEstadoEstacion(estado);
			this.estacion.setEstacionamientosLibres(this.getCant_bicis());
			estacionDAO.persistir(this.estacion);
			this.borrarCampos();
			this.message="<div class='alert alert-success' role='alert'>Estacion agregada con exito!</div>";
			return "alta_estacion";
		}else{
			this.message="<div class='alert alert-danger' role='alert'>Ya existe una estacion con ese nombre</div>";
			return "alta_estacion";
		}
	}
	
	public String modificar_estacion(){
		UbicacionDAO ubidao = this.factory.getUbicacionDAO();
		EstacionDAO estacionDAO = this.factory.getEstacionDAO();
		EstadoEstacionDAO estadao = this.factory.getEstadoEstacion();
		if(!estacionDAO.existeNombreId(this.estacion.getNombre(),this.estacion.getId())){
			this.estacion.setEstadoEstacion(estadao.recuperar(new Long(this.estado_estacion)));
			estacionDAO.actualizar(this.estacion);	
			ubidao.actualizar(this.estacion.getUbicacionEstacion());
			return "administrar_estaciones";
		}else{
			this.message="<div class='alert alert-danger' role='alert'>Ya existe una estacion con ese nombre</div>";
			return "mod_estacion";
		}

	}

	 public String vista_modificar(Long id_estacion){
		 this.message = null;
		 EstacionDAO estaDAO = this.factory.getEstacionDAO();
		 this.estacion = estaDAO.recuperar(id_estacion);
		 this.ubicacion=this.estacion.getUbicacionEstacion();
		 return "mod_estacion";
	 }
	 
	 public ArrayList<EstadoEstacion> getEstados() {
			EstadoEstacionDAO estadodao = factory.getEstadoEstacion();
			return (ArrayList<EstadoEstacion>)estadodao.getAllEstadoEstacion();
	 }
	 
	 public String vista_agregar(){
		 this.borrarCampos();
		 return "alta_estacion.xhtml";
	 }
	 

	//Setters and Getters

	
	
	public String getNombre() {
		return estacion.getNombre();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getLatitud() {
		return this.ubicacion.getLatitud();
	}

	public void setLatitud(double latitud) {
		this.ubicacion.setLatitud(latitud); 
	}

	public double getLongitud() {
		return this.ubicacion.getLongitud();
	}

	public void setLongitud(double longitud) {
		this.ubicacion.setLongitud(longitud); 
	}

	public EstadoEstacion getEstado_estacion() {
		return estacion.getEstadoEstacion();
	}

	public void setEstado_estacion(EstadoEstacion estado_estacion) {
		this.estacion.setEstadoEstacion(estado_estacion);;
	}

	public void setNombre(String nombre) {
		this.estacion.setNombre(nombre);
	}

	public Integer getCant_bicis() {
		return this.estacion.getCapacidad();
	}

	public void setCant_bicis(Integer cant_bicis) {
		this.estacion.setCapacidad(cant_bicis);
	}

	public Integer getCant_lugares() {
		return this.estacion.getEstacionamientosLibres();
	}

	public void setCant_lugares(Integer cant_lugares) {
		this.estacion.setEstacionamientosLibres(cant_lugares);
	}

	public String getCodpostal() {
		return this.ubicacion.getDireccionPostal();
	}

	public void setCodpostal(String codpostal) {
		this.ubicacion.setDireccionPostal(codpostal); 
	}

	public Long getEstado() {
		return this.estado_estacion;
	}

	public void setEstado(Long estado){
		this.estado_estacion = estado;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public FactoryDAO getFactory() {
		return factory;
	}

	public void setFactory(FactoryDAO factory) {
		this.factory = factory;
	}
	
	

}
