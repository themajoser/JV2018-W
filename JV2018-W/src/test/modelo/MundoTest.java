package modelo;

/** Proyecto: Juego de la vida.
 *  Prueba Junit5 de la clase Mundo según el modelo2.1
 *  @since: prototipo2.1
 * @version: 2.1 - 2019.05.06
 * @author: Grupo 1
 * @author: Ivan 
 * @author: Pedro 
 * @author: Alvaro 
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import config.Configuracion;
import modelo.Mundo.FormaEspacio;

public class MundoTest {

	private static Mundo mundo1;
	private Mundo mundo2;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 * @throws ModeloException 
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() throws ModeloException {
		// Objetos no modicados en las pruebas.
		try {
			mundo1 = new Mundo("Mundo", 
					new byte[10][10], 
					new LinkedList<Posicion>(), 
					new HashMap<String, int[]>(), 
					Mundo.FormaEspacio.PLANO);
		} 
		catch (ModeloException e) {
		}
	}
	

	/**
	 * Método que se ejecuta una sola vez al final del conjunto pruebas.
	 * No es necesario en este caso.
	 */
	@AfterAll
	public static void limpiarDatosFijos() {
		mundo1 = null;
	}

	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void iniciarlizarDatosVariables() {	
		try {
			mundo2 = new Mundo();
		} 
		catch (ModeloException e) {
		}
	}

	/**
	 * Método que se ejecuta después de cada pruebas.
	 */
	@AfterEach
	public void borrarDatosPrueba() {	
		mundo2 = null;
	}

	// Test's con DATOS VALIDOS
	@Test
	public void testMundoConvencional() {	
			assertEquals(mundo1.getNombre(), "Mundo");
			assertEquals(mundo1.getTamañoMundo(), 0);
			assertEquals(mundo1.getTipoMundo(), FormaEspacio.PLANO);
			assertEquals(mundo1.getId(), "Mundo");		
	}

	@Test
	public void testMundoDefecto() {
		assertEquals(mundo2.getNombre(), "Demo0");
		assertEquals(mundo2.getTamañoMundo(), 0);
		assertEquals(mundo2.getTipoMundo(), FormaEspacio.PLANO);
		assertEquals(mundo2.getId(), "Demo0");
	}

	@Test
	public void testMundoCopia() {
		Mundo mundo = new Mundo(mundo1);
		assertNotSame(mundo, mundo1);
		assertNotSame(mundo.getNombre(), mundo1.getNombre());
		assertNotSame(mundo.getEspacio(), mundo1.getEspacio());
		assertNotSame(mundo.getDistribucion(),mundo1.getDistribucion());
		assertNotSame(mundo.getConstantes(), mundo1.getConstantes());
		assertEquals(mundo.getTipoMundo(), mundo1.getTipoMundo());
		assertNotSame(mundo.getId(), mundo1.getId());
	}

	@Test
	public void testSetNombre() {
		try {
			mundo2.setNombre("Mundo");
		}
		catch (ModeloException e) {
		}
		assertEquals(mundo2.getNombre(), "Mundo");
	}

	@Test
	public void testSetConstantes() {
		Map<String, int[]> constantes = new HashMap<String, int[]>();
		constantes.put("mundo", new int[1]);
		mundo2.setConstantes(constantes);
		assertEquals(mundo2.getConstantes(), constantes);
	}
	
	@Test
	public void testSetFormaEspacio() {
		mundo2.setTipoMundo(Mundo.FormaEspacio.PLANO);
		assertEquals(mundo2.getTipoMundo(), FormaEspacio.PLANO);
	}

	@Test
	public void testToStringEstadoMundo() {
		assertNotNull(mundo1.toStringEstadoMundo());
	}

	// Test's CON DATOS NO VALIDOS

	@Test
	public void testSetConstantesNull() {
		try {
			mundo2.setConstantes(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(mundo2.getConstantes() != null);
		}
	}
	
	@Test
	public void testSetNombreNull() {
		try {
			mundo2.setNombre(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | ModeloException e) { 
			assertTrue(mundo2.getNombre() != null);
		}
	}
	
	@Test
	public void testSetTipoMundoNull() {
		try {
			mundo2.setTipoMundo(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(mundo2.getTipoMundo() != null);
		}
	}
	
	@Test
	public void testSetNombreBlanco() {
		try {
			mundo2.setNombre("  ");
			fail("No debe llegar aquí...");
		} 
		catch (ModeloException e) {
			assertTrue(mundo2.getNombre() != null);
		}
	}
	
} // class


