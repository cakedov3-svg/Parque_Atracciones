package presentacion.atracciones;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable; 
import javax.swing.border.EmptyBorder;

import negocio.atracciones.TAtraccion;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;

public class VistaAtraccionesListar extends JFrame implements IGUI{
	
	private static boolean existe = false;
	private ModeloTablaAtracciones modeloTabla;
	
	public VistaAtraccionesListar() {
		super("Listar Atracciones");
		initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(15,25,15,25));
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		setContentPane(panelPrincipal);
		
		
		modeloTabla = new ModeloTablaAtracciones();
		JTable tabla = new JTable(modeloTabla);
		tabla.setColumnSelectionAllowed(false);
		tabla.setRowSelectionAllowed(false);
		add(new JScrollPane(tabla));
		
		JPanel panelAtras = new JPanel();
		panelAtras.setLayout(new FlowLayout());
		panelAtras.setAlignmentX(CENTER_ALIGNMENT);
		JButton atrasButton = new JButton("Atras");
		atrasButton.addActionListener((e)-> atrasAction());
		panelAtras.add(atrasButton);
		panelAtras.setBorder(new EmptyBorder(0,15,0,0));
		panelPrincipal.add(panelAtras);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				atrasAction();
			}
		});
		
		if(!existe) {
			existe = true;
			Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_LISTAR, null);
		}
		
		pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(false);
		
	}
	
	private void atrasAction() {
		setVisible(false);
		existe = false;
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.ATRACCIONES_LISTAR_OK) {
			List<TAtraccion> listaAtracciones = (List<TAtraccion>) datos;
			modeloTabla.actualizar(listaAtracciones);
		}
		else if(evento == EventosControlador.ATRACCIONES_LISTAR_ERROR) {
			modeloTabla.actualizar(new ArrayList<>());
		}
		pack();
		setVisible(true);
	}
	

}
