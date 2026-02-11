package presentacion.eventos;

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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import negocio.eventos.TEvento;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaEventosCrear extends JFrame implements IGUI {
	
	private static final String NOMBRE = "Nombre:";
	private static final String AFORO = "Aforo:";
	private static final int MIN_AFORO = 1;
	private static final int MAX_AFORO = 200;
	private static final int AFORO_DEF = 100;
	private static final int SALTO_AFORO = 1;
	private static final String TIPO = "Tipo:";
	private static final String OK_TITLE = "OK";
	private static final String CANCEL_TITLE = "Cancel";
	private static final String CREADO_OK_TEXT = "Se ha incorporado correctamente el evento\nId generado : ";
	private static final String ENTRADA_INCORRECTA = "Se ha dejado algún campo sin rellenar. Por favor, vuelva a intentarlo";

	public VistaEventosCrear() {
		super("Crear Evento");
		initGUI();
	}
	
	private void initGUI() {
		
		//Panel Principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15,25,15,25));
		
		//TITULO
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Crear evento");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		//Primera fila de componentes
		JPanel l1 = new JPanel();
		l1.setLayout(new FlowLayout());
		l1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel nomText = new JLabel(NOMBRE);
		l1.add(nomText);
		JTextField nomField = new JTextField();
		nomField.setPreferredSize(new Dimension(400, 25));
		l1.add(nomField);
		mainPanel.add(l1);
		
		//Segunda fila de componentes
		JPanel l2 = new JPanel();
		l2.setLayout(new FlowLayout());
		l2.setAlignmentX(CENTER_ALIGNMENT);
		JLabel aforoText = new JLabel(AFORO);
		l2.add(aforoText);
		JSpinner aforoSpin = new JSpinner(new SpinnerNumberModel(AFORO_DEF, MIN_AFORO, MAX_AFORO, SALTO_AFORO));
		l2.add(aforoSpin);
		JLabel tipoText = new JLabel(TIPO);
		l2.add(tipoText);
		List<String> listTipos = TEvento.getTipos();
		DefaultComboBoxModel tiposBoxModel = new DefaultComboBoxModel<>();
		tiposBoxModel.addAll(listTipos);
		JComboBox tiposBox = new JComboBox<String>(tiposBoxModel);
		l2.add(tiposBox);
		mainPanel.add(l2);
		
		//Botones OK y Cancel
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		botones.setAlignmentX(CENTER_ALIGNMENT);
		JButton okButton = new JButton(OK_TITLE);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int aforo = (int) aforoSpin.getValue();
				String tipo = (String) tiposBox.getSelectedItem();
				String nombre = nomField.getText();
				if(tipo == null || nombre.equals("") || nombre == null) Utils.showErrorMsg(ENTRADA_INCORRECTA);
				else {
					TEvento transfer = new TEvento(aforo, tipo, nombre);
					Controlador.getInstancia().accion(EventosControlador.EVENTOS_CREAR, transfer);
					setVisible(false);
				}
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
		if(evento == EventosControlador.EVENTOS_CREAR_OK) {
			int id = (int) datos;
			Utils.showOkMsg(CREADO_OK_TEXT + id);
		}
		else if(evento == EventosControlador.EVENTOS_CREAR_ERROR) Utils.showErrorMsg(datos.toString());
		Controlador.getInstancia().accion(EventosControlador.EVENTOS_VISTA_INICIO, null);
	}

}
