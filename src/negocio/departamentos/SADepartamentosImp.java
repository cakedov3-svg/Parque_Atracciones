package negocio.departamentos;

import java.util.List; 

import integracion.factoria.FactoriaAbstractaIntegracion;
import negocio.trabajadores.TTrabajador;

public class SADepartamentosImp implements SADepartamentos {

	@Override
	public List<TDepartamento> listarDepartamentos() {
		return FactoriaAbstractaIntegracion.getInstance().crearDAODepartamentos().listarDepartamentos();
	}

	@Override
	public TDepartamento buscarDepartamento(int idDep) {
		if(idDep<0)return null;
		else {
			return FactoriaAbstractaIntegracion.getInstance().crearDAODepartamentos().buscarDepartamento(idDep);
		}
	}

	@Override
	public boolean modificarDepartamento(TDepartamento d) {
		if(d.getidDep()<0)return false;
		TDepartamento aux=buscarDepartamento(d.getidDep());
		if(aux==null) {
			return false;
		}
		List<TTrabajador> lista = FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().quedanTrabajadores(d.getidDep());
		if(!lista.isEmpty())return false;
		else{
			return FactoriaAbstractaIntegracion.getInstance().crearDAODepartamentos().modificarDepartamento(d);
		}
	}

	@Override
	public int anyadirDepartamento(TDepartamento d) {
		if(d.getNombre()=="" || d.getNombre()==null || d.isActivo()==false)return -1;
		TDepartamento aux=buscarDepartamento(d.getidDep());
		if(aux!=null && aux.isActivo())return -1;
		else {
			return FactoriaAbstractaIntegracion.getInstance().crearDAODepartamentos().anyadirDepartamento(d);
		}
	}

	@Override
	public boolean eliminarDepartamento(int idDep) {
		if(idDep<0 || this.buscarDepartamento(idDep)==null)return false;
		List<TTrabajador> lista = FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().quedanTrabajadores(idDep);
		if(!lista.isEmpty())return false;
		else {
			return FactoriaAbstractaIntegracion.getInstance().crearDAODepartamentos().eliminarDepartamento(idDep);
		}
	}

}
