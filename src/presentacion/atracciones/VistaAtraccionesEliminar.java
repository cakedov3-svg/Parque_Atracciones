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

import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaAtraccionesEliminar extends JFrame implements IGUI{
	
	private JTextField idAtraccion;

	public VistaAtraccionesEliminar(){
		super("Eliminar Atraccion");
		initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBorder(new EmptyBorder(15,25,15,25));
		setContentPane(panelPrincipal);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Eliminar atraccion");
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
		setVisible(false);
		int id_atraccion=-1;
		try {
			id_atraccion = Integer.parseInt(idAtraccion.getText());
		}
		catch(Exception e) {
			Utils.showErrorMsg("El id de la atraccion debe ser un numero entero");
			Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
			return;
		}
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_ELIMINAR, id_atraccion);
	}
	
	private void cancelarAction() {
		setVisible(false);
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if(evento==EventosControlador.ATRACCIONES_ELIMINAR_OK) {
			int id = (int) datos;
			Utils.showOkMsg("Se ha eliminado correctamente la atraccion con id " + id);
		}
		else if(evento==EventosControlador.ATRACCIONES_ELIMINAR_ERROR) Utils.showErrorMsg((String) datos);
		Controlador.getInstancia().accion(EventosControlador.ATRACCIONES_VISTA_INICIO, null);
	}
}
