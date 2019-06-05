/** 
 * Proyecto: Juego de la vida.
 * Implementación de la clase PresenterEjecucionSimulacion.java 
 * @since: prototipo 2.1
 * @source: PresenterEjecucionSimulacion.java
 * @version: 2.2 - 2019.06.05
 * @author: Grupo 2:
 * @author: VictorJLucas
 * @author: themajoser
 */
package accesoUsr.swing.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import accesoDatos.Datos;
import accesoUsr.swing.vista.VistaEjecucionSimulacion;
import config.Configuracion;
import modelo.Mundo;
import modelo.Simulacion;

public class PresenterEjecucionSimulacion implements ActionListener, WindowListener {

	private  Integer ciclos;
	private VistaEjecucionSimulacion vistaEjecucionSimulacion;
	private Simulacion simulacion;
	private Mundo mundo;
	private Datos datos;
	
	private PresenterPrincipal presenterPrincipal ;

	public PresenterEjecucionSimulacion(PresenterPrincipal controlPrincipal) {
		this.presenterPrincipal=controlPrincipal;
		this.initControlEjecucionSimulacion();
	}
		

	public PresenterEjecucionSimulacion(Simulacion demo) {
		this.simulacion=demo;
		this.presenterPrincipal=presenterPrincipal;
		this.ciclos = Integer.parseInt(Configuracion.get().getProperty("simulacion.ciclosPredeterminados"));
		this.datos=new Datos();
		this.mundo=demo.getMundo();
		vistaEjecucionSimulacion= new VistaEjecucionSimulacion();
		arrancarSimulacion();
		this.configListener();
		this.vistaEjecucionSimulacion.pack();
		this.vistaEjecucionSimulacion.setVisible(true);		
	}

	private void initControlEjecucionSimulacion() {
		
		this.ciclos = Integer.parseInt(Configuracion.get().getProperty("simulacion.ciclosPredeterminados"));
		this.datos=new Datos();
		this.mundo=simulacion.getMundo();
		vistaEjecucionSimulacion= new VistaEjecucionSimulacion();
		arrancarSimulacion();
		this.configListener();
		this.vistaEjecucionSimulacion.pack();
		this.vistaEjecucionSimulacion.setVisible(true);		
	}


	
	/*
	 * Constructor para inicializar la simulación.
	 */
	private void initControlSimulacion() {

		vistaEjecucionSimulacion = new VistaEjecucionSimulacion();
		arrancarSimulacion();
		//vistaEjecucionSimulacion.confirmar();

	}
	/**
	 * Reproduce una simulacion
	 */
	private void arrancarSimulacion() {
		int generacion = 0;
		do {
			//vistaEjecucionSimulacion.mostrarMensaje("\nGeneración: " + generacion);
			simulacion.getMundo().actualizarMundo();
			generacion++;
			vistaEjecucionSimulacion.mostrarSimulacion(this);
		} while (generacion < ciclos);
	}
	/*
	 * Método que devuelve el número de ciclos
	 */
	public Integer getCICLOS() {
		return this.ciclos;
	}

	/*
	 * Método que devuelve el objeto vistaSimulacion
	 */
	public VistaEjecucionSimulacion getVistaEjecucionSimulacion() {
		return this.vistaEjecucionSimulacion;
	}

	/*
	 * Método que devuelve el objeto vistaSimulacion
	 */
	public Simulacion getSimulacion() {
		return this.simulacion;
	}

	/*
	 * Método que devuelve el objeto mundo
	 */
	public Mundo getMundo() {
		return this.mundo;
	}

	/*
	 * Método que devuelve el objeto datos
	 */
	public Datos getDatos() {
		return this.datos;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	private void configListener() {
		// Hay que escuchar todos los componentes que tengan interacción de la vista
		// registrándoles la clase control que los escucha.
		this.vistaEjecucionSimulacion.addWindowListener(this);
		this.vistaEjecucionSimulacion.getBotonFinalizar().addActionListener(this);
		


		//...
	}
	//Manejador de eventos de componentes... ActionListener
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.vistaEjecucionSimulacion.getBotonFinalizar()) {
				//Cierra y borra de la memoria.
				vistaEjecucionSimulacion.dispose();
			}
			
			

		}
		public boolean mensajeConfirmacion(String mensaje) {
			return JOptionPane.showConfirmDialog(null, mensaje,
					"JV-2018", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
		}
		// Salida segura única de la aplicación
		private void salir() {
			// Confirmar cierre
				this.datos.cerrar();
			
		}



}