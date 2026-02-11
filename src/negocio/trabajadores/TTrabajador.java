package negocio.trabajadores;

import org.json.JSONObject;

public class TTrabajador {

	protected String tipo;
	protected String id;
	protected String nombre;
	protected String apellido;
	protected int idDep;
	protected boolean activo;
	
	public TTrabajador(String _id, String _nombre, String _apellido, int _idDep, boolean _activo) {
		id = _id;
		nombre = _nombre;
		apellido = _apellido;
		idDep = _idDep;
		activo = _activo;
	}
	
	public TTrabajador() {
		id = null;
		nombre = null;
		apellido = null;
		idDep = -1;
		activo = false;
	}
	
	public JSONObject getJSON() {
		JSONObject j = new JSONObject();
		j.put("tipo", tipo);
		j.put("id", id);
		j.put("nombre", nombre);
		j.put("apellido",  apellido);
		j.put("idDep", idDep);
		j.put("activo", activo);
		return j;
	}
	
	public static TTrabajador getTransfer(JSONObject j) {
		if(!j.has("tipo")) return null;
		String s = j.getString("tipo");
		if("Encargado".equals(s)) return TEncargado.getTransfer(j);
		else if("Vendedor".equals(s)) return TVendedor.getTransfer(j);
		else if("Jefe".equals(s)) return TJefe.getTransfer(j);
		else return null;
	}

	public boolean getActivo() {
		return activo;
	}
	
	public String getId() { return id; }
	
	public String getNombre() { return nombre; }

	public String getApellido() { return apellido; }

	public int getIdDep() { return idDep; }
	
	public String getTipo() { return tipo; }
	
	public void setNombre(String s) { nombre = s; }
	
	public void setApellido(String s) { apellido = s; }
	
	public void setIdDep(int s) { idDep = s; }
	
	public void setActivo(boolean s) { activo = s; }

}
