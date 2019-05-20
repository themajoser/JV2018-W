/** 
 * Proyecto: Juego de la vida.
 * Resuelve todos los aspectos del almacenamiento del DTO SesionUsuario.
 * Utiliza base de datos db4o.
 * Colabora en el patron Fachada.
 * @since: prototipo2.0
 * @source: SesionesDAO.java 
 * @version: 2.1 - 2019.05.03
 * @author: ajp
 */

package accesoDatos.db4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import modelo.SesionUsuario;

public class SesionesDAO implements OperacionesDAO {

	// Singleton.
	private static SesionesDAO instance;

	// Elemento de almacenamiento, base datos db4o.
	private ObjectContainer db;

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instance
	 */
	public static SesionesDAO getInstance() {
		if (instance == null) {
			instance = new SesionesDAO();
		}
		return instance;
	}

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private SesionesDAO() {
		db = Conexion.getDB();
	}

	/**
	 *  Cierra conexión.
	 */
	@Override
	public void cerrar() {
		db.close();
	}

	//OPERACIONES DAO
	/**
	 * Obtiene una SesionUsuario por idUsr + fecha.
	 * @param idSesion - la SesionUsuario a buscar.
	 * @return - la sesión encontrada, null si no existe.
	 */
	@Override
	public SesionUsuario obtener(String id) {
		ObjectSet<SesionUsuario> result = null;
		Query consulta = db.query();
		consulta.constrain(SesionUsuario.class);
		consulta.descend("getId()").constraints().equals(id);
		result = consulta.execute();
		if (result.size() > 0) {
			return result.get(0);
		}	
		return null;
	}

	/**
	 * Obtiene una SesionUsuario dado un objeto, reenvía al método que utiliza id de sesión.
	 * @param obj - la SesionUsuario a buscar.
	 * @return - la sesión encontrada, null si no existe.
	 */
	public SesionUsuario obtener(Object obj) {
		return this.obtener(((SesionUsuario) obj).getId());
	}	

	/**
	 * Obtiene todos los objetos SesionUsuario almacenados.
	 * @return - la List de todas las encontradas.
	 */
	@Override
	public List<SesionUsuario> obtenerTodos() {
		Query consulta = db.query();
		consulta.constrain(SesionUsuario.class);
		return consulta.execute();
	}

	/**
	 * Obtiene todas las sesiones por IdUsr de usuario.
	 * @param idUsr - el idUsr a buscar.
	 * @return - las sesiones encontradas.
	 */
	public List<SesionUsuario> obtenerTodasMismoUsr(String idUsr) {
		assert idUsr != null;
		Query consulta = db.query();
		consulta.constrain(SesionUsuario.class);
		consulta.descend("usr").descend("idUsr").constrain(idUsr);
		return consulta.execute();
	}

	/**
	 * Alta de una nueva SesionUsuario sin repeticiones según los campos idUsr + fecha. 
	 * @param obj - la SesionUsuario a almacenar.
	 */
	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		SesionUsuario sesion = (SesionUsuario) obj;
		db.store(sesion);
	}

	/**
	 * Elimina el objeto, dado el idUsr + fecha utilizado para el almacenamiento.
	 * @param idSesion - el idUsr + fecha de la SesionUsuario a eliminar.
	 * @return - la SesionUsuario eliminada.
	 * @throws DatosException - si no existe.
	 */
	@Override
	public SesionUsuario baja(String idSesion) throws DatosException {
		assert idSesion != null;
		assert idSesion != "";
		assert idSesion != " ";
		SesionUsuario sesion = obtener(idSesion);
		if (sesion != null) {
			db.delete(sesion);
			return sesion;
		}
		throw new DatosException("Baja: " + idSesion + " no existe.");
	}

	/**
	 *  Actualiza datos de una SesionUsuario reemplazando el almacenado por el recibido.
	 *	@param obj - SesionUsuario con las modificaciones.
	 *  @throws DatosException - si no existe.
	 */
	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		SesionUsuario sesionActualizada = (SesionUsuario) obj;
		SesionUsuario sesionPrevia = null;
		sesionPrevia = (SesionUsuario) obtener(sesionActualizada.getId());
		if (sesionPrevia != null) {
			sesionPrevia.setUsr(sesionActualizada.getUsr());
			sesionPrevia.setFecha(sesionActualizada.getFecha());
			sesionPrevia.setEstado(sesionActualizada.getEstado());
			db.store(sesionPrevia);
			return;
		}
		throw new DatosException("Actualizar: " + sesionActualizada.getId() + " no existe.");
	}

	/**
	 * Obtiene el listado de todos las sesiones almacenadas.
	 * @return el texto con el volcado de datos.
	 */
	@Override
	public String listarDatos() {
		StringBuilder listado = new StringBuilder();
		ObjectSet<SesionUsuario> result = null;
		Query consulta = db.query();
		consulta.constrain(SesionUsuario.class);	
		result = consulta.execute();	
		if (result.size() > 0) {
			for (SesionUsuario sesion: result) {
				listado.append("\n" + sesion);
			}
			return listado.toString();
		}
		return null;
	}

	/**
	 * Obtiene el listado de todos los identificadores de sesiones almacenadas.
	 * @return el texto con el volcado de datos.
	 */
	@Override
	public String listarId() {
		StringBuilder listado = new StringBuilder();
		for (SesionUsuario sesion: obtenerTodos()) {
			if (sesion != null) {
				listado.append("\n" + sesion.getId());
			}
		}
		return listado.toString();
	}

	/**
	 * Quita todos los objetos SesionUsuario de la base de datos.
	 */
	@Override
	public void borrarTodo() {
		// Elimina cada uno de los objetos obtenidos
		for (SesionUsuario sesion: obtenerTodos()) {
			db.delete(sesion);
		}	
	}

} //class
