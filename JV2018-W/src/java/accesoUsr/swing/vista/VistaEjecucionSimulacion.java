/** Proyecto: Juego de la vida.
 *  Resuelve todos los aspectos relacionados con la presentación de la 
 *  ejecucion de la simulacion en interfaz grafica.
 *  @since: prototipo2.1
 *  @source: VistaEjecucionSimulacion.java 
 *  @version: 2.2 - 2019.06.07
 *  @author: Grupo 2
 *  @author: arm - Antonio Ramírez Márquez
 *  @author: Fran Arce
 */

package accesoUsr.swing.vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import accesoUsr.swing.presenter.PresenterEjecucionSimulacion;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;

public class VistaEjecucionSimulacion extends JFrame { //La clase se llama VistaEjecucionSimulacion.
	
	private static final long serialVersionUID = 1L;

	/**
	 * Lista de atributos que definen los componentes de la ventana.
	 */
	
	private JPanel panelControles;
	private JPanel panelSimulacion;
	private JTextArea textAreaVisualizacion;
	private JToolBar toolBar;
	private JPanel panelEstadoFin;
	private JScrollPane panelVisualizacion;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JPanel panelBoton;
	private JButton botonFinalizar;
	private JSeparator separadorSur;
	private JPanel panelIdMundo;
	private JPanel panelIdSimulacion;
	private JLabel idMundo;
	private JLabel idSimulacion;
	private JPanel panelEstado;
	private JLabel estado;
	int contadorGeneracion = 1;
	
	/**
	 * Constructor de la clase VistaEjecucionSimulacion.
	 * Diseña la ventana con una serie de texto que contiene informacion del mundo,
	 * un conjunto de paneles y botones, y una area de visualizacion de texto para mostrar
	 * el mundo.
	 */
	
	public VistaEjecucionSimulacion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Ejecución Simulación JV");
		getContentPane().setLayout(new BorderLayout(0, 0));

		Dimension sizePantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(sizePantalla.width / 2, sizePantalla.height / 2));
		setPreferredSize(this.getSize());
		
		panelControles = new JPanel();
		getContentPane().add(panelControles, BorderLayout.NORTH);
		panelControles.setLayout(new BorderLayout(0, 0));
		
		panelIdMundo = new JPanel();
		panelControles.add(panelIdMundo, BorderLayout.EAST);
		
		idMundo = new JLabel("");
		panelIdMundo.add(idMundo);
		
		panelIdSimulacion = new JPanel();
		panelControles.add(panelIdSimulacion, BorderLayout.WEST);
		
		idSimulacion = new JLabel("");
		panelIdSimulacion.add(idSimulacion);


		panelSimulacion = new JPanel();
		getContentPane().add(panelSimulacion, BorderLayout.CENTER);
		panelSimulacion.setLayout(new BorderLayout(0, 0));	

		textAreaVisualizacion = new JTextArea();
		textAreaVisualizacion.setEditable(false);
		textAreaVisualizacion.setRows(50);
		textAreaVisualizacion.setColumns(50);
		textAreaVisualizacion.setTabSize(4);
		textAreaVisualizacion.setFont(new Font("Courier New", Font.PLAIN, 16));
		textAreaVisualizacion.setBackground(Color.WHITE);
		
		panelVisualizacion = new JScrollPane(textAreaVisualizacion);
		panelVisualizacion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panelSimulacion.add(panelVisualizacion, BorderLayout.NORTH);

		toolBar = new JToolBar();
		panelSimulacion.add(toolBar, BorderLayout.SOUTH);

		panelEstadoFin = new JPanel();
		getContentPane().add(panelEstadoFin, BorderLayout.SOUTH);
		panelEstadoFin.setLayout(new BorderLayout(0, 0));
		
		separadorSur = new JSeparator();
		panelEstadoFin.add(separadorSur, BorderLayout.NORTH);
		
		panelBoton = new JPanel();
		panelEstadoFin.add(panelBoton, BorderLayout.EAST);
		panelBoton.setLayout(new BorderLayout(0, 0));
		
		botonFinalizar = new JButton("Finalizar");
		panelBoton.add(botonFinalizar, BorderLayout.NORTH);
		
		panelEstado = new JPanel();
		panelEstadoFin.add(panelEstado, BorderLayout.WEST);
		
		estado = new JLabel("");
		panelEstado.add(estado);
		
	}

	/**
	 * Metodo get que devuelve el atributo del area de texto.
	 * @return textAreaVisualizacion
	 */
	
	public JTextArea getTextAreaVisualizacion() {
		return textAreaVisualizacion;
	}
	
	/**
	 * Metodo get que devuelve el atributo de boton Finalizar.
	 * @return botonFinalizar
	 */
	
	public JButton getBotonFinalizar() {
		return botonFinalizar;
	}
	
	/**
     * Metodo que muestra la forma de un mundo para la simulacion. 
     * @param presenterEjecucionSimulacion
     */

	public void mostrarSimulacion(PresenterEjecucionSimulacion presenterEjecucionSimulacion) {
        
        byte[][] espacio = presenterEjecucionSimulacion.getMundo().getEspacio();
        
        String idMundo = presenterEjecucionSimulacion.getMundo().getId();
        String idSimulacion = presenterEjecucionSimulacion.getSimulacion().getId();
        this.idMundo.setText(idMundo);
        this.idSimulacion.setText(idSimulacion);
        this.estado.setText("Simulacion por defecto 'DEMO'");
        
        for(int i=0 ; i< espacio.length ; i++) {
            for(int j=0 ; j< espacio.length ; j++) {
                this.textAreaVisualizacion.append((espacio[i][j] == 1) ? "|0" : "| ");
            }
            this.textAreaVisualizacion.append("| \n");
            
        }
        
        this.textAreaVisualizacion.append("\n" + "Generacion " + contadorGeneracion + "\n\n");
        contadorGeneracion++;
    }
	
}