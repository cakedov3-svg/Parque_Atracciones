package presentacion.productos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.productos.TProducto;

public class TablaProductos extends AbstractTableModel {
	
	private String[] titulosTabla = {"Id", "Entrada Asoc.", "Nombre", "Precio", "Tipo"};
	List<TProducto> productos;

	public TablaProductos() {
		productos = new ArrayList<>();
	}
	
	@Override
	public String getColumnName(int col) {
		return titulosTabla[col];
	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		return titulosTabla.length;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return productos.get(rowIndex).getId();
		} 
		else if(columnIndex == 1) {
			return productos.get(rowIndex).getIdEntrada();
		}
		else if(columnIndex == 2) {
			return productos.get(rowIndex).getNombre();
		} 
		else if(columnIndex == 3) {
			return productos.get(rowIndex).getPrecio();
		} else {
			return productos.get(rowIndex).getTipo();
		} 
	}
	
	public void update(List<TProducto> l) {
		if(l != null) productos = l;
		else productos = new ArrayList<>();
		fireTableStructureChanged();
	}

}
