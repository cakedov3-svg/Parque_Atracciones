package presentacion.atracciones;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import negocio.atracciones.TAtraccion;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaAtraccionesCrear extends JFrame implements IGUI{
		
	//Constantes
	
	private static final int MIN_AFORO = 1;
	private static final int MAX_AFORO = 100;
	private static final int MIN_NIVEL_EMOCION = 1;
	private static final int MAX_NIVEL_EMOCION = 10;
	
	//Atributos
	private JTextField nombre;
	private JRadioButton siFamiliar;
	private JRadioButton noFamiliar;
	private JSpinner aforo;
	private JSpinner nivel_emocion;
	private JTextField id_encargado;
	

	//Constructora 
	
	public VistaAtraccionesCrear() {
		super("Crear una atraccion");
		initGUI();
	}
	
	
	//Metodos
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		setContentPane(panelPrincipal);
		panelPrincipal.setBorder(new EmptyBorder(15,25,15,25));
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Crear atraccion");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		panelPrincipal.add(panelTitulo);
		
		JPanel panelDatos1 = new JPanel();
		panelDatos1.setLayout(new FlowLayout());
		panelDatos1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel nombreLabel = new JLabel("Nombre:");
		nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(300, 25));
		JLabel familiarLabel = new JLabel("Familiar:");
		siFamiliar = new JRadioButton("Si");
		noFamiliar = new JRadioButton("No");
		siFamiliar.addActionListener((e)->siFamiliarAction());
		noFamiliar.addActionListener((e)->noFamiliarAction());
		JPanel panelDatos1Izq = new JPanel();
		panelDatos1Izq.setLayout(new FlowLayout());
		panelDatos1Izq.setAlignmentX(CENTER_ALIGNMENT);
		panelDatos1Izq.add(nombreLabel);
		panelDatos1Izq.add(nombre);
		panelDatos1Izq.add(familiarLabel);
		JPanel panelDatos1Der = new JPanel();
		panelDatos1Der.setLayout(new BoxLayout(panelDatos1Der, BoxLayout.Y_AXIS));
		panelDatos1Der.add(siFamiliar);
		panelDatos1Der.add(noFamiliar);
		siFamiliar.setSelected(true);
		panelDatos1.add(panelDatos1Izq);
		panelDatos1.add(panelDatos1Der);
		panelDatos1.setBorder(new EmptyBorder(0,0,10,0));
		panelPrincipal.add(panelDatos1);
		
		JPanel panelDatosRestantes = new JPanel();
		panelDatosRestantes.setLayout(new FlowLayout());
		panelDatosRestantes.setAlignmentX(CENTER_ALIGNMENT);
		panelDatosRestantes.setBorder(new EmptyBorder(0,0,10,0));
		JLabel aforoLabel = new JLabel("Aforo:");
		panelDatosRestantes.add(aforoLabel);
		aforo = new JSpinner(new SpinnerNumberModel(50, MIN_AFORO, MAX_AFORO, 1)); //valor por defecto: 50, valor minimo: MIN_AFORO, valor maximo: MAX_AFORO, incrementar/decrementar de 1 en 1)
		panelDatosRestantes.add(aforo);
		JLabel nivel_emocionLabel = new JLabel("Nivel de emocion:");
		panelDatosRestantes.add(nivel_emocionLabel);
		nivel_emocion = new JSpinner(new SpinnerNumberModel(5,MIN_NIVEL_EMOCION, MAX_NIVEL_EMOCION,1));;
		panelDatosRestantes.add(nivel_emocion);
		JLabel id_encargadoLabel = new JLabel("Id del encargado:");
		panelDatosRestantes.add(id_encargadoLabel);
		id_encargado = new JTextField();
		id_encargado.setPreferredSize(new Dimension(70, 25));
		panelDatosRestantes.add(id_encargado);
		panelPrincipal.add(panelDatosRestantes);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.setAlignmentX(CENTER_ALIGNMENT);
		JButton confirmarButton = new JButton("Confirmar");
		confirmarButton.setPreferredSize(new Dimension(100, 25));
		confirmarButton.addActionListener((e)->confirmarAction());
		panelBotones.add(confirmarButton);
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setPreferredSize(new Dimension(100, 25));
		cancelarButton.addActionListener((e)-> cancelarAction());
		panelBotones.add(cancelarButton);
		panelPrincipal.add(panelBotones);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
			}
		});
		pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private void siFamiliarAction() {
		noFamiliar.setSelected(false);
	}
	
	private void noFamiliarAction() {
		siFamiliar.setSelected(false);
	}
	
	private void confirmarAction() {
		TAtraccion atraccion = new TAtraccion();
		setVisible(false);
		String nombreAtraccion = nombre.getText();
		int aforoAtraccion = (int)aforo.getValue();
		int nivelEmocionAtraccion = (int)nivel_emocion.getValue();
		String idEncargadoAtraccion = id_encargado.getText();
		boolean familiarAtraccion=false;
		if(noFamiliar.isSelected()) familiarAtraccion = false;
		else if(siFamiliar.isSelected()) familiarAtraccion = true;
		atraccion = new TAtraccion(nombreAtraccion, familiarAtraccion, aforoAtraccion, nivelEmocionAtraccion, idEncargadoAtraccion);
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_CREAR, atraccion);
	}
	
	private void cancelarAction() {
		setVisible(false);
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}
	
	
	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if(evento==EventosControlador.ATRACCIONES_CREAR_OK) {
			TAtraccion atraccion = (TAtraccion) datos;
			Utils.showOkMsg("Se ha incorporado correctamente la atraccion\nId generado : " + atraccion.getId());
		}
		else if(evento==EventosControlador.ATRACCIONES_CREAR_ERROR) Utils.showErrorMsg((String) datos);
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}
}
