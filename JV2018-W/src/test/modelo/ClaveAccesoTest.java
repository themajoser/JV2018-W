/** Proyecto: Juego de la vida.
 *  Implementa el concepto de claveAccesoTest según el modelo1.3
 *  En esta versión se ha revisado el diseño OO previo.
 *  @since: prototipo2.0
 *  @source: ClaveAccesoTest.java 
 *  @version: 2.1 - 2019/05/06
 *  @author: GRUPO 2:
 *  @author: Jorge Orenes Rubio
 */
package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class ClaveAccesoTest {

	
	private static ClaveAcceso ClaveAcceso1;
	private ClaveAcceso ClaveAcceso2;
	
	
	
	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 * @throws AccesoUsrException 
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		try {
			ClaveAcceso1=new ClaveAcceso("Miau#1");
		} catch (ModeloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void borrarDatosPrueba() {	
		ClaveAcceso1= null;
	}

	@BeforeEach
	public void iniciarlizarDatosVariables() {	
		
			try {
				ClaveAcceso2 = new ClaveAcceso();
			} catch (ModeloException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	

	// Test's con DATOS VALIDOS
		@Test
		public void testClaveAccesoConvencional() {	
			assertEquals(ClaveAcceso1.getTexto(),"Pmezd9");
		}
		@Test
		public void testClaveAccesoDefecto() {
			assertNotNull(ClaveAcceso2.getTexto(),"Pmezd8");
			
		}

		@Test
		public void testClaveAccesoCopia() {
			assertNotSame(ClaveAcceso1, new ClaveAcceso(ClaveAcceso1));
		}
		@Test
		public void testSetTexto() {
			try {			   
				ClaveAcceso2.setTexto("Miau#1");
			} catch (ModeloException  | AssertionError| NullPointerException e) {
				fail("No debe llegar aquí...");
			}
			assertEquals(ClaveAcceso2.getTexto(), "Pmezd9");
		}
		
		@Test
		public void testSetTextoFormatoIncorrecto() {
			try {			   
				ClaveAcceso2.setTexto("jia1");
			} catch (ModeloException  | AssertionError| NullPointerException e) {
				assertEquals(ClaveAcceso2.getTexto(), "Pmezd8");
			}
			
		}
		@Test
		public void testSetTextoBlanco() {
			
			
			try {			   
				ClaveAcceso2.setTexto(" ");
			} catch (ModeloException  | AssertionError| NullPointerException e) {
				assertEquals(ClaveAcceso2.getTexto(), "Pmezd8");
				
			}
			
			
		}
		@Test
		public void testToString() {
			assertNotNull(ClaveAcceso1.toString());
		}

		@Test
		public void testSetClaveAcceso() throws ModeloException {
			try {
				ClaveAcceso2.setTexto(null);
				fail("No debe llegar aquí...");
			} 
			catch (AssertionError e) { 
				assertTrue(ClaveAcceso2.getTexto() != null);
			}
		}



}