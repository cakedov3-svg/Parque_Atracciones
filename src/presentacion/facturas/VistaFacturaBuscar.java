package presentacion.facturas;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaFacturaBuscar extends JFrame implements IGUI {

	JTextField idFactura;
	int id; 
	
	public VistaFacturaBuscar() {
		super("Buscar Factura");
		initGUI();
	}
	
	void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
		
		//Añadimos el título de la vista
		JPanel titlePanel = new JPanel();
		titlePanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel title = new JLabel("Buscar Factura");
		Font letter = new Font("Dialog", Font.BOLD, 20);
		title.setFont(letter);
		titlePanel.add(title);
		titlePanel.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(titlePanel);
		
		//Panel Superior:
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.setAlignmentX(CENTER_ALIGNMENT);
		topPanel.add(new JLabel("Introduce el Id de la Factura: "));
		
		idFactura = new JTextField(10);
		idFactura.setEditable(true);
		idFactura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = Integer.parseInt(idFactura.getText());
			}
		});
		topPanel.setBorder(new EmptyBorder(0,0,10,0));
		topPanel.add(idFactura);
		
		mainPanel.add(topPanel);
		
		mainPanel.add(new JSeparator());
		
		//Panel Inferior:
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setAlignmentX(CENTER_ALIGNMENT);
		bottomPanel.setBorder(new EmptyBorder(10,0,0,0));
		
		bottomPanel.add(crearButton("Cancelar", EventosControlador.FACTURAS_INICIO, null)); 		
		
		JButton OKButton = new JButton("OK");
		OKButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(idFactura.getText() == null || "".equals(idFactura.getText())) {
					Utils.showErrorMsg("Debe introducir un id.");
				}
				else {
					Utils.getWindow(OKButton).setVisible(false);
					int nextEvento = EventosControlador.FACTURAS_LINEAS_OK_2INI;
					Point datos = new Point(id, nextEvento);
					Controlador.getInstancia().accion(EventosControlador.FACTURAS_CONSULTAR_LINEAS, datos);
				}
			}
		});
		bottomPanel.add(OKButton);

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
	
	private JButton crearButton(String title, int evento, Object datos) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utils.getWindow(button).setVisible(false);
				Controlador.getInstancia().accion(evento, datos);
			}
		});
		return button;
	}

	@Override
	public void actualizar(int evento, Object datos) { }

}
