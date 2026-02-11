package negocio.eventos;

import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;

public class SAEventoImp implements SAEvento {
	
	private static final int MIN_AFORO = 1;
	private static final int MAX_AFORO = 200;
	private static final int MIN_LONGITUD_NOMBRE = 1;
	private static final int MAX_LONGTIUD_NOMBRE = 50;
	private static final String ERROR_AFORO_MSG = "No se ha podido crear el evento porque el aforo está fuera del rango establecido [" + MIN_AFORO + "," + MAX_AFORO+ "].\nVuelve a intentarlo.";
	private static final String ERROR_TIPO_MSG = "No se ha podido crear el evento porque el tipo no es correcto o es vacío.\nVuelve a intentarlo.";
	private static final String ERROR_NOMBRE_MSG1 = "No se ha podido crear el evento porque el nombre es nulo o vacío.\nVuelve a intentarlo.";
	private static final String ERROR_NOMBRE_MSG2 = "No se ha podido crear el evento porque la longitud del nombre está fuera del rango establecido [" + MIN_LONGITUD_NOMBRE + "," + MAX_LONGTIUD_NOMBRE+ "] caracteres.\nVuelve a intentarlo.";
	private static final String ERROR_ACTIVO_MSG = "No se ha podido crear el evento porque no se puede crear un objeto inactivo.\nVuelve a intentarlo.";
	private static final String ERROR_ELIMINAR_MSG1 = "No se ha podido eliminar el evento porque no se ha encontrado ningún evento con id ";
	private static final String ERROR_ELIMINAR_MSG2 = "No se ha podido eliminar el evento porque existe algún pase activo asociado al mismo.";
	private static final String ERROR_MODIFICAR_MSG1 = "No se ha podido modificar el evento porque no se ha encontrado ningún evento con id ";
	private static final String ERROR_MODIFICAR_MSG2 = "No se ha podido modificar el evento porque la longitud del nombre está fuera del rango establecido [" + MIN_LONGITUD_NOMBRE + "," + MAX_LONGTIUD_NOMBRE+ "] caracteres.\nVuelve a intentarlo.";
	private static final String ERROR_MODIFICAR_MSG3 = "No se ha podido modificar el evento porque el aforo está fuera del rango establecido [" + MIN_AFORO + "," + MAX_AFORO+ "].\nVuelve a intentarlo.";
	private static final String ERROR_MODIFICAR_MSG4 = "No se ha podido modificar (dar de baja) el evento porque existe algún pase activo asociado al mismo.";
	
	
	//Para evitar enum incorrecto para tipo, se ha escogido JCOMBOBOX con opciones limitadas; error solo si no se escoge ninguna de las opciones
	@Override
	public int crearEvento(TEvento t) throws IllegalArgumentException { 
		//Entradas incorrectas
		if(t.getAforo() < MIN_AFORO || t.getAforo() > MAX_AFORO) throw new IllegalArgumentException(ERROR_AFORO_MSG);
		if(t.getTipo() == null) throw new IllegalArgumentException(ERROR_TIPO_MSG);
		if(t.getNombre() == null || t.getNombre() == "") throw new IllegalArgumentException(ERROR_NOMBRE_MSG1);
		if(t.getNombre().length() < MIN_LONGITUD_NOMBRE || t.getNombre().length() > MAX_LONGTIUD_NOMBRE) throw new IllegalArgumentException(ERROR_NOMBRE_MSG2);
		if(t.getActivo() == false) throw new IllegalArgumentException(ERROR_ACTIVO_MSG);
		return FactoriaAbstractaIntegracion.getInstance().crearDAOEvento().crearEvento(t); //True si se creo, false en caso contrario
	}

	@Override
	public boolean eliminarEvento(int id) throws IllegalArgumentException {
		if(id < 0 || buscarEvento(id) == null) throw new IllegalArgumentException(ERROR_ELIMINAR_MSG1 + id); //Entrada incorrecta ó id inexistente
		if(!FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().productoAsociadoEvento(id).isEmpty()) throw new IllegalArgumentException(ERROR_ELIMINAR_MSG2); //Relacion 1 a N, lado 1 : no se puede borrar un evento si hay pases asociados al mismo
		return FactoriaAbstractaIntegracion.getInstance().crearDAOEvento().eliminarEvento(id); //True si se elimino, false en caso contrario
	}

	@Override
	public TEvento buscarEvento(int id) {
		if(id < 0) return null; //Entrada incorrecta
		return FactoriaAbstractaIntegracion.getInstance().crearDAOEvento().buscarEvento(id); //TEvento si se encontro, null en caso contrario
	}

	@Override
	public List<TEvento> listarEventos() {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOEvento().listarEventos(); //List<TEvento> si existen eventos creados, lista vacia en caso contrario. En caso de error, devuelve null
	} 

	@Override
	public boolean modificarEvento(TEvento t, String selecActivo) throws IllegalArgumentException {
		String nombre, tipo; int aforo; boolean activo;
		//ID correcto
		TEvento antiguo = buscarEvento(t.getId());
		if(antiguo == null) throw new IllegalArgumentException(ERROR_MODIFICAR_MSG1 + t.getId());
		//NOMBRE correcto
		String aux = t.getNombre();
		if(aux.equals("") || aux.equals(null)) {
			nombre = antiguo.getNombre();
		} 
		else {
			nombre = t.getNombre();
			if(nombre.length() < MIN_LONGITUD_NOMBRE || nombre.length() > MAX_LONGTIUD_NOMBRE) throw new IllegalArgumentException(ERROR_MODIFICAR_MSG2);
		}
		//TIPO correcto
		if(t.getTipo() == null) {
			tipo = String.valueOf(antiguo.getTipo());
		} 
		else tipo = String.valueOf(t.getTipo());
		//AFORO correcto
		if(t.getAforo() == 0) {
			aforo = antiguo.getAforo();
		} 
		else {
			aforo = t.getAforo();
			if(aforo < MIN_AFORO || aforo > MAX_AFORO) throw new IllegalArgumentException(ERROR_MODIFICAR_MSG3);
		}
		//ACTIVO correcto
		if(selecActivo == "") {
			activo = antiguo.getActivo();
		} 
		else {
			activo = t.getActivo();
			if(!activo && antiguo.getActivo()) { //Relacion 1 a N, lado 1. Considerar pases activos si se va a dar de baja un evento
				if(!FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().productoAsociadoEvento(t.getId()).isEmpty()) throw new IllegalArgumentException(ERROR_MODIFICAR_MSG4);
			}
		}
		//TRANSFER FINAL A PASAR AL DAO (como las comprobaciones estan hechas previamente, no se vuelven a evaluar)
		TEvento result = new TEvento(t.getId(), aforo, tipo, nombre, activo);
		return FactoriaAbstractaIntegracion.getInstance().crearDAOEvento().modificarEvento(result); //True si se modifico, false en caso contrario
	}

}
