package APIRest;

import java.util.List;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/stats")
public class StatResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	StatService statService;

	
	public StatResource() {
		statService = new StatService();
	}

	//Ejemplo /stats/Bicicleta
	@GET
	@Path("{entidad}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Stat> getStats(@PathParam("entidad") String name_entidad) {
		return statService.getStatAsList(name_entidad);
	}

}