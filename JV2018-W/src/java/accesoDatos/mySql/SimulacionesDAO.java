/**  
 * Proyecto: Juego de la vida.
 *  Resuelve todos los aspectos del almacenamiento del
 *  DTO Usuario utilizando base de datos mySQL.
 *  Colabora en el patron Fachada.
 *  @since: prototipo2.2
 *  @source: SimulacionesDAO.java  
 *  @version: 2.2 - 2019/06/05  
 *  @author: ajp
 *  @author: VictorJLucas
 */
 
package accesoDatos.mySql;
 
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
 
import java.sql.Connection;
 
import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import config.Configuracion;
import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.ModeloException;
import modelo.Mundo;
import modelo.Nif;
import modelo.Simulacion;
import modelo.Simulacion.EstadoSimulacion;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;
import util.Formato;
 
public class SimulacionesDAO implements OperacionesDAO {
    
    //1. Victor
 
    // Singleton
    private static SimulacionesDAO instance = null;
    private Connection db;
    private Statement stSimulaciones;
    private ResultSet rsSimulaciones;
    private DefaultTableModel tmSimulaciones;     // Tabla del resultado de la consulta.
    private ArrayList<Simulacion> bufferSimulaciones;     
 
    /**
     *  Método de acceso a la instancia única.
     *  Si no existe la crea invocando al constructor interno.
     *  Utiliza inicialización diferida de la instancia única.
     *  @return instancia
     */
    public static SimulacionesDAO getInstance() {
   	 if (instance == null) {
   		 instance = new SimulacionesDAO();
   	 }
   	 return instance;
    }
 
    /**
     * Constructor de uso interno.
     */
    private SimulacionesDAO() {
   		 inicializar();
    }
 
    /**
     * Inicializa el DAO, detecta si existen las tablas de datos capturando la
     * excepción SQLException.
     * @throws SQLException  
     */
    private void inicializar() {
   	 db = Conexion.getDB();
   	 try {
   		 crearTablaSimulaciones();
   		 stSimulaciones = db.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
   	 }  
   	 catch (SQLException e) {
   		 e.printStackTrace();
   	 }
   	 // Crea el tableModel y el buffer de objetos para usuarios.
   	 tmSimulaciones = new DefaultTableModel();
   	 bufferSimulaciones = new ArrayList<Simulacion>();  
    }
 
    /**
     * Crea la tabla de usuarios en la base de datos.
     * @throws SQLException  
     */
    private void crearTablaSimulaciones() throws SQLException {
   	 Statement s = db.createStatement();
   	 s.executeUpdate("CREATE TABLE IF NOT EXISTS simulaciones("  
   			 + "usr VARCHAR(45) NOT NULL,"
   			 + "fecha DATE,"
   			 + "mundo VARCHAR(45) NOT NULL,"
   			 + "ciclos CHAR(10) NOT NULL,"  
   			 + "estado VARCHAR(20) NOT NULL);"  
   			 );
    }
 
    // MÉTODOS DAO Usuarios
 
    /**
     * Obtiene el usuario buscado dado un objeto.  
     * Si no existe devuelve null.
     * @param Usuario a obtener.
     * @return (Usuario) buscado.  
     */    
    public Simulacion obtener(Object obj) {
   	 assert obj != null;
   	 return obtener(((Simulacion) obj).getId());
    }
     
   
    
} // class





