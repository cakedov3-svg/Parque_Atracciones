package negocio.clientes;

import java.util.List;

public abstract class SAClientes {
	public abstract int crearCliente(TCliente c);
	public abstract boolean eliminarCliente(int id);
	public abstract  TCliente buscarCliente(int id);
	public abstract List<TCliente> listarClientes();
	public abstract boolean modificarCliente(TCliente c);
}
