package integracion.trabajadores;

import java.util.List;

import negocio.trabajadores.TTrabajador;

public interface DAOTrabajadores {
	
	public boolean guardarTrabajador (TTrabajador t);
	public boolean eliminarTrabajador (String id);
	public TTrabajador buscarTrabajador (String id);
	public List<TTrabajador> listarTrabajadores();
	public boolean modificarTrabajador(TTrabajador t);
	public List<TTrabajador> quedanTrabajadores(int idDep);
	public TTrabajador buscarEncargado(String id);
}
