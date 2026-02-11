package negocio.atracciones;

import java.util.List;

public interface SAAtracciones {
	
	public int crearAtraccion(TAtraccion atraccion);
	public boolean eliminarAtraccion(int id);
	public TAtraccion buscarAtraccion(int id);
	public List<TAtraccion> listarAtracciones();
	public boolean modificarAtraccion(TAtraccion atraccion, String nuevoValorActiva, String nuevoValorFamiliar);
}
