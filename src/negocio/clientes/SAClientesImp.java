package negocio.clientes;
import java.util.List;
import integracion.factoria.FactoriaAbstractaIntegracion;

public class SAClientesImp extends SAClientes {

	public static int id=0;
	@Override
	public int crearCliente(TCliente c) {
		
		if( c.getNombre() == null || c.getNombre().isEmpty() || 
				c.getApellido() == null || c.getApellido().isEmpty()) return -1;

		try {
			return FactoriaAbstractaIntegracion.getInstance().crearDAOClientes().guardarCliente(c);	
		}
		catch(Exception e) {
			return -1;
		}
	}

	@Override
	public boolean eliminarCliente(int id) {
		if(id==-1)return false;
		return FactoriaAbstractaIntegracion.getInstance().crearDAOClientes().eliminarCliente(id);
	}

	@Override
	public TCliente buscarCliente(int id) {
		if(id==-1)return null;
		return FactoriaAbstractaIntegracion.getInstance().crearDAOClientes().buscarCliente(id);
	}

	@Override
	public List<TCliente> listarClientes() {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOClientes().listarClientes();
	}

	@Override
	public boolean modificarCliente(TCliente c) {
		if(c.getId() == -1 || c.getNombre().isEmpty() || c.getNombre() == null || 
				c.getApellido() == null || c.getApellido().isEmpty()|| buscarCliente(c.getId()) == null) return false;
	
	return FactoriaAbstractaIntegracion.getInstance().crearDAOClientes().modificarCliente(c);
	}
}
