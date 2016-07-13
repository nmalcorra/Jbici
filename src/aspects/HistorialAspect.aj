package aspects;
import java.sql.Timestamp;
import java.util.Date;

import model.EstadoBicicleta;
import model.Bicicleta;
import model.HistorialBicicleta;
import interfacesDAO.BicicletaDAO;
import interfacesDAO.FactoryDAO;
import interfacesDAO.HistorialBicicletaDAO;
public aspect HistorialAspect {
	private FactoryDAO factory = new FactoryDAO();
	
	before(EstadoBicicleta estado, Bicicleta bici): call(* model.Bicicleta.setEstadoActual(EstadoBicicleta))
	&& args(estado)&& target(bici){
		System.out.println(estado.getDescripcion());
		System.out.println(bici.getId());
		//Creamos el historial
		HistorialBicicleta historial = new HistorialBicicleta();
		historial.setEstado(estado);
		historial.setFecha(new Timestamp(new Date().getTime()));
		HistorialBicicletaDAO histdao = factory.getHistorialBicicleta();
		histdao.persistir(historial);
		//Lo agregamos al historial de la bicicleta.
		bici.agregarHistorial(historial);
		BicicletaDAO bicidao = factory.getBicicletaDAO();
		bicidao.actualizar(bici);
		
	}
	
	


}
