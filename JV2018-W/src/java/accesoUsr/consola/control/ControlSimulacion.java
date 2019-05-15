/** 
 * Proyecto: Juego de la vida.
 * Implementación de la clase ControlSimulacion.java 
 * @since: prototipo 2.1
 * @source: ControlSimulacion.java
 * @version: 2.1 - 2019.05.15
 * @author: Grupo 2:
 * @author: VictorJLucas
 */
package accesoUsr.consola.control;

import static org.junit.Assert.assertArrayEquals;
import accesoDatos.Datos;
import accesoUsr.OperacionesVista;
import accesoUsr.VistaSimulacion;
import accesoUsr.consola.vista.VistaPrincipal;
import config.Configuracion;
import modelo.ModeloException;
import modelo.Mundo;
import modelo.SesionUsuario;
import modelo.Simulacion;

/*
 * Constructor por defecto.
 */
public class ControlSimulacion {

	private final Integer CICLOS = Integer
			.parseInt(Configuracion.get().getProperty("simulacion.ciclosPredeterminados"));
	private VistaSimulacion vistaSimulacion;
	private Simulacion simulacion;
	private Mundo mundo;
	private Datos datos;

	/*
	 * Constructor adicional que recibe el objeto demo de la clase simulacion.
	 */
	public ControlSimulacion(Simulacion demo) {
		datos = new Datos();
		this.simulacion = demo;
		initControlSimulacion();
	}

	/*
	 * Constructor para inicializar la simulación.
	 */
	private void initControlSimulacion() {

		mundo = simulacion.getMundo();
		vistaSimulacion = new VistaSimulacion();
		arrancarSimulacion();
		vistaSimulacion.confirmar();

	}

}
