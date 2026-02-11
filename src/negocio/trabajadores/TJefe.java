package negocio.trabajadores;

import org.json.JSONObject;

public class TJefe extends TTrabajador{

	int numSub;

	public TJefe(String _id, String _nombre, String _apellido, int _idDep, boolean _activo, int _numSub) {
		super(_id, _nombre, _apellido, _idDep, _activo);
		tipo = "Jefe";
		numSub = _numSub;
	}
	
	public TJefe() {
		super();
		numSub = -1;
	}
	
	public JSONObject getJSON() {
		JSONObject j = super.getJSON();
		j.put("numSub", numSub);
		return j;
	}
	
	public static TJefe getTransfer(JSONObject j) {
		TJefe transfer = new TJefe();
		try {
			transfer.id = j.getString("id");
			transfer.nombre = j.getString("nombre");
			transfer.apellido = j.getString("apellido");
			transfer.idDep = j.getInt("idDep");
			transfer.activo = j.getBoolean("activo");
			transfer.numSub = j.getInt("numSub");
			transfer.tipo = "Jefe";
		} catch (ClassCastException  | NullPointerException e) {
			
		}
		return transfer;
	}

	public int getNumSub() { return numSub; }
	
	public void setNumSub(int x) { if(x >= 0) numSub = x; }
}
