package presentacion.facturas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.facturas.TLineaFactura;

class ModeloTablaLineas extends AbstractTableModel {

	String[] header = { "Id", "Producto", "Cantidad", "Precio"};
	List<TLineaFactura> lineas;
	
	//CONSTRUCTOR------------------------------------------
	ModeloTablaLineas() {
		this.lineas = new ArrayList<TLineaFactura>();
	}
	

	// OVERRIDED METHODS OF AbstractTableModel-------------
	@Override
	public int getRowCount() {
		return lineas.size();
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
	public String getValueAt(int row, int col){
	TLineaFactura lin = lineas.get(row);
	switch(col) {
		case 0:
			return Integer.toString(lin.getId());
		case 1:
			return lin.getNombreProducto(); 
		case 2:
			return Integer.toString(lin.getCantidad());
		case 3:
			return Double.toString(lin.getPrecio()) + "€";
		default: 
			return null;
		}
	}

	@Override
	public String getColumnName(int col) {
	    return header[col];
	}

	public void actTabla(List<TLineaFactura> lista) {
		lineas = lista;
		fireTableDataChanged();
	}
}