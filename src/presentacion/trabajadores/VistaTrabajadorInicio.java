package presentacion.trabajadores;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VistaTrabajadorInicio extends JFrame implements IGUI{
	
	public VistaTrabajadorInicio(){
		super("Subsistema Trabajadores");
		initGUI();
	}
	
	private void initGUI(){
		//Establecer el panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15,25,15,25));
		
		//Panel de titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Trabajadores");
		Font fuente = new Font("Dialog", Font.BOLD, 20);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		//Primera fila de botones (mutadores)
		JPanel superior = new JPanel();
		superior.setLayout(new FlowLayout());
		superior.setAlignmentX(CENTER_ALIGNMENT);
		JButton crear = crearButton("Crear", EventosControlador.TRABAJADOR_VISTA_CREAR);
		superior.add(crear);
		JButton eliminar = crearButton("Eliminar", EventosControlador.TRABAJADOR_VISTA_ELIMINAR);
		superior.add(eliminar);
		JButton modificar = crearButton("Modificar", EventosControlador.TRABAJADOR_VISTA_MODIFICAR);
		superior.add(modificar);
		mainPanel.add(superior);
		
		//Segunda fila de botones (observadores)
		JPanel inferior = new JPanel();
		inferior.setLayout(new FlowLayout());
		inferior.setAlignmentX(CENTER_ALIGNMENT);
		JButton buscar = crearButton("Buscar", EventosControlador.TRABAJADOR_VISTA_BUSCAR);
		inferior.add(buscar);
		JButton listar = crearButton("Listar", EventosControlador.TRABAJADOR_VISTA_LISTAR);
		inferior.add(listar);
		mainPanel.add(inferior);
		
		//Panel del botón de vuelta atrás:
		JPanel buttonPanelBack = new JPanel();
		buttonPanelBack.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		buttonPanelBack.add(crearButton("Atrás", EventosControlador.SISTEMA_PANTALLA_INICIO));

		mainPanel.add(buttonPanelBack);
		
		
		
		this.addWindowListener (new WindowAdapter() {
				public void windowClosing(WindowEvent e) { 
					setVisible(false);
					Controlador.getInstancia().accion(EventosControlador.SISTEMA_PANTALLA_INICIO, null);
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
				Controlador.getInstancia().accion(evento, null);
				setVisible(false);
			}
		});
		return button;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		
	}

}
