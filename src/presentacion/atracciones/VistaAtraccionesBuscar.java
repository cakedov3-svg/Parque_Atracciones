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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import negocio.atracciones.TAtraccion;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaAtraccionesBuscar extends JFrame implements IGUI{
	
	
	//Atributos
	
	private JTextField idAtraccion;
	
	
	//Constructora

	public VistaAtraccionesBuscar() {
		super("Buscar Atraccion");
		initGUI();
	}
	
	
	//Metodos

	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBorder(new EmptyBorder(15,25,15,25));
		setContentPane(panelPrincipal);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Buscar atraccion");
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
		idAtraccion.setPreferredSize(new Dimension(80, 25));
		panelArriba.add(idAtraccion);
		panelArriba.setBorder(new EmptyBorder(0,0,15,0));
		panelPrincipal.add(panelArriba);
		
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
	
	private void confirmarAction() {
		try {
			setVisible(false);
			int id_atraccion =Integer.parseInt(idAtraccion.getText());
			Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_BUSCAR, id_atraccion);
		}
		catch(Exception e) {
			Utils.showErrorMsg("El id de la atraccion debe ser un numero entero");
			Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
		}
		
	}
	
	private void cancelarAction() {
		setVisible(false);
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if(EventosControlador.ATRACCIONES_BUSCAR_OK==evento) {
			TAtraccion atraccion = (TAtraccion) datos;
			StringBuilder datosAtraccion = new StringBuilder("Se ha encontrado la siguiente atraccion con id " + atraccion.getId());
			datosAtraccion.append("\nNombre: " + atraccion.getNombre());
			Boolean familiar = atraccion.getFamiliar();
			if(familiar) datosAtraccion.append("\nFamiliar: si");
			else datosAtraccion.append("\nFamiliar: no");
			datosAtraccion.append("\nAforo: "+atraccion.getAforo());
			datosAtraccion.append("\nNivel de emocion: " + atraccion.getNivelEmocion());
			datosAtraccion.append("\nId del encargado: "+atraccion.getIdEncargado());
			if(atraccion.getActiva()) datosAtraccion.append("\nActiva: si");
			else datosAtraccion.append("\nActiva: no");
			Utils.showOkMsg(datosAtraccion.toString());
		}
		else if(EventosControlador.ATRACCIONES_BUSCAR_ERROR==evento) {
			Utils.showErrorMsg((String) datos);
		}
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}
}
