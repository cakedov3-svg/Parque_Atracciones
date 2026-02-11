package negocio.facturas;

import org.json.JSONObject;

public class TLineaFactura {
	//CLAVES JSONObject-------------------------------------------
	private static final String ID_KEY = "id";
	private static final String PRECIO_KEY = "precio";
	private static final String ID_FACTURA_KEY = "idFactura";
	private static final String ID_PRODUCTO_KEY = "idProducto";
	private static final String PRODUCTO_KEY = "producto";
	private static final String CANTIDAD_KEY = "cantidad";

	//ATRIBUTOS---------------------------------------------------
	private int id;
	private int idFactura;
	private double precio;
	private int idProducto;
	private String nombreProducto;
	private int cantidad;
	
	//CONSTRUCTORES-----------------------------------------------
	public TLineaFactura(int id, int idFactura, double precio, int idProducto, String nombreProducto, int cantidad) {
		this.id = id;
		this.idFactura = idFactura;
		this.precio = precio;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
	}
	
	public TLineaFactura() {
		this.id = -1;
		this.idFactura = -1;
		this.precio = 0;
		this.idProducto = -1;
		this.nombreProducto = null;
		this.cantidad = 0;
	}
	
	//METODOS-----------------------------------------------------
	public JSONObject getJSON() {
		JSONObject JO = new JSONObject();
		
		JO.put(ID_KEY, id);
		JO.put(ID_FACTURA_KEY, idFactura);
		JO.put(PRECIO_KEY, precio);
		JO.put(ID_PRODUCTO_KEY, idProducto);
		JO.put(PRODUCTO_KEY, nombreProducto);
		JO.put(CANTIDAD_KEY, cantidad);
		
		return JO;
	}
	
	public static TLineaFactura getTransfer(JSONObject JO) {
		try {
			int JoId = JO.getInt(ID_KEY);
			int JoIdFactura = JO.getInt(ID_FACTURA_KEY);
			double JoPrecio = JO.getDouble(PRECIO_KEY);
			int JoidProducto = JO.getInt(ID_PRODUCTO_KEY);
			String JoProducto = JO.getString(PRODUCTO_KEY);
			int JoCantidad = JO.getInt(CANTIDAD_KEY);

			return new TLineaFactura(JoId, JoIdFactura, JoPrecio, JoidProducto, JoProducto, JoCantidad);
		}catch(ClassCastException | NullPointerException ce) {
			return null;
		}
	}
	
	public String getEstado() {
		return getJSON().toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
}
