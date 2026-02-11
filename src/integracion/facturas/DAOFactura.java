package integracion.facturas;

import java.util.List;

import negocio.facturas.TFactura;

public interface DAOFactura {
	public int guardarFactura(TFactura factura);
	public TFactura getFactura(int id);
	public List<TFactura> getFacturas();
}
