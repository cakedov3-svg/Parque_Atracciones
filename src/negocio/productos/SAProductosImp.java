package negocio.productos;

import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;

public class SAProductosImp implements SAProductos{

	@Override
	public int anyadirProducto(TProducto producto) {
		if(producto.getPrecio() == 0 || producto.isActivo() == false 
				|| producto.getNombre() == null || producto.getTipo() == null || producto.getIdEntrada() == -1) return -1;
		if(producto.getTipo().toString() == "paseEvento" &&  FactoriaAbstractaIntegracion.getInstance().crearDAOEvento().buscarEvento(producto.getIdEntrada()) == null) return -1;
		if(producto.getTipo().toString() == "paseAtraccion" &&  FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().buscarAtraccion(producto.getIdEntrada()) == null) return -1;
		else return FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().anyadirProducto(producto);
	}

	@Override
	public boolean eliminarProducto(int id) {
		if(id == -1) return false;
		return FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().eliminarProducto(id);
	}

	@Override
	public TProducto buscarProducto(int id) {
		if(id == -1) return null;
		TProducto prod = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().buscarProducto(id);
		if(prod == null) return null;
		else return prod;
	}

	@Override
	public List<TProducto> listarProductos() {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().listarProductos();
	}

	@Override
	public boolean modificarProducto(TProducto producto) {
		if(producto.getId() == -1 || producto.getPrecio() == 0) return false; 
		return FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().modificarProducto(producto);
	}

}
