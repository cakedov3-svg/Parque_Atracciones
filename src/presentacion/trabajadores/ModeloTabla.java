package presentacion.trabajadores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.trabajadores.TTrabajador;

public class ModeloTabla extends AbstractTableModel{
	
	List<TTrabajador> lista;
	String [] header = { "id", "nombre", "apellido", "tipo", "idDep", "activo"};
	
	public ModeloTabla() {
		lista = new ArrayList<>();
	}
	
	@Override
	public String getColumnName(int col) {
		return header[col];
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return lista.get(rowIndex).getId();
		} else if(columnIndex == 1) {
			return lista.get(rowIndex).getNombre();
		} else if(columnIndex == 2) {
			return lista.get(rowIndex).getApellido();
		} else if(columnIndex == 3) {
			return lista.get(rowIndex).getTipo();
		} else if(columnIndex == 4){
			return lista.get(rowIndex).getIdDep();
		} else {
			if(lista.get(rowIndex).getActivo()) return "true";
			else return false;
		}
	}
	
	public void update(List<TTrabajador> l) {
		if(l != null) lista = l;
		else lista = new ArrayList<>();
		fireTableStructureChanged();
	}

	
}
