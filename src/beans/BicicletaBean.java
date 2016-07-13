package beans;

import interfacesDAO.BicicletaDAO;
import interfacesDAO.EstacionDAO;
import interfacesDAO.EstadoBicicletaDAO;
import interfacesDAO.FactoryDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Bicicleta;
import model.Estacion;
import model.EstadoBicicleta;
import model.HistorialBicicleta;

@ManagedBean(name="bicicletaBean")
@SessionScoped
public class BicicletaBean {
	
	private FactoryDAO factory = new FactoryDAO();
	
	private String fecha_ingreso;
	
	private Long estacion_bici;
	
	private Long idBicicleta;
	
	private Long estado_bici;
	
	private List<HistorialBicicleta> historial;
	
	private ArrayList<Estacion> estaciones = getAllEstaciones();
	
	private ArrayList<Bicicleta> bicicletas = getAllBicicletas();
	
	private ArrayList<EstadoBicicleta> estados = getAllEstadoBicicleta();
	
	private String message;
	
	/* Webpages */ 
    public String agregarBicicletaForm(){

    	return "alta_bici";
    }
    


	public String modificarForm(Long id_bici){
		this.idBicicleta=id_bici;
		BicicletaDAO bicidao = factory.getBicicletaDAO();
    	Bicicleta bici_mod = bicidao.recuperar(id_bici);
    	this.fecha_ingreso = dateToString(bici_mod.getFechaIngreso());
    	this.estacion_bici = bici_mod.getEstacionActual().getId();
    	this.estado_bici = bici_mod.getEstadoActual().getId();
    	
    	
    	return "modificar_bicicleta";
    }
    
    public String administrarBicicletas(){
    	this.bicicletas= getAllBicicletas();
    	return "administrar_bicicletas";
    }
    
    
    public String agregarForm(){
    	clearBean();
    	return "alta_bici";
    }
    

    private ArrayList<Bicicleta> getAllBicicletas() {
		
        BicicletaDAO bicidao = factory.getBicicletaDAO();
        this.bicicletas = (ArrayList<Bicicleta>) bicidao.getAllBicicletas();
        return bicicletas;
	}

	private ArrayList<Estacion> getAllEstaciones() {
		
        EstacionDAO estaciondao = factory.getEstacionDAO();
        this.estaciones = (ArrayList<Estacion>) estaciondao.getAllEstaciones();
        return estaciones;
	}
	
    private ArrayList<EstadoBicicleta> getAllEstadoBicicleta() {
		EstadoBicicletaDAO estadodao = factory.getEstadoBicicletaDAO();
		this.estados = (ArrayList<EstadoBicicleta>) estadodao.getAllEstadoBicicleta();
		return estados;
	}

	public String agregarBicicleta(){
    	BicicletaDAO bicidao = factory.getBicicletaDAO();
    	EstadoBicicletaDAO estadodao = factory.getEstadoBicicletaDAO(); 
    	EstacionDAO estaciondao = factory.getEstacionDAO();
    	EstadoBicicleta estado= estadodao.recuperar(new Long(1)); //Estado Bueno ->Refactorizar aca. 
    	Estacion estacion = estaciondao.recuperar(new Long(this.estacion_bici));
        if(estacion.getEstacionamientosLibres() > 0){
        	//Si hay lugar en la estacion
        	estacion.setEstacionamientosLibres(estacion.getEstacionamientosLibres()-1);
        	estaciondao.actualizar(estacion);
        	Date fecha = formatDate();
        	Bicicleta bicicleta = new Bicicleta(fecha,estado,estacion);	
        	bicicleta = bicidao.persistir(bicicleta);
        	bicicleta.setEstadoActual(estado); //Creamos log para el historial.
        	this.message="<div class='alert alert-success'>Bicicleta creada exitósamente! con ID: #"+bicicleta.getId()+"</div>";
        }
        else{
        	this.message="<div class='alert alert-danger'>Error:La estación no posee lugares disponibles para ingresar nuevas bicicletas</div>";
        }
        
		clearBean();
		getAllBicicletas();
		return "alta_bici";
    } 
	
	public String modificarBicicleta() {
		BicicletaDAO bicidao = factory.getBicicletaDAO();
		
		EstadoBicicletaDAO estadodao = factory.getEstadoBicicletaDAO(); 
    	EstacionDAO estaciondao = factory.getEstacionDAO();
    	EstadoBicicleta estado_mod= estadodao.recuperar(this.getEstado_bici()); 
    	Estacion estacion_mod = estaciondao.recuperar(this.getEstacion_bici());
        Date fecha = formatDate();
        
    	Bicicleta bici_mod = bicidao.recuperar(this.getIdBicicleta());
    	
    	bici_mod.setEstacionActual(estacion_mod);
    	bici_mod.setEstadoActual(estado_mod);
    	bici_mod.setFechaIngreso(fecha);
    	bicidao.actualizar(bici_mod);
    	this.getAllBicicletas();    	
    	clearBean();
		return "administrar_bicicletas";
	}
	
	public String eliminarBicicleta(Long id_bici){
		BicicletaDAO bicidao = factory.getBicicletaDAO();
		
		bicidao.borrar(id_bici);
		
		this.getAllBicicletas();
		
		return "administrar_bicicletas";
	}
	
	public List<HistorialBicicleta> getAllHistorial(Long id_bici) {
		BicicletaDAO bicidao = factory.getBicicletaDAO();
		Bicicleta bici = bicidao.recuperar(id_bici);
		
		return bici.getHistorial();
	}
	
	public String verHistorial(Long id_bici){
		this.historial = getAllHistorial(id_bici);
		return "historial_bicicleta";
	}
	
	public List<HistorialBicicleta> getHistorial(){
		return this.historial;
	}
	
	
	/* Getters y setters */
    
	public Long getEstacion_bici() {
		return estacion_bici;
	}

	public void setEstacion_bici(Long estacion_bici) {
		this.estacion_bici = estacion_bici;
	}

	public String getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<Estacion> getEstaciones() {
        EstacionDAO estaciondao = factory.getEstacionDAO();
        this.estaciones = (ArrayList<Estacion>) estaciondao.getAllEstaciones();
        return estaciones;
	}

	public void setEstaciones(ArrayList<Estacion> estaciones) {
		this.estaciones = estaciones;
	}

	public ArrayList<Bicicleta> getBicicletas() {
		return bicicletas;
	}

	public void setBicicletas(ArrayList<Bicicleta> bicicletas) {
		this.bicicletas = bicicletas;
	}

	private void clearBean(){
		this.fecha_ingreso = null;
		this.estacion_bici = null;
	}

	public Long getIdBicicleta() {
		return idBicicleta;
	}

	public void setIdBicicleta(Long idBicicleta) {
		this.idBicicleta = idBicicleta;
	}

	public Long getEstado_bici() {
		return estado_bici;
	}

	public void setEstado_bici(Long estado_bici) {
		this.estado_bici = estado_bici;
	}
	
	public ArrayList<EstadoBicicleta> getEstados() {
		return this.estados;
	}

	public void setEstados(ArrayList<EstadoBicicleta> estados) {
		this.estados = estados;
	}
	
	/* Helpers */
	
	private Date formatDate(){
    	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(this.fecha_ingreso); //String ingresado x el user
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        return fecha;
        
	}
	
	private String dateToString(Date fecha){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		String reportDate = df.format(fecha);
		
		return reportDate;
	}
	
	public String getClearMessage(){
		this.message="";
		return message;
	}
	
	
}
