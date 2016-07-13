package APIRest;

import model.EstadoEstacion;
import model.Ubicacion;

public class EstacionAPI {
	String nombre;
	int estacionamientosLibres;
	int capacidad;
	Ubicacion ubicacionEstacion;
	EstadoEstacion estadoEstacion;
	
	public EstacionAPI(String nombre, int estacionamientosLibres,
			int capacidad, Ubicacion ubicacionEstacion,
			EstadoEstacion estadoEstacion) {
		super();
		this.nombre = nombre;
		this.estacionamientosLibres = estacionamientosLibres;
		this.capacidad = capacidad;
		this.ubicacionEstacion = ubicacionEstacion;
		this.estadoEstacion = estadoEstacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstacionamientosLibres() {
		return estacionamientosLibres;
	}

	public void setEstacionamientosLibres(int estacionamientosLibres) {
		this.estacionamientosLibres = estacionamientosLibres;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Ubicacion getUbicacionEstacion() {
		return ubicacionEstacion;
	}

	public void setUbicacionEstacion(Ubicacion ubicacionEstacion) {
		this.ubicacionEstacion = ubicacionEstacion;
	}

	public EstadoEstacion getEstadoEstacion() {
		return estadoEstacion;
	}

	public void setEstadoEstacion(EstadoEstacion estadoEstacion) {
		this.estadoEstacion = estadoEstacion;
	}
	
	
	
	
	
	
	
}
