/** 
 * Proyecto: Juego de la vida.
 * Implementación de la clase ControlSimulacion.java 
 * @since: prototipo 2.1
 * @source: ControlSimulacion.java
 * @version: 2.1 - 2019.05.15
 * @author: Grupo 2:
 * @author: VictorJLucas
 * @author: themajoser
 */
package accesoUsr.swing.control;

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

public class ControlEjecucionSimulacion{

	private  Integer ciclos;
	private VistaEjecucionSimulacion vistaEjecucionSimulacion;
	private Simulacion simulacion;
	private Mundo mundo;
	private Datos datos;
	
	private ControlPrincipal controlPrincipal;

	public ControlEjecucionSimulacion(ControlPrincipal controlPrincipal) {
		this.controlPrincipal=controlPrincipal;
		this.initControlEjecucionSimulacion();
	}
		

	public ControlEjecucionSimulacion(Simulacion demo) {
		this.simulacion=demo;
		this.controlPrincipal=controlPrincipal;
		this.ciclos = Integer.parseInt(Configuracion.get().getProperty("simulacion.ciclosPredeterminados"));
		this.datos=new Datos();
		this.mundo=demo.getMundo();
		vistaEjecucionSimulacion= new VistaEjecucionSimulacion();
		arrancarSimulacion();
		
		this.vistaEjecucionSimulacion.pack();
		this.vistaEjecucionSimulacion.setVisible(true);		
	}

	private void initControlEjecucionSimulacion() {
		
		this.ciclos = Integer.parseInt(Configuracion.get().getProperty("simulacion.ciclosPredeterminados"));
		this.datos=new Datos();
		this.mundo=simulacion.getMundo();
		vistaEjecucionSimulacion= new VistaEjecucionSimulacion();
		arrancarSimulacion();
		
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

		


	



}