package integracion.departamentos;

import java.util.List;

import negocio.departamentos.TDepartamento;

public interface DAODepartamentos {
	public List<TDepartamento> listarDepartamentos();
	public TDepartamento buscarDepartamento(int idDep);
	public boolean modificarDepartamento(TDepartamento d);
	public int anyadirDepartamento(TDepartamento d);
	public boolean eliminarDepartamento(int idDep);
	
}
