package negocio.eventos;

import java.util.List;

public interface SAEvento {
	public int crearEvento (TEvento t) throws IllegalArgumentException;
	public boolean eliminarEvento (int id) throws IllegalArgumentException;
	public TEvento buscarEvento (int id);
	public List<TEvento> listarEventos();
	public boolean modificarEvento(TEvento t, String selecEvento) throws IllegalArgumentException;
}
