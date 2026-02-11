package presentacion.facturas;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import negocio.facturas.TFactura;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;

public class VistaFacturaListar extends JFrame implements IGUI{
	
	ModeloTablaFacturas modeloTablaFacturas;
	JComboBox<String> lineasBox;
	DefaultComboBoxModel<String> idsFact;
	int idFactSelect;
	
	
	public VistaFacturaListar(){
		super("Subsistema Facturas");
		initGUI();
	}
	
	void initGUI(){
		//Establecer el panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setPreferredSize(new Dimension(500, 300));
		mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
		
		//Añadimos el título de la vista
		JPanel titlePanel = new JPanel();
		titlePanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel title = new JLabel("Lista de Facturas");
		Font letter = new Font("Dialog", Font.BOLD, 20);
		title.setFont(letter);
		titlePanel.add(title);
		titlePanel.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(titlePanel);
				
		//Creamos la tabla con los datos
		modeloTablaFacturas = new ModeloTablaFacturas();
		JTable tabla = new JTable(modeloTablaFacturas);
		JScrollPane tablePanel = new JScrollPane(tabla);
		mainPanel.add(tablePanel);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		
		//Creamos el área de consulta de LineasFactura:
		bottomPanel.add(new JLabel("Factura: "));
		idsFact = new DefaultComboBoxModel<String>();
		lineasBox = new JComboBox<String>(idsFact);
		lineasBox.addActionListener((e)->{
			idFactSelect = Integer.parseInt(idsFact.getElementAt(lineasBox.getSelectedIndex()));
		});
		
		bottomPanel.add(lineasBox);
		bottomPanel.add(new JLabel("   "));
		//bottomPanel.add(crearButton("Consultar Lineas", EventosControlador.FACTURAS_CONSULTAR_LINEAS, idFactSelect, true));
		JButton consultLin = new JButton("Consultar Lineas");
		consultLin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				Point data = new Point(idFactSelect, EventosControlador.FACTURAS_LINEAS_OK_2LISTAR);
				Controlador.getInstancia().accion(EventosControlador.FACTURAS_CONSULTAR_LINEAS, data);
			}
			
		});
		bottomPanel.add(consultLin);
		bottomPanel.add(new JSeparator());
		
		//Creamos el botón Atrás
		bottomPanel.add(crearButton("Atrás", EventosControlador.FACTURAS_INICIO));

		mainPanel.add(bottomPanel);
		
		this.addWindowListener (new WindowAdapter() {
				public void windowClosing(WindowEvent e) { 
					setVisible(false);
					Controlador.getInstancia().accion(EventosControlador.FACTURAS_INICIO, null);
				}
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private JButton crearButton(String title, int evento) {
		return crearButton(title, evento, null, false);
	}
	
	private JButton crearButton(String title, int evento, Object datos, boolean visible) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(visible);
				Controlador.getInstancia().accion(evento, datos);
			}
		});
		return button;
	}

	
	
	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.FACTURAS_LISTAR_OK) {
			List<TFactura> lista = (List<TFactura>) datos;
			idsFact.removeAllElements();
			for(TFactura tF: lista) {
				idsFact.addElement(Integer.toString(tF.getId()));
			}
			modeloTablaFacturas.actTabla(lista);
			pack();
			setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar las lineas de la factura");
		}
	}
}
