package integracion.facturas;

import java.util.List;

import negocio.facturas.TLineaFactura;

public interface DAOLineaFactura {
	public int guardarLineaFactura(TLineaFactura newLineas);
	public int[] guardarLineasFactura(List<TLineaFactura> newLineas);
	public List<TLineaFactura> getLineasFactura(int idFactura);
}