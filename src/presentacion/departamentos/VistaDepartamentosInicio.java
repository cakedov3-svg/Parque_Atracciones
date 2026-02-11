package presentacion.departamentos;

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

import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;

public class VistaDepartamentosInicio extends JFrame implements IGUI {
	
	public VistaDepartamentosInicio() {
		super("Subsistema Departamentos");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel titulo = new JPanel();
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel tit = new JLabel("Departamentos");
		Font fuente = new Font("Dialog", Font.BOLD, 20);
		tit.setFont(fuente);
		titulo.add(tit);
		
		mainPanel.add(titulo);
		
		JPanel panelsuperior=new JPanel();
		panelsuperior.setLayout(new FlowLayout());
		panelsuperior.setAlignmentX(CENTER_ALIGNMENT);
		JButton botonanyadir = crearButton("Añadir",EventosControlador.DEPARTAMENTOS_VISTA_CREAR);
		JButton botonbuscar=crearButton("Buscar",EventosControlador.DEPARTAMENTOS_VISTA_BUSCAR);
		JButton botoncrear = crearButton("Eliminar",EventosControlador.DEPARTAMENTOS_VISTA_ELIMINAR);
		panelsuperior.add(botonanyadir);
		panelsuperior.add(botonbuscar);
		panelsuperior.add(botoncrear);
		mainPanel.add(panelsuperior);
		
		JPanel panelinferior=new JPanel();
		panelinferior.setLayout(new FlowLayout());
		panelinferior.setAlignmentX(CENTER_ALIGNMENT);
		JButton botonmodificar=crearButton("Modificar", EventosControlador.DEPARTAMENTOS_VISTA_MODIFICAR);
		JButton botonlistar=crearButton("Listar", EventosControlador.DEPARTAMENTOS_LISTAR);
		panelinferior.add(botonmodificar);
		panelinferior.add(botonlistar);
		mainPanel.add(panelinferior);
		
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
