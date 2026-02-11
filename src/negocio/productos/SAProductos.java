package negocio.productos;

import java.util.List;


public interface SAProductos {

	public int anyadirProducto(TProducto producto);
	public  TProducto buscarProducto(int id);
	public boolean eliminarProducto(int id);
	public List<TProducto> listarProductos();
	public boolean modificarProducto(TProducto producto);
}
