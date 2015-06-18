package com.ejemplo.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.ejemplo.vaadin.admusuarios.UsuarioForm;
import com.ejemplo.vaadin.entidades.Usuario;
import com.ejemplo.vaadin.servicios.ServicioUsuarios;
import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

/**
 *
 * @author siwenu
 * @version
 */
public class MyApplication extends Application implements ClickListener {
	private static final long serialVersionUID = 1539466153154709086L;
	
	@Autowired
    ServicioUsuarios servicioUsuarios;
	
	UsuarioForm formUser;
	Button btReset;
	Button btGuardar;

    @Override
    public void init() {
        Window mainWindow = new Window("Aplicacion");
        Label label = new Label("<h2>Ejemplo de Vaadin, Hibernate y Spring MVC/REST</h2>", Label.CONTENT_XHTML);
        mainWindow.addComponent(label);
        
        formUser = new UsuarioForm();
        
        btReset = formUser.getBtReset();
        btGuardar = formUser.getBtGuardar();
        
        btReset.addListener(this);
        btGuardar.addListener(this);
        
        mainWindow.addComponent(formUser);
        setMainWindow(mainWindow);
        
    }

    /**
     * Metodo que retorna el objeto injectado para que otras clases de la
     * aplicacion puedan usarlo
     *
     * @return El objeto ServicioUsuario injectado
     */
    public ServicioUsuarios getServicioUsuarios() {
        return servicioUsuarios;
    }

	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == btReset){
			
			formUser.getWindow().showNotification(new Notification("RESET", Notification.TYPE_TRAY_NOTIFICATION));
			
			formUser.getTxtNombre().setValue("");
			formUser.getTxtApellidos().setValue("");
			formUser.getTxtEmail().setValue("");
			formUser.getTxtClave().setValue("");

			
		}else if (event.getSource() == btGuardar){
			
			if (formUser.isValid()){
				formUser.getWindow().showNotification(new Notification("GUARDAR", Notification.TYPE_TRAY_NOTIFICATION));
				
				Usuario usuario = formUser.formularioEntidad();
				Integer respuesta = ((MyApplication)this).getServicioUsuarios().guardarUsuario(usuario);
				
				switch (respuesta.intValue()) {
				
				case 0:
					formUser.getWindow().showNotification(new Notification("Usuario guardado", Notification.TYPE_HUMANIZED_MESSAGE));
					break;
				case 1:
					formUser.getWindow().showNotification(new Notification("Usuario No Existe! LOSER", Notification.TYPE_HUMANIZED_MESSAGE));
					break;
				case 2:
					formUser.getWindow().showNotification(new Notification("No dispones de permisos! LOSER!", Notification.TYPE_HUMANIZED_MESSAGE));
					break;
				case 4:
					formUser.getWindow().showNotification(new Notification("Ya existe para este email! LOSER!", Notification.TYPE_HUMANIZED_MESSAGE));
					break;

				default:
					formUser.getWindow().showNotification(new Notification("ERROR! LOSER!", Notification.TYPE_HUMANIZED_MESSAGE));
					break;
				}
			}else{
				formUser.getWindow().showNotification(new Notification("Faltan Datos! LOSER!", Notification.TYPE_HUMANIZED_MESSAGE));
			}
			
			
			
		}
		
	}
}
