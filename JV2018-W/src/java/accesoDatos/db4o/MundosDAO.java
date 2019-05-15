/** 
 * Proyecto: Juego de la vida.
 * Resuelve todos los aspectos del almacenamiento del DTO Mundo utilizando
 * una base de datos orientadas a objetos (db4o).
 * Aplica el patron Singleton.
 * Colabora en el patrón Fachada.
 * @since: prototipo2.1
 * @source: MundosDAO.java 
 * @version: 2.1 - 2019/05/15
 * @author: Grupo 1
 * @author: Ivan
 * @author: Alvaro
 * @author: Pedro
 */

package accesoDatos.db4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import modelo.Identificable;
import modelo.ModeloException;
import modelo.Mundo;

public class MundosDAO implements OperacionesDAO {

	//Atributo dpara la instancia.
	private static MundosDAO instance;

	//Atributo de Base datos db4o.
	private ObjectContainer db;

	/**
	 * Método estático de acceso a la instancia única.
	 * Si no existe la crea invocando al constructor interno.
	 * Utiliza inicialización diferida.
	 * Sólo se crea una vez; instancia única -patrón singleton-
	 * @return instance
	 */

	public static MundosDAO getInstance() {
		if(instance == null) {
			instance = new MundosDAO();
		}
		return instance;
	}

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */

	private MundosDAO() {
		db = Conexion.getDB();
		if (obtener("AAA0T") == null && obtener("III1R") == null) {
			cargarPredeterminados();
		}
	}

	/**
	 * Método para generar de datos predeterminados de Mundo.
	 */

	private void cargarPredeterminados() {
		try {	
			Mundo mundoDemo = new Mundo();
			// En este array los 0 indican celdas con célula muerta y los 1 vivas
			byte[][] espacioDemo =  new byte[][]{ 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // 
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Planeador
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Flip-Flop
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Still Life
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }  //
			};
			mundoDemo.setEspacio(espacioDemo);
			mundoDemo.setTipoMundo(Mundo.FormaEspacio.ESFERICO);
			alta(mundoDemo);
		} 
		catch (DatosException | ModeloException e) {
			e.printStackTrace();
		}
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
	 * Metodo que busca un Mundo dado id.
	 * @param id - el id del Mundo a buscar. 
	 * @return - el Mundo encontrado; null si no encuentra. 
	 */	

	@Override
	public Mundo obtener(String id) {
		ObjectSet <Mundo> resultado;
		Query consulta = db.query();
		consulta.constrain(Mundo.class);
		consulta.descend("id").constrain(id);
		resultado = consulta.execute();
		if (resultado.size() > 0) {
			return resultado.get(0);
		}		
		return null;
	}

	/**
	 * Metodo que obtiene todas los mundos en una lista.
	 * @return - una consulta de BBDD que obtiene todas los mundos.
	 */

	@Override
	public List<Mundo> obtenerTodos() {
		Query consulta = db.query();
		consulta.constrain(Mundo.class);
		return consulta.execute();
	}

	/**
	 * Búsqueda de Mundo dado un objeto, reenvía al método que utiliza idUsr.
	 * @param obj - el Mundo a buscar.
	 * @return - el objeto Mundo encontrado o null si no encuentra.
	 */

	public Mundo obtener(Object obj) {
		return this.obtener(((Mundo) obj).getId());
	}	

	/**
	 * Metodo que busca todas el mundo de un usuario.
	 * @param idUsr - el identificador de usuario a buscar.
	 * @return - una consulta de BBDD que obtiene el mundo de un mismo usuario.
	 * @throws ModeloException 
	 */

	public List<Identificable> obtenerTodasMismoUsr(String idUsr) throws ModeloException {
		Query consulta = db.query();
		consulta.constrain(Mundo.class);
		consulta.descend("usr").descend("id").constrain(idUsr).equal();
		return consulta.execute();

	}

	/**
	 * Metodo que registra un nuevo Mundo.
	 * @param obj - Mundo a almacenar en BBDD.
	 * @throws DatosException - si ya existe.
	 */	

	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Mundo mundoNuevo = (Mundo) obj;
		if (obtener(mundoNuevo.getId()) == null) {
			db.store(mundoNuevo);  		
			return;
		} else {
			throw new DatosException("MundoDAO.alta:" + mundoNuevo.getId() +" Ya existe.");
		}

	}

	/**
	 * Metodo que elimina el objeto, dado el id utilizado para el almacenamiento.
	 * @param id - identificador del Mundo a eliminar.
	 * @return - el Mundo eliminado. 
	 * @throws DatosException - si no existe.
	 */

	@Override
	public Object baja(String id) throws DatosException {
		assert id != null;
		assert id != "";
		assert id != " ";
		Mundo mundo = obtener(id);
		if (mundo != null) {
			db.delete(mundo);
			return mundo;
		}
		throw new DatosException("MundosDAO.baja: "+ id + " no existe.");
	}

	/**
	 *  Metodo que actualiza datos de un Mundo reemplazando el almacenado por el recibido.
	 *	@param obj - Patron con las modificaciones.
	 *  @throws DatosException - si no existe.
	 */

	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		Mundo mundoActualizado = (Mundo) obj;
		Mundo mundoPrevio = (Mundo) obtener(mundoActualizado.getId());
		if (mundoPrevio != null) {
			try {
				mundoPrevio.setNombre(mundoActualizado.getNombre());
				mundoPrevio.setTipoMundo(mundoActualizado.getTipoMundo());
				mundoPrevio.setEspacio(mundoActualizado.getEspacio());
				mundoPrevio.setDistribucion(mundoActualizado.getDistribucion());
				mundoPrevio.setConstantes(mundoActualizado.getConstantes());
				mundoPrevio.establecerTamañoMundo();
				db.store(mundoPrevio);
				return;
			} catch (ModeloException e) {
				e.printStackTrace();
			}
		}
		throw new DatosException("MundosDAO.actualizar: "+ mundoActualizado.getId() + " no existe.");
	}

	/**
	 *  Metodo que obtiene el listado de todos los mundos almacenadas.
	 * @return el texto con el volcado de datos.
	 */

	@Override
	public String listarDatos() {
		StringBuilder listado = new StringBuilder();
		for (Mundo mundo: obtenerTodos()) {
			listado.append("\n" + mundo);
		}
		return listado.toString();
	}

	/**
	 *  Metodo que obtiene el listado de todos id de los objetos almacenados de Mundo.
	 * @return el texto con el volcado de id.
	 */

	@Override
	public String listarId() {
		StringBuilder listado = new StringBuilder();
		for (Mundo mundo: obtenerTodos()) {
			if (mundo != null) {
				listado.append("\n" + mundo.getId());
			}
		}
		return listado.toString();
	}

	/**
	 *  Metodo que elimina todos los mundos almacenadas y regenera la demo predeterminada.
	 */

	@Override
	public void borrarTodo() {
		for (Mundo mundo: obtenerTodos()) {
			db.delete(mundo);
		}
		cargarPredeterminados();

	}
}//class