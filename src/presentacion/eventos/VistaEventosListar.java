package presentacion.eventos;

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

import negocio.eventos.TEvento;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaEventosListar extends JFrame implements IGUI {
	
	EventoTableModel modeloTablaEventos;
	static boolean ini = false;
	private static final String ATRAS_TITLE = "Atrás";
	private static final String ERROR_LISTAR_MSG = "Ha ocurrido un error al listar los eventos.";
	
	public VistaEventosListar() {
		super("Listar Eventos");
		initGUI();
	}

	private void initGUI() {
		
		//Panel Principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15,25,15,25));
		
		//TITULO
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Lista de eventos");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		//Tabla de eventos
		modeloTablaEventos = new EventoTableModel();
		JTable tablaEventos = new JTable(modeloTablaEventos);
		tablaEventos.setColumnSelectionAllowed(false);
		tablaEventos.setRowSelectionAllowed(false);
		JScrollPane tabla = new JScrollPane(tablaEventos);
		mainPanel.add(tabla);
		
		//Boton Atras
		JPanel atras = new JPanel();
		atras.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JButton atrasButton = new JButton(ATRAS_TITLE);
		atrasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ini = false;
				Controlador.getInstancia().accion(EventosControlador.EVENTOS_VISTA_INICIO, null);
			}
		});
		atras.add(atrasButton);
		mainPanel.add(atras);
		
		//Boton x funcional
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				ini = false;
				Controlador.getInstancia().accion(EventosControlador.EVENTOS_VISTA_INICIO, null);
			}
		});
		
		if(!ini) {
			ini = true;
			Controlador.getInstancia().accion(EventosControlador.EVENTOS_LISTAR, null);
		}
		
		//Ajustes sobre la ventana
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(false);
		
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.EVENTOS_LISTAR_OK) {
			List<TEvento> lista = (List<TEvento>) datos;
			modeloTablaEventos.update(lista);
			pack();
			setVisible(true);
		}
		else if (evento == EventosControlador.EVENTOS_LISTAR_ERROR) {
			Utils.showErrorMsg(ERROR_LISTAR_MSG);
		}
	}

}
