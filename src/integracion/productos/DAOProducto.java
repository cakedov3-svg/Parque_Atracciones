package integracion.productos;

import java.util.ArrayList;
import java.util.List;

import negocio.productos.TProducto;

public interface DAOProducto {
	
	public int anyadirProducto(TProducto producto);
	public TProducto buscarProducto(int id);
	public boolean eliminarProducto(int id);
	public List<TProducto> listarProductos();
	public boolean modificarProducto(TProducto producto);
	boolean buscarProductoPorIdAsoc(int id);
	public List<TProducto> productoAsociadoAtraccion(int idAtraccion);
	public List<TProducto> productoAsociadoEvento(int idEvento);
}
