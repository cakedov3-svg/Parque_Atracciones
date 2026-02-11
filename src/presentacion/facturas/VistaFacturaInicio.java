package presentacion.facturas;
import java.awt.Dimension; 
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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaFacturaInicio extends JFrame implements IGUI{
	
	public VistaFacturaInicio(){
		super("Subsistema Facturas");
		initGUI();
	}
	
	void initGUI(){
		//Establecer el panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setPreferredSize(new Dimension(400, 200));
		mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
		
		//Añadimos el título de la vista
		JPanel titlePanel = new JPanel();
		titlePanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel title = new JLabel("Facturas");
		Font letter = new Font("Dialog", Font.BOLD, 20);
		title.setFont(letter);
		titlePanel.add(title);
		titlePanel.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(titlePanel);
		
		//Panel de los botones observadores
		JPanel buttonPanelTop = new JPanel();
		buttonPanelTop.setLayout(new FlowLayout());
		buttonPanelTop.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonPanelTop.add(crearButton("Buscar", EventosControlador.FACTURAS_BUSCAR_VISTA));
		buttonPanelTop.add(crearButton("Listar", EventosControlador.FACTURAS_LISTAR));

		mainPanel.add(buttonPanelTop);

		
		mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

		//Panel del botón Comprar
		JPanel buttonPanelBott = new JPanel();
		buttonPanelBott.setLayout(new FlowLayout());
		buttonPanelBott.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonPanelBott.add(crearButton("Comprar", EventosControlador.FACTURAS_ANYADIR_VISTA));

		mainPanel.add(buttonPanelBott);

		
		//mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		
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
