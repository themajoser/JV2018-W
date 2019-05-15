/**
 *		| Juego de la vida.
 *
 *		| Resuelve todos los aspectos relacionados con la presentación principal del programa con un menú. 
 *		  Colabora en el patrón MVC.
 *
 *		@since: prototipo2.1
 *  	@source: VistaPrincipal.java 
 *  	@version: 2.1 - 2019.05.06
 *  	@author: arm
 */

package accesoUsr;

import java.io.Console;
import java.util.Scanner;

public class VistaSimulacion implements OperacionesVista {


	private Console consola;
	private int opcionActiva;
	
	public VistaSimulacion() {
		consola = System.console();
		opcionActiva = 0;
	}

	@Override
	public void mostrarMensaje(String mensaje) {
		if (consola != null) {
			consola.writer().println(mensaje);
			return;
		}
		System.out.println(mensaje);
	}

	public void mostrarMundo() {

	}

}
