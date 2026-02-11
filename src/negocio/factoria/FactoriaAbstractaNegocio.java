package negocio.factoria;

import negocio.atracciones.SAAtracciones;
import negocio.clientes.SAClientes;
import negocio.departamentos.SADepartamentos;
import negocio.eventos.SAEvento;
import negocio.facturas.SAFactura;
import negocio.productos.SAProductos;
import negocio.trabajadores.SATrabajadores;

public abstract class FactoriaAbstractaNegocio {
	
	private static FactoriaAbstractaNegocio instancia = null;
	
	public static FactoriaAbstractaNegocio getInstance() {
		if (instancia == null)
			instancia = new FactoriaNegocio();
		return instancia;
	}

	public abstract SATrabajadores crearSATrabajadores();
	public abstract SAFactura crearSAFactura();
	public abstract SADepartamentos crearSADepartamentos();
	public abstract SAProductos crearSAProductos();
	public abstract SAEvento crearSAEvento();
	public abstract SAAtracciones crearSAAtraccion();
	public abstract SAClientes crearSAClientes();
}
