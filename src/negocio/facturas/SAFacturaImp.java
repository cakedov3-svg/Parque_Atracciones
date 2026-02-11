package negocio.facturas;

import java.util.ArrayList;
import java.util.List;

import integracion.clientes.DAOClientes;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.facturas.DAOFactura;
import integracion.facturas.DAOLineaFactura;
import integracion.productos.DAOProducto;
import integracion.trabajadores.DAOTrabajadores;
import negocio.clientes.TCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.productos.TProducto;
import negocio.trabajadores.SATrabajadores;
import negocio.trabajadores.TTrabajador;
import negocio.trabajadores.TVendedor;

public class SAFacturaImp implements SAFactura {
	@Override
	public int anyadirVenta(TDatosVenta t) throws IllegalArgumentException {
		TFactura factura = t.getFactura();
		List<TLineaFactura> lineas = t.getLineas();
		
		int idF = -1;
		
		FactoriaAbstractaIntegracion factI = FactoriaAbstractaIntegracion.getInstance();
		DAOFactura daoF = factI.crearDAOFactura();
		DAOLineaFactura daoLF = factI.crearDAOLineaFactura();
		DAOClientes daoC = factI.crearDAOClientes();
		DAOTrabajadores daoT = factI.crearDAOTrabajadores();
		DAOProducto daoP = factI.crearDAOProducto();
		
		//Existe vendedor y esta activo
		TTrabajador vendedor = daoT.buscarTrabajador(factura.getIdVendedor());
		if(vendedor == null || !vendedor.getActivo() || !("Vendedor".equals(vendedor.getTipo()))) 
			throw new IllegalArgumentException("No se ha podido crear la factura porque el vendedor no existe o está inactivo.\nVuelve a intentarlo.");
		//Existe cliente y esta activo
		TCliente cliente = daoC.buscarCliente(factura.getIdCliente());
		if(cliente == null || !cliente.getActivo()) 
			throw new IllegalArgumentException("No se ha podido crear la factura porque el cliente no existe o está inactivo.\nVuelve a intentarlo.");
		//Existen los productos y están activos.....
		for(TLineaFactura tLF: lineas) {
			TProducto tP = daoP.buscarProducto(tLF.getIdProducto()); 
			if(tP == null || !tP.isActivo()) 
				throw new IllegalArgumentException("No se ha podido crear la factura porque al menos un producto no existe o está inactivo.\nVuelve a intentarlo.");
		}
		
		idF = daoF.guardarFactura(t.getFactura());
		for(TLineaFactura linea: t.getLineas()) 
			linea.setIdFactura(idF);
		daoLF.guardarLineasFactura(t.getLineas());
		
		FactoriaAbstractaNegocio factN = FactoriaAbstractaNegocio.getInstance();
		SATrabajadores SAT = factN.crearSATrabajadores();
		SAT.increaseNumVentas(1, vendedor.getId());
		
		return idF;
	}

	@Override
	public TDatosVenta buscarFactura(int id) {
		FactoriaAbstractaIntegracion factoria = FactoriaAbstractaIntegracion.getInstance();
		DAOFactura daoF = factoria.crearDAOFactura();
		DAOLineaFactura daoLF = factoria.crearDAOLineaFactura();
		
		TDatosVenta tDV = new TDatosVenta();  
		TFactura fact = daoF.getFactura(id);
		List<TLineaFactura> lineas = daoLF.getLineasFactura(id);
		if(fact != null && lineas != null) {
			tDV.setFactura(fact);
			tDV.setLineas(lineas);
		}
		return tDV;
	}

	
	@Override
	public List<TFactura> listarFacturas() {
		FactoriaAbstractaIntegracion factoria = FactoriaAbstractaIntegracion.getInstance();
		DAOFactura daoF = factoria.crearDAOFactura();
		List<TFactura> facturas = new ArrayList<TFactura>();
		
		try {
			facturas = daoF.getFacturas();
			return facturas;
		}catch (Exception e) {
			
		}
		return null;
	}
}
