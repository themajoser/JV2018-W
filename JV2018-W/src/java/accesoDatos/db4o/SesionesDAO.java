/** 
 * Proyecto: Juego de la vida.
 * Resuelve todos los aspectos del almacenamiento del DTO SesionUsuario.
 * Utiliza base de datos db4o.
 * Colabora en el patron Fachada.
 * @since: prototipo2.0
 * @source: SesionesDAO.java 
 * @version: 2.1 - 2019.05.20
 * @author: ajp
 */

package accesoDatos.db4o;

import java.util.List;

import com.db4o.ObjectContainer;

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

	@Override
	public SesionUsuario obtener(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alta(Object obj) throws DatosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SesionUsuario baja(String id) throws DatosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Object obj) throws DatosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String listarDatos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarTodo() {
		// TODO Auto-generated method stub
		
	}

	public List<SesionUsuario> obtenerTodasMismoUsr(String idUsr) {
		// TODO Auto-generated method stub
		return null;
	}

}
