package negocio.trabajadores;

import org.json.JSONObject;

public class TEncargado extends TTrabajador{
	
	private String idJefe;

	public TEncargado(String _id, String _nombre, String _apellido, int _idDep, boolean _activo, String _idJefe) {
		super(_id, _nombre, _apellido, _idDep, _activo);
		tipo = "Encargado";
		idJefe = _idJefe;
	}
	
	public TEncargado() {
		super();
		idJefe = null;
	}
	
	public JSONObject getJSON() {
		JSONObject j = super.getJSON();
		j.put("idJefe", idJefe);
		return j;
	}
	
	public static TEncargado getTransfer(JSONObject j) {
		TEncargado transfer = new TEncargado();
		try {
			transfer.id = j.getString("id");
			transfer.nombre = j.getString("nombre");
			transfer.apellido = j.getString("apellido");
			transfer.idDep = j.getInt("idDep");
			transfer.activo = j.getBoolean("activo");
			transfer.idJefe = j.getString("idJefe");
			transfer.tipo = "Encargado";
		} catch (ClassCastException  | NullPointerException e) {
			
		}
		return transfer;
	}

	public String getIdJefe() { return idJefe; }
	
}
