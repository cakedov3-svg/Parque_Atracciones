package presentacion.clientes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import negocio.clientes.TCliente;

public class ModeloTablaListar extends AbstractTableModel{
	List<TCliente> lista;
	String[] headers= {"id", "nombre", "apellido"};
	
	public ModeloTablaListar(){
		this.lista = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
		}
	
	@Override
	public String getColumnName(int col) {
		return headers[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return lista.get(row).getId();
		} else if(col == 1) {
			return lista.get(row).getNombre();
		} else if(col == 2) {
			return lista.get(row).getApellido();
		}
		else return false;
	}
	

	public void update(List<TCliente> l) {
		if(l != null) lista = l;
		else lista = new ArrayList<>();
		fireTableStructureChanged();
	}
}
