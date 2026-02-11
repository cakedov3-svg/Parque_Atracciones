package integracion.eventos;

import java.util.List;

import negocio.eventos.TEvento;

public interface DAOEvento {
	public int crearEvento (TEvento t);
	public boolean eliminarEvento (int id);
	public TEvento buscarEvento (int id);
	public List<TEvento> listarEventos();
	public boolean modificarEvento(TEvento t);
}
