package presentacion.departamentos;

import java.util.ArrayList; 
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.departamentos.TDepartamento;

public class ModeloTablaDepartamentos extends AbstractTableModel {
	
	List<TDepartamento> list;
	String [] _header = { "idDep", "nombre"};
	
	public ModeloTablaDepartamentos() {
		list = new ArrayList<>();
	}
	
	@Override
	public String getColumnName(int col) {
		return _header[col];
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return _header.length;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(col==0) {
			return list.get(row).getidDep();
		}
		else {
			return list.get(row).getNombre();
		}
	}
	
	public void update(List<TDepartamento> l) {
		if(l!=null)this.list=l;
		else this.list=new ArrayList<>();
		fireTableStructureChanged();
	}

}
