package presentacion.clientes;

import java.awt.FlowLayout;

import java.awt.Font;
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
import javax.swing.border.EmptyBorder;

import negocio.clientes.TCliente;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaClienteListar extends JFrame implements  IGUI {

	ModeloTablaListar modelo;
	
	public VistaClienteListar(){
		super("Listado de clientes");
		initGUI();
	}
	private void initGUI() {
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//título
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Listar clientes");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		//LISTA
		modelo = new ModeloTablaListar();
		JTable tabla = new JTable(modelo);
		JScrollPane scrollTable = new JScrollPane(tabla);
		mainPanel.add(scrollTable);
		

		//Panel del boton de vuelta atras:
		JPanel buttonPanelBack = new JPanel();
		buttonPanelBack.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JButton atras=new JButton("Atrás");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(EventosControlador.CLIENTE_VISTA_INICIO, null);
				setVisible(false);
			}
			
		});
		buttonPanelBack.add(atras);
		mainPanel.add(buttonPanelBack);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.CLIENTE_VISTA_INICIO, null);
			}
		});
	
		
		pack();
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.CLIENTE_LISTAR_OK) {
			List<TCliente> lista = (List<TCliente>) datos;
			modelo.update(lista);
			pack();
			setVisible(true);
		}
	}
	
	private JButton crearButton(String title, int evento) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(evento, null);
			}
		});
		return button;
	}

}
