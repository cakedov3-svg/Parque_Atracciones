package presentacion.factoria;

import presentacion.atracciones.VistaAtraccionesBuscar;
import presentacion.atracciones.VistaAtraccionesCrear;
import presentacion.atracciones.VistaAtraccionesEliminar;
import presentacion.atracciones.VistaAtraccionesInicio;
import presentacion.atracciones.VistaAtraccionesListar;
import presentacion.atracciones.VistaAtraccionesModificar;

import presentacion.clientes.VistaClienteAnyadir;
import presentacion.clientes.VistaClienteBuscar;
import presentacion.clientes.VistaClienteInicio;
import presentacion.clientes.VistaClienteModificar;
import presentacion.clientes.VistaClienteEliminar;
import presentacion.clientes.VistaClienteListar;

import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;

import presentacion.departamentos.VistaDepartamentosAnyadir;
import presentacion.departamentos.VistaDepartamentosBuscar;
import presentacion.departamentos.VistaDepartamentosEliminar;
import presentacion.departamentos.VistaDepartamentosInicio;
import presentacion.departamentos.VistaDepartamentosListar;
import presentacion.departamentos.VistaDepartamentosModificar;

import presentacion.eventos.VistaEventosBuscar;
import presentacion.eventos.VistaEventosCrear;
import presentacion.eventos.VistaEventosEliminar;
import presentacion.eventos.VistaEventosInicio;
import presentacion.eventos.VistaEventosListar;
import presentacion.eventos.VistaEventosModificar;

import presentacion.facturas.VistaFacturaBuscar;
import presentacion.facturas.VistaFacturaAnyadir;
import presentacion.facturas.VistaFacturaConsultarLineas;
import presentacion.facturas.VistaFacturaInicio;
import presentacion.facturas.VistaFacturaListar;

import presentacion.productos.VistaProductosInicio;
import presentacion.productos.VistaProductosListar;
import presentacion.productos.VistaProductosModificar;
import presentacion.productos.VistaProductosBuscar;
import presentacion.productos.VistaProductosCrear;
import presentacion.productos.VistaProductosEliminar;

import presentacion.trabajadores.VistaTrabajadorBuscar;
import presentacion.trabajadores.VistaTrabajadorCrear;
import presentacion.trabajadores.VistaTrabajadorEliminar;
import presentacion.trabajadores.VistaTrabajadorInicio;
import presentacion.trabajadores.VistaTrabajadorListar;
import presentacion.trabajadores.VistaTrabajadorModificar;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{

	@Override
	public IGUI createVista(int id) {
		switch(id) {
		
		//FACTURAS
		case EventosControlador.FACTURAS_INICIO:{
			return new VistaFacturaInicio();
		}
		
		case EventosControlador.FACTURAS_BUSCAR:{
			return new VistaFacturaBuscar();
		}
		
		case EventosControlador.FACTURAS_LISTAR:{
			return new VistaFacturaListar();
		}
		
		case EventosControlador.FACTURAS_CONSULTAR_LINEAS:{
			return new VistaFacturaConsultarLineas();
		}
		
		case EventosControlador.FACTURAS_ANYADIR_VISTA:{
			return new VistaFacturaAnyadir();
		}
		
		case EventosControlador.FACTURAS_BUSCAR_VISTA:{
			return new VistaFacturaBuscar();
		}
		
		
		//TRABAJADOR
		case EventosControlador.TRABAJADOR_VISTA_INICIO:{
			return new VistaTrabajadorInicio();
		}
		case EventosControlador.TRABAJADOR_VISTA_CREAR:{
			return new VistaTrabajadorCrear();
		}
		case EventosControlador.TRABAJADOR_VISTA_ELIMINAR:{
			return new VistaTrabajadorEliminar();
		}
		case EventosControlador.TRABAJADOR_VISTA_BUSCAR:{
			return new VistaTrabajadorBuscar();
		}
		case EventosControlador.TRABAJADOR_VISTA_MODIFICAR:{
			return new VistaTrabajadorModificar();
		}
		case EventosControlador.TRABAJADOR_VISTA_LISTAR:{
			return new VistaTrabajadorListar();
		}
		
		//EVENTOS
		
		case EventosControlador.EVENTOS_VISTA_INICIO:{
			return new VistaEventosInicio();
		}
		case EventosControlador.EVENTOS_VISTA_CREAR:{
			return new VistaEventosCrear();
		}
		case EventosControlador.EVENTOS_VISTA_ELIMINAR:{
			return new VistaEventosEliminar();
		}
		case EventosControlador.EVENTOS_VISTA_BUSCAR:{
			return new VistaEventosBuscar();
		}
		case EventosControlador.EVENTOS_VISTA_LISTAR:{
			return new VistaEventosListar();
		}
		case EventosControlador.EVENTOS_VISTA_MODIFICAR:{
			return new VistaEventosModificar();
		}
		
		//ATRACCIONES
		
		case EventosControlador.ATRACCIONES_VISTA_INICIO:{
			return new VistaAtraccionesInicio();
		}
		case EventosControlador.ATRACCIONES_VISTA_BUSCAR:{
			return new VistaAtraccionesBuscar();
		}
		case EventosControlador.ATRACCIONES_VISTA_CREAR:{
			return new VistaAtraccionesCrear();
		}
		case EventosControlador.ATRACCIONES_VISTA_ELIMINAR:{
			return new VistaAtraccionesEliminar();
		}
		case EventosControlador.ATRACCIONES_VISTA_LISTAR:{
			return new VistaAtraccionesListar();
		}
		case EventosControlador.ATRACCIONES_VISTA_MODIFICAR:{
			return new VistaAtraccionesModificar();
		}
		
		//PRODUCTOS
		
		case EventosControlador.PRODUCTOS_VISTA_INICIO:{
			return new VistaProductosInicio();
		}
		case EventosControlador.PRODUCTOS_VISTA_CREAR:{
			return new VistaProductosCrear();
		}
		case EventosControlador.PRODUCTOS_VISTA_ELIMINAR:{
			return new VistaProductosEliminar();
		}
		case EventosControlador.PRODUCTOS_VISTA_MODIFICAR:{
			return new VistaProductosModificar();
		}
		case EventosControlador.PRODUCTOS_VISTA_BUSCAR:{
			return new VistaProductosBuscar();
		}
		case EventosControlador.PRODUCTOS_VISTA_LISTAR:{
			return new VistaProductosListar();
		}
		
		//CLIENTE
	
		case EventosControlador.CLIENTE_VISTA_INICIO:{
			return new VistaClienteInicio();
		}
		case EventosControlador.CLIENTE_VISTA_CREAR:{
			return new VistaClienteAnyadir();
		}
		case EventosControlador.CLIENTE_VISTA_ELIMINAR:{
			return new VistaClienteEliminar();
		}
		case EventosControlador.CLIENTE_VISTA_BUSCAR:{
			return new VistaClienteBuscar();
		}
		case EventosControlador.CLIENTE_VISTA_MODIFICAR:{
			return new VistaClienteModificar();
		}
		case EventosControlador.CLIENTE_VISTA_LISTAR:{
			return new VistaClienteListar();
		}
	
		//DEPARTAMENTOS
		
		case EventosControlador.DEPARTAMENTOS_VISTA_INICIO:{
			return new VistaDepartamentosInicio();
		}
		case EventosControlador.DEPARTAMENTOS_VISTA_CREAR:{
			return new VistaDepartamentosAnyadir();
		}
		case EventosControlador.DEPARTAMENTOS_VISTA_ELIMINAR:{
			return new VistaDepartamentosEliminar();
		}
		case EventosControlador.DEPARTAMENTOS_VISTA_BUSCAR:{
			return new VistaDepartamentosBuscar();
		}
		case EventosControlador.DEPARTAMENTOS_VISTA_MODIFICAR:{
			return new VistaDepartamentosModificar();
		}
		case EventosControlador.DEPARTAMENTOS_VISTA_LISTAR:{
			return new VistaDepartamentosListar();
		}
	
		}
		return null;
		
	}

}
