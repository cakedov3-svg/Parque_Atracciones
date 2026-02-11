package presentacion.atracciones;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.atracciones.TAtraccion;

public class ModeloTablaAtracciones extends AbstractTableModel{
	
	private List<TAtraccion> listaAtracciones;
	private String[] nombreColumnas = {"Id","Nombre", "Aforo", "Familiar", "Nivel de emocion", "Id del encargado"}; 
	
	public ModeloTablaAtracciones() {
		listaAtracciones = new ArrayList<>();
	}
	
	@Override
	public String getColumnName(int column) {
		return nombreColumnas[column];
	}

	@Override
	public int getRowCount() {
		return listaAtracciones.size();
	}
	
	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex < 0 || rowIndex >= getRowCount() || columnIndex < 0 || columnIndex >= getColumnCount()) 
			throw new IllegalArgumentException("La posicion seleccionada no existe");
		switch(columnIndex){
		case 0: return listaAtracciones.get(rowIndex).getId();
		case 1: return listaAtracciones.get(rowIndex).getNombre();
		case 2: return listaAtracciones.get(rowIndex).getAforo();
		case 3: {
			if(listaAtracciones.get(rowIndex).getFamiliar()) return "Si";
			else return "No";
		}
		case 4: return listaAtracciones.get(rowIndex).getNivelEmocion();
		case 5: return listaAtracciones.get(rowIndex).getIdEncargado();
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void actualizar(List<TAtraccion> nuevaListaAtracciones) {
		if(nuevaListaAtracciones==null)listaAtracciones = new ArrayList<>();
		else listaAtracciones=nuevaListaAtracciones;
		fireTableStructureChanged();
	}

	
	

}
