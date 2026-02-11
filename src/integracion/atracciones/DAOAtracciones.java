package integracion.atracciones;

import java.util.List;

import negocio.atracciones.TAtraccion;

public interface DAOAtracciones {

	public int guardarAtraccion(TAtraccion atraccion);
	public boolean eliminarAtraccion(int id);
	public TAtraccion buscarAtraccion(int id);
	public List<TAtraccion> listarAtracciones();
	public boolean modificarAtraccion(TAtraccion atraccion);
	public List<TAtraccion> atraccionesEncargado(String idEncargado);

	
}
