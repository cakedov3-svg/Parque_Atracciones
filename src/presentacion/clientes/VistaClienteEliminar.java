package presentacion.clientes;

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

public class VistaClienteEliminar extends JFrame implements  IGUI{

	JTextField idTxt;
	
	public VistaClienteEliminar(){
		super("Listado de clientes");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS ));
		setContentPane(mainPanel);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Eliminar cliente");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		JPanel panelsuperior=new JPanel();
		panelsuperior.setLayout(new FlowLayout());
		
		JLabel idLabel = new JLabel("ID");
		idTxt = new JTextField();
		idTxt.setEditable(true);
		idTxt.setPreferredSize(new Dimension(70, 30));
		
		panelsuperior.add(idLabel);
		panelsuperior.add(idTxt);
		mainPanel.add(panelsuperior);
		
		JPanel inferiorpanel=new JPanel();
		inferiorpanel.setLayout(new FlowLayout());
		inferiorpanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton botonok=crearButton("OK", EventosControlador.CLIENTE_ELIMINAR);
		JButton botoncancel = crearButton("CANCEL",EventosControlador.CLIENTE_VISTA_INICIO);
		
		inferiorpanel.add(botonok);
		inferiorpanel.add(botoncancel);
		mainPanel.add(inferiorpanel);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.CLIENTE_VISTA_INICIO, null);
			}
		});

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.CLIENTE_ELIMINAR_OK) {
			setVisible(false);
			Utils.showOkMsg("Se ha eliminado el cliente");
		}
		else {
			setVisible(false);
			Utils.showErrorMsg("No se ha eliminado correctamente el cliente");
		}
	}
	
	private JButton crearButton(String title, int evento) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(evento, idTxt.getText());
				Controlador.getInstancia().accion(EventosControlador.CLIENTE_VISTA_INICIO, null);
			}
		});
		return button;
	}

}
