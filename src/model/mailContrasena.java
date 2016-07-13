package model;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailContrasena {

	public mailContrasena() {
	}
	
	public void enviarMail(){
	  	//Clase que te permite enviar los datos  para envío de  correo
    	Properties  props= new Properties();                        
    	// Nombre del host de correo, es smtp.gmail.com      

    	props.setProperty("mail.smtp.host", "smtp.gmail.com");                          

    	// TLS establecerlo disponible      
    	props.setProperty("mail.smtp.starttls.enable", "true");                      

    	// Puerto de gmail para envio de correos
    	//este es el que usa google            
    	props.setProperty("mail.smtp.port","25");    


    	// Cuenta de correo en gmail una cuenta que
    	//tengas  que va a ser la que se autentique
    	//con el servidor.      
    	props.setProperty("mail.smtp.user","jbicilaplata@gmail.com");                        

    	// Si requiere o no usuario y password para    
    	//conectarse.  
    	props.setProperty("mail.smtp.auth", "true");  


    	//props.put("mail.smtp.auth", "true");                      

    	// Se obtiene una sesión con las propiedades
    	//anteriormente definidas      
    	Session sesion =Session.getDefaultInstance(props,null);

    	System.out.println("INICIO VERIFICAR SESSION");
    	sesion.setDebug(true);      
    	System.out.println("FIN VERIFICAR SESSION");                      
    	
    	
    	// Se crea un mensaje vacío      
    	Message mensaje = new MimeMessage(sesion);      

    	try {                
    		
    		//Subject mensaje.setSubject("Subject");          

    		System.out.println(mensaje.getSubject());              
    		// Cuerpo del mensaje      
    		mensaje.setText("Este mail es enviado desde JBici");      
    		System.out.println("Mail enviado desde JBici");                        
    		String dt=" jbici-laplata@gmail.com ";           
    		mensaje.setSubject("Bienvenido a JBici !");
    		Address address = new InternetAddress(dt,"JBici La Plata");      
    		mensaje.setFrom(address);                      
    		//La direccion de la persona a quien le
    		//van a enviar el correo.      
    		Address address2 =
    				new InternetAddress("santiagopravisani@gmail.com",false);
    		mensaje.addRecipient(Message.RecipientType.TO,address2);

    		// Se envía el mensaje      
    		Transport t = sesion.getTransport("smtp");
    		t.connect("smtp.gmail.com", "jbicilaplata@gmail.com", "santinicoteam");        

    		t.sendMessage(mensaje,mensaje.getAllRecipients());      
    		t.close();

    		System.out.println("Termine bien");                
    	}
    	catch (MessagingException e)
    	{      System.err.println(e.getMessage());        
    	System.out.println("Estoy en la excepcion1");          
    	}        
    	catch (UnsupportedEncodingException ex) {                
    		ex.printStackTrace();              
    		System.out.println("Estoy en la excepcion2");  
    	}                    
		
		System.out.println("sin excep"); 
		
	}
}
