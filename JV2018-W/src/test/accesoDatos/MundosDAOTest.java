/** 
 *  Proyecto: Juego de la vida.
 *  Clase JUnit 4 para pruebas del DAO de mundos y la parte de la fachada de Datos correspondiente.
 *  @since: prototipo2.1
 *  @source: MundosDAOTest.java 
 *  @version: 2.1 - 2019/05/09 
 *  @author: ajp
 */

package accesoDatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Mundo;
import modelo.Posicion;

public class MundosDAOTest {

	private static Datos datos;
	private Mundo mundoPrueba;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void crearFachadaDatos() {
		datos = new Datos();
	}
	
	/**
	 * Método que se ejecuta antes de cada @test. 
	 */
	@BeforeEach
	public void crearDatosPrueba() {
		mundoPrueba = datos.obtenerMundo("Demo0");
	}
	
	/**
	 * Método que se ejecuta al terminar de cada @test.
	 */
	@AfterEach
	public void borraDatosPrueba() {
		datos.borrarTodosMundos();
		mundoPrueba = null;
	}

	@Test
	public void testObtenerMundoString() {
		try {
			datos.altaMundo(mundoPrueba);
			// Busca el mismo Mundo almacenado.
			assertSame(mundoPrueba, datos.obtenerMundo(mundoPrueba.getNombre()));
		} 
		catch (DatosException e) {
		}
	}

	@Test
	public void testObtenerMundoMundo() {
		try {
			datos.altaMundo(mundoPrueba);
			// Busca el mismo Mundo almacenado.
			assertSame(mundoPrueba, datos.obtenerMundo(mundoPrueba));
		} 
		catch (DatosException e) {
		}
	}

	@Test
	public void testAltaMundo() {
		try {
			// Mundo nuevo, que no existe.
			datos.altaMundo(mundoPrueba);
			// Busca el mismo Mundo almacenado.
			assertSame(mundoPrueba, datos.obtenerMundo(mundoPrueba));
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testBajaMundo() {
		try {
			datos.altaMundo(mundoPrueba);
			// Baja del mismo Mundo almacenado.
			assertSame(mundoPrueba, datos.bajaMundo(mundoPrueba.getNombre()));
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testActualizarMundo() {
		List<Posicion> distribucionPrueba = new ArrayList<Posicion>();
		Mundo mundoNuevo = null;
		try {
			mundoNuevo = new Mundo(mundoPrueba);
			distribucionPrueba.add(new Posicion(3,5));
			datos.altaMundo(mundoPrueba);
			mundoNuevo.setDistribucion(distribucionPrueba);
			datos.actualizarMundo(mundoNuevo);
			assertEquals(datos.obtenerMundo(mundoNuevo), mundoNuevo);
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testToStringDatosMundos() {
		assertNotNull(datos.toStringDatosMundos());
	}

} //class
