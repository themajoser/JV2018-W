/** 
 *  Proyecto: Juego de la vida.
 *  Clase JUnit 4 para pruebas del DAO de sesiones y la parte de la fachada de Datos correspondiente.
 *  @since: prototipo2.1
 *  @source: SesionesDAOTest.java 
 *  @version: 2.1 - 2019/05/09 
 *  @author: ajp
 */

package accesoDatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.ModeloException;
import modelo.SesionUsuario;
import modelo.SesionUsuario.EstadoSesion;
import modelo.Usuario;
import util.Fecha;

public class SesionesDAOTest {

	private static Datos fachada;
	private static Usuario usr;
	private SesionUsuario sesionPrueba;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 * @throws DatosException 
	 */
	@BeforeAll
	public static void crearFachadaDatos() {
		fachada = new Datos();
		try {
			usr = new Usuario();
		} 
		catch (ModeloException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Método que se ejecuta antes de cada @test.
	 */
	@BeforeEach
	public void crearDatosPrueba() {
		sesionPrueba = new SesionUsuario(usr, new Fecha(), EstadoSesion.EN_PREPARACION);
	}
	/**
	 * Método que se ejecuta al terminar de cada @test.
	 */
	@AfterEach
	public void borraDatosPrueba() {
		fachada.borrarTodasSesiones();
		sesionPrueba = null;
	}

	@Test
	public void testObtenerSesionId() {
		try {
			fachada.altaSesion(sesionPrueba);
			// Encuentra la misma sesión almacenada.
			assertSame(sesionPrueba, fachada.obtenerSesion(sesionPrueba.getId()));
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testObtenerSesion() {
		try {
			fachada.altaSesion(sesionPrueba);
			// Encuentra la misma sesión almacenada.
			assertSame(sesionPrueba, fachada.obtenerSesion(sesionPrueba));
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testAltaSesion() {
		try {
			fachada.altaSesion(sesionPrueba);
			assertSame(sesionPrueba, fachada.obtenerSesion(sesionPrueba));
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testBajaSesionUsuario() {
		try {
			fachada.altaSesion(sesionPrueba);
			// Baja de la misma sesion almacenada.
			assertSame(sesionPrueba, fachada.bajaSesion(sesionPrueba.getId()));
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testActualizarSesion() {
		try {
			fachada.altaSesion(sesionPrueba);
			sesionPrueba.setEstado(EstadoSesion.CERRADA);
			fachada.actualizarSesion(sesionPrueba);
			assertEquals(fachada.obtenerSesion(sesionPrueba).getEstado(), EstadoSesion.CERRADA);
		} 
		catch (DatosException e) { 
		}
	}

	@Test
	public void testToStringDatosSesiones() {
		try {
			fachada.altaSesion(sesionPrueba);
			assertNotNull(fachada.toStringDatosSesiones());
		}
		catch (DatosException e) {
		}
	}

} //class
