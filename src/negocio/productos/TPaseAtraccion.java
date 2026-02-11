package negocio.productos;

import org.json.JSONObject;

public class TPaseAtraccion extends TProducto {
	
	private int idAtraccion;
	
	//CONSTRUCTORES------------------------------------------------
	public TPaseAtraccion(int idProducto, String nombre, double precioProducto, boolean activo, tiposProducto tipo, int idAtraccion) {
		super(idProducto, nombre, precioProducto, activo, tipo);
		this.idAtraccion = idAtraccion;
	}
	
	public TPaseAtraccion(String nombre, double precio, String tipo, int id) {
		super(nombre, precio, tipo);
		this.idAtraccion = id;
	}
	
	public TPaseAtraccion() {
		super();
		this.idAtraccion = -1;
	}
	
	//METODOS------------------------------------------------------
	public JSONObject getJSON() {
		JSONObject JO = super.getJSON();
		
		JO.put(ID_ATRACCION_KEY, idAtraccion);
		
		return JO;
	}
	
	public static TPaseAtraccion getTransfer(JSONObject JO) {
		TPaseAtraccion transfer = new TPaseAtraccion();
		try {
			int JoIdProducto = JO.getInt(ID_KEY);
			int JoIdAtraccion = JO.getInt(ID_ATRACCION_KEY);
			String JoNombreProducto = JO.getString(NOMBRE_PRODUCTO_KEY);
			double JoPrecioProducto = JO.getDouble(PRECIO_PRODUCTO_KEY);
			boolean JoActivoProducto = JO.getBoolean(OPERATIVO_PRODUCTO_KEY);
			tiposProducto JoTipoProducto = tiposProducto.valueOf(JO.getString(TIPO_PRODUCTO_KEY));
			
			return new TPaseAtraccion(JoIdProducto, JoNombreProducto, JoPrecioProducto, JoActivoProducto,JoTipoProducto, JoIdAtraccion);
		}catch(ClassCastException  | NullPointerException ce) {
			return new TPaseAtraccion();
		}
	}
	
	@Override
	public int getIdEntrada(){ return this.idAtraccion;}
}
