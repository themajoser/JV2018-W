/** 
 * Proyecto: Juego de la vida.
 * Resuelve todos los aspectos del almacenamiento del DTO Simulacion utilizando
 * una base de datos orientadas a objetos (db4o).
 * Aplica el patron Singleton.
 * Colabora en el patrón Fachada.
 * @since: prototipo2.1
 * @source: SimulacionesDAO.java 
 * @version: 2.1 - 2019/05/13 
 * @author: Grupo 2
 * @author: Fran Arce
 * @author: ajp
 */

package accesoDatos.db4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import config.Configuracion;
import modelo.ModeloException;
import modelo.Simulacion;
import modelo.Simulacion.EstadoSimulacion;
import modelo.Usuario;
import util.Fecha;

public class SimulacionesDAO implements OperacionesDAO {

	//Atributo dpara la instancia.
	private static SimulacionesDAO instance;

	//Atributo de Base datos db4o.
	private ObjectContainer db;

	/**
	 * Método estático de acceso a la instancia única.
	 * Si no existe la crea invocando al constructor interno.
	 * Utiliza inicialización diferida.
	 * Sólo se crea una vez; instancia única -patrón singleton-
	 * @return instance
	 */

	public static SimulacionesDAO getInstance() {
		if(instance == null) {
			instance = new SimulacionesDAO();
		}
		return instance;
	}

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */

	private SimulacionesDAO() {
		db = Conexion.getDB();
	}

	/**
	 * Metodo que cierra conexión de la base de datos db4o.
	 */

	@Override
	public void cerrar() {
		db.close();
	}

	//OPERACIONES DAO

	/**
	 * Metodo que busca una Simulacion dado idUsr y fecha.
	 * @param id - el idUsr+fecha de la Simulacion a buscar. 
	 * @return - la Simulacion encontrada; null si no encuentra. 
	 */	

	@Override
	public Simulacion obtener(String id) {
		ObjectSet<Simulacion> result = null;
		Query consulta = db.query();
		consulta.constrain(Simulacion.class);
		consulta.descend("getId()").constraints().equals(id);
		result = consulta.execute();
		if (result.size() > 0) {
			return result.get(0);
		}	
		return null;
	}

	/**
	 * Metodo que obtiene todas las simulaciones en una lista.
	 * @return - una consulta de BBDD que obtiene todas las simulaciones.
	 */

	@Override
	public List<Simulacion> obtenerTodos() {
		Query consulta = db.query();
		consulta.constrain(Simulacion.class);
		return consulta.execute();
	}

	/**
	 * Búsqueda de Simulacion dado un objeto, reenvía al método que utiliza idUsr.
	 * @param obj - la Simulacion a buscar.
	 * @return - el objeto Simulacion encontrado o null si no encuentra.
	 */

	public Simulacion obtener(Object obj) {
		return this.obtener(((Simulacion) obj).getId());
	}	

	/**
	 * Metodo que busca todas la simulaciones de un usuario.
	 * @param idUsr - el identificador de usuario a buscar.
	 * @return - una consulta de BBDD que obtiene la simulacion de un mismo usuario. 
	 */

	public List<Simulacion> obtenerTodasMismoUsr(String idUsr) {
		Query consulta = db.query();
		consulta.constrain(Simulacion.class);
		consulta.descend("usr").descend("id").constrain(idUsr).equal();
		return consulta.execute();

	}

	/**
	 * Metodo que registra una nueva Simulacion.
	 * @param obj - Simulación a almacenar en BBDD.
	 * @throws DatosException - si ya existe.
	 */	

	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Simulacion simulNuevo = (Simulacion) obj;
		if (obtener(simulNuevo.getId()) == null) {
			db.store(simulNuevo);  		
			return;
		} else {
			throw new DatosException("SimulacionesDAO.alta:" + simulNuevo.getId() +" Ya existe.");
		}
	}

	/**
	 * Metodo que elimina el objeto, dado el id utilizado para el almacenamiento.
	 * @param id - identificador de la Simulacion a eliminar.
	 * @return - la Simulacion eliminada. 
	 * @throws DatosException - si no existe.
	 */

	@Override
	public Simulacion baja(String id) throws DatosException {
		assert id != null;
		assert id != "";
		assert id != " ";
		Simulacion simul = obtener(id);
		if (simul != null) {
			db.delete(simul);
			return simul;
		}
		throw new DatosException("SimulacionesDAO.baja: "+ id + " no existe.");
	}

	/**
	 *  Metodo que actualiza datos de una Simulacion reemplazando el almacenado por el recibido.
	 *  No admitirá cambios en usr ni en la fecha.
	 *	@param obj - Patron con las modificaciones.
	 *  @throws DatosException - si no existe.
	 */

	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		Simulacion simulActualizado = (Simulacion) obj;
		Simulacion simulPrevio = (Simulacion) obtener(simulActualizado.getId());
		if (simulPrevio != null) {
			simulPrevio.setUsr(simulActualizado.getUsr());
			simulPrevio.setFecha(simulActualizado.getFecha());
			simulPrevio.setEstado(simulActualizado.getEstado());
			simulPrevio.setCiclos(simulActualizado.getCiclos());
			simulPrevio.setMundo(simulActualizado.getMundo());
			db.store(simulPrevio);
			return;
		}
		throw new DatosException("SimulacionesDAO.actualizar: "+ simulActualizado.getId() + " no existe.");
	}

	/**
	 *  Metodo que obtiene el listado de todos las simulaciones almacenadas.
	 * @return el texto con el volcado de datos.
	 */

	@Override
	public String listarDatos() {
		StringBuilder listado = new StringBuilder();
		for (Simulacion simul: obtenerTodos()) {
			listado.append("\n" + simul);
		}
		return listado.toString();
	}

	/**
	 *  Metodo que obtiene el listado de todos id de los objetos almacenados de Simulacion.
	 * @return el texto con el volcado de id.
	 */

	@Override
	public String listarId() {
		StringBuilder listado = new StringBuilder();
		for (Simulacion simul: obtenerTodos()) {
			if (simul != null) {
				listado.append("\n" + simul.getId());
			}
		}
		return listado.toString();
	}

	/**
	 *  Metodo que elimina todos las simulaciones almacenadas y regenera la demo predeterminada.
	 */

	@Override
	public void borrarTodo() {
		for (Simulacion simul: obtenerTodos()) {
			db.delete(simul);
		}
	}
	
}//class
