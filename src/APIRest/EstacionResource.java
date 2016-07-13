package APIRest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import model.Estacion;

@Path("/estaciones")
public class EstacionResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	EstacionService estacionService;

	public EstacionResource() {
		this.estacionService = new EstacionService();
	}
	
	//Ejemplo /estaciones/listado
		@GET
		@Path("/listado")
		@Produces({ MediaType.APPLICATION_JSON })
		public List<EstacionAPI> getEstaciones() {
			List<EstacionAPI> result = new ArrayList<EstacionAPI>();
			List<Estacion> lista = estacionService.getEstacionesAsList();
			for (Estacion e : lista) {
				result.add(new EstacionAPI(e.getNombre(),e.getEstacionamientosLibres(),e.getCapacidad(),e.getUbicacionEstacion(),e.getEstadoEstacion()));
			}
			return result;
		}

}
