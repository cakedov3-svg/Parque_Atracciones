package presentacion.factoria;

import presentacion.controlador.IGUI;

public abstract class FactoriaAbstractaPresentacion {
	
	private static FactoriaAbstractaPresentacion instancia;
	
	public static FactoriaAbstractaPresentacion getInstance() {
		if(instancia == null) instancia = new FactoriaPresentacion();
		return instancia;
	}

	public abstract IGUI createVista(int id);
}
