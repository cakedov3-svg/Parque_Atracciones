package presentacion.atracciones;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;

public class VistaAtraccionesInicio extends JFrame implements IGUI{
	
	public VistaAtraccionesInicio() {
		super("Subsistema Atracciones");
		initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		setContentPane(panelPrincipal);
		panelPrincipal.setBorder(new EmptyBorder(15,25,15,25));
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Atracciones");
		Font fuente = new Font("Dialog", Font.BOLD, 20);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		panelPrincipal.add(panelTitulo);
		
		
		JPanel opcionesMutadoras = new JPanel();
		opcionesMutadoras.setLayout(new FlowLayout());
		opcionesMutadoras.setAlignmentX(CENTER_ALIGNMENT);
		JButton crear = crearButton("Crear", EventosControlador.ATRACCIONES_VISTA_CREAR);
		opcionesMutadoras.add(crear);
		JButton eliminar = crearButton("Eliminar", EventosControlador.ATRACCIONES_VISTA_ELIMINAR);
		opcionesMutadoras.add(eliminar);
		JButton modificar = crearButton("Modificar", EventosControlador.ATRACCIONES_VISTA_MODIFICAR);
		opcionesMutadoras.add(modificar);
		panelPrincipal.add(opcionesMutadoras);
		
		JPanel opcionesObservadoras = new JPanel();
		opcionesObservadoras.setLayout(new FlowLayout());
		opcionesObservadoras.setAlignmentX(CENTER_ALIGNMENT);
		JButton buscar = crearButton("Buscar", EventosControlador.ATRACCIONES_VISTA_BUSCAR);
		opcionesObservadoras.add(buscar);
		JButton listar = crearButton("Listar", EventosControlador.ATRACCIONES_VISTA_LISTAR);
		opcionesObservadoras.add(listar);
		panelPrincipal.add(opcionesObservadoras);
		
		JPanel panelAtras = new JPanel();
		panelAtras.setLayout(new FlowLayout());
		panelAtras.setAlignmentX(CENTER_ALIGNMENT);
		JButton atrasButton = crearButton("Atras", EventosControlador.SISTEMA_PANTALLA_INICIO);
		panelAtras.add(atrasButton);
		panelAtras.setBorder(new EmptyBorder(0,15,0,0));
		panelPrincipal.add(panelAtras);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.SISTEMA_PANTALLA_INICIO, -1);
			}
		});
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private JButton crearButton(String titulo, int evento) {
		JButton button = new JButton(titulo);
		button.addActionListener((e)-> realizarAccion(evento));
		return button;
	}
	
	private void realizarAccion(int evento) {
		setVisible(false);
		Controlador.getInstancia().accion(evento, null);
	}

	@Override
	public void actualizar(int evento, Object datos) {
	}
}
