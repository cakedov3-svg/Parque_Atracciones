package presentacion.productos;

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

public class VistaProductosInicio extends JFrame implements IGUI{

	public VistaProductosInicio() {
		super("Subsistema Productos");
		initGUI();
	}
	
	void initGUI() {
		//Creamos panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
		
		//Creamos el panel del titulo
		JPanel titlePanel = new JPanel();
		titlePanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel title = new JLabel("Productos");
		Font letter = new Font("Dialog", Font.BOLD, 20);
		title.setFont(letter);
		titlePanel.add(title);
		titlePanel.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(titlePanel);
		
		//Creamos panel donde iran los botones(mutadores)
		JPanel buttonPanelSup = new JPanel();
		buttonPanelSup.setLayout(new FlowLayout());
		buttonPanelSup.setAlignmentX(CENTER_ALIGNMENT);
		
		//Boton crear
		buttonPanelSup.add(crearButton("Crear", EventosControlador.PRODUCTOS_VISTA_CREAR));
		//Boton eliminar
		buttonPanelSup.add(crearButton("Eliminar", EventosControlador.PRODUCTOS_VISTA_ELIMINAR));
		//Boton modificar
		buttonPanelSup.add(crearButton("Modificar", EventosControlador.PRODUCTOS_VISTA_MODIFICAR));
	
		mainPanel.add(buttonPanelSup);
		
		
		//Creamos panel donde iran los botones(observadores)
		JPanel buttonPanelInf = new JPanel();
		buttonPanelInf.setLayout(new FlowLayout());
		buttonPanelInf.setAlignmentX(CENTER_ALIGNMENT);
		
		//Boton buscar
		buttonPanelInf.add(crearButton("Buscar", EventosControlador.PRODUCTOS_VISTA_BUSCAR));
		//Boton listar
		buttonPanelInf.add(crearButton("Listar", EventosControlador.PRODUCTOS_VISTA_LISTAR));
	
		mainPanel.add(buttonPanelInf);

		JPanel rigid = new JPanel();
		rigid.add(Box.createRigidArea(new Dimension(0,2)));
		mainPanel.add(rigid);
		
		
		//Panel del botón de vuelta atrás
		JPanel atras = new JPanel();
		atras.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JButton atrasButton = new JButton("Atrás");
		atrasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.SISTEMA_PANTALLA_INICIO, null);
			}
		});
		atras.add(atrasButton);
		mainPanel.add(atras);
		
		this.addWindowListener (new WindowAdapter() {
		public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.SISTEMA_PANTALLA_INICIO, -1);
			}
		});
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
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

	@Override
	public void actualizar(int evento, Object datos) { }
}
