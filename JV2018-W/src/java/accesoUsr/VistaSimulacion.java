/**
 *		| Juego de la vida.
 *
 *		| Resuelve todos los aspectos relacionados con la presentación principal del programa con un menú. 
 *		  Colabora en el patrón MVC.
 *
 *		@since: prototipo2.1
 *  	@source: VistaPrincipal.java 
 *  	@version: 2.1 - 2019.05.06
 *  	@author: Grupo2
 *  	@author: arm
 */

package accesoUsr;

import java.io.Console;
import java.util.Scanner;

public class VistaSimulacion implements OperacionesVista {
	

	private Console consola;
	
	
	public VistaSimulacion() {
		consola = System.console();
	}

	public void mostrarMundo() {

	}
	
	private void mostrarTextoSimple(String texto) {
	}

public void confirmarSimulacion() {
		mostrarTextoSimple("Simulacion Completada.");
	}
	
	@Override
	public void mostrarMensaje(String mensaje) {
		if (consola != null) {
			consola.writer().println(mensaje);
			return;
		}
		System.out.println(mensaje);
	}


}