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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaDepartamentosAnyadir extends JFrame implements IGUI{
	
	private JTextField txt;
	
	public VistaDepartamentosAnyadir() {
		super("Añadir un departamento");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Añadir departamento");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		JPanel panelsuperior = new JPanel();
		panelsuperior.setLayout(new FlowLayout());
		panelsuperior.setAlignmentX(CENTER_ALIGNMENT);
		JLabel namelabel = new JLabel("Nombre del departamento");
		panelsuperior.add(namelabel);
		txt = new JTextField();
		txt.setEditable(true);
		txt.setPreferredSize(new Dimension(70, 30));
		panelsuperior.add(txt);
		
		mainPanel.add(panelsuperior);
		
		JPanel inferiorpanel=new JPanel();
		inferiorpanel.setLayout(new FlowLayout());
		inferiorpanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton botonok=crearButton("OK", EventosControlador.DEPARTAMENTOS_CREAR);
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
		if(evento == EventosControlador.DEPARTAMENTOS_CREAR_OK) {
			Utils.showOkMsg("Se ha creado al departamento correctamente");
		}
		else if(evento == EventosControlador.DEPARTAMENTOS_CREAR_ERROR) Utils.showErrorMsg("No se ha logrado crear al departamento");
	}
	
	private JButton crearButton(String title, int evento) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(evento, txt.getText());
				if (title =="OK")Controlador.getInstancia().accion(EventosControlador.DEPARTAMENTOS_VISTA_INICIO, null);
			}
		});
		return button;
	}

}
