package negocio.factoria;

import negocio.atracciones.SAAtracciones;
import negocio.atracciones.SAAtraccionesImp;
import negocio.clientes.SAClientes;
import negocio.clientes.SAClientesImp;
import negocio.departamentos.SADepartamentos;
import negocio.departamentos.SADepartamentosImp;
import negocio.eventos.SAEvento;
import negocio.eventos.SAEventoImp;
import negocio.facturas.SAFactura;
import negocio.facturas.SAFacturaImp;
import negocio.productos.SAProductos;
import negocio.productos.SAProductosImp;
import negocio.trabajadores.SATrabajadores;
import negocio.trabajadores.SATrabajadoresImp;

public class FactoriaNegocio extends FactoriaAbstractaNegocio{

	@Override
	public SATrabajadores crearSATrabajadores() {
		return new SATrabajadoresImp();
	}

	@Override
	public SAFactura crearSAFactura() {
		return new SAFacturaImp();
	}

	@Override
	public SADepartamentos crearSADepartamentos() {
		return new SADepartamentosImp();
	}

	@Override
	public SAProductos crearSAProductos() {
		return new SAProductosImp();
	}

	@Override
	public SAEvento crearSAEvento() {
		return new SAEventoImp();
	}

	@Override
	public SAAtracciones crearSAAtraccion() {
		return new SAAtraccionesImp();
	}

	@Override
	public SAClientes crearSAClientes() {
		return new SAClientesImp();
	}

}
