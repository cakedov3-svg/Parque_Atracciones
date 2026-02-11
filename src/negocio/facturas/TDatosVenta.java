package negocio.facturas;
import java.util.ArrayList;
import java.util.List;

public class TDatosVenta {
	
	TFactura factura;
	List<TLineaFactura> lineas;
	
	public TDatosVenta(TFactura factura, List<TLineaFactura> lineas){
		this.factura = factura;
		this.lineas = lineas;
	}
	
	public TDatosVenta() {
		this.factura = null;
		this.lineas = null;
	}

	public TFactura getFactura() {
		return factura;
	}

	public void setFactura(TFactura factura) {
		this.factura = factura;
	}

	public List<TLineaFactura> getLineas() {
		return lineas;
	}

	public void setLineas(List<TLineaFactura> lineas) {
		this.lineas = lineas;
	}
	
	
}
