/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Nif según el modelo1.3
 *  En esta versión se ha revisado el diseño OO previo.
 *  @since: prototipo1.0
 *  @source: NifTest.java 
 *  @version: 2.1 - 2019/05/06
 *  @author: Grupo 1
 *  @author: Ivan
 *  @author: Pedro
 *  @author: Alvaro
 */
package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Usuario.RolUsuario;
import util.Fecha;

public class NifTest {

	private static Nif nif1;
	private Nif nif2;
	
	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 * @throws AccesoUsrException 
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		try {
			nif1=new Nif("00000001R");
		} catch (ModeloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void borrarDatosPrueba() {	
		nif1= null;
	}

	@BeforeEach
	public void iniciarlizarDatosVariables() {	
		
			try {
				nif2 = new Nif();
			} catch (ModeloException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

	// Test's con DATOS VALIDOS
		@Test
		public void testNifConvencional() {	
			assertEquals(nif1.getTexto(), "00000001R");
		}
		
		@Test
		public void testNifDefecto() {
			assertNotNull(nif2.getTexto(),"00000000T");	
		}

		@Test
		public void testNifCopia() {
			assertNotSame(nif1, new Nif(nif1));
		}
		
		@Test
		public void testSetTexto() {
			try {			   
				nif2.setTexto("00000001R");
			} catch (ModeloException  | AssertionError| NullPointerException e) {
				fail("No debe llegar aquí...");
			}
			assertEquals(nif2.getTexto(), "00000001R");
		}
		
		@Test
		public void testSetTextoFormatoIncorrecto() {
			try {			   
				nif2.setTexto("00G00001R");
				fail("No debe llegar aquí...");
			} catch (ModeloException  | AssertionError| NullPointerException e) {
				assertEquals(nif2.getTexto(), "00000001R");
			}
		}
		
		@Test
		public void testNifAtributosIncorrectos() {

			Nif nif = null;
			try {
				nif = new Nif(" "); 
						
						fail();				// No debe llegar aquí.
			} 
			catch (ModeloException e) {
				//assertTrue(true);
			} 
		}
		
		@Test
		public void testSetTextoBlanco() {
			
			try {			   
				nif2.setTexto(" ");
				
				fail("No debe llegar aquí...");
			} catch (ModeloException  | AssertionError| NullPointerException e) {
				assertEquals(nif2.getTexto(), "00000001R");
			}
		}
		
		@Test
		public void testToString() {
			assertNotNull(nif1.toString());
		}

		@Test
		public void testSetNif() throws ModeloException {
			try {
				nif2.setTexto(null);
				fail("No debe llegar aquí...");
			} 
			catch (AssertionError e) { 
				assertTrue(nif2.getTexto() != null);
			}
		}
}