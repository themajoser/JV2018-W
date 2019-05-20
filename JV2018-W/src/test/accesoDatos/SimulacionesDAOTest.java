/** 
 *  Proyecto: Juego de la vida.
 *  Clase JUnit 4 para pruebas del DAO de Simulaciones y la parte de la fachada de Datos correspondiente.
 *  @since: prototipo2.1
 *  @source: SimulacionesDAOTest.java 
 *  @version: 2.1 - 2019/05/17 
 *  @author: arm
 */

package accesoDatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import config.Configuracion;
import modelo.ModeloException;
import modelo.Mundo;
import modelo.Simulacion;
import modelo.Simulacion.EstadoSimulacion;
import modelo.Usuario;
import util.Fecha;

public class SimulacionesDAOTest {

	private static Datos fachada;
	private Simulacion simPrueba;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 * @throws DatosException 
	 */
	@BeforeAll
	public static void crearFachadaDatos() {
		fachada = new Datos();
	}
	
	/**
	 * Método que se ejecuta antes de cada @test.
	 * @throws ModeloException 
	 */
	@BeforeEach
	public void crearDatosPrueba() {
		try {
			simPrueba =  new Simulacion(new Usuario(), new Fecha(), new Mundo(), 
					Integer.parseInt(Configuracion.get().getProperty("simulacion.ciclosPredeterminados")), 
					EstadoSimulacion.PREPARADA);
		} 
		catch (ModeloException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que se ejecuta al terminar de cada @test.
	 */
	@AfterEach
	public void borraDatosPrueba() {
		fachada.borrarTodasSimulaciones();
		simPrueba = null;
	}

	@Test
	public void testObtenerSimulacion() { // !?
		try {
			fachada.altaSimulacion(simPrueba);
			// Busca la misma Simulacion almacenada.
			assertSame(simPrueba, fachada.obtenerSimulacion(simPrueba));
		} 
		catch (DatosException e) {
		}
	}

	@Test
	public void testAltaSimulacion() {
		try {
			// Simulacion nueva, que no existe.
			fachada.altaSimulacion(simPrueba);
			// Busca la misma Simulacion almacenada.
			assertSame(simPrueba, fachada.obtenerSimulacion(simPrueba));
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testBajaSImulacion() {
		try {
			fachada.altaSimulacion(simPrueba);
			// Baja de la misma Simulacion almacenada.
			assertSame(simPrueba, fachada.bajaSimulacion(simPrueba.getId()));
		} 
		catch (DatosException e) { 
			e.printStackTrace();
		}
	}

	@Test
	public void testActualizarSimulacion() {
		try {
			// Simulacion nueva, que no existe.
			fachada.altaSimulacion(simPrueba);
			simPrueba.setCiclos(12);
			fachada.actualizarSimulacion(simPrueba);
			assertEquals(fachada.obtenerSimulacion(simPrueba).getCiclos(), 12);
		} 
		catch (DatosException e) {
		}
	}

	@Test
	public void testToStringDatosSimulaciones() {
		assertNotNull(fachada.toStringDatosSimulaciones());
	}

} //class