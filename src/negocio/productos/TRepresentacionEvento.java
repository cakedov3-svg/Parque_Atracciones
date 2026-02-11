package negocio.productos;

import org.json.JSONObject;

import negocio.productos.TProducto.tiposProducto;

public class TRepresentacionEvento extends TProducto {

	private int idEvento;
	
	//CONSTRUCTORES------------------------------------------------
	public TRepresentacionEvento(int idProducto, String nombre, double precioProducto, boolean activo, tiposProducto tipo, int idEvento) {
		super(idProducto, nombre, precioProducto, activo, tipo);
		this.idEvento = idEvento;
	}
	
	public TRepresentacionEvento(String nombre, double precio, String tipo, int id) {
		super(nombre, precio, tipo);
		this.idEvento = id;
	}
	
	public TRepresentacionEvento() {
		super();
		this.idEvento = -1;
	}
	
	//METODOS------------------------------------------------------
	public JSONObject getJSON() {
		JSONObject JO = super.getJSON();
		
		JO.put(ID_EVENTO_KEY, idEvento);
		
		return JO;
	}
	
	public static TRepresentacionEvento getTransfer(JSONObject JO) {
		TRepresentacionEvento transfer = new TRepresentacionEvento();
		try {
			int JoIdProducto = JO.getInt(ID_KEY);
			int JoIdEvento = JO.getInt(ID_EVENTO_KEY);
			String JoNombreProducto = JO.getString(NOMBRE_PRODUCTO_KEY);
			double JoPrecioProducto = JO.getDouble(PRECIO_PRODUCTO_KEY);
			boolean JoActivoProducto = JO.getBoolean(OPERATIVO_PRODUCTO_KEY);
			tiposProducto JoTipoProducto = tiposProducto.valueOf(JO.getString(TIPO_PRODUCTO_KEY));
			
			return new TRepresentacionEvento(JoIdProducto, JoNombreProducto, JoPrecioProducto, JoActivoProducto,JoTipoProducto, JoIdEvento);
		}catch(ClassCastException  | NullPointerException ce) {
			return new TRepresentacionEvento();
		}
	}

	@Override
	public int getIdEntrada(){ return this.idEvento;}
}
