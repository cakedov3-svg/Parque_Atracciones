package presentacion.controlador;

import java.awt.Point;
import java.util.List;


import launcher.MainWindow;
import negocio.atracciones.TAtraccion;
import negocio.eventos.TEvento;
import negocio.clientes.TCliente;
import negocio.departamentos.TDepartamento;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturas.SAFactura;
import negocio.facturas.TDatosVenta;
import negocio.facturas.TFactura;
import negocio.productos.SAProductos;
import negocio.productos.TProducto;
import negocio.trabajadores.TTrabajador;
import presentacion.atracciones.TupleAtracciones;
import presentacion.eventos.Pair;
import presentacion.factoria.FactoriaAbstractaPresentacion;
import presentacion.productos.Tuple;
public class ControladorImp extends Controlador{
	
	@Override
	public void accion(int evento, Object datos) {
		switch(evento) {
		case EventosControlador.SISTEMA_PANTALLA_INICIO:{
			new MainWindow();
		}
		
		//FACTURAS-------------------------------------------------------------------------------
		
		case EventosControlador.FACTURAS_INICIO:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
			
		case EventosControlador.FACTURAS_ANYADIR_VISTA:{
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			SAProductos saP = FactoriaAbstractaNegocio.getInstance().crearSAProductos();
			igui.actualizar(EventosControlador.FACTURAS_ANYADIR_VISTA, saP.listarProductos());
			break;
		}
		
		case EventosControlador.FACTURAS_ANYADIR:{
			TDatosVenta t = (TDatosVenta) datos;
			SAFactura saF = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.FACTURAS_ANYADIR_VISTA);
			
			try {
				int res = saF.anyadirVenta(t);
				if(res != -1)  igui.actualizar(EventosControlador.FACTURAS_ANYADIR_OK, res);
				else igui.actualizar(EventosControlador.FACTURAS_ANYADIR_KO, "Se ha producido un error al crear la factura.\nVuelve a intentarlo.");
			}catch (IllegalArgumentException iae){
				igui.actualizar(EventosControlador.FACTURAS_ANYADIR_KO, iae.getMessage());
				
			}
			break;
		}
		
		case EventosControlador.FACTURAS_BUSCAR_VISTA:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		case EventosControlador.FACTURAS_BUSCAR:{
			int id = (int) datos;
			SAFactura saF = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
			
			TDatosVenta tDV = saF.buscarFactura(id);
			
			if(tDV != null) {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(evento);
				igui.actualizar(EventosControlador.FACTURAS_BUSCAR_OK, tDV);
			}
			else {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(evento);
				igui.actualizar(EventosControlador.FACTURAS_BUSCAR_KO, tDV);
			}
			break;
		}
		
		case EventosControlador.FACTURAS_LISTAR:{
			SAFactura saF = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
			List<TFactura> tDV = saF.listarFacturas();
			
			if(tDV != null) {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(evento);
				igui.actualizar(EventosControlador.FACTURAS_LISTAR_OK, tDV);
			}
			else {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(evento);
				igui.actualizar(EventosControlador.FACTURAS_LISTAR_KO, tDV);
			}
			break;
		}
		
		case EventosControlador.FACTURAS_CONSULTAR_LINEAS:{
			Point data = (Point) datos;
			SAFactura saF = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
			TDatosVenta tDV = saF.buscarFactura(data.x);
			
			if(tDV != null) {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(evento);
				igui.actualizar(data.y, tDV);
			}
			else {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(evento);
				igui.actualizar(EventosControlador.FACTURAS_LINEAS_KO, tDV);
			}
			break;
		}
		
		//TRABAJADORES -------------------------------------------------------------------------------
		case EventosControlador.TRABAJADOR_CREAR: {
			TTrabajador t = (TTrabajador) datos;
			if(FactoriaAbstractaNegocio.getInstance().crearSATrabajadores().crearTrabajador(t) == null) {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_CREAR);
				igui.actualizar(EventosControlador.TRABAJADOR_CREAR_ERROR, t);
			}
			else {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_CREAR);
				igui.actualizar(EventosControlador.TRABAJADOR_CREAR_OK, t);
			}
			break;
		}
		case EventosControlador.TRABAJADOR_ELIMINAR:{
			String id = (String) datos;
			if(FactoriaAbstractaNegocio.getInstance().crearSATrabajadores().eliminarTrabajador(id)) {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_ELIMINAR);
				igui.actualizar(EventosControlador.TRABAJADOR_ELIMINAR_OK, id);
			}
			else {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_ELIMINAR);
				igui.actualizar(EventosControlador.TRABAJADOR_ELIMINAR_ERROR, id);
			}
			break;
		}
		case EventosControlador.TRABAJADOR_BUSCAR:{
			String id = (String) datos;
			TTrabajador t = FactoriaAbstractaNegocio.getInstance().crearSATrabajadores().buscarTrabajador(id);
			if(t == null) {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_BUSCAR);
				igui.actualizar(EventosControlador.TRABAJADOR_BUSCAR_ERROR, t);
			}
			else {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_BUSCAR);
				igui.actualizar(EventosControlador.TRABAJADOR_BUSCAR_OK, t);
			}
			
			break;
		}
		
		case EventosControlador.TRABAJADOR_LISTAR:{
			List<TTrabajador> list = FactoriaAbstractaNegocio.getInstance().crearSATrabajadores().listarTrabajadores();
			if(list == null) {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_LISTAR);
				igui.actualizar(EventosControlador.TRABAJADOR_LISTAR_ERROR, list);
			}
			else {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_LISTAR);
				igui.actualizar(EventosControlador.TRABAJADOR_LISTAR_OK, list);
			}
			break;
		}
		case EventosControlador.TRABAJADOR_MODIFICAR:{
			TTrabajador t = (TTrabajador) datos;
			if(FactoriaAbstractaNegocio.getInstance().crearSATrabajadores().modificarTrabajador(t)) {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_MODIFICAR);
				igui.actualizar(EventosControlador.TRABAJADOR_MODIFICAR_OK, t);
			}
			else {
				IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.TRABAJADOR_VISTA_MODIFICAR);
				igui.actualizar(EventosControlador.TRABAJADOR_MODIFICAR_ERROR, t);
			}
			break;
		}
		case EventosControlador.TRABAJADOR_VISTA_INICIO:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.TRABAJADOR_VISTA_CREAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.TRABAJADOR_VISTA_ELIMINAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.TRABAJADOR_VISTA_BUSCAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}

		case EventosControlador.TRABAJADOR_VISTA_MODIFICAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}

		case EventosControlador.TRABAJADOR_VISTA_LISTAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		//DEPARTAMENTOS -------------------------------------------------------------------------------
		
		case EventosControlador.DEPARTAMENTOS_VISTA_INICIO:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.DEPARTAMENTOS_VISTA_CREAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.DEPARTAMENTOS_VISTA_ELIMINAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.DEPARTAMENTOS_VISTA_BUSCAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}

		case EventosControlador.DEPARTAMENTOS_VISTA_MODIFICAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}

		case EventosControlador.DEPARTAMENTOS_VISTA_LISTAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		case EventosControlador.DEPARTAMENTOS_LISTAR:{
			List<TDepartamento> list = FactoriaAbstractaNegocio.getInstance().crearSADepartamentos().listarDepartamentos();
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.DEPARTAMENTOS_VISTA_LISTAR);
			if(list == null) {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_LISTAR_ERROR, list);
			}
			else {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_LISTAR_OK, list);
			}
			break;
		}
		
		case EventosControlador.DEPARTAMENTOS_BUSCAR:{
			int id = (int) datos;
			TDepartamento t = FactoriaAbstractaNegocio.getInstance().crearSADepartamentos().buscarDepartamento(id);
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.DEPARTAMENTOS_VISTA_BUSCAR);
			if(t == null) {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_BUSCAR_ERROR, t);
			}
			else {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_BUSCAR_OK, t);
				Controlador.getInstancia().accion(EventosControlador.DEPARTAMENTOS_VISTA_INICIO, id);
			}
			
			break;
		}
		
		case EventosControlador.DEPARTAMENTOS_ELIMINAR:{
			int id = (int) datos;
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.DEPARTAMENTOS_VISTA_ELIMINAR);
			if(FactoriaAbstractaNegocio.getInstance().crearSADepartamentos().eliminarDepartamento(id)) {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_ELIMINAR_OK, id);
				Controlador.getInstancia().accion(EventosControlador.DEPARTAMENTOS_VISTA_INICIO, id);
			}
			else {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_ELIMINAR_ERROR, id);
			}
			break;
		}
		
		case EventosControlador.DEPARTAMENTOS_CREAR: {
			String str=(String) datos;
			TDepartamento t = new TDepartamento(str,true);
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.DEPARTAMENTOS_VISTA_CREAR);
			if(FactoriaAbstractaNegocio.getInstance().crearSADepartamentos().anyadirDepartamento(t) == -1) {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_CREAR_ERROR, t);
			}
			else {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_CREAR_OK, t);
			}
			break;
		}
		
		case EventosControlador.DEPARTAMENTOS_MODIFICAR:{
			TDepartamento t = (TDepartamento) datos;
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.DEPARTAMENTOS_VISTA_MODIFICAR);
			if(FactoriaAbstractaNegocio.getInstance().crearSADepartamentos().modificarDepartamento(t)) {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_MODIFICAR_OK, t);
			}
			else {
				igui.actualizar(EventosControlador.DEPARTAMENTOS_MODIFICAR_ERROR, t);
			}
			break;
		}
		
		//PRODUCTOS -------------------------------------------------------------------------------
		
		case EventosControlador.PRODUCTOS_VISTA_INICIO:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.PRODUCTOS_VISTA_CREAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		case EventosControlador.PRODUCTOS_CREAR: {
			TProducto prod = (TProducto) datos;
			int id = FactoriaAbstractaNegocio.getInstance().crearSAProductos().anyadirProducto(prod);
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.PRODUCTOS_VISTA_CREAR);
			if(id != -1) igui.actualizar(EventosControlador.PRODUCTOS_CREAR_OK, id);
			else igui.actualizar(EventosControlador.PRODUCTOS_CREAR_ERROR, -1);
			break;
		}
		
		case EventosControlador.PRODUCTOS_VISTA_ELIMINAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		case EventosControlador.PRODUCTOS_ELIMINAR:{
			int id = (int) datos;
			boolean eliminado = FactoriaAbstractaNegocio.getInstance().crearSAProductos().eliminarProducto(id);
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.PRODUCTOS_VISTA_ELIMINAR);
			if(eliminado) igui.actualizar(EventosControlador.PRODUCTOS_ELIMINAR_OK, id);
			else igui.actualizar(EventosControlador.PRODUCTOS_ELIMINAR_ERROR, id);
			break;
		}
		
		case EventosControlador.PRODUCTOS_VISTA_BUSCAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}

		case EventosControlador.PRODUCTOS_BUSCAR:{
			int id = (int) datos;
			TProducto buscado = FactoriaAbstractaNegocio.getInstance().crearSAProductos().buscarProducto(id);
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.PRODUCTOS_VISTA_BUSCAR);
			if(buscado != null) igui.actualizar(EventosControlador.PRODUCTOS_BUSCAR_OK, buscado);
			else igui.actualizar(EventosControlador.PRODUCTOS_BUSCAR_ERROR, id);
			break;
		}

		case EventosControlador.PRODUCTOS_VISTA_MODIFICAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		case EventosControlador.PRODUCTOS_MODIFICAR:{
			Tuple<Integer, Integer, Boolean> id_precio_activo = (Tuple<Integer, Integer, Boolean>) datos;
			TProducto prod = FactoriaAbstractaNegocio.getInstance().crearSAProductos().buscarProducto(id_precio_activo.first());
			prod.setPrecio(id_precio_activo.second());
			prod.setActivo(id_precio_activo.third());
			boolean modificado = FactoriaAbstractaNegocio.getInstance().crearSAProductos().modificarProducto(prod);
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.PRODUCTOS_VISTA_MODIFICAR);
			if(modificado) igui.actualizar(EventosControlador.PRODUCTOS_MODIFICAR_OK, prod);
			else igui.actualizar(EventosControlador.PRODUCTOS_MODIFICAR_ERROR, prod);
			break;
		}

		case EventosControlador.PRODUCTOS_VISTA_LISTAR:{
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			accion(EventosControlador.PRODUCTOS_LISTAR, igui);
			break;
		}

		case EventosControlador.PRODUCTOS_LISTAR:{
			IGUI igui = (IGUI) datos;
			List<TProducto> lista = FactoriaAbstractaNegocio.getInstance().crearSAProductos().listarProductos();
			//IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.PRODUCTOS_VISTA_LISTAR);
			if(lista != null) igui.actualizar(EventosControlador.PRODUCTOS_LISTAR_OK, lista); 
			else igui.actualizar(EventosControlador.PRODUCTOS_LISTAR_ERROR, null); //TODO o isEmpty()
			break;
		}
		
		//ATRACCIONES -------------------------------------------------------------------------------
		case EventosControlador.ATRACCIONES_VISTA_INICIO:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.ATRACCIONES_VISTA_CREAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.ATRACCIONES_VISTA_BUSCAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.ATRACCIONES_VISTA_ELIMINAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.ATRACCIONES_VISTA_MODIFICAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.ATRACCIONES_VISTA_LISTAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.ATRACCIONES_CREAR:{
			TAtraccion atraccion = (TAtraccion) datos;
			IGUI i = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.ATRACCIONES_VISTA_CREAR);
			try {
				int id = FactoriaAbstractaNegocio.getInstance().crearSAAtraccion().crearAtraccion(atraccion);
				if(id==-1) i.actualizar(EventosControlador.ATRACCIONES_CREAR_ERROR, "No se ha podido crear la atraccion");
				else i.actualizar(EventosControlador.ATRACCIONES_CREAR_OK, FactoriaAbstractaNegocio.getInstance().crearSAAtraccion().buscarAtraccion(id));
			}
			catch(IllegalArgumentException e){
				i.actualizar(EventosControlador.ATRACCIONES_CREAR_ERROR, e.getMessage());
			}
			break;
		}
		case EventosControlador.ATRACCIONES_ELIMINAR:{
			int idAtraccion = (int) datos;
			IGUI i = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.ATRACCIONES_VISTA_ELIMINAR);	
			try {
				boolean eliminado = FactoriaAbstractaNegocio.getInstance().crearSAAtraccion().eliminarAtraccion(idAtraccion);
				if(eliminado) i.actualizar(EventosControlador.ATRACCIONES_ELIMINAR_OK, idAtraccion);
				else i.actualizar(EventosControlador.ATRACCIONES_ELIMINAR_ERROR, "No se ha podido eliminar la atraccion correctamente");
			}
			catch(IllegalArgumentException e) {
				i.actualizar(EventosControlador.ATRACCIONES_ELIMINAR_ERROR, e.getMessage());
			}
			
			break;
		}
		case EventosControlador.ATRACCIONES_BUSCAR:{
			int idAtraccion = (int) datos;
			IGUI i = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.ATRACCIONES_VISTA_BUSCAR); 
			TAtraccion encontrada = FactoriaAbstractaNegocio.getInstance().crearSAAtraccion().buscarAtraccion(idAtraccion);
			if(encontrada==null) i.actualizar(EventosControlador.ATRACCIONES_BUSCAR_ERROR, "La atraccion con id "+ idAtraccion + " no ha sido encontrada");
			else i.actualizar(EventosControlador.ATRACCIONES_BUSCAR_OK, encontrada);
			break;
		}
		case EventosControlador.ATRACCIONES_MODIFICAR:{
			TupleAtracciones<TAtraccion, String, String> tupla = (TupleAtracciones<TAtraccion, String, String>) datos; //Tupla cuyo primer valor es la atraccion, el segundo un string que indica el nuevo valor de activa si se ha modificado (si no es null) y el tercero indica lo mismo que el segundo pero para el valor de familiar.
			TAtraccion atraccion = tupla.first();
			String nuevoValorActiva = tupla.second();
			String nuevoValorFamiliar = tupla.third();
			IGUI i = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.ATRACCIONES_VISTA_MODIFICAR);
			try {
			boolean modificadaCorrectamente = FactoriaAbstractaNegocio.getInstance().crearSAAtraccion().modificarAtraccion(atraccion, nuevoValorActiva, nuevoValorFamiliar);
			if(modificadaCorrectamente) i.actualizar(EventosControlador.ATRACCIONES_MODIFICAR_OK, atraccion);
			else i.actualizar(EventosControlador.ATRACCIONES_MODIFICAR_ERROR, "No se ha podido modificar correctamente la atraccoin con id "+atraccion.getId());
			}
			catch(IllegalArgumentException e) {
				i.actualizar(EventosControlador.ATRACCIONES_MODIFICAR_ERROR, e.getMessage());
			}
			break;
		}
		case EventosControlador.ATRACCIONES_LISTAR:{
			List<TAtraccion> listaAtracciones = FactoriaAbstractaNegocio.getInstance().crearSAAtraccion().listarAtracciones();
			IGUI i = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.ATRACCIONES_VISTA_LISTAR);
			if(listaAtracciones!=null&&!listaAtracciones.isEmpty()) i.actualizar(EventosControlador.ATRACCIONES_LISTAR_OK, listaAtracciones);
			else i.actualizar(EventosControlador.ATRACCIONES_LISTAR_ERROR, null);
			break;
		}
	
		
		//EVENTOS -------------------------------------------------------------------------------
		
		case EventosControlador.EVENTOS_CREAR:{
			TEvento transfer = (TEvento) datos;
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.EVENTOS_VISTA_CREAR);
			try {
				int creado = FactoriaAbstractaNegocio.getInstance().crearSAEvento().crearEvento(transfer);
				if(creado != -1) igui.actualizar(EventosControlador.EVENTOS_CREAR_OK, creado);
				else igui.actualizar(EventosControlador.EVENTOS_CREAR_ERROR, "Ha ocurrido un error. No se ha podido incorporar el evento");
			}
			catch (IllegalArgumentException exc) {
				igui.actualizar(EventosControlador.EVENTOS_CREAR_ERROR, exc.getMessage());
			}
			break;
		}
		case EventosControlador.EVENTOS_ELIMINAR:{
			int id = (int) datos;
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.EVENTOS_VISTA_ELIMINAR);
			try {
				boolean eliminado = FactoriaAbstractaNegocio.getInstance().crearSAEvento().eliminarEvento(id);
				if(eliminado) igui.actualizar(EventosControlador.EVENTOS_ELIMINAR_OK, id);
				else igui.actualizar(EventosControlador.EVENTOS_ELIMINAR_ERROR, "Ha ocurrido un error. No se ha podido eliminar el evento.");
			}
			catch (IllegalArgumentException exc) {
				igui.actualizar(EventosControlador.EVENTOS_ELIMINAR_ERROR, exc.getMessage());
			}
			break;
		}
		case EventosControlador.EVENTOS_BUSCAR:{
			int id = (int) datos;
			TEvento buscado = FactoriaAbstractaNegocio.getInstance().crearSAEvento().buscarEvento(id);
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.EVENTOS_VISTA_BUSCAR);
			if(buscado != null) igui.actualizar(EventosControlador.EVENTOS_BUSCAR_OK, buscado);
			else igui.actualizar(EventosControlador.EVENTOS_BUSCAR_ERROR, id);
			break;
		}
		case EventosControlador.EVENTOS_LISTAR:{
			List<TEvento> lista = FactoriaAbstractaNegocio.getInstance().crearSAEvento().listarEventos();
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.EVENTOS_VISTA_LISTAR);
			if(lista != null) igui.actualizar(EventosControlador.EVENTOS_LISTAR_OK, lista); 
			else igui.actualizar(EventosControlador.EVENTOS_LISTAR_ERROR, null);
			break;
		}
		case EventosControlador.EVENTOS_MODIFICAR:{
			Pair<TEvento, String> par = (Pair<TEvento, String>) datos;
			TEvento transfer = par.first();
			String selecActivo = par.second();
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.EVENTOS_VISTA_MODIFICAR);
			try {
				boolean modificado = FactoriaAbstractaNegocio.getInstance().crearSAEvento().modificarEvento(transfer, selecActivo);
				if(modificado) igui.actualizar(EventosControlador.EVENTOS_MODIFICAR_OK, transfer);
				else igui.actualizar(EventosControlador.EVENTOS_MODIFICAR_ERROR, "Ha ocurrido un error. No se ha podido modificar el evento.");
			}
			catch (IllegalArgumentException exc) {
				igui.actualizar(EventosControlador.EVENTOS_MODIFICAR_ERROR, exc.getMessage());
			}
			break;
		}
		case EventosControlador.EVENTOS_VISTA_INICIO:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.EVENTOS_VISTA_CREAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.EVENTOS_VISTA_ELIMINAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.EVENTOS_VISTA_BUSCAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.EVENTOS_VISTA_LISTAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.EVENTOS_VISTA_MODIFICAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		
		//CLIENTES -------------------------------------------------------------------------------
		case EventosControlador.CLIENTE_VISTA_INICIO:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		case EventosControlador.CLIENTE_VISTA_CREAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.CLIENTE_VISTA_ELIMINAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.CLIENTE_VISTA_LISTAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.CLIENTE_VISTA_MODIFICAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		case EventosControlador.CLIENTE_VISTA_BUSCAR:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento);
			break;
		}
		
		case EventosControlador.CLIENTE_LISTAR:{
			List<TCliente> lista = FactoriaAbstractaNegocio.getInstance().crearSAClientes().listarClientes();
			IGUI igui=FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.CLIENTE_VISTA_LISTAR);
			
			if(lista==null) {
				igui.actualizar(EventosControlador.CLIENTE_LISTAR_ERROR, lista);
			}
			else {
				igui.actualizar(EventosControlador.CLIENTE_LISTAR_OK, lista);
			}
			break;
		}
		
		case EventosControlador.CLIENTE_BUSCAR:{
			IGUI igui=FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.CLIENTE_VISTA_BUSCAR);
			int id;
			try {
				id = Integer.parseInt((String) datos);	
				TCliente c = FactoriaAbstractaNegocio.getInstance().crearSAClientes().buscarCliente(id);
				if(c!=null) {
					igui.actualizar(EventosControlador.CLIENTE_BUSCAR_OK, c);
				}
				else igui.actualizar(EventosControlador.CLIENTE_BUSCAR_ERROR, null);
			}catch(Exception e) {
				igui.actualizar(EventosControlador.CLIENTE_BUSCAR_ERROR, null);
			}
			break;
		}
		
		case EventosControlador.CLIENTE_CREAR:{
			TCliente c = (TCliente) datos;
			IGUI igui=FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.CLIENTE_VISTA_CREAR);
			int id = FactoriaAbstractaNegocio.getInstance().crearSAClientes().crearCliente(c);
			if(id!=-1) {
				igui.actualizar(EventosControlador.CLIENTE_CREAR_OK, id);
			}
			else igui.actualizar(EventosControlador.CLIENTE_CREAR_ERROR, null);
			break;
		}
		
		case EventosControlador.CLIENTE_ELIMINAR:{
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.CLIENTE_VISTA_ELIMINAR);
			int id;
			try {
				id = Integer.parseInt((String) datos);
				boolean eliminado =FactoriaAbstractaNegocio.getInstance().crearSAClientes().eliminarCliente(id);
				if(eliminado) {
					igui.actualizar(EventosControlador.CLIENTE_ELIMINAR_OK, id);
				}
				else {
					igui.actualizar(EventosControlador.CLIENTE_ELIMINAR_ERROR, id);
				}
			}catch(Exception e) {
				igui.actualizar(EventosControlador.CLIENTE_ELIMINAR_ERROR, null);
			}
			break;
		}
		
		case EventosControlador.CLIENTE_MODIFICAR: {
			TCliente c = (TCliente) datos;
			IGUI igui = FactoriaAbstractaPresentacion.getInstance().createVista(EventosControlador.CLIENTE_VISTA_MODIFICAR);
			if(FactoriaAbstractaNegocio.getInstance().crearSAClientes().modificarCliente(c)) {
				igui.actualizar(EventosControlador.CLIENTE_MODIFICAR_OK, c);
			}
			else {
				igui.actualizar(EventosControlador.CLIENTE_MODIFICAR_ERROR, null);
			}
			break;
		}
		
		
		}
	}

}
