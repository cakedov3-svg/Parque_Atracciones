package presentacion.departamentos;

import java.awt.Dimension; 
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import negocio.departamentos.TDepartamento;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaDepartamentosModificar extends JFrame implements IGUI{
	
	private JTextField txtid;
	private JTextField txtnombre;
	private JCheckBox cb;
	
	public VistaDepartamentosModificar() {
		super("Modificar un departamento");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS ));
		setContentPane(mainPanel);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Modificar departamento");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		JPanel panelsuperior=new JPanel();
		panelsuperior.setLayout(new FlowLayout());
		
		JLabel idLabel = new JLabel("Introduzca el idDep del departamento a modificar: ");
		txtid = new JTextField();
		txtid.setEditable(true);
		txtid.setPreferredSize(new Dimension(70, 30));
		panelsuperior.add(idLabel);
		panelsuperior.add(txtid);
		mainPanel.add(panelsuperior);
		
		JPanel panelmedio=new JPanel();
		panelmedio.setLayout(new FlowLayout());
		
		JLabel nombreLabel = new JLabel("Introduzca el nuevo nombre: ");
		txtnombre=new JTextField();
		txtnombre.setEditable(true);
		txtnombre.setPreferredSize(new Dimension(70, 30));
		panelmedio.add(nombreLabel);
		panelmedio.add(txtnombre);
		JLabel activoLabel = new JLabel("Indique si está activo: ");
		cb = new JCheckBox();
		
		panelmedio.add(activoLabel);
		panelmedio.add(cb);
		mainPanel.add(panelmedio);
		
		JPanel inferiorpanel=new JPanel();
		inferiorpanel.setLayout(new FlowLayout());
		inferiorpanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton botonok=crearButton("OK", EventosControlador.DEPARTAMENTOS_MODIFICAR);
		JButton botoncancel = crearButton("CANCEL",EventosControlador.DEPARTAMENTOS_VISTA_INICIO);
		
		inferiorpanel.add(botonok);
		inferiorpanel.add(botoncancel);
		mainPanel.add(inferiorpanel);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.DEPARTAMENTOS_VISTA_INICIO, null);
			}
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if(evento == EventosControlador.DEPARTAMENTOS_MODIFICAR_OK) {
			Utils.showOkMsg("Se ha modificado al departamento correctamente");
		}
		else if(evento == EventosControlador.DEPARTAMENTOS_MODIFICAR_ERROR) Utils.showErrorMsg("No se ha logrado modificar al departamento");
	}

	private JButton crearButton(String title, int evento) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int idDep=-1;
				try {
					idDep= Integer.parseInt(txtid.getText());
				}catch(Exception ex) {
					
				}
				TDepartamento t = new TDepartamento(txtnombre.getText(), cb.isSelected(), idDep);
				Controlador.getInstancia().accion(evento, t);
				if (title =="OK")Controlador.getInstancia().accion(EventosControlador.DEPARTAMENTOS_VISTA_INICIO, null);
			}
		});
		return button;
	}
}
