package negocio.productos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TProducto {
	//CLAVES JSONObject--------------------------------------------
	protected static final String ID_KEY = "id";
	protected static final String NOMBRE_PRODUCTO_KEY = "nombreProducto";
	protected static final String PRECIO_PRODUCTO_KEY = "precioProducto";
	protected static final String OPERATIVO_PRODUCTO_KEY = "activo";
	protected static final String ID_EVENTO_KEY = "idEvento";
	protected static final String ID_ATRACCION_KEY = "idAtraccion";
	protected static final String TIPO_PRODUCTO_KEY = "tipoProducto";
	
	//ATRIBUTOS----------------------------------------------------
	private int id;
	private String nombreProducto;
	private double precioProducto;
	boolean activo;
	enum tiposProducto { paseAtraccion, paseEvento};
	tiposProducto tipoProducto;

	//CONSTRUCTORES------------------------------------------------
	public TProducto(int idProducto, String nombre, double precioProducto, boolean activo, tiposProducto producto) {
		this.id = idProducto;
		this.nombreProducto = nombre;
		this.precioProducto = precioProducto;
		this.activo = activo;
		this.tipoProducto = producto;
	}
	
	public TProducto(int idProducto, String nombre, double precioProducto, boolean activo, String producto) {
		this.id = idProducto;
		this.nombreProducto = nombre;
		this.precioProducto = precioProducto;
		this.activo = activo;
		this.tipoProducto = tiposProducto.valueOf(producto);
	}
	
	public TProducto(String nombre, double precio, String tipo) {
		this.id = -1;
		this.nombreProducto = nombre;
		this.precioProducto = precio;
		this.tipoProducto = tiposProducto.valueOf(tipo);
		this.activo = true;
	}
	
	public TProducto () {
		this.id = -1;
		this.nombreProducto = null;
		this.precioProducto = -1;
		this.tipoProducto = null;
		this.activo = false;
	}
	
	//METODOS GET------------------------------------------------------
	public JSONObject getJSON() {
		JSONObject JO = new JSONObject();
		
		JO.put(ID_KEY, id);
		JO.put(NOMBRE_PRODUCTO_KEY, nombreProducto);
		JO.put(PRECIO_PRODUCTO_KEY, precioProducto);
		JO.put(OPERATIVO_PRODUCTO_KEY, activo);
		JO.put(TIPO_PRODUCTO_KEY, tipoProducto);
		
		return JO;
	}
	
	public static TProducto getTransfer(JSONObject JO) {
		try {
			if(!JO.has(TIPO_PRODUCTO_KEY)) return new TProducto();
			String type = JO.getString(TIPO_PRODUCTO_KEY);
			if("paseEvento".equals(type)) return TRepresentacionEvento.getTransfer(JO);
			else if("paseAtraccion".equals(type)) return TPaseAtraccion.getTransfer(JO);
			else return null;
		}catch(ClassCastException  | NullPointerException ce) {
			return new TProducto();
		}
	}
	
	public String getEstado() {
		return getJSON().toString();
	}

	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombreProducto;
	}
	
	public double getPrecio() {
		return this.precioProducto;
	}

	public String getIdStr() {
		return Integer.toString(getId());
	}
	
	public Boolean isActivo() {
		return this.activo;
	}
	
	public tiposProducto getTipo() {
		return this.tipoProducto;
	}
	
	public static List<String> getProductos() {
		List<String> tipos = new ArrayList<String>();
		for(tiposProducto tipo : tiposProducto.values()) {
			tipos.add(tipo.toString());
		}
		return tipos;
	}
	
	public int getIdEntrada(){ return -1;}
	
	//METODOS SET------------------------------------------------------
	public void setActivo(boolean activo) { 
		this.activo = activo; 
	}
	
	public void setPrecio(int precio) {
		this.precioProducto = precio;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombreProducto = nombre;
	}
	
}
