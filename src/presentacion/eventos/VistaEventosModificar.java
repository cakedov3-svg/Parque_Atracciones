package presentacion.eventos;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import negocio.eventos.TEvento;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaEventosModificar extends JFrame implements IGUI {
	
	private static final String ID_TEXT = "Introduzca el id del evento a modificar:";
	private static final String SELEC_TEXT = "Seleccione el atributo que desea modificar:";
	private static final String NOMBRE_LABEL = "Introduzca el nuevo nombre:";
	private static final String TIPO_LABEL = "Seleccione el nuevo tipo:";
	private static final String AFORO_LABEL = "Introduzca el nuevo aforo:";
	private static final int MIN_AFORO = 0;
	private static final int MAX_AFORO = 200;
	private static final int SALTO_AFORO = 1;
	private static final String TIPO = "Tipo:";
	private static final String OK_TITLE = "OK";
	private static final String CANCEL_TITLE = "Cancel";
	private static final String ACTIVO_B = "Activo";
	private static final String NO_ACTIVO_B = "No activo";
	private static final String ID_ERROR_TEXT = "El id del evento debe ser un número entero. Vuelva a intenterlo";
	
	//Opciones JComboBox
	private static final String NOMBRE_J = "Nombre";
	private static final String TIPO_J = "Tipo";
	private static final String AFORO_J = "Aforo";
	private static final String ACTIVO_J = "Activo";
	
	//Atributos visuales
	CardLayout cardLayout;
	JPanel l2_2;
	JPanel nombreP;
	JPanel tipoP;
	JPanel aforoP;
	JPanel activoP;
	JRadioButton activoB;
	JRadioButton noActivoB;
	
	//Opciones para el CardLayout
	private static final String NOMBRE_CARD = "Nombre";
	private static final String TIPO_CARD = "Tipo";
	private static final String AFORO_CARD = "Aforo";
	private static final String ACTIVO_CARD = "Activo";
	
	public VistaEventosModificar() {
		super("Modificar Evento");
		initGUI();
	}
	
	private void initGUI() {
		
		//Panel Principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15,25,15,25));
		
		//Titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Modificar evento");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		//Primera Fila de componentes
		JPanel l1 = new JPanel();
		l1.setLayout(new FlowLayout());
		l1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel idText = new JLabel(ID_TEXT);
		l1.add(idText);
		JTextField idField = new JTextField();
		idField.setPreferredSize(new Dimension(100, 25));
		l1.add(idField);
		mainPanel.add(l1);
		
		//Segunda fila de componentes
		
		JPanel l2 = new JPanel();
		l2.setLayout(new BoxLayout(l2, BoxLayout.X_AXIS));
		l2.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel l2_1 = new JPanel();
		l2_1.setLayout(new FlowLayout());
		JLabel selecText = new JLabel(SELEC_TEXT);
		l2_1.add(selecText);
		List<String> listAtrib = TEvento.getAtrib();
		DefaultComboBoxModel<String> atribsBoxModel = new DefaultComboBoxModel<>();
		atribsBoxModel.addAll(listAtrib);
		JComboBox<String> atribsBox = new JComboBox<String>(atribsBoxModel);
		atribsBox.setSelectedItem(NOMBRE_J);
		atribsBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(atribsBox.getSelectedItem() == NOMBRE_J) {
					cardLayout.show(l2_2, NOMBRE_CARD);
				}
				else if(atribsBox.getSelectedItem() == TIPO_J) {
					cardLayout.show(l2_2, TIPO_CARD);
				}
				else if(atribsBox.getSelectedItem() == AFORO_J) {
					cardLayout.show(l2_2, AFORO_CARD);
				}
				else if(atribsBox.getSelectedItem() == ACTIVO_J) {
					cardLayout.show(l2_2, ACTIVO_CARD);
				}
			}
		});
		l2_1.add(atribsBox);
		l2.add(l2_1);
		
		cardLayout = new CardLayout();
		l2_2 = new JPanel(cardLayout);
		
		//Paneles de cardlayout
		
		nombreP = new JPanel();
		nombreP.setLayout(new FlowLayout());
		nombreP.setAlignmentX(CENTER_ALIGNMENT);
		JLabel nomLabel = new JLabel(NOMBRE_LABEL);
		nombreP.add(nomLabel);
		JTextField nomField = new JTextField();
		nomField.setPreferredSize(new Dimension(300, 25));
		nombreP.add(nomField);
		l2_2.add(nombreP, NOMBRE_CARD);
		
		tipoP = new JPanel();
		tipoP.setLayout(new FlowLayout());
		tipoP.setAlignmentX(CENTER_ALIGNMENT);
		JLabel tipoLabel = new JLabel(TIPO_LABEL);
		tipoP.add(tipoLabel);
		List<String> listTipos = TEvento.getTipos();
		DefaultComboBoxModel<String> tiposBoxModel = new DefaultComboBoxModel<>();
		tiposBoxModel.addAll(listTipos);
		JComboBox<String> tiposBox = new JComboBox<String>(tiposBoxModel);
		tipoP.add(tiposBox);
		l2_2.add(tipoP, TIPO_CARD);
		
		aforoP = new JPanel();
		aforoP.setLayout(new FlowLayout());
		aforoP.setAlignmentX(CENTER_ALIGNMENT);
		JLabel aforoLabel = new JLabel(AFORO_LABEL);
		aforoP.add(aforoLabel);
		JSpinner aforoSpin = new JSpinner(new SpinnerNumberModel(MIN_AFORO, MIN_AFORO, MAX_AFORO, SALTO_AFORO));
		aforoSpin.setPreferredSize(new Dimension(60, 25));
		aforoP.add(aforoSpin);
		l2_2.add(aforoP, AFORO_CARD);
		
		activoP = new JPanel();
		activoP.setLayout(new FlowLayout());
		activoB = new JRadioButton(ACTIVO_B);
		activoP.add(activoB);
		noActivoB = new JRadioButton(NO_ACTIVO_B);
		activoP.add(noActivoB);
		activoB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(( (JRadioButton) e.getSource()).isSelected()) {
					noActivoB.setSelected(false);
				}
			}
		});
		noActivoB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(( (JRadioButton) e.getSource()).isSelected()) {
					activoB.setSelected(false);
				}
			}
		});
		l2_2.add(activoP, ACTIVO_CARD);
		
		l2.add(l2_2);
		
		mainPanel.add(l2);
		
		
		//Botones OK y Cancel
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		botones.setAlignmentX(CENTER_ALIGNMENT);
		JButton okButton = new JButton(OK_TITLE);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = -1;
				try { 
					id = Integer.parseInt(idField.getText());
				}
				catch(Exception exc) {
					Utils.showErrorMsg(ID_ERROR_TEXT);
					return;
				}
				String nombre = nomField.getText();
				String tipo = (String) tiposBox.getSelectedItem();
				int aforo = (int) aforoSpin.getValue();
				//Para ver si se ha cambiado o no el aforo escogemos valor a 0 para representar que no se ha cambiado
				String selecActivo = "";
				if(activoB.isSelected()) selecActivo = "SI";
				else if(noActivoB.isSelected()) selecActivo = "NO";
				TEvento transfer = new TEvento(id, aforo, tipo, nombre, selecActivo);
				Pair<TEvento, String> par = new Pair<>(transfer, selecActivo);
				Controlador.getInstancia().accion(EventosControlador.EVENTOS_MODIFICAR, par);
				setVisible(false);
			}
		});
		botones.add(okButton);
		JButton cancelButton = new JButton(CANCEL_TITLE);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.EVENTOS_VISTA_INICIO, null);
			}
		});
		botones.add(cancelButton);
		mainPanel.add(botones);
		
		//Boton x funcional
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.EVENTOS_VISTA_INICIO, null);
			}
		});
		
		//Ajustes sobre la ventana
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if(evento==EventosControlador.EVENTOS_MODIFICAR_OK) {
			TEvento transfer = (TEvento) datos;
			Utils.showOkMsg("El evento con id " + transfer.getId() + " ha sido modificado correctamente.");
		}
		else if(evento==EventosControlador.EVENTOS_MODIFICAR_ERROR) Utils.showErrorMsg(datos.toString());
		Controlador.getInstancia().accion(EventosControlador.EVENTOS_VISTA_INICIO, null);
	}

}
