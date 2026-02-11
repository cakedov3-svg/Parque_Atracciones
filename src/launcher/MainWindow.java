package launcher;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.utils.Utils;

public class MainWindow extends JFrame{
	private static final int ROWS = 3;
	private static final int COLS = 3;
	private static final int GAP = 10;
	
	public MainWindow(){
		super("Gestion: Parque De Atracciones");
		initGUI();
	}
	
	void initGUI() {
		//Establecer panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		Border mainBorder = new LineBorder(new Color(255, 255, 255), 25);
		mainPanel.setBorder(mainBorder);
		setContentPane(mainPanel);
		
		//Titulo
		JPanel titulo = new JPanel();
		titulo.setBackground(new Color(255, 255, 255));
		Border border = BorderFactory.createLineBorder(new Color(255, 255, 255), 5);
		titulo.setBorder(border);
		titulo.setLayout(new FlowLayout());
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel parqueAtracciones = new JLabel("Parque de atracciones");
		Font fuente=new Font("Times New Roman", Font.BOLD, 25);
		parqueAtracciones.setFont(fuente);
		titulo.add(parqueAtracciones);
		mainPanel.add(titulo);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(new Color(255, 255, 255));
		panelButtons.setBorder(border);
		panelButtons.setLayout(new GridLayout(ROWS, COLS, GAP ,GAP));
		
		//Creacion de Botones:
		panelButtons.add(crearButton("Subsistema Trabajadores", EventosControlador.TRABAJADOR_VISTA_INICIO));
		panelButtons.add(crearButton("Subsistema Clientes", EventosControlador.CLIENTE_VISTA_INICIO));
		panelButtons.add(crearButton("Subsistema Facturas", EventosControlador.FACTURAS_INICIO));
		panelButtons.add(crearButton("Subsistema Eventos", EventosControlador.EVENTOS_VISTA_INICIO));
		panelButtons.add(crearButton("Subsistema Atracciones", EventosControlador.ATRACCIONES_VISTA_INICIO));
		panelButtons.add(crearButton("Subsistema Productos", EventosControlador.PRODUCTOS_VISTA_INICIO));
		JPanel aux = new JPanel(); aux.setBackground(new Color(255, 255, 255));
		panelButtons.add(aux);
		panelButtons.add(crearButton("Subsistema Departamentos", EventosControlador.DEPARTAMENTOS_VISTA_INICIO));		
		mainPanel.add(panelButtons);
		
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				Utils.quit(MainWindow.this);
			}
		});
		
		setLocationRelativeTo(null);
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

}
