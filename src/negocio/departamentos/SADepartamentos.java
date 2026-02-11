package negocio.departamentos;

import java.util.List;

public interface SADepartamentos {
	public List<TDepartamento> listarDepartamentos();
	public TDepartamento buscarDepartamento(int idDep);
	public boolean modificarDepartamento(TDepartamento d);
	public int anyadirDepartamento(TDepartamento d);
	public boolean eliminarDepartamento(int idDep);
}
