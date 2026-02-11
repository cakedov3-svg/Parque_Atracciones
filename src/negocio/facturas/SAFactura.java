package negocio.facturas;

import java.util.List;

public interface SAFactura {
	public int anyadirVenta(TDatosVenta t);
	public TDatosVenta buscarFactura(int id);
	public List<TFactura> listarFacturas();
}
