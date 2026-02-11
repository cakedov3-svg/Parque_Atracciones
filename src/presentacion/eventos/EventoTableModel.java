package presentacion.eventos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.eventos.TEvento;

public class EventoTableModel extends AbstractTableModel {

	private String[] _header = { "Id", "Nombre", "Tipo", "Aforo" };
	private static final int NUM_COLS = 4;	
	private List<TEvento> eventosList;
	
	public EventoTableModel() {
		eventosList = new ArrayList<>();
	}
	
	@Override
	public int getRowCount() {
		return eventosList.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return _header[col];
	}

	@Override
	public int getColumnCount() {
		return _header.length;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex < 0 || rowIndex >= eventosList.size() || columnIndex < 0 || columnIndex >= NUM_COLS) 
			throw new IllegalArgumentException("Posicion ilegal de la tabla seleccionada");
		switch(columnIndex) {
		case 0:
			return eventosList.get(rowIndex).getId();
		case 1:
			return eventosList.get(rowIndex).getNombre();
		case 2:
			return eventosList.get(rowIndex).getTipo();
		case 3:
			return eventosList.get(rowIndex).getAforo();
		}
		return null;
	}

	public void update(List<TEvento> lista) {
		if(lista != null) eventosList = lista;
		else eventosList = new ArrayList<>();
		fireTableStructureChanged();
	}

}
