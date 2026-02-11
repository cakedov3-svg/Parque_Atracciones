package integracion.clientes;

import java.util.List;

import org.json.JSONObject;

import negocio.clientes.TCliente;

public interface DAOClientes {
	public TCliente getCliente(JSONObject j);
	public int guardarCliente(TCliente c);
	public boolean eliminarCliente(int id);
	public List<TCliente> listarClientes();
	public boolean modificarCliente(TCliente c);
	public TCliente buscarCliente(int id);
		
	}
