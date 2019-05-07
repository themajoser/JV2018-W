/** Proyecto: Juego de la vida.
 *  Prueba Junit5 de la clase Correo.java según el modelo1.1
 *  @since: prototipo1.0
 *  @source: SesionUsuarioTest.java 
 *  @version: 2.1 - 2019/05/06
 *  @author: Grupo 2:
 *  @author: VictorJLucas
 */
package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CorreoTest {

	private static Correo Correo1;
	private Correo Correo2;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {

		try {
			Correo1 = new Correo("nombre@gmail.com");

		} catch (ModeloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Método que se ejecuta una sola vez al final del conjunto pruebas. No es
	 * necesario en este caso.
	 */
	@AfterAll
	public static void limpiarDatosFijos() {
		Correo1 = null;
	}

	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void inicializarDatosVariables() {
		try {
			Correo2 = new Correo();
		} catch (ModeloException e) {
		}
	}

	/**
	 * Método que se ejecuta después de cada pruebas.
	 */
	@AfterEach
	public void borrarDatosPrueba() {
		Correo2 = null;
	}

	// Test's con DATOS VALIDOS

	@Test
	public void testCorreoConvencional() {
		assertEquals(Correo1.getTexto(), "nombre@gmail.com");
	}

	@Test
	public void testCorreoDefecto() {
		assertNotNull(Correo2.getTexto(), "correo@correo.es");

	}

	@Test
	public void testCorreoCopia() {
		assertNotSame(Correo1, new Correo(Correo1));
	}

	@Test
	public void testSetTexto() {
		try {
			Correo2.setTexto("nombre@gmail.com");
			assertEquals(Correo2.getTexto(), "nombre@gmail.com");
		} catch (ModeloException | AssertionError | NullPointerException e) {
			fail("No debe llegar aquí...");
		}

	}

	@Test
	public void testSetTextoFormatoIncorrecto() {
		try {
			Correo2.setTexto("nombregmail.com");
			fail("No debe llegar aquí...");
			
		} catch (ModeloException | AssertionError | NullPointerException e) {
			assertEquals(Correo2.getTexto(), "invitado@gmail.com");
		}
		
	}

	@Test
	public void testSetTextoBlanco() {

		try {
			Correo2.setTexto("  ");
			fail("No debe llegar aquí...");
			
		} catch (ModeloException | AssertionError | NullPointerException e) {
			assertEquals(Correo2.getTexto(), "invitado@gmail.com");
		}

	}

	@Test
	public void testToString() {
		assertNotNull(Correo1.toString());
	}

	@Test
	public void testSetCorreo() throws ModeloException {
		try {
			Correo2.setTexto(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) {
			assertTrue(Correo2.getTexto() != null);
		}
	}

}
