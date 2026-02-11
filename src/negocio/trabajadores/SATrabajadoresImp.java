package negocio.trabajadores;

import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;
import negocio.departamentos.TDepartamento;

public class SATrabajadoresImp implements SATrabajadores{

	@Override
	public String crearTrabajador(TTrabajador t) {
		if(t.getId() == null || t.getId() == "" || !formatoId(t.getId()) || t.getNombre() == null || t.getNombre() == "" || 
				t.getApellido() == null || t.getApellido() == "" || t.getIdDep() < 0) return null;
		TDepartamento depAux = FactoriaAbstractaIntegracion.getInstance().crearDAODepartamentos().buscarDepartamento(t.getIdDep());
		if(depAux == null || !depAux.isActivo()) return null;
		TTrabajador aux1 = buscarTrabajador(t.getId());
		if(aux1 != null && aux1.getActivo()) return null;
		else if(aux1 != null) {
			if(modificarTrabajador(t)) return t.getId();
			else return null;
		}
		//Comprobar los atributos especificos de cada subtipo de trabajador
		
		if("Jefe".equals(t.getTipo())) {
			TJefe aux = (TJefe) t;
			if(aux.getNumSub() < 0) return null;
		}
		else if("Vendedor".equals(t.getTipo())) {
			TVendedor aux = (TVendedor) t;
			if(aux.getIdJefe() == null || aux.getNumVentas() < 0) return null;
			TTrabajador aux2 = buscarTrabajador(aux.getIdJefe());
			if(aux2 == null || !"Jefe".equals(aux2.getTipo()) || !aux2.getActivo()) return null;
		}
		else {
			TEncargado aux = (TEncargado) t;
			if(aux.getIdJefe() == null) return null;
			TTrabajador aux2 = buscarTrabajador(aux.getIdJefe());
			if(aux2 == null || !"Jefe".equals(aux2.getTipo()) || !aux2.getActivo()) return null;
		}
		
		if(FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().guardarTrabajador(t)) {
			if("Vendedor".equals(t.getTipo())) {
				TVendedor aux = (TVendedor) t;
				TJefe aux2 = (TJefe) buscarTrabajador(aux.getIdJefe());
				aux2.setNumSub(aux2.getNumSub() + 1);
				modificarTrabajador(aux2);
			} else if("Encargado".equals(t.getTipo())) {
				TEncargado aux = (TEncargado) t;
				TJefe aux2 = (TJefe) buscarTrabajador(aux.getIdJefe());
				aux2.setNumSub(aux2.getNumSub() + 1);
				modificarTrabajador(aux2);
			}
			return t.getId();
		}
		else return null;
	}

	@Override
	public boolean eliminarTrabajador(String id) {
		if(id == null) return false;
		TTrabajador t = buscarTrabajador(id);
		if(t == null || !t.getActivo()) return false;
		if("Jefe".equals(t.getTipo()) && ((TJefe) t).getNumSub() != 0) return false;
		if("Encargado".equals(t.getTipo()) && !FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().atraccionesEncargado(id).isEmpty()) return false;
		if(FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().eliminarTrabajador(id)) {
			if("Vendedor".equals(t.getTipo())) {
				TVendedor aux = (TVendedor) t;
				TJefe aux2 = (TJefe) buscarTrabajador(aux.getIdJefe());
				aux2.setNumSub(aux2.getNumSub() - 1);
				modificarTrabajador(aux2);
			} else if("Encargado".equals(t.getTipo())) {
				TEncargado aux = (TEncargado) t;
				TJefe aux2 = (TJefe) buscarTrabajador(aux.getIdJefe());
				aux2.setNumSub(aux2.getNumSub() - 1);
				modificarTrabajador(aux2);
			}
			return true;
		} else return false;
	}

	@Override
	public TTrabajador buscarTrabajador(String id) {
		if(id == null) return null;
		return FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().buscarTrabajador(id);
	}

	@Override
	public List<TTrabajador> listarTrabajadores() {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().listarTrabajadores();
	}

	@Override
	public boolean modificarTrabajador(TTrabajador t) {
		if(t.getId() == null || t.getId() == "" || t.getNombre() == null || t.getApellido() == null || t.getIdDep() < 0) return false;
		TDepartamento depAux = FactoriaAbstractaIntegracion.getInstance().crearDAODepartamentos().buscarDepartamento(t.getIdDep());
		if(depAux == null || !depAux.isActivo()) return false;
		TTrabajador aux = buscarTrabajador(t.getId());
		if(aux == null) return false;
		
		//Si los campos son vacios se toman valores por defecto
		if(t.getNombre().equals("")) t.setNombre(aux.getNombre());
		if(t.getApellido().equals("")) t.setApellido(aux.getApellido());
		if(t.getIdDep() < 0) t.setIdDep(aux.getIdDep());
		
		if("Jefe".equals(t.getTipo())) {
			TJefe aux2 = (TJefe) t;
			if(aux2.getNumSub() < 0) return false;
		}
		else if("Vendedor".equals(t.getTipo())) {
			TVendedor aux2 = (TVendedor) t;
			if(aux2.getIdJefe() == null || aux2.getNumVentas() < 0) return false;
			TTrabajador aux3 = buscarTrabajador(aux2.getIdJefe());
			if(aux3 == null || !"Jefe".equals(aux3.getTipo()) || !aux3.getActivo()) return false;
		}
		else {
			TEncargado aux2 = (TEncargado) t;
			if(aux2.getIdJefe() == null) return false;
			TTrabajador aux3 = buscarTrabajador(aux2.getIdJefe());
			if(aux3 == null || !"Jefe".equals(aux3.getTipo()) || !aux3.getActivo()) return false;
		}
		
		//Comprobacones 1 a N
		if(!t.getActivo()) {
			if("Jefe".equals(t.getTipo()) && ((TJefe) t).getNumSub() != 0) return false;
			if("Encargado".equals(t.getTipo()) && !FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().atraccionesEncargado(t.getId()).isEmpty()) return false;
		}
		
		//Ajustes de atributos
		if(FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().modificarTrabajador(t)) {
			if("Vendedor".equals(t.getTipo())) {
				TVendedor aux3 = (TVendedor) t;
				TJefe aux2 = (TJefe) buscarTrabajador(aux3.getIdJefe());
				
				if(t.getActivo() && !aux.getActivo()) aux2.setNumSub(aux2.getNumSub() + 1);
				else if(!t.getActivo() && aux.getActivo()) aux2.setNumSub(aux2.getNumSub() - 1);
				
				return modificarTrabajador(aux2);
			} else if("Encargado".equals(t.getTipo())) {
				TEncargado aux3 = (TEncargado) t;
				TJefe aux2 = (TJefe) buscarTrabajador(aux3.getIdJefe());
				
				if(t.getActivo() && !aux.getActivo()) aux2.setNumSub(aux2.getNumSub() + 1);
				else if(!t.getActivo() && aux.getActivo()) aux2.setNumSub(aux2.getNumSub() - 1);
				
				return modificarTrabajador(aux2);
			}
			return true;
		} else return false;
	}
	
	public boolean increaseNumVentas(int amount, String id) {
		if(id == null) return false;
		try {
			TVendedor t = (TVendedor) buscarTrabajador(id);
			t.setNumVentas(t.getNumVentas() + amount);
			return modificarTrabajador(t);
		} catch(Exception e) {
			return false;
		}
	}

	private boolean formatoId(String id) {
		if(id.length() != 9) return false;
		for(int i = 0; i < 8; i++) {
			if(!Character.isDigit(id.charAt(i))) return false;
		}
		if(!Character.isUpperCase(id.charAt(8))) return false;
		return true;
	}
}
