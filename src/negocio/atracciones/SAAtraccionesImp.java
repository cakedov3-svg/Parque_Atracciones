package negocio.atracciones;

import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;

public class SAAtraccionesImp implements SAAtracciones{
	
	private static final int MIN_NIVEL_EMOCION = 0;
	private static final int MAX_NIVEL_EMOCION = 10;
	private static final int MIN_AFORO = 1;
	private static final int MAX_AFORO = 100;
	private static final int MIN_LONGITUD_NOMBRE = 1;
	private static final int MAX_LONGITUD_NOMBRE = 30;

	@Override
	public int crearAtraccion(TAtraccion atraccion) throws IllegalArgumentException {
		if(atraccion.getNombre().equals(null)||atraccion.getNombre().equals("")||atraccion.getNombre().length()<MIN_LONGITUD_NOMBRE||atraccion.getNombre().length()>MAX_LONGITUD_NOMBRE) throw new IllegalArgumentException("La longitud del nombre de la atraccion debe estar entre "+ MIN_LONGITUD_NOMBRE+" y "+MAX_LONGITUD_NOMBRE); //El campo nombre o el campo idEncargado esta vacio
		else if(atraccion.getAforo()<MIN_AFORO||atraccion.getAforo()>MAX_AFORO) throw new IllegalArgumentException("El aforo debe estar comprendido entre "+MIN_AFORO+" y "+MAX_AFORO);
		else if(atraccion.getNivelEmocion()<MIN_NIVEL_EMOCION||atraccion.getNivelEmocion()>MAX_NIVEL_EMOCION) throw new IllegalArgumentException("El nivel de emocion debe estar comprendido entre "+MIN_NIVEL_EMOCION+" y "+MAX_NIVEL_EMOCION);
		else if(atraccion.getIdEncargado().equals(null)||atraccion.getIdEncargado().equals("")) throw new IllegalArgumentException("El id del encargado no puede estar vacio");
		else if(FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().buscarEncargado(atraccion.getIdEncargado())==null)throw new IllegalArgumentException("No se ha podido crear la atraccion, ya que no existe ningun encargado activo con id "+atraccion.getIdEncargado());	//No existe el encargado al que se quiere asignar la atraccion
		else if (FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().buscarAtraccion(atraccion.getId())!=null)throw new IllegalArgumentException("No se ha podido crear la atraccion porque ya existe una con el mismo id"); //Ya existe una atraccion con ese id
		TAtraccion existente = buscarAtraccion (atraccion.getId());
		if(existente!=null) return -1;
		return FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().guardarAtraccion(atraccion);
	}

	@Override
	public boolean eliminarAtraccion(int id) throws IllegalArgumentException {
		if(id < 0 || buscarAtraccion(id) == null)  throw new IllegalArgumentException("No se ha encontrado la atraccion con id " + id);
		if(!FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().productoAsociadoAtraccion(id).isEmpty()) throw new IllegalArgumentException("No se puede eliminar, ya que hay productos asociados a la atraccion con id " + id);
		return FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().eliminarAtraccion(id);
	}

	@Override
	public TAtraccion buscarAtraccion(int id) {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().buscarAtraccion(id);
	}

	@Override
	public List<TAtraccion> listarAtracciones() {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().listarAtracciones();
	}

	@Override
	public boolean modificarAtraccion(TAtraccion atraccion, String nuevoValorActiva, String nuevoValorFamiliar) throws IllegalArgumentException {
		TAtraccion atraccionOriginal = buscarAtraccion(atraccion.getId());
		String nombre;
		boolean familiar;
		int aforo;
		int nivelEmocion;
		String idEncargado;
		boolean activa;
		if(atraccionOriginal==null)throw new IllegalArgumentException("No se ha encontrado la atraccion con id "+atraccion.getId());
		else if(atraccion.getNombre().equals(null)||atraccion.getNombre().equals("")) nombre = atraccionOriginal.getNombre();
		else nombre = atraccion.getNombre();
		if(nuevoValorFamiliar!=null) familiar=atraccion.getFamiliar();
		else familiar = atraccionOriginal.getFamiliar();
		if(nuevoValorActiva!=null) activa = atraccion.getActiva();
		else activa = atraccionOriginal.getActiva();
		if(atraccion.getAforo()==-1) aforo = atraccionOriginal.getAforo();
		else aforo = atraccion.getAforo();
		if(atraccion.getNivelEmocion()==-1)nivelEmocion = atraccionOriginal.getNivelEmocion();
		else nivelEmocion = atraccion.getNivelEmocion();
		if(atraccion.getIdEncargado().equals(null)||atraccion.getIdEncargado().equals("")) idEncargado = atraccionOriginal.getIdEncargado();
		else idEncargado = atraccion.getIdEncargado();
		
		TAtraccion nuevaAtraccion = new TAtraccion(atraccion.getId(), nombre, familiar, aforo, nivelEmocion, activa, idEncargado);

		
		if(nuevaAtraccion.getNombre().equals(null)||nuevaAtraccion.getNombre().equals("")||nuevaAtraccion.getNombre().length()<MIN_LONGITUD_NOMBRE||nuevaAtraccion.getNombre().length()>MAX_LONGITUD_NOMBRE) throw new IllegalArgumentException("La longitud del nombre de la atraccion debe estar entre "+ MIN_LONGITUD_NOMBRE+" y "+MAX_LONGITUD_NOMBRE);
		else if(nuevaAtraccion.getAforo()<MIN_AFORO||nuevaAtraccion.getAforo()>MAX_AFORO) throw new IllegalArgumentException("El aforo debe estar comprendido entre "+MIN_AFORO+" y "+MAX_AFORO);
		else if(nuevaAtraccion.getNivelEmocion()<MIN_NIVEL_EMOCION||nuevaAtraccion.getNivelEmocion()>MAX_NIVEL_EMOCION) throw new IllegalArgumentException("El nivel de emocion debe estar comprendido entre "+MIN_NIVEL_EMOCION+" y "+MAX_NIVEL_EMOCION);
		else if(nuevaAtraccion.getIdEncargado().equals(null)||nuevaAtraccion.getIdEncargado().equals("")) throw new IllegalArgumentException("El id del encargado no puede estar vacio");
		else if(FactoriaAbstractaIntegracion.getInstance().crearDAOTrabajadores().buscarTrabajador(nuevaAtraccion.getIdEncargado())==null)throw new IllegalArgumentException("No se ha podido crear la atraccion, ya que no existe ningun encargado activo con id "+atraccion.getIdEncargado());	//No existe el encargado al que se quiere asignar la atraccion
		else if(!nuevaAtraccion.getActiva()&&!FactoriaAbstractaIntegracion.getInstance().crearDAOProducto().productoAsociadoAtraccion(nuevaAtraccion.getId()).isEmpty())  throw new IllegalArgumentException("No se puede modificar, ya que hay productos asociados a la atraccion con id " + nuevaAtraccion.getId()); 

		return FactoriaAbstractaIntegracion.getInstance().crearDAOAtracciones().modificarAtraccion(nuevaAtraccion);
	}

}
