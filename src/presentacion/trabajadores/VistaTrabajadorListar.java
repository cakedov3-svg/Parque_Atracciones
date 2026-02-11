package presentacion.trabajadores;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import negocio.trabajadores.TTrabajador;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;

public class VistaTrabajadorListar  extends JFrame implements IGUI{
	
	ModeloTabla modelo;
	static boolean creado = false;

	
	public VistaTrabajadorListar(){
		super("Listar trabajadores");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		modelo = new ModeloTabla();
		JTable tabla = new JTable(modelo);
		JScrollPane scroll = new JScrollPane(tabla);
		mainPanel.add(scroll);
		
		//Añadir botones ok
		JPanel botones = new JPanel();
		botones.setAlignmentX(CENTER_ALIGNMENT);
		botones.setLayout(new FlowLayout());
		
		//Panel del botón de vuelta atrás:
		JPanel buttonPanelBack = new JPanel();
		buttonPanelBack.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JButton backButton = new JButton("Atras");
		backButton.addActionListener((e) -> cerrarVentana());
		buttonPanelBack.add(backButton);
		
		mainPanel.add(buttonPanelBack);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				cerrarVentana();
			}
		});

		
		if(!creado) {
			creado = true;
			Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_LISTAR, null);
		}
		
		pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(false);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.TRABAJADOR_LISTAR_OK) {
			List<TTrabajador> lista = (List<TTrabajador>) datos;
			modelo.update(lista);
			pack();
			setVisible(true);
		}
	}
	
	private void cerrarVentana() {
		setVisible(false);
		creado = false;
		Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_VISTA_INICIO, null);
	}

}
