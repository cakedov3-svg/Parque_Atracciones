package negocio.facturas;

import org.json.JSONObject;

public class TFactura {
	//CLAVES JSONObject--------------------------------------------
	private static final String ID_KEY = "id";
	private static final String ID_CLIENTE_KEY = "idCliente";
	private static final String ID_VENDEDOR_KEY = "idVendedor";
	private static final String PRECIO_TOTAL_KEY = "precioTotal";
	private static final String FECHA_KEY = "fecha";
	
	//ATRIBUTOS----------------------------------------------------
	private int id;
	private int idCliente;
	private String idVendedor;
	private double precioTotal;
	private String fecha;
	

	//CONSTRUCTORES------------------------------------------------
	public TFactura(int id, int idCliente, String idVendedor, double precioTotal, String fecha) {
		this.id = id;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		this.precioTotal = precioTotal;
		this.fecha = fecha;
	}
	
	public TFactura(int idCliente, String idVendedor, double precioTotal, String fecha) {
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		this.precioTotal = precioTotal;
		this.fecha = fecha;
	}
	
	
	//METODOS------------------------------------------------------
	public JSONObject getJSON() {
		JSONObject JO = new JSONObject();
		
		JO.put(ID_KEY, id);
		JO.put(ID_CLIENTE_KEY, idCliente);
		JO.put(ID_VENDEDOR_KEY, idVendedor);
		JO.put(PRECIO_TOTAL_KEY, precioTotal);
		JO.put(FECHA_KEY, fecha);
		
		return JO;
	}

	public static TFactura getTransfer(JSONObject JO) {
		try {
			int JoId = JO.getInt(ID_KEY);
			int JoIdCliente = JO.getInt(ID_CLIENTE_KEY);
			String JoIdVendedor = JO.getString(ID_VENDEDOR_KEY);
			double JoPrecioTotal = JO.getDouble(PRECIO_TOTAL_KEY);
			String JoFecha = JO.getString(FECHA_KEY);
			
			return new TFactura(JoId, JoIdCliente, JoIdVendedor, JoPrecioTotal, JoFecha);
		}catch(ClassCastException  | NullPointerException ce) {
			return null;
		}
	}
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getEstado() {
		return getJSON().toString();
	}

	public int getId() {
		return id;
	}

	public String getIdStr() {
		return Integer.toString(getId());
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
