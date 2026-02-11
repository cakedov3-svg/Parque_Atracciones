package negocio.trabajadores;

import java.util.List;

public interface SATrabajadores {

	public abstract String crearTrabajador(TTrabajador t);
	public abstract boolean eliminarTrabajador(String id);
	public abstract  TTrabajador buscarTrabajador(String id);
	public abstract List<TTrabajador> listarTrabajadores();
	public abstract boolean modificarTrabajador(TTrabajador t);
	public boolean increaseNumVentas(int amount, String id);
}
