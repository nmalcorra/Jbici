package aspects;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Pravisani Santiago & Malcorra Nicolas
 * 
 * La siguiente clase sera destinada para generar logs sobre las operaciones realizadas sobre los daos. 
 */

@Entity
@Table(name="operation_logs")
public class Logger {
	
	@Id@GeneratedValue
	@Column(name="id_log")
	private Long idLog;
	
	//Nombre de la operacion realizada.
	private String operation;
	//Momento de la operacion realizada.
	private Timestamp time;
	//Nombre de la clase involucrada en la operacion.
	private String class_name;
	//Id de la entidad que realiza la operacion
	private Long entity_id;
	
	public Logger(){
		
	}
	

	public Logger(String operation, Timestamp time,
			String class_name, Long entity_id) {
		this.operation = operation;
		this.time = time;
		this.class_name = class_name;
		this.entity_id = entity_id;
	}



	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public Long getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(Long id) {
		this.entity_id = id;
	}
	public Long getIdLog() {
		return idLog;
	}
	public void setIdLog(Long idLog) {
		this.idLog = idLog;
	}
	
	
	
}
