package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="historial_bicicleta")
public class HistorialBicicleta implements aspects.LogInterface{
	
	
	@Id@GeneratedValue
	@Column(name="id_historial")
	private Long idHistorialBicicleta;

	private Timestamp fecha;
	
	@OneToOne
	private EstadoBicicleta estado;
	
	public HistorialBicicleta(Timestamp time, EstadoBicicleta estado){
		this.fecha = time;
		this.estado = estado;
	}
	
	public HistorialBicicleta(){
		
	}
	
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public EstadoBicicleta getEstado() {
		return estado;
	}
	public void setEstado(EstadoBicicleta estado) {
		this.estado = estado;
	}

	public Long getHistorialBicicletaId() {
		return idHistorialBicicleta;
	}

	public void setHistorialBicicletaId(Long historialBicicletaId) {
		this.idHistorialBicicleta = historialBicicletaId;
	}

	@Override
	public Long getId() {
		return getHistorialBicicletaId(); 
	}
	

}
