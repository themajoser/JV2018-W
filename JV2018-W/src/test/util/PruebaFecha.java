/** Proyecto: Juego de la vida.
 *  Prueba Junit5 de la clase Fecha según el modelo1.1
 *  @since: prototipo1.1
 *  @source: SesionUsuarioTest.java 
 *  @version: 2.1 - 2019/05/06
 *  @author: Grupo 1
 *  @author Alvaro
 *  @author Pedro
 *  @author Ivan
 */
package util;

public class PruebaFecha {

	public static void main(String[] args) {
		Fecha primeroAño = new Fecha(2019, 1, 1);

		Fecha hoy = new Fecha();
		
		System.out.println(hoy.difSegundos(primeroAño));
		System.out.println(hoy.difMinutos(primeroAño));
		System.out.println(hoy.difHoras(primeroAño));
		System.out.println(hoy.difDias(primeroAño));
		
		//hoy.addSegundos(1964711);
		System.out.println(hoy);
		
		
		System.out.println(hoy.toDate());
		System.out.println(hoy.toGregorianCalendar());
		
		System.out.println(hoy.getMarcaTiempoMilisegundos());
		System.out.println(hoy.toStringMarcaTiempo());
		
		Fecha ayer = new Fecha(hoy.addDias(-1));
		if (hoy.compareTo(ayer) < 0) {
			System.out.println("Hoy: "+ hoy);
			System.out.println("Hoy es mayor que ayer");
		}
		
		hoy = new Fecha();
		Fecha mañana = new Fecha(hoy.addDias(1));;
		if (hoy.compareTo(mañana) > 0){
			System.out.println("Hoy es menor ");
		}

	}

}
