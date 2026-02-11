package integracion.factoria;

import integracion.atracciones.DAOAtracciones;
import integracion.clientes.DAOClientes;
import integracion.departamentos.DAODepartamentos;
import integracion.eventos.DAOEvento;
import integracion.facturas.DAOFactura;
import integracion.facturas.DAOLineaFactura;
import integracion.productos.DAOProducto;
import integracion.trabajadores.DAOTrabajadores;

public abstract class FactoriaAbstractaIntegracion {


	private static FactoriaAbstractaIntegracion instancia;
	
	public static FactoriaAbstractaIntegracion getInstance() {
		if(instancia == null) instancia = new FactoriaIntegracion();
		return instancia;
	}
	
	public abstract DAOTrabajadores crearDAOTrabajadores();
	public abstract DAOFactura crearDAOFactura();
	public abstract DAOLineaFactura crearDAOLineaFactura();
	public abstract DAOProducto crearDAOProducto();
	public abstract DAOClientes crearDAOClientes();
	public abstract DAOAtracciones crearDAOAtracciones();
	public abstract DAODepartamentos crearDAODepartamentos();
	public abstract DAOEvento crearDAOEvento();
}
