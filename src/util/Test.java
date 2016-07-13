package util;

import interfacesDAO.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Administrador;
import model.Alquiler;
import model.Bicicleta;
import model.Cliente;
import model.Denuncia;
import model.Estacion;
import model.EstadoBicicleta;
import model.EstadoEstacion;
import model.HistorialBicicleta;
import model.Ubicacion;


public class Test {

	@SuppressWarnings({ "deprecation", "deprecation" })
	public static void main(String[] args) {
		FactoryDAO factory = new FactoryDAO();
		
		/**
		 * Obtengo mis Daos
		 */
		AdministradorDAO admindao = factory.getAdministradorDAO();
		AlquilerDAO alquilerdao = factory.getAlquilerDAO();
		BicicletaDAO bicidao = factory.getBicicletaDAO();
		ClienteDAO clientedao = factory.getClienteDAO();
		DenunciaDAO denunciadao = factory.getDenunciaDAO();
		EstacionDAO estaciondao = factory.getEstacionDAO();
		EstadoBicicletaDAO estadobicidao = factory.getEstadoBicicletaDAO();
		EstadoEstacionDAO estadoestaciondao = factory.getEstadoEstacion();
		HistorialBicicletaDAO historialdao = factory.getHistorialBicicleta();
		UbicacionDAO ubicaciondao = factory.getUbicacionDAO();

		/**
		 * Test de Administrador
		 */
		System.out.println("=====================================");
		System.out.println("Test Administrador");
		System.out.println("--Test persistencia");
		Administrador admin = new Administrador("Benedict", "Cumberbatch" , 37456987, "Baker st.",'M',new Date(1989,05,03),"sherlock@holmes.com","1234");
		admindao.persistir(admin);
		Long id = admin.getIdUsuario();
		System.out.println("Administrador persistio con id="+id);
		
		System.out.println("--Test recuperar");
		Administrador admin_recuperado = admindao.recuperar(id);
		if (admin_recuperado != null){
			System.out.println(" - Admin recuperado con id="+id);
		}
		admin_recuperado = admindao.recuperar(new Long(99999)); //Esto no existe
		if (admin_recuperado == null){
			System.out.println(" - No existe admin con id 999999");
		}
		
		System.out.println("--Test actualizar");
		admin.setEmail("nuevoemailadmin@mail.com"); //Actualizamos e-mail.
		admin= admindao.actualizar(admin);
		System.out.println(" - Nuevo email:"+admin.getEmail());
		
		System.out.println("--Test borrar");
		admindao.borrar(id);
		if(admindao.recuperar(id) == null){
			System.out.println(" - Entidad admin ya no existe");
		}
		Administrador admin_def = new Administrador("Benedict", "Cumberbatch" , 37456987, "Baker st.",'M',new Date(1989,05,03),"admin","admin");
		admindao.persistir(admin_def);
		
		/**
		 * Test cliente
		 */
		System.out.println("=====================================");
		System.out.println("Test Cliente");
		System.out.println("--Test persistencia");
		Cliente cliente =  new Cliente("Matthew","McConaughey",8596321,"Los Angeles",'M',new Date(1969,11,04),"matthew@mail.com","interstellar321"); 
		clientedao.persistir(cliente);
		id = cliente.getIdUsuario();
		System.out.println(" - Cliente persistido con id="+id);
		System.out.println("--Test coleccion Cliente<->Alquiler* (Bidireccion)");
		Alquiler alquiler = getAlquilerPersistido(ubicaciondao, estadoestaciondao, bicidao, estadobicidao, alquilerdao, estaciondao, cliente);
		//Agregamos un alquiler
		cliente.agregarAlquiler(alquiler);
		clientedao.actualizar(cliente);
		id = cliente.getIdUsuario();
		Long id_alqui_usuario = alquiler.getCliente().getIdUsuario();
		System.out.println(" - Lado Alquiler, traemos su cliente con id cliente="+id_alqui_usuario);
		 
		System.out.println("--Test recuperar");
		Cliente cliente_recuperado = clientedao.recuperar(id);
		if (cliente_recuperado != null){
			System.out.println(" - Cliente recuperado con id="+id);
		}
		cliente_recuperado = clientedao.recuperar(new Long(99999));
		if (cliente_recuperado == null){
			System.out.println(" - No existe cliente con id 999999");
		}
		
		System.out.println("--Test actualizar");
		cliente.setNombre("Cooper"); //Actualizamos nombre.
		cliente= clientedao.actualizar(cliente);
		System.out.println(" - Nuevo nombre "+cliente.getNombre());
		
		System.out.println("--Test borrar");
		clientedao.borrar(id);
		if(clientedao.recuperar(id) == null){
			System.out.println(" - Entidad cliente ya no existe!");
		}
		
		
		/**
		 * Test Alquiler
		 */
		System.out.println("=====================================");
		System.out.println("Test Alquiler");
		System.out.println("--Test persistencia");
		//Creamos cliente 
		cliente =  new Cliente("Tiffany","Hwang",12345678,"Los Angeles",'F',new Date(1969,11,04),"user","user"); 
		clientedao.persistir(cliente);
		alquiler = getAlquilerPersistido(ubicaciondao, estadoestaciondao, bicidao, estadobicidao, alquilerdao, estaciondao, cliente);
		id = alquiler.getAlquilerId();
		System.out.println(" - Alquiler persistido con id:"+id);
		
		System.out.println("--Test recuperar");
		Alquiler alquiler_recuperado = alquilerdao.recuperar(id);
		if (alquiler_recuperado != null){
			System.out.println(" - Alquiler recuperado con id="+id);
		}
		alquiler_recuperado = alquilerdao.recuperar(new Long(99999));
		if (alquiler_recuperado == null){
			System.out.println(" - No existe alquiler con id 999999");
		}
		
		System.out.println("--Test actualizar");
		alquiler.setFecha_devuelve(new Timestamp(new Date().getTime())); //Nueva fecha para devolucion. 
		alquiler= alquilerdao.actualizar(alquiler);
		System.out.println(" - Nueva fecha de devolucion "+alquiler.getFecha_devuelve());
		
		System.out.println("--Test borrar");
		alquilerdao.borrar(id);
		if(alquilerdao.recuperar(id) == null){
			System.out.println(" - Entidad alquiler ya no existe!");
		}
		
		
		/**
		 * Test Denuncia
		 */
		System.out.println("=====================================");
		System.out.println("Test Denuncia");
		System.out.println("--Test persistencia");
		Denuncia denuncia = new Denuncia(new Timestamp(new Date().getTime()),"Bicicleta le falta una rueda");
		denunciadao.persistir(denuncia);
		id = denuncia.getIdDenuncia();
		System.out.println(" - Denuncia persistida con id="+id);
		//Testeamos agregar una denuncia a un alquiler,dada la naturaleza del objeto.
		cliente = new Cliente("Gabriel","Macht",5698745,"New York",'M',new Date(1975,05,12),"suits@gmail.com","suits1234"); 
		clientedao.persistir(cliente);
		alquiler = getAlquilerPersistido(ubicaciondao, estadoestaciondao, bicidao, estadobicidao, alquilerdao, estaciondao, cliente);
		alquiler.setDenuncia(denuncia);
		alquiler = alquilerdao.actualizar(alquiler);
		System.out.println(" - Denuncia agregada al alquiler con id="+alquiler.getDenuncia().getIdDenuncia());
		
		System.out.println("--Test recuperar");
		denuncia = null;
		denuncia = denunciadao.recuperar(id);
		if (denuncia != null){
			System.out.println(" - Denuncia recuperada con id="+id);
		}
		denuncia = denunciadao.recuperar(new Long(-1));
		if (denuncia == null){
			System.out.println(" - No existe denuncia con id -1");
		}
		
		System.out.println("--Test actualizar");
		denuncia = denunciadao.recuperar(id); 
		denuncia.setDescripcion("Bicicleta robada"); //Nueva descripcion
		denuncia= denunciadao.actualizar(denuncia);
		System.out.println(" - Nueva descripcion "+denuncia.getDescripcion());
		
		System.out.println("--Test borrar");
		//Prueba de borrado desde un alquiler.
		alquilerdao.borrarDenuncia(alquiler.getAlquilerId());
		if(denunciadao.recuperar(id) == null){
			System.out.println(" - Entidad Denuncia ya no existe en alquiler!");
		}
		//Prueba de borrado independiente
		denuncia = new Denuncia(new Timestamp(new Date().getTime()),"Manubrio roto");
		denunciadao.persistir(denuncia);
		denunciadao.borrar(denuncia.getIdDenuncia());
		if(denunciadao.recuperar(id) == null){
			System.out.println(" - Entidad Denuncia independiente ya no existe!");
		}
		
		
		
		/**
		 * Test Ubicacion
		 */
		System.out.println("=====================================");
		System.out.println("Test Ubicacion");
		System.out.println("--Test persistencia");
		Ubicacion ubicacion = new Ubicacion(123456,654321,"1900 - La Plata");
		ubicaciondao.persistir(ubicacion);
		id = ubicacion.getIdUbicacion();
		System.out.println(" - Ubicacion persistida con id="+id);
		
		System.out.println("--Test recuperar");
		ubicacion = null;
		ubicacion = ubicaciondao.recuperar(id);
		if (ubicacion != null){
			System.out.println(" - Ubicacion recuperada con id="+id);
		}
		ubicacion = ubicaciondao.recuperar(new Long(-1));
		if (ubicacion == null){
			System.out.println(" - No existe ubicacion con id -1");
		}
		
		System.out.println("--Test actualizar");
		ubicacion = ubicaciondao.recuperar(id);
		ubicacion.setDireccionPostal("1923 - Berisso");
		ubicacion= ubicaciondao.actualizar(ubicacion);
		System.out.println(" - Nueva direccion postal "+ubicacion.getDireccionPostal());
		
		System.out.println("--Test borrar");
		ubicaciondao.borrar(id);
		if(ubicaciondao.recuperar(id) == null){
			System.out.println(" - Entidad Ubicacion ya no existe!");
		}

		
        /**
         * Test de EstadoBicicleta
         */
        System.out.println("=====================================");
        System.out.println("Test EstadoBicleta");
        System.out.println("--Test persistencia");
        EstadoBicicleta estado_bici = new EstadoBicicleta("Apta para uso");
        estadobicidao.persistir(estado_bici);
        Long id_estadobici = estado_bici.getIdEstadoBicicleta();
        System.out.println("EstadoBicicleta persistio con id="+id_estadobici);
        System.out.println("--Test recuperar");
        EstadoBicicleta estadobici_recuperado = estadobicidao.recuperar(id_estadobici);
        if (estadobici_recuperado != null){
                System.out.println(" - EstadoBicicleta recuperado con id="+id_estadobici);
        }
        estadobici_recuperado = estadobicidao.recuperar(new Long(99999)); //Esto no existe
        if (estadobici_recuperado == null){
                System.out.println(" - No existe EstadoBicicleta con id 999999");
        }
        System.out.println("--Test actualizar");
        System.out.println("Antes de actualizar :" + estado_bici.getDescripcion()); 
        estado_bici.setDescripcion("Denunciada"); //Actualizamos descripcion.
        estado_bici= estadobicidao.actualizar(estado_bici);
        System.out.println("Actualizado: " + estado_bici.getDescripcion());
        System.out.println("--Test borrar");
        estadobicidao.borrar(id_estadobici);
       
        if(estadobicidao.recuperar(id_estadobici) == null){
                System.out.println("Entidad EstadoBicicleta ya no existe");
        }
        /**
         * Test de EstadoEstacion
         */
        System.out.println("=====================================");
        System.out.println("Test EstadoEstacion");
        System.out.println("--Test persistencia");
        EstadoEstacion estado_estacion = new EstadoEstacion("Operativa");
        estadoestaciondao.persistir(estado_estacion);
        Long id_estadoestacion = estado_estacion.getIdEstadoEstacion();
        System.out.println("EstadoEstacion persistio con id="+id_estadoestacion);
        System.out.println("--Test recuperar");
        EstadoEstacion estadoestacion_recuperado = estadoestaciondao.recuperar(id_estadoestacion);
        if (estadobici_recuperado != null){
                System.out.println(" - EstadoEstacion recuperado con id="+id_estadoestacion);
        }
        estadoestacion_recuperado = estadoestaciondao.recuperar(new Long(99999)); //Esto no existe
        if (estadoestacion_recuperado == null){
                System.out.println(" - No existe EstadoEstacion con id 999999");
        }
        System.out.println("--Test actualizar");
        System.out.println("Antes de actualizar :" + estado_estacion.getDescripcion()); 
        estado_estacion.setDescripcion("En construccion"); //Actualizamos descripcion.
        estado_estacion= estadoestaciondao.actualizar(estado_estacion);
        System.out.println("Actualizado: " + estado_estacion.getDescripcion());
        System.out.println("--Test borrar");
        estadoestaciondao.borrar(id_estadoestacion);
       
        if(estadoestaciondao.recuperar(id_estadoestacion) == null){
                System.out.println("Entidad EstadoEstacion ya no existe");
        }
       
        
        /**
         * Test de Bicicleta
         */
        System.out.println("=====================================");
        System.out.println("Test Bicicleta");
        System.out.println("--Test persistencia");
        //Datos necesarios para persistir una bicicleta.
        Date date = new Date();
        EstadoBicicleta estado_bici_test = new EstadoBicicleta("En reparacion");
        estadobicidao.persistir(estado_bici_test);
        EstadoEstacion estado_estacion_test = new EstadoEstacion("operativa");
        estadoestaciondao.persistir(estado_estacion_test);
        Ubicacion ubicacion_esta = new Ubicacion();
        ubicaciondao.persistir(ubicacion_esta);
        Estacion estacion_test = new Estacion("constitucion",3,ubicacion_esta,estado_estacion_test);
        estaciondao.persistir(estacion_test);
        
        Bicicleta bicicleta = new Bicicleta(date,estado_bici_test,estacion_test);
        bicidao.persistir(bicicleta);
        Long id_bici = bicicleta.getIdBicicleta();
        System.out.println("Bicicleta persistio con id="+id_bici);
        System.out.println("--Test coleccion Bicicleta<->Alquiler* (Bidireccion)");
        
        //Probamos del lado Alquiler.
		Alquiler alqui= getAlquilerPersistido(ubicaciondao,estadoestaciondao, bicidao,estadobicidao, alquilerdao,estaciondao,cliente);
        bicicleta = alqui.getBicicleta();
        bicicleta.agregarAlquiler(alqui);
        bicidao.actualizar(bicicleta);
        id_bici= bicicleta.getIdBicicleta();
		Long id_alquiler_bici = alqui.getBicicleta().getIdBicicleta();
		System.out.println(" - Lado Alquiler, con id de bicicleta:"+id_alquiler_bici);
		
		//Traemos todos los alquileres para la bicicleta (Lado bicicleta).
		List<Alquiler> res = alquilerdao.recuperarAlquileresPorBicicleta(id_bici); //Con un id de bici.
		System.out.println(" - Lado Bicicleta, traemos lista de alquileres, 1er alquiler con id="+res.get(0).getAlquilerId());
		//Traemos los historiales de la bicicleta
		System.out.println("--Test coleccion Bicicleta -> HistorialBicicleta (Unidireccional) ");
		HistorialBicicleta his = new HistorialBicicleta(new Timestamp(new Date().getTime()), estado_bici_test);
		historialdao.persistir(his);
		bicicleta.agregarHistorial(his);
		bicidao.actualizar(bicicleta);
		Bicicleta bicihistorial = bicidao.recuperarconHistorial(id_bici); //Con un id de bici.
		System.out.println(" - Historiales de la bicicleta, traemos el primero con id="+bicihistorial.getHistorial().get(0).getHistorialBicicletaId());
		
		System.out.println("--Test recuperar");
        Bicicleta bici_recuperada = bicidao.recuperar(id_bici);
        if (bici_recuperada != null){
                System.out.println(" - Bicicleta recuperada con id="+id_bici);
        }
        bici_recuperada = bicidao.recuperar(new Long(99999)); //Esto no existe
        if (bici_recuperada == null){
                System.out.println(" - No existe Bicicleta con id 999999");
        }
        
        System.out.println("--Test actualizar");
        System.out.println("Antes de actualizar :" + bicicleta.getFechaIngreso().toString()); 
        bicicleta.setFechaIngreso(new Date(2014,2,32));; //Actualizamos fecha.
        bicicleta = bicidao.actualizar(bicicleta);
        System.out.println("Actualizado: " + bicicleta.getFechaIngreso().toString());
        System.out.println("--Test borrar");
        bicidao.borrar(id_bici);
       
        if(bicidao.recuperar(id_bici) == null){
                System.out.println("Entidad Bicicleta ya no existe");
        }
        
        
        /**
         * Test Estacion
         */
        System.out.println("=====================================");
        System.out.println("Test Estacion");
        System.out.println("--Test persistencia");
        estado_estacion_test = new EstadoEstacion("operativa");
        estadoestaciondao.persistir(estado_estacion_test);
        ubicacion_esta = new Ubicacion();
        ubicaciondao.persistir(ubicacion_esta);
        estacion_test = new Estacion("constitucion",3,ubicacion_esta,estado_estacion_test);
        estaciondao.persistir(estacion_test);
        Long id_esta = estacion_test.getIdEstacion();
        System.out.println("Estacion persistio con id="+id_esta);
	    
        System.out.println("--Test colecciones Estacion<->Bicicleta* (Bidireccion)");
        //Lado Estacion, agregamos una bicicleta
        EstadoBicicleta est = new EstadoBicicleta();
        estadobicidao.persistir(est);
        Bicicleta bici_test = new Bicicleta(new Date(2015,11,25),est,estacion_test);
        bicidao.persistir(bici_test);
        estacion_test.agregarBicicleta(bici_test);
        estaciondao.actualizar(estacion_test);
        bicidao.actualizar(bici_test);
        Long id_est = bicidao.recuperar(bici_test.getIdBicicleta()).getEstacionActual().getIdEstacion();
        System.out.println(" - Lado Bicicleta, traemos su estacion actual con id="+id_est);
		List<Bicicleta> resultado = estaciondao.recuperarBicicletas(id_est); //Recibe un id de cliente
		System.out.println(" - Lado Estacion, recuperamos bicicletas, el primero con id="+resultado.get(0).getIdBicicleta());
		
        System.out.println("--Test recuperar");
        Estacion esta_recuperada = estaciondao.recuperar(id_esta);
        if (esta_recuperada != null){
                System.out.println(" - Estacion recuperada con id="+id_esta);
        }
        esta_recuperada = estaciondao.recuperar(new Long(99999)); //Esto no existe
        if (esta_recuperada == null){
                System.out.println(" - No existe Estacion con id 999999");
        }
        
        System.out.println("--Test actualizar");
        System.out.println("Antes de actualizar nombre :" + estacion_test.getNombre()); 
        estacion_test.setNombre("Est. Rodrigo");; //Actualizamos nombre.
        estacion_test = estaciondao.actualizar(estacion_test);
        System.out.println("Actualizado: " + estacion_test.getNombre());
        System.out.println("--Test borrar");
        estaciondao.borrar(id_esta);
       
        if(estaciondao.recuperar(id_esta) == null){
                System.out.println("Entidad Estacion ya no existe");
        }
		
        /**
         * Test HistorialBicicleta
         */
        
	    System.out.println("=====================================");
        System.out.println("Test HistorialBicicleta");
        System.out.println("--Test persistencia");
        date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        estado_bici = new EstadoBicicleta("Denunciada");
        estadobicidao.persistir(estado_bici);
        HistorialBicicleta historial = new HistorialBicicleta(time,estado_bici);
        historialdao.persistir(historial);
        Long id_historial= historial.getHistorialBicicletaId();
        System.out.println("HistorialBicicleta persistio con id="+id_historial);
        
        
        System.out.println("--Test recuperar");
        HistorialBicicleta historial_recuperado = historialdao.recuperar(id_historial);
        if (historial_recuperado != null){
                System.out.println(" - HistorialBicicleta recuperado con id="+id_historial);
        }
        historial_recuperado = historialdao.recuperar(new Long(99999)); //Esto no existe
        if (historial_recuperado == null){
                System.out.println(" - No existe HistorialBicicleta con id 999999");
        }
        System.out.println("--Test actualizar");
        EstadoBicicleta estado_prueba = new EstadoBicicleta("En reparacion");
        estadobicidao.persistir(estado_prueba);
        System.out.println("Antes de actualizar, estado historial:" + historial.getEstado().getDescripcion()); 
        historial.setEstado(estado_prueba); //Actualizamos descripcion.
        historial= historialdao.actualizar(historial);
        System.out.println("Actualizado estado historial: " + historial.getEstado().getDescripcion());
        System.out.println("--Test borrar");
        historialdao.borrar(id_historial);
       
        if(historialdao.recuperar(id_historial) == null){
                System.out.println("Entidad HistorialBicicleta ya no existe");
        }
     
   
        
	}
	
	/**
	 * Metodo para testear con alquileres persistidos.
	 * 
	 */
	private static Alquiler getAlquilerPersistido(UbicacionDAO ubicaciondao, EstadoEstacionDAO estadoestaciondao, BicicletaDAO bicidao,
			EstadoBicicletaDAO estadobicidao, AlquilerDAO alquilerdao,EstacionDAO estaciondao,Cliente cliente){
		Ubicacion ubicacion = new Ubicacion();
		ubicaciondao.persistir(ubicacion);
		EstadoEstacion estadoest = new EstadoEstacion();
		estadoestaciondao.persistir(estadoest);
		Estacion estacion = new Estacion("Pza Malvinas", 11, ubicacion, estadoest);
		estaciondao.persistir(estacion);
		EstadoBicicleta estadobici = new EstadoBicicleta("bueno");
		estadobicidao.persistir(estadobici);
		Bicicleta bicicleta = new Bicicleta(new Date(),estadobici,estacion);
		bicidao.persistir(bicicleta);
		Alquiler alquiler = new Alquiler(cliente, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()),
							estacion, bicicleta);
		alquilerdao.persistir(alquiler);
		return alquiler;
	}
}
