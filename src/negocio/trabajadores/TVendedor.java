package negocio.trabajadores;

import org.json.JSONObject;

public class TVendedor extends TTrabajador{

	private String idJefe;
	private int numVentas;

	public TVendedor(String _id, String _nombre, String _apellido, int _idDep, boolean _activo, String _idJefe, int _numVentas) {
		super(_id, _nombre, _apellido, _idDep, _activo);
		tipo = "Vendedor";
		idJefe = _idJefe;
		numVentas = _numVentas;
	}
	
	public TVendedor() {
		super();
		idJefe = null;
		numVentas = -1;
	}
	
	public JSONObject getJSON() {
		JSONObject j = super.getJSON();
		j.put("idJefe", idJefe);
		j.put("numVentas", numVentas);
		return j;
	}
	
	public static TVendedor getTransfer(JSONObject j) {
		TVendedor transfer = new TVendedor();
		try {
			transfer.id = j.getString("id");
			transfer.nombre = j.getString("nombre");
			transfer.apellido = j.getString("apellido");
			transfer.idDep = j.getInt("idDep");
			transfer.activo = j.getBoolean("activo");
			transfer.idJefe = j.getString("idJefe");
			transfer.numVentas = j.getInt("numVentas");
			transfer.tipo = "Vendedor";
		} catch (ClassCastException  | NullPointerException e) {
			
		}
		return transfer;
	}
	
	public String getIdJefe() { return idJefe; }
	
	public int getNumVentas() { return numVentas; }
	
	public void setNumVentas(int x) { if(x >= 0) numVentas = x; }
}
