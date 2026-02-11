package negocio.atracciones;

import org.json.JSONObject;

public class TAtraccion {
	
	
	//Keys 
	
	private static final String ID_KEY = "id";
	private static final String AFORO_KEY = "aforo";
	private static final String FAMILIAR_KEY = "familiar";
	private static final String ACTIVA_KEY = "activa";
	private static final String NOMBRE_KEY = "nombre";
	private static final String NIVEL_EMOCION_KEY = "nivel_emocion";
	private static final String ID_ENCARGADO_KEY = "id_encargado";
	

	//Atributos
	
	private int id;
	private String nombre;
	private boolean familiar;
	private int aforo;
	private int nivel_emocion;
	private boolean activa;
	private String id_encargado;
	
	
	//Constructores
	
	public TAtraccion(int id, String nombre, boolean familiar, int aforo, int nivel_emocion, boolean activa, String id_encargado) {
		this.id=id;
		this.nombre=nombre;
		this.familiar=familiar;
		this.aforo=aforo;
		this.nivel_emocion=nivel_emocion;
		this.activa=activa;
		this.id_encargado=id_encargado;
	}
	
	public TAtraccion(String nombre, boolean familiar, int aforo, int emocion, String id_encargado) {
		this.id=-1;
		this.nombre=nombre;
		this.familiar=familiar;
		this.aforo=aforo;
		this.nivel_emocion=emocion;
		this.activa=true;
		this.id_encargado=id_encargado;

	}
	
	public TAtraccion() {
		this.id=-1;
		this.nombre=null;
		this.familiar=false;
		this.aforo=0;
		this.nivel_emocion=-1;
		this.activa=false;
		this.id_encargado=null;

	}
	
	
	//Metodos
	
	public JSONObject getJSON() {
		JSONObject o = new JSONObject();
		if(id!=-1)o.put(ID_KEY, id);
		o.put(NOMBRE_KEY, nombre);
		o.put(FAMILIAR_KEY, familiar);
		o.put(NIVEL_EMOCION_KEY, nivel_emocion);
		o.put(ACTIVA_KEY, activa);
		o.put(AFORO_KEY, aforo);
		o.put(ID_ENCARGADO_KEY, id_encargado);
		return o;
	}
	
	public static TAtraccion getTransfer(JSONObject o) {
		TAtraccion atraccion=new TAtraccion();
		try {
			atraccion.id=o.getInt(ID_KEY);
			atraccion.nombre=o.getString(NOMBRE_KEY);
			atraccion.familiar=o.getBoolean(FAMILIAR_KEY);
			atraccion.nivel_emocion=o.getInt(NIVEL_EMOCION_KEY);
			atraccion.activa=o.getBoolean(ACTIVA_KEY);
			atraccion.aforo = o.getInt(AFORO_KEY);
			atraccion.id_encargado = o.getString(ID_ENCARGADO_KEY);
		}
		catch(ClassCastException  | NullPointerException e){
			return null;
		}
		return atraccion;
	}

	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getAforo() {
		return aforo;
	}
	
	public int getNivelEmocion() {
		return nivel_emocion;
	}
	
	public boolean getFamiliar() {
		return familiar;
	}
	
	public String getIdEncargado() {
		return id_encargado;
	}
	
	public boolean getActiva() {
		return activa;
	}
	
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	
	
}
