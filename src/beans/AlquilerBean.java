package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import interfacesDAO.AlquilerDAO;
import interfacesDAO.BicicletaDAO;
import interfacesDAO.ClienteDAO;
import interfacesDAO.DenunciaDAO;
import interfacesDAO.EstacionDAO;
import interfacesDAO.EstadoBicicletaDAO;
import interfacesDAO.FactoryDAO;
import interfacesDAO.UsuarioDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Alquiler;
import model.Bicicleta;
import model.Cliente;
import model.Denuncia;
import model.Estacion;
import model.Usuario;

@ManagedBean(name="alquilerBean")
@SessionScoped

public class AlquilerBean {

	private FactoryDAO factory = new FactoryDAO();
	
	private Long estacion_bici;
	
	private Long estacion_devuelve;
	
	private String motivoDenuncia;

	private Usuario user;
	
	private Long id_alquiler;
	
	private String message;
	
	public AlquilerBean(){
		super();
		HttpSession session = (HttpSession)
		          FacesContext.
		          getCurrentInstance().
		          getExternalContext().
		          getSession(true);
		Long user_id = (Long) session.getAttribute("userid");	
		UsuarioDAO userdao = factory.getUsuarioDAO();
		Usuario found_user = userdao.recuperar(user_id);
		
		if(found_user!=null){			
			this.setUser(found_user);

		}
	}
	
	public String alquilarBicicleta(){
		EstacionDAO estaciondao = factory.getEstacionDAO();
		ArrayList<Bicicleta> bicicletas =(ArrayList<Bicicleta>) estaciondao.recuperarBicicletasDisponibles(this.getEstacion_bici());
		
		if(!bicicletas.isEmpty()){
			Alquiler alquiler = new Alquiler();
			//Agregamos bicicleta al alquiler
			Bicicleta bici_alquila = bicicletas.get(0);
			alquiler.setBicicleta(bici_alquila);
			//Agregamos fechas de inicio-fin al alquiler
			Timestamp today = new Timestamp(new Date().getTime());
			alquiler.setFecha_inicio(today);
			Calendar c = Calendar.getInstance();  
			c.add(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE,0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND,0);
			Date tomorrow = c.getTime();
			alquiler.setFecha_fin(new Timestamp(tomorrow.getTime()));
			//Agregamos estacion_retira al alquiler
			Estacion estacion_retira = estaciondao.recuperar(estacion_bici);
			alquiler.setEstacion_retira(estacion_retira);
			//Agregamos el usuario al alquiler
			ClienteDAO clientedao = factory.getClienteDAO();
			alquiler.setCliente(clientedao.recuperar(user.getId()));

			//Actualizamos datos referentes a la estacion y la bicicleta
			bici_alquila.setAlquilada(true);
			BicicletaDAO bicidao = factory.getBicicletaDAO();
			bicidao.actualizar(bici_alquila);
			
			estacion_retira.setEstacionamientosLibres(estacion_retira.getEstacionamientosLibres()+1);
			estaciondao.actualizar(estacion_retira);
			
			//Persistimos el alquiler
			AlquilerDAO alquilerdao = factory.getAlquilerDAO();
			alquilerdao.persistir(alquiler);			
			setMessage("<div class='alert alert-success'>Bicicleta retirada exitósamente! con ID: #"+alquiler.getBicicleta().getId()+"</div>");
		}
		else{
			setMessage("<div class='alert alert-danger'>La estación no tiene bicicletas disponibles."
					+ "Por favor dirigirse a otra estación</div>");
		}
		
		return "retirar_bicileta";
	}
	

	public String denunciarForm(Long id_alqui){
		setId_alquiler(id_alqui);
		return "denunciar_bicicleta";
				
	}
	
	public String denunciarBicicleta(){
		AlquilerDAO alquilerdao = factory.getAlquilerDAO();
		Alquiler alquiler = alquilerdao.recuperar(this.getId_alquiler());
		if(alquiler.getDenuncia()==null){
			//Creamos la denuncia.
			DenunciaDAO denunciadao = factory.getDenunciaDAO();
			Denuncia denuncia = new Denuncia();
			Timestamp today = new Timestamp(new Date().getTime());
			denuncia.setDescripcion(this.motivoDenuncia);
			denuncia.setFecha_denuncia(today);
			denunciadao.persistir(denuncia);
			//Agregamos Denuncia al alquiler.
			alquiler.setDenuncia(denuncia);
			alquilerdao.actualizar(alquiler);
			//Cambiamos el estado de la bici
			BicicletaDAO bicidao = factory.getBicicletaDAO();
			Bicicleta bici_mod = alquiler.getBicicleta();
			EstadoBicicletaDAO estadodao = factory.getEstadoBicicletaDAO();
			bici_mod.setEstadoActual(estadodao.recuperar(new Long(4))); //Estado denunciado.
			bicidao.actualizar(bici_mod);
			this.message= "<div class='alert alert-success'>Denuncia efectuada exitosamente!</div>";
		}
		else{
			this.message= "<div class='alert alert-danger'>No puedes efectuar otra denuncia sobre este alquiler!</div>";
		}
		
		return "denunciar_bicicleta";
				
	}

	public String devolverForm(Long id_alqui){
		setId_alquiler(id_alqui);
		return "devolver_bicicleta";
			
	}
	
	public String devolverBicicleta(){
		Timestamp today = new Timestamp(new Date().getTime());
		AlquilerDAO alquilerdao = factory.getAlquilerDAO();
		Alquiler alqui = alquilerdao.recuperar(this.getId_alquiler());
		EstacionDAO estdao = factory.getEstacionDAO();
		Estacion est_devuelve = estdao.recuperar(estacion_devuelve);
		if(est_devuelve.getEstacionamientosLibres()>0){
			
			//Seteamos fecha devolucion y estacion
			alqui.setFecha_devuelve(today);
			alqui.setEstacion_devuelve(est_devuelve);
			alquilerdao.actualizar(alqui);
			//Actualizamos estacion
			est_devuelve.setEstacionamientosLibres(est_devuelve.getEstacionamientosLibres()-1);
			estdao.actualizar(est_devuelve);
			//Actualizamos la bicicleta
			BicicletaDAO bicidao = factory.getBicicletaDAO();
			Bicicleta bici_devuelve = bicidao.recuperar(alqui.getBicicleta().getId());
			bici_devuelve.setAlquilada(false);
			bicidao.actualizar(bici_devuelve);
			
			if(today.before(alqui.getFecha_fin())){
				setMessage("<div class='alert alert-success'>Bicicleta devuelta exitósamente!</div>");
			}
			else{
				setMessage("<div class='alert alert-danger'>Bicicleta devuelta. Se aplicará una sanción por"
						+ "excederse en el tiempo de la devolución</div>");
			}
			
		}
		else{
			setMessage("<div class='alert alert-danger'>Estación sin lugares libres. Por favor dirigirse"
					+ "a otra estación para su devolución</div>");
		}
		
		return "devolver_bicicleta";
	}
	
	

	/** Getter & Setter **/
	
	public ArrayList<Estacion> getEstaciones() {
        EstacionDAO estaciondao = factory.getEstacionDAO();
        return (ArrayList<Estacion>) estaciondao.getAllEstaciones();
	}
	
	public List<Alquiler> getAlquileres(){
		AlquilerDAO alquilerdao = factory.getAlquilerDAO();
		return alquilerdao.recuperarAlquileresActivosPorCliente(user.getId());
		
	}
	
	public List<Alquiler> getHistorial(){
		AlquilerDAO alquilerdao = factory.getAlquilerDAO();
		return alquilerdao.recuperarAlquileresPorCliente(user.getId());
		
	}
	

	public Long getEstacion_bici() {
		return estacion_bici;
	}

	public void setEstacion_bici(Long estacion_bici) {
		this.estacion_bici = estacion_bici;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	


	public Long getId_alquiler() {
		return id_alquiler;
	}

	public void setId_alquiler(Long id_alquiler) {
		this.id_alquiler = id_alquiler;
	}
	
	public Long getEstacion_devuelve() {
		return estacion_devuelve;
	}

	public void setEstacion_devuelve(Long estacion_devuelve) {
		this.estacion_devuelve = estacion_devuelve;
	}

	public String getMotivoDenuncia() {
		return motivoDenuncia;
	}

	public void setMotivoDenuncia(String motivoDenuncia) {
		this.motivoDenuncia = motivoDenuncia;
	}
	
	public String getClearBean(){
		this.message="";
		return message;
	}
	
}
