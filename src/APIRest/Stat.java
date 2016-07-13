package APIRest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stat {
	private String operacion;
	private int cantidad;
	
	public Stat(){
		
	}
	
	public Stat(String operacion,int cantidad){
		this.operacion=operacion;
		this.cantidad=cantidad;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
