package presentacion.eventos;

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

public class VistaEventosEliminar extends JFrame implements IGUI {
	
	private static final String ID_TITLE = "Introduzca el ID del evento: ";
	private static final String OK_TITLE = "OK";
	private static final String CANCEL_TITLE = "Cancel";
	private static final String ELIMINADO_OK_TEXT = "Se ha eliminado correctamente el evento con id ";
	private static final String ID_ERROR_TEXT = "El id del evento debe ser un número entero. Vuelva a intenterlo";
	
	public VistaEventosEliminar() {
		super("Eliminar Evento");
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
		JLabel titulo = new JLabel("Eliminar evento");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		//Primera fila de componentes
		JPanel l1 = new JPanel();
		l1.setLayout(new FlowLayout());
		l1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel idText = new JLabel(ID_TITLE);
		l1.add(idText);
		JTextField idField = new JTextField();
		idField.setPreferredSize(new Dimension(80, 25));
		l1.add(idField);
		l1.setBorder(new EmptyBorder(0,0,15,0));
		mainPanel.add(l1);
		
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
				Controlador.getInstancia().accion(EventosControlador.EVENTOS_ELIMINAR, id);
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
		if(evento == EventosControlador.EVENTOS_ELIMINAR_OK) {
			int id = (int) datos;
			Utils.showOkMsg(ELIMINADO_OK_TEXT + id);
		}
		else if(evento == EventosControlador.EVENTOS_ELIMINAR_ERROR) Utils.showErrorMsg(datos.toString());
		Controlador.getInstancia().accion(EventosControlador.EVENTOS_VISTA_INICIO, null);
	}

}
