package presentacion.facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import negocio.facturas.TFactura;
import presentacion.controlador.Controlador;

class ModeloTablaFacturas extends AbstractTableModel{

	String[] header = { "Id", "Precio Total", "Id Cliente", "Id Vendedor", "Fecha" };
	List<TFactura> facturas;
	
	//CONSTRUCTOR------------------------------------------
	ModeloTablaFacturas() {
		this.facturas = new ArrayList<TFactura>();
	}
	

	// OVERRIDED METHODS OF AbstractTableModel-------------
	@Override
	public int getRowCount() {
		return facturas.size();
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
	public Object getValueAt(int row, int col){
	TFactura fact = facturas.get(row);
	switch(col) {
		case 0:
			return Integer.toString(fact.getId());
		case 1:
			return Double.toString(fact.getPrecioTotal()) + "€"; 
		case 2:
			return fact.getIdCliente();
		case 3:
			return fact.getIdVendedor();
		case 4:
			return fact.getFecha();
		default: 
			return null;
		}
	}

	@Override
	public String getColumnName(int col) {
	    return header[col];
	}
	
	private JButton crearButton(String title, int evento, Object datos) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(evento, datos);
			}
		});
		return button;
	}

	void actTabla(List<TFactura> lista){
		facturas = lista;
		fireTableDataChanged();
	}
	
}
