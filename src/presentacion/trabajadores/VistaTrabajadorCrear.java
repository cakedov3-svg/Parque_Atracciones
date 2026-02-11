package presentacion.trabajadores;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.trabajadores.TEncargado;
import negocio.trabajadores.TJefe;
import negocio.trabajadores.TTrabajador;
import negocio.trabajadores.TVendedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaTrabajadorCrear extends JFrame implements IGUI{

	
	//Atributos
	private JTextField textId;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textJefeId1;
	private JTextField textJefeId2;
	private JTextField textDep;
	private JTextField textNumVent;
	private JRadioButton radioEncargado;
	private JRadioButton radioVendedor;
	private JRadioButton radioJefe;
	private int status;
	
	public VistaTrabajadorCrear(){
		super("Crear un trabajador");
		initGUI();
	}
	
	void initGUI() {
		
		//Crea el panel principal de la ventana
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//Crea y añade los textField para introducir el nombre, id, departamento...
		JPanel data = new JPanel();
		data.setLayout(new FlowLayout());
		JLabel lname = new JLabel("Nombre: ");
		data.add(lname);
		textName = new JTextField();
		textName.setPreferredSize(new Dimension(70, 30));
		data.add(textName);
		JLabel lsurname = new JLabel("Apellido: ");
		data.add(lsurname);
		textSurname = new JTextField();
		textSurname.setPreferredSize(new Dimension(70, 30));
		data.add(textSurname);
		JLabel lid = new JLabel("DNI: ");
		data.add(lid);
		textId = new JTextField();
		textId.setPreferredSize(new Dimension(70, 30));
		data.add(textId);

		JLabel ldep = new JLabel("Id del departamento: ");
		data.add(ldep);
		textDep = new JTextField();
		textDep.setPreferredSize(new Dimension(70, 30));
		textDep.setPreferredSize(new Dimension(70, 40));
		data.add(textDep);
		
		mainPanel.add(data);
		
		//Crea el cardLayout y añadele los diferentes Paneles
		CardLayout cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel(cardLayout);
		
		JPanel tarjetaEncargado = new JPanel();
		JLabel lJefeId1 = new JLabel("Id del jefe: ");
		tarjetaEncargado.add(lJefeId1);
		textJefeId1 = new JTextField();
		textJefeId1.setPreferredSize(new Dimension(70, 30));
		tarjetaEncargado.add(textJefeId1);
		cardPanel.add(tarjetaEncargado, "Encargado");
		
		JPanel tarjetaVendedor = new JPanel();
		JLabel lJefeId2 = new JLabel("Id del jefe: ");
		tarjetaVendedor.add(lJefeId2);
		textJefeId2 = new JTextField();
		textJefeId2.setPreferredSize(new Dimension(70, 30));
		tarjetaVendedor.add(textJefeId2);
		JLabel lNumVentas = new JLabel("Numero de Ventas:");
		textNumVent = new JTextField();
		textNumVent.setPreferredSize(new Dimension(70, 30));
		tarjetaVendedor.add(lNumVentas);
		tarjetaVendedor.add(textNumVent);
		cardPanel.add(tarjetaVendedor, "Vendedor");
		
		JPanel tarjetaJefe = new JPanel();
		cardPanel.add(tarjetaJefe, "Jefe");
		
		
		//Crea 3 RadioButtons para elegir el tipo de trabajador y varia el cardLayout segun 
		//el que esta seleccionado
		
		JPanel panelRadio = new JPanel();
		panelRadio.setLayout(new FlowLayout());
		radioEncargado = new JRadioButton();
		radioEncargado.setSelected(true);
		radioVendedor = new JRadioButton();
		radioJefe = new JRadioButton();
		radioEncargado.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(( (JRadioButton) e.getSource()).isSelected()) {
					radioVendedor.setSelected(false);
					radioJefe.setSelected(false);
					cardLayout.show(cardPanel, "Encargado");
				}
			}
			
		});
		radioVendedor.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(( (JRadioButton) e.getSource()).isSelected()) {
					radioEncargado.setSelected(false);
					radioJefe.setSelected(false);
					cardLayout.show(cardPanel, "Vendedor");
				}
			}
			
		});
		radioJefe.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(( (JRadioButton) e.getSource()).isSelected()) {
					radioEncargado.setSelected(false);
					radioVendedor.setSelected(false);
					cardLayout.show(cardPanel, "Jefe");
				}
			}
			
		});
		JLabel rbl1 = new JLabel("Encargado:");
		panelRadio.add(rbl1);
		panelRadio.add(radioEncargado);
		JLabel rbl2 = new JLabel("Vendedor:");
		panelRadio.add(rbl2);
		panelRadio.add(radioVendedor);
		JLabel rbl3 = new JLabel("Jefe:");
		panelRadio.add(rbl3);
		panelRadio.add(radioJefe);
		panelRadio.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelRadio);
		
		
		//Añade el cardLayout al panel principal
		mainPanel.add(cardPanel);
		
		//Añadir botones ok y cancel
		JPanel botones = new JPanel();
		botones.setAlignmentX(CENTER_ALIGNMENT);
		botones.setLayout(new FlowLayout());
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status = 0;
				setVisible(false);
				okOrCancel();
				Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_VISTA_INICIO, null);
			}
		});
		botones.add(cancelButton);
		
		botones.add(Box.createRigidArea(new Dimension(10,0)));
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status = 1;
				setVisible(false);
				okOrCancel();
				Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_VISTA_INICIO, null);
			}
		});
		botones.add(okButton);
		
		mainPanel.add(botones);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_VISTA_INICIO, null);
			}
	});

		pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void okOrCancel() {
		
		if(status == 1) {
			
			try {
				String id = textId.getText();
				String name = textName.getText();
				String surname = textSurname.getText();
				int idDep;
				try {
					idDep = Integer.parseInt(textDep.getText());
				} catch (Exception e) {
					idDep = -1;
				}
				TTrabajador t = new TTrabajador();
				if(radioEncargado.isSelected()) {
					String idJefe = textJefeId1.getText();
					t = new TEncargado(id, name, surname, idDep, true, idJefe);
					Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_CREAR, t);
				}
				else if(radioVendedor.isSelected()) {
					String idJefe = textJefeId2.getText();
					int numVentas = Integer.parseInt(textNumVent.getText());
					t = new TVendedor(id, name, surname, idDep, true, idJefe, numVentas);
					Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_CREAR, t);
				} else if(radioJefe.isSelected()) {
					t = new TJefe(id, name, surname, idDep, true, 0);
					Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_CREAR, t);
				}
			} catch(Exception e) {
				Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_CREAR_ERROR, null);
			}
			Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_CREAR_OK, null);
		} 
		else 
			Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_CREAR_CANCEL, null);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if(evento == EventosControlador.TRABAJADOR_CREAR_OK) {
			TTrabajador t = (TTrabajador) datos;
			Utils.showOkMsg("Se ha creado al trabajador con id " + t.getId());
		}
		else if(evento == EventosControlador.TRABAJADOR_CREAR_ERROR) Utils.showErrorMsg("No se ha logrado crear al trabajador");
	}
	
}
