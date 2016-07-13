package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estado_estacion")
public class EstadoEstacion implements aspects.LogInterface{
	
	@Id@GeneratedValue
	@Column(name="id")
	private Long idEstadoEstacion;
	private String descripcion;

	
	//constructores
	public EstadoEstacion(){
		
	}
	
	public EstadoEstacion(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
	
	//Getter & Setter
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdEstadoEstacion() {
		return this.idEstadoEstacion;
	}

	@Override
	public Long getId() {
		return getIdEstadoEstacion();
	}
	
}
