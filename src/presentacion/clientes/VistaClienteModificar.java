package presentacion.clientes;

import java.awt.BorderLayout;
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

import negocio.clientes.TCliente;
import negocio.departamentos.TDepartamento;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaClienteModificar extends JFrame implements  IGUI{

	JTextField idTxt;
	JTextField nombreTxt;
	JTextField apellidoTxt;
	JCheckBox activoCh;
	
	public VistaClienteModificar() {
		super("Modificar Cliente");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//titulo
		JPanel tituloPanel=new JPanel();
		tituloPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo=new JLabel("Eliminar cliente");
		Font fuente=new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		tituloPanel.add(titulo);
		
		//panel de ID
		JPanel idPanel=new JPanel();
		idPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel idLabel=new JLabel("ID del cliente que se desea modificar: ");
		idTxt=new JTextField();
		idTxt.setPreferredSize(new Dimension(70, 30));
		idTxt.setEditable(true);
		idPanel.add(idLabel);
		idPanel.add(idTxt);
		mainPanel.add(idPanel);
		
		//panel de cambios
		JPanel cambiosPanel=new JPanel();
		cambiosPanel.setLayout(new FlowLayout());
		cambiosPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		//nombre
		JLabel nombreLabel=new JLabel("Nuevo nombre: ");
		cambiosPanel.add(nombreLabel);
		nombreTxt=new JTextField();
		nombreTxt.setPreferredSize(new Dimension(70, 30));
		nombreTxt.setEditable(true);
		cambiosPanel.add(nombreTxt);
		
		//apellido
		JLabel apellidoLabel=new JLabel("Nuevo apellido: ");
		cambiosPanel.add(apellidoLabel);
		apellidoTxt=new JTextField();
		apellidoTxt.setPreferredSize(new Dimension(70, 30));
		apellidoTxt.setEditable(true);
		cambiosPanel.add(apellidoTxt);
		
		//activo
		JLabel activoLabel=new JLabel("Indique si está activo: ");
		activoCh=new JCheckBox();
		cambiosPanel.add(activoLabel);
		cambiosPanel.add(activoCh);
		
		mainPanel.add(cambiosPanel);
		
		
		
		//Panel botones
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton okButton= crearButton("OK", EventosControlador.CLIENTE_MODIFICAR);
		JButton cancelButton=crearButton("CANCEL", EventosControlador.CLIENTE_VISTA_INICIO);

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.CLIENTE_VISTA_INICIO, null);
			}
		});
		

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if(evento == EventosControlador.CLIENTE_MODIFICAR_OK) {
			Utils.showOkMsg("Se ha modificado el cliente correctamente");
		}
		else if(evento == EventosControlador.CLIENTE_MODIFICAR_ERROR) Utils.showErrorMsg("No se ha logrado modificar el cliente");
		
	}
	private JButton crearButton(String title, int evento) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int id=0;
				try {
					id=Integer.parseInt(idTxt.getText());
					TCliente c=new TCliente(id, nombreTxt.getText(), apellidoTxt.getText(), activoCh.isSelected());
					Controlador.getInstancia().accion(evento, c);
					
				}
				catch(Exception ex) {
					Utils.showErrorMsg("El ID debe ser un numero");
				}
				Controlador.getInstancia().accion(EventosControlador.CLIENTE_VISTA_INICIO, null);
			}
		});
		return button;
	}

}
