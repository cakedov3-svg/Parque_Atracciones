package presentacion.atracciones;

import java.awt.CardLayout;
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

public class VistaAtraccionesModificar extends JFrame implements IGUI{
	
	private JTextField idAtraccion;
	private JRadioButton aforoRadioButton;
	private JRadioButton familiarRadioButton;
	private JRadioButton nivelEmocionRadioButton;
	private JRadioButton idEncargadoRadioButton;
	private JRadioButton activaRadioButton;
	private JRadioButton nombreRadioButton;
	private JTextField nombre;
	private JTextField aforo;
	private JTextField nivelEmocion;
	private JRadioButton siActiva;
	private JRadioButton noActiva;
	private JRadioButton siFamiliar;
	private JRadioButton noFamiliar;
	private JTextField idEncargado;
	private CardLayout cardlayout;
	private JPanel panelCard;
	
	private static final int MIN_AFORO = 1;
	private static final int MAX_AFORO = 100;
	private static final int MIN_NIVEL_EMOCION = 1;
	private static final int MAX_NIVEL_EMOCION = 10;
	

	public VistaAtraccionesModificar(){
		super("Modificar atraccion");
		initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBorder(new EmptyBorder(15,25,15,25));
		setContentPane(panelPrincipal);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Modificar atraccion");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		panelPrincipal.add(panelTitulo);
		
		JPanel panelArriba = new JPanel();
		panelArriba.setLayout(new BoxLayout( panelArriba, BoxLayout.X_AXIS));
		JLabel id = new JLabel("Introduzca el ID de la atraccion: ");
		panelArriba.add(id);
		idAtraccion = new JTextField();
		panelArriba.add(idAtraccion);
		panelArriba.setBorder(new EmptyBorder(0,0,15,0));
		panelPrincipal.add(panelArriba);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());
		labelPanel.setAlignmentX(CENTER_ALIGNMENT);
		labelPanel.add(new JLabel("Seleccione el dato que desea modificar"));
		labelPanel.setBorder(new EmptyBorder(0,0,15,0));
		panelPrincipal.add(labelPanel);
		
		//RadioButtons para seleccionar el dato a modificar
		JPanel panelOpciones = new JPanel();
		panelOpciones.setLayout(new FlowLayout());
		panelOpciones.setAlignmentX(CENTER_ALIGNMENT);
		nombreRadioButton = new JRadioButton("Nombre");
		nombreRadioButton.addActionListener((e)->modificarNombreAction());
		panelOpciones.add(nombreRadioButton);
		nombreRadioButton.setSelected(true);
		aforoRadioButton = new JRadioButton("Aforo");
		aforoRadioButton.addActionListener((e)->modificarAforoAction());
		panelOpciones.add(aforoRadioButton);
		familiarRadioButton = new JRadioButton("Familiar");
		familiarRadioButton.addActionListener((e)->modificarFamiliarAction());
		panelOpciones.add(familiarRadioButton);
		nivelEmocionRadioButton = new JRadioButton("Nivel de emocion");
		nivelEmocionRadioButton.addActionListener((e)->modificarNivelEmocionAction());
		panelOpciones.add(nivelEmocionRadioButton);
		idEncargadoRadioButton = new JRadioButton("Id del encargado");
		idEncargadoRadioButton.addActionListener((e)->modificarIdEncargadoAction());
		panelOpciones.add(idEncargadoRadioButton);
		activaRadioButton = new JRadioButton("Activa");
		activaRadioButton.addActionListener((e)->modificarActivaAction());
		panelOpciones.add(activaRadioButton);
		panelPrincipal.add(panelOpciones);
		
		//CardLayout
		cardlayout = new CardLayout();
		panelCard = new JPanel(cardlayout);
		
		JPanel nombrePanel = new JPanel();
		nombrePanel.setLayout(new FlowLayout());
		nombrePanel.setAlignmentX(CENTER_ALIGNMENT);
		nombrePanel.add(new JLabel("Introduzca el nuevo nombre: "));
		nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(300, 25));
		nombrePanel.add(nombre);
		panelCard.add(nombrePanel, "Nombre");
		
		JPanel aforoPanel = new JPanel();
		aforoPanel.setLayout(new FlowLayout());
		aforoPanel.setAlignmentX(CENTER_ALIGNMENT);
		aforoPanel.add(new JLabel("Introduzca el nuevo aforo: "));
		aforo = new JTextField(); //valor por defecto: 50, valor minimo: MIN_AFORO, valor maximo: MAX_AFORO, incrementar/decrementar de 1 en 1)
		aforo.setPreferredSize(new Dimension(40, 25));
		aforoPanel.add(aforo);
		panelCard.add(aforoPanel, "Aforo");
		
		JPanel familiarPanel = new JPanel();
		familiarPanel.setLayout(new FlowLayout());
		familiarPanel.setAlignmentX(CENTER_ALIGNMENT);
		siFamiliar = new JRadioButton("Si");
		noFamiliar = new JRadioButton("No");
		siFamiliar.addActionListener((e)->siFamiliarAction());
		noFamiliar.addActionListener((e)->noFamiliarAction());
		JPanel panelDatos1Izq = new JPanel();
		panelDatos1Izq.add(new JLabel("Familiar:"));
		JPanel panelDatos1Der = new JPanel();
		panelDatos1Der.setLayout(new BoxLayout(panelDatos1Der, BoxLayout.Y_AXIS));
		panelDatos1Der.add(siFamiliar);
		panelDatos1Der.add(noFamiliar);
		siFamiliar.setSelected(true);
		familiarPanel.add(panelDatos1Izq);
		familiarPanel.add(panelDatos1Der);
		panelCard.add(familiarPanel, "Familiar");
		
		JPanel nivelEmocionPanel = new JPanel();
		nivelEmocionPanel.setLayout(new FlowLayout());
		nivelEmocionPanel.setAlignmentX(CENTER_ALIGNMENT);
		nivelEmocionPanel.add(new JLabel("Introduzca el nuevo nivel de emocion:"));
		nivelEmocion = new JTextField();
		nivelEmocion.setPreferredSize(new Dimension(40, 25));
		nivelEmocionPanel.add(nivelEmocion);
		panelCard.add(nivelEmocionPanel, "Emocion");
		
		JPanel activaPanel = new JPanel();
		activaPanel.setLayout(new FlowLayout());
		activaPanel.setAlignmentX(CENTER_ALIGNMENT);
		siActiva = new JRadioButton("Si");
		noActiva = new JRadioButton("No");
		siActiva.addActionListener((e)-> siActivaAction());
		noActiva.addActionListener((e)-> noActivaAction());
		JPanel panelDatos2Izq = new JPanel();
		panelDatos2Izq.add(new JLabel("Activa:"));
		JPanel panelDatos2Der = new JPanel();
		panelDatos2Der.setLayout(new BoxLayout(panelDatos2Der, BoxLayout.Y_AXIS));
		panelDatos2Der.add(siActiva);
		panelDatos2Der.add(noActiva);
		siActiva.setSelected(true);
		activaPanel.add(panelDatos2Izq);
		activaPanel.add(panelDatos2Der);
		panelCard.add(activaPanel, "Activa");
		
		JPanel encargadoPanel = new JPanel();
		encargadoPanel.add(new JLabel("Introduzca el id del nuevo encargado:"));
		idEncargado = new JTextField();
		idEncargado.setPreferredSize(new Dimension(70, 25));
		encargadoPanel.add(idEncargado);
		panelCard.add(encargadoPanel, "Encargado");
		
		panelPrincipal.add(panelCard);
		
		
		JPanel panelAbajo = new JPanel();
		panelAbajo.setLayout(new FlowLayout());
		panelAbajo.setAlignmentX(CENTER_ALIGNMENT);
		JButton confirmarButton = new JButton("Confirmar");
		confirmarButton.setPreferredSize(new Dimension(100, 25));
		confirmarButton.addActionListener((e)->confirmarAction());
		panelAbajo.add(confirmarButton);
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setPreferredSize(new Dimension(100, 25));
		cancelarButton.addActionListener((e)->cancelarAction());
		panelAbajo.add(cancelarButton);
		panelPrincipal.add(panelAbajo);
		

		
		
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
	
	private void modificarAforoAction() {
		familiarRadioButton.setSelected(false);
		nivelEmocionRadioButton.setSelected(false);
		idEncargadoRadioButton.setSelected(false);
		activaRadioButton.setSelected(false);
		nombreRadioButton.setSelected(false);
		cardlayout.show(panelCard, "Aforo");
	}
	
	private void modificarNombreAction() {
		aforoRadioButton.setSelected(false);
		familiarRadioButton.setSelected(false);
		nivelEmocionRadioButton.setSelected(false);
		idEncargadoRadioButton.setSelected(false);
		activaRadioButton.setSelected(false);
		cardlayout.show(panelCard, "Nombre");

	}
	
	private void modificarFamiliarAction() {
		aforoRadioButton.setSelected(false);
		nivelEmocionRadioButton.setSelected(false);
		idEncargadoRadioButton.setSelected(false);
		activaRadioButton.setSelected(false);
		nombreRadioButton.setSelected(false);
		cardlayout.show(panelCard, "Familiar");

	}
	
	private void modificarActivaAction() {
		aforoRadioButton.setSelected(false);
		familiarRadioButton.setSelected(false);
		nivelEmocionRadioButton.setSelected(false);
		idEncargadoRadioButton.setSelected(false);
		nombreRadioButton.setSelected(false);
		cardlayout.show(panelCard, "Activa");

	}
	
	private void modificarNivelEmocionAction() {
		aforoRadioButton.setSelected(false);
		familiarRadioButton.setSelected(false);
		idEncargadoRadioButton.setSelected(false);
		activaRadioButton.setSelected(false);
		nombreRadioButton.setSelected(false);
		cardlayout.show(panelCard, "Emocion");

	}
	
	private void modificarIdEncargadoAction() {
		aforoRadioButton.setSelected(false);
		familiarRadioButton.setSelected(false);
		nivelEmocionRadioButton.setSelected(false);
		activaRadioButton.setSelected(false);
		nombreRadioButton.setSelected(false);
		cardlayout.show(panelCard, "Encargado");

	}
	
	private void siFamiliarAction() {
		noFamiliar.setSelected(false);
	}
	
	private void noFamiliarAction() {
		siFamiliar.setSelected(false);
	}
	
	private void siActivaAction() {
		noActiva.setSelected(false);
	}
	
	private void noActivaAction() {
		siActiva.setSelected(false);
	}
	
	private void confirmarAction() {
		TAtraccion atraccion=null;
		String nuevoValorActiva = null;
		String nuevoValorFamiliar=null;
		int id_atraccion=-1;
		setVisible(false);
		try {
			id_atraccion =Integer.parseInt(idAtraccion.getText());
		}
		catch(Exception e) {
			Utils.showErrorMsg("El id debe ser un numero entero");
			Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
			return;

		}
		if(aforoRadioButton.isSelected()) {
			try {
				int nuevoAforo =  Integer.parseInt(aforo.getText());
				atraccion = new TAtraccion(-1, "", false, nuevoAforo, -1, false, "");
			}
			catch(Exception e) {
				Utils.showErrorMsg("El aforo debe ser un numero entero");
				Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
				return;
			}
		}
		else if(familiarRadioButton.isSelected()&&noFamiliar.isSelected()) {
			nuevoValorFamiliar="NO";
			atraccion = new TAtraccion(id_atraccion,"", false,-1,-1,false,"");
		}
		else if(familiarRadioButton.isSelected()&&siFamiliar.isSelected()) {
			nuevoValorFamiliar="SI";
			atraccion = new TAtraccion(id_atraccion,"", true,-1,-1,false,"");
		}
		else if(nombreRadioButton.isSelected()) atraccion = new TAtraccion(id_atraccion, nombre.getText(), false, -1, -1, false, "");
		else if(nivelEmocionRadioButton.isSelected()) {
			try {
				int nuevaEmocion = Integer.parseInt(nivelEmocion.getText());
				atraccion = new TAtraccion(id_atraccion, "",false,-1, nuevaEmocion, false, "");
			}
			catch(Exception e){
				Utils.showErrorMsg("El nivel de emocion debe ser un numero entero");
			}
		}
		else if(idEncargadoRadioButton.isSelected()) atraccion = new TAtraccion(id_atraccion, "", false, -1,-1,false,idEncargado.getText());
		else if(activaRadioButton.isSelected()&&siActiva.isSelected()) {
			nuevoValorActiva="SI";
			atraccion = new TAtraccion(id_atraccion, "", false, -1, -1, true, "");
		}
		else if(activaRadioButton.isSelected()&&noActiva.isSelected()) {
			atraccion = new TAtraccion(id_atraccion, "", false, -1, -1, false, "");
			nuevoValorActiva="NO";
		}
		TupleAtracciones<TAtraccion, String, String> tupla = new TupleAtracciones<>(atraccion, nuevoValorActiva, nuevoValorFamiliar);
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_MODIFICAR, tupla);		
	}
	
	private void cancelarAction() {
		setVisible(false);
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if(evento==EventosControlador.ATRACCIONES_MODIFICAR_OK) {
			TAtraccion atraccion = (TAtraccion) datos;
			Utils.showOkMsg("La atraccion con id "+atraccion.getId()+" ha sido modificada correctamente");
		}
		else if(evento==EventosControlador.ATRACCIONES_MODIFICAR_ERROR) {
			Utils.showErrorMsg((String) datos);
		}
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}

}
