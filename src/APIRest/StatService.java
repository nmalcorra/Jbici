package APIRest;

import java.util.ArrayList;
import java.util.List;

import aspects.Logger;
import aspects.LoggerDAOhiberJPA;

public class StatService {
	
	LoggerDAOhiberJPA loggerDao;
	
	public StatService() {
		loggerDao = new LoggerDAOhiberJPA();
	}
	
	
	public List<Stat> getStatAsList(String entidad) {
		String[] operations = {"persistir","actualizar","borrar","recuperar"};
		List<Stat> listaStats = new ArrayList<Stat>();
		for(String elemento : operations){
			listaStats.add(new Stat(elemento,loggerDao.getLogsFromOperationAndEntity(elemento,entidad).size()));
		}
		return listaStats;
	}


}
	
