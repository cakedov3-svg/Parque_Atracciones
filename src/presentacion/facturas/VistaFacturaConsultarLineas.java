package presentacion.facturas;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import negocio.facturas.TDatosVenta;
import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaFacturaConsultarLineas extends JFrame implements IGUI{

	ModeloTablaLineas modeloTablaLineas;
	JTextArea infoFactura;
	JPanel topPanel;
	boolean toIni;
	
	public VistaFacturaConsultarLineas(){
		super("Consultando Lineas de la Factura");
		initGUI();
	}
	
	void initGUI(){
		//Establecer el panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
		
		//Añadimos el título de la vista
		JPanel titlePanel = new JPanel();
		titlePanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel title = new JLabel("Consultar Factura");
		Font letter = new Font("Dialog", Font.BOLD, 20);
		title.setFont(letter);
		titlePanel.add(title);
		titlePanel.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(titlePanel);
		
		mainPanel.add(crearTopPanel());
		
		//Creamos el titulo de la tabla
		JPanel tableTPanel = new JPanel();
		tableTPanel.setAlignmentX(RIGHT_ALIGNMENT);
		JLabel tableTitle = new JLabel("Lineas de la Factura:");
		Font letter2 = new Font("Dialog", Font.BOLD, 15);
		tableTitle.setFont(letter2);
		tableTPanel.add(tableTitle);
		tableTPanel.setBorder(new EmptyBorder(0,0,5,0));
		mainPanel.add(tableTPanel);
		
		//Creamos la tabla con los datos
		modeloTablaLineas = new ModeloTablaLineas();
		JTable tabla = (new JTable(modeloTablaLineas));
		tabla.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane tablePanel = new JScrollPane(tabla);
		mainPanel.add(tablePanel);
		
		
		//Panel del botón de vuelta atrás:
		JPanel buttonPanelBack = new JPanel();
		buttonPanelBack.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		toIni = false;
		JButton atras = new JButton("Atrás");
		atras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utils.getWindow(atras).setVisible(false);
				if(toIni)
					Controlador.getInstancia().accion(EventosControlador.FACTURAS_INICIO, null);
			}
		});
		
		//buttonPanelBack.add(crearButton("Atrás", false));
		buttonPanelBack.add(atras);
		mainPanel.add(buttonPanelBack);
		
		this.addWindowListener (new WindowAdapter() {
				public void windowClosing(WindowEvent e) { 
					setVisible(false);
				}
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private JPanel crearTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,3,10,10));
		topPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
		return topPanel;
	}

	void actInfoFactura(TFactura tF) {
		topPanel.removeAll();
		topPanel.add(new JLabel("Id Factura:  " + tF.getId()));
		topPanel.add(new JLabel("Precio Total:  " + tF.getPrecioTotal()));
		topPanel.add(new JLabel("Fecha:  " + tF.getFecha()));
		topPanel.add(new JLabel("Id Cliente:  " + tF.getIdCliente()));
		topPanel.add(new JLabel("Vendedor:  " + tF.getIdVendedor()));
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.FACTURAS_LINEAS_OK_2INI|| evento == EventosControlador.FACTURAS_LINEAS_OK_2LISTAR) {
			if(evento == EventosControlador.FACTURAS_LINEAS_OK_2INI) toIni = true;
			
			TDatosVenta tDV = (TDatosVenta) datos;
			List<TLineaFactura> lista = tDV.getLineas();
			modeloTablaLineas.actTabla(lista);
			actInfoFactura(tDV.getFactura());
			pack();
			setVisible(true);
		}
		else if(evento == EventosControlador.FACTURAS_LINEAS_KO) {
			Utils.showErrorMsg("Ha ocurrido un error al mostrar las lineas de la factura.");
			setVisible(false);
		}
	}
}
