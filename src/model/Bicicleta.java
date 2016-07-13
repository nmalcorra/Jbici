package model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bicicleta")

public class Bicicleta implements aspects.LogInterface{
	
	@Id@GeneratedValue
	@Column(name="id_bicicleta")
	private Long idBicicleta;
	
	private Date fecha_ingreso;
	
	@OneToOne() //Uno a Uno Unidireccional
	private EstadoBicicleta estadoActual;

	@OneToMany(mappedBy="bicicleta",cascade=CascadeType.ALL)
	private List<Alquiler> alquileres;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="estacion_id")
	private Estacion estacionActual;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="bicicleta_id")	
	private List<HistorialBicicleta> historialEstados;
	
	private boolean alquilada;
	
	/**
	 * Constructores
	 */
	public Bicicleta() {
		
	}

	
	public Bicicleta(Date fechaIngreso, EstadoBicicleta estadoActual, Estacion estacion) {
		super();
		this.fecha_ingreso = fechaIngreso;
		this.estadoActual = estadoActual;
		this.alquileres = new LinkedList<Alquiler>();
		this.historialEstados = new LinkedList<HistorialBicicleta>();
		this.estacionActual = estacion;
		this.alquilada=false;
	}

	

	//Getter & setter
	public Long getIdBicicleta() {
		return idBicicleta;
	}

	public void setIdBicicleta(Long bicicletaId) {
		this.idBicicleta = bicicletaId;
	}
	
	public Estacion getEstacionActual() {
		return estacionActual;
	}


	public void setEstacionActual(Estacion estacionActual) {
		this.estacionActual = estacionActual;
	}
	
	public Date getFechaIngreso() {
		return fecha_ingreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fecha_ingreso = fechaIngreso;
	}


	public List<Alquiler> getAlquileres() {
		return alquileres;
	}
	
	public List<HistorialBicicleta> getHistorial(){
		return this.historialEstados;
	}

	private void setAlquileres(List<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	public EstadoBicicleta getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(EstadoBicicleta estadoActual) {
		this.estadoActual = estadoActual;
	}


	private List<HistorialBicicleta> getHistorialEstados() {
		return historialEstados;
	}


	private void setHistorialEstados(List<HistorialBicicleta> historial_estados) {
		this.historialEstados = historial_estados;
	}

	//agregar estado al historial de la bicleta
	public void agregarHistorial(HistorialBicicleta historial){
		this.historialEstados.add(historial);
	}
	
	//agregar alquiler a la bicicleta
	public void agregarAlquiler(Alquiler alquiler){
		this.alquileres.add(alquiler);
	}


	@Override
	public Long getId() {
		return getIdBicicleta();
	}


	public boolean isAlquilada() {
		return alquilada;
	}


	public void setAlquilada(boolean alquilada) {
		this.alquilada = alquilada;
	}

	
	
	
	
}
