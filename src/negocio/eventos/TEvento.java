package negocio.eventos;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class TEvento {
	
	//CLAVES JSONObject--------------------------------------------
	private static final String ID_KEY = "id";
	private static final String AFORO_KEY = "aforo";
	private static final String TIPO_KEY = "tipo";
	private static final String NOMBRE_KEY = "nombre";
	private static final String ACTIVO_KEY = "activo";
	private static final String NEXT_ID_KEY = "next_id";
	
	//ATRIBUTOS----------------------------------------------------
	private int id;
	private int aforo;
	private enum tiposEvento { concierto, circo, magia, teatro, otros };
	private tiposEvento tipo;
	private String nombre;
	private boolean activo;
	private static final String[] ATRIBS = { "Nombre" , "Tipo" , "Aforo" , "Activo" };

	//CONSTRUCTORES------------------------------------------------
	public TEvento(int id, int aforo, tiposEvento tipo, String nombre, boolean activo) {
		this.id = id;
		this.aforo = aforo;
		this.tipo = tipo;
		this.nombre = nombre;
		this.activo = activo;
	}
	
	public TEvento(int id, int aforo, String tipo, String nombre, boolean activo) {
		this.id = id;
		this.aforo = aforo;
		this.tipo = tiposEvento.valueOf(tipo);
		this.nombre = nombre;
		this.activo = activo;
	}
	
	public TEvento(int aforo, String tipo, String nombre) {
		this.id = -1;
		this.aforo = aforo;
		this.tipo = tiposEvento.valueOf(tipo);
		this.nombre = nombre;
		this.activo = true;
	}
	
	public TEvento(int id, int aforo, String tipo, String nombre, String selecActivo) {
		this.id = id;
		this.aforo = aforo;
		if(tipo == "" || tipo == null) this.tipo = null;
		else this.tipo = tiposEvento.valueOf(tipo);
		this.nombre = nombre;
		if(selecActivo == "SI") this.activo = true;
		else this.activo = false;
	}
	
	public TEvento() {
		this.id = -1;
		this.aforo = -1;
		this.tipo = null;
		this.nombre = "";
		this.activo = false;
	}

	//METODOS------------------------------------------------------
	public JSONObject getJSON() {
		JSONObject JO = new JSONObject();
		
		JO.put(ID_KEY, id);
		JO.put(AFORO_KEY, aforo);
		JO.put(TIPO_KEY, tipo);
		JO.put(NOMBRE_KEY, nombre);
		JO.put(ACTIVO_KEY, activo);
		
		return JO;
	}
	
	public static TEvento getTransfer(JSONObject JO) {
		try {
			int JOId = JO.getInt(ID_KEY);
			int JOAforo = JO.getInt(AFORO_KEY);
			tiposEvento JOTipo =  tiposEvento.valueOf(JO.getString(TIPO_KEY));
			String JONombre = JO.getString(NOMBRE_KEY);
			boolean JOActivo = JO.getBoolean(ACTIVO_KEY);
			return new TEvento(JOId, JOAforo, JOTipo, JONombre, JOActivo);
		} catch(ClassCastException | NullPointerException ce) {
			return null;
		}
	}
	
	public String getEstado() {
		return getJSON().toString();
	}

	public String getIdStr() {
		return Integer.toString(getId());
	}
	
	public static List<String> getTipos() {
		List<String> tipos = new ArrayList<String>();
		for(tiposEvento t : tiposEvento.values()) {
			tipos.add(t.toString());
		}
		return tipos;
	}
	
	public static List<String> getAtrib() {
		List<String> atribs = new ArrayList<String>();
		for(String s : ATRIBS) {
			atribs.add(s);
		}
		return atribs;
	}
	

	//GETTERS
	public int getId() { return id; }
	public int getAforo() { return aforo; }
	public tiposEvento getTipo() { return tipo; }
	public String getNombre() { return nombre; }
	public boolean getActivo() { return activo; }
	
	//SETTERS
	public void setActivo(boolean activo) { this.activo = activo; }

}
