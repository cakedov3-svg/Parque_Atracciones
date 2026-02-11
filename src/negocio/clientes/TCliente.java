package negocio.clientes;
import org.json.JSONObject;

public class TCliente {

	private int id;
	private String nombre;
	private String apellido;
	private boolean activo;
	
	public TCliente(int id, String nombre, String apellido, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.activo =activo;
	}
	
	public TCliente(String nombre, String apellido, boolean activo) {
		this.id=-1;
		this.nombre = nombre;
		this.apellido = apellido;
		this.activo =activo;
	}
	
	public TCliente() {
		id = -1;
		nombre = null;
		apellido = null;
		activo = false;
	}
	
	public int getId() { return id; }
	
	public void setActivo(boolean activo) { this.activo = activo; }
	
	public JSONObject getJSON() {
		JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("nombre", nombre);
		j.put("apellido",  apellido);
		j.put("activo", activo);
		return j;
	}
	
	public static TCliente getTransfer(JSONObject j) {
		TCliente transfer = null;
		try {
			transfer = new TCliente();
			transfer.id = j.getInt("id");
			transfer.nombre = j.getString("nombre");
			transfer.apellido = j.getString("apellido");
			transfer.activo = j.getBoolean("activo");
		} catch (ClassCastException  | NullPointerException e) {
			
		}
		return transfer;
	}

	public boolean getActivo() {
		return activo;
	}

	public String getNombre() { return nombre;	}

	public String getApellido() { return apellido; }

}
