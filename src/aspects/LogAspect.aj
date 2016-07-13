package aspects;
import java.util.Arrays;
import java.util.Calendar;

import java.util.List;
import java.lang.reflect.*;
import java.sql.Timestamp;

import org.aspectj.lang.JoinPoint;



/**
 * 
 * @author Pravisani Santiago & Malcorra Nicolas.
 * El objetivo del siguiente aspecto es proveer un Log de los eventos ocurridos en el modelo de Jbici.
 * Nota: Podria utilizarse el patron Reflection, sin embargo para evitar errores inesperados crearemos pointcuts por
 * clase del modelo que desee generarse logs.
 */
public aspect LogAspect {
	
	private LoggerDAOhiberJPA logdao = new LoggerDAOhiberJPA();
	
	Object around(): execution(* daoHiberJPA.GenericDAOhiberJPA.*(..)) 
	&& !execution(* daoHiberJPA.GenericDAOhiberJPA.recuperar(..))
	&& !execution(* daoHiberJPA.GenericDAOhiberJPA.existe(..))
	
	{
	
	Object obj = proceed();
	Logger abmLog = new Logger();
	abmLog.setTime(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	String calledMethod = thisJoinPoint.getSignature().getName();
	doLog(abmLog, obj, calledMethod);
	
	return obj; 
}

private void doLog(Logger abmLog, Object entity, String calledMethod) {
	Long entityId = null;
	String entityClass = null;
	Method[] methods = entity.getClass().getDeclaredMethods();
	
	for(Method m : methods){
		if(m.getName().equals("getId")){
			entityId = getEntityId(entity,m);
			break;
		}
	}
	entityClass = entity.getClass().getSimpleName();
	if(entityId!=null && entityClass != null && calledMethod != null){
		abmLog.setEntity_id(entityId);
		abmLog.setClass_name(entityClass);
		abmLog.setOperation(calledMethod);
		logdao.persistir(abmLog);
	}

	
}


	//Helpers
	
	
	private Long getEntityId(Object entity,Method method) {
		Long id = null;
		try {
			id = ((Long)method.invoke(entity,null));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return id;
	}

	
}
