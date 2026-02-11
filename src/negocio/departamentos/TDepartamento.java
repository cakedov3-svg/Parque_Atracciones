package negocio.departamentos;

import org.json.JSONObject;

public class TDepartamento {
		//CLAVES JSONObject--------------------------------------------
		private static final String IDDEP_KEY = "idDep";
		private static final String ACTIVO_KEY = "activo";
		private static final String NOMBRE_KEY = "nombre";
		
		//ATRIBUTOS----------------------------------------------------
		private String nombre;
		private boolean activo;
		private int idDep;
		
		public TDepartamento(String nombre, boolean activo, int id) {
			this.idDep=id;
			this.activo=activo;
			this.nombre=nombre;
		}
		
		public TDepartamento(String nombre, boolean activo) {
			this.nombre=nombre;
			this.activo=activo;
			this.idDep=-1;
		}
		
		public TDepartamento() {
			this.idDep=-1;
			this.activo=false;
			this.nombre="";
		}
		
		public int getidDep() {return this.idDep;}
		
		public boolean isActivo() {return this.activo;}
		
		public String getNombre() {return this.nombre;}
		
		public void setActivo(boolean activo) {this.activo=activo;}
		
		public JSONObject getJSON() {
			JSONObject json = new JSONObject();
			
			json.put(NOMBRE_KEY, this.nombre);
			json.put(IDDEP_KEY, this.idDep);
			json.put(ACTIVO_KEY, this.activo);
			
			return json;
		}
		
		public static TDepartamento getTransfer(JSONObject json) {
			try {
				int idDep = json.getInt(IDDEP_KEY);
				String nombre = json.getString(NOMBRE_KEY);
				boolean activo = json.getBoolean(ACTIVO_KEY);
				
				return new TDepartamento(nombre, activo, idDep);
			}catch(ClassCastException  | NullPointerException ce) {
				return null;
			}
		}
}
