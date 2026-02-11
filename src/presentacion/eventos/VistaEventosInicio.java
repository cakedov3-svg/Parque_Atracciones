package presentacion.eventos;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaEventosInicio extends JFrame implements IGUI {
	
	private static final String ATRAS_TITLE = "Atrás";
	private static final String CREAR_EVENTO_TITLE = "Crear Evento";
	private static final String ELIMINAR_EVENTO_TITLE = "Eliminar Evento";
	private static final String BUSCAR_EVENTO_TITLE = "Buscar Evento";
	private static final String LISTAR_EVENTOS_TITLE = "Listar Eventos";
	private static final String MODIFICAR_EVENTO_TITLE = "Modificar Evento";
	
	
	public VistaEventosInicio() {
		super("Subsistema Eventos");
		initGUI();
	}

	private void initGUI() {
		
		//Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15,25,15,25));
		
		//Titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Eventos");
		Font fuente = new Font("Dialog", Font.BOLD, 20);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		//Primera fila de botones (mutadoras)
		JPanel l1 = new JPanel();
		l1.setLayout(new FlowLayout());
		l1.setAlignmentX(CENTER_ALIGNMENT);
		l1.add(crearButton(CREAR_EVENTO_TITLE, EventosControlador.EVENTOS_VISTA_CREAR));
		l1.add(crearButton(ELIMINAR_EVENTO_TITLE, EventosControlador.EVENTOS_VISTA_ELIMINAR));
		l1.add(crearButton(MODIFICAR_EVENTO_TITLE, EventosControlador.EVENTOS_VISTA_MODIFICAR));
		mainPanel.add(l1);
		
		//Segunda fila de botones (observadoras)
		JPanel l2 = new JPanel();
		l2.setLayout(new FlowLayout());
		l2.setAlignmentX(CENTER_ALIGNMENT);
		l2.add(crearButton(BUSCAR_EVENTO_TITLE, EventosControlador.EVENTOS_VISTA_BUSCAR));
		l2.add(crearButton(LISTAR_EVENTOS_TITLE, EventosControlador.EVENTOS_VISTA_LISTAR));
		mainPanel.add(l2);
		
		//Rigid Area
		JPanel rigid = new JPanel();
		rigid.add(Box.createRigidArea(new Dimension(0,2)));
		mainPanel.add(rigid);
		
		//Boton Atras
		JPanel atras = new JPanel();
		atras.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JButton atrasButton = new JButton(ATRAS_TITLE);
		atrasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.SISTEMA_PANTALLA_INICIO, null);
			}
		});
		atras.add(atrasButton);
		mainPanel.add(atras);
		
		//Boton x funcional
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.SISTEMA_PANTALLA_INICIO, null);
			}
		});
		
		//Ajustes sobre la ventana
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	

	private JButton crearButton(String title, int evento) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utils.getWindow(button).setVisible(false);
				Controlador.getInstancia().accion(evento, null);
			}
		});
		return button;
	}	

	@Override
	public void actualizar(int evento, Object datos) {
	}

}
