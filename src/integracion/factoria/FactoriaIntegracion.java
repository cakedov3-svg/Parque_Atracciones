package integracion.factoria;

import integracion.atracciones.DAOAtracciones;
import integracion.atracciones.DAOAtraccionesImp;
import integracion.clientes.DAOClientes;
import integracion.clientes.DAOClientesImp;
import integracion.departamentos.DAODepartamentos;
import integracion.departamentos.DAODepartamentosImp;
import integracion.eventos.DAOEvento;
import integracion.eventos.DAOEventoImp;
import integracion.facturas.DAOFactura;
import integracion.facturas.DAOFacturaImp;
import integracion.facturas.DAOLineaFactura;
import integracion.facturas.DAOLineaFacturaImp;
import integracion.productos.DAOProducto;
import integracion.productos.DAOProductoImp;
import integracion.trabajadores.DAOTrabajadores;
import integracion.trabajadores.DAOTrabajadoresImp;

public class FactoriaIntegracion extends FactoriaAbstractaIntegracion{

	@Override
	public DAOTrabajadores crearDAOTrabajadores() {
		return new DAOTrabajadoresImp();
	}

	@Override
	public DAOFactura crearDAOFactura() {
		return new DAOFacturaImp();
	}

	@Override
	public DAOLineaFactura crearDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}
	
	@Override
	public DAOProducto crearDAOProducto() {
		return new DAOProductoImp();
	}

	@Override
	public DAOClientes crearDAOClientes() {
		return new DAOClientesImp();
	}

	@Override
	public DAOAtracciones crearDAOAtracciones() {
		return new DAOAtraccionesImp();
	}

	@Override
	public DAODepartamentos crearDAODepartamentos() {
		return new DAODepartamentosImp();
	}

	@Override
	public DAOEvento crearDAOEvento() {
		return new DAOEventoImp();
	}


	
}
