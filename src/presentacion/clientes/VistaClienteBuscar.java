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

import negocio.clientes.TCliente;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaClienteBuscar extends JFrame implements  IGUI{
	JTextField idTxt;
	
	public VistaClienteBuscar() {
		
		super("Buscar Cliente");
		initGUI();
		
	}

	private void initGUI() {
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS ));
		setContentPane(mainPanel);
		
		//título
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Buscar cliente");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		
		//Panel superior
		JPanel superior=new JPanel();
		
		superior.setLayout(new FlowLayout());
		JLabel id=new JLabel("ID del cliente");
		idTxt=new JTextField();
		idTxt.setEditable(true);
		idTxt.setPreferredSize(new Dimension(70, 30));
		superior.add(id);
		superior.add(idTxt);
		
		mainPanel.add(superior);

		//Panel botones
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton okButton= crearButton("OK", EventosControlador.CLIENTE_BUSCAR);
		JButton cancelButton=crearButton("CANCEL", EventosControlador.CLIENTE_VISTA_INICIO);

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel);
		
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
		
		if(evento==EventosControlador.CLIENTE_BUSCAR_OK) {
			setVisible(false);
			TCliente c=(TCliente) datos;
			StringBuilder info=new StringBuilder("Se encontro al cliente con los siguientes datos:\n");
			info.append("ID: ");
			info.append(c.getId() + "\n");
			info.append("Nombre: ");
			info.append(c.getNombre() + "\n");
			info.append("Apellido: ");
			info.append(c.getApellido() + "\n");
			info.append("Activo: ");
			info.append(c.getActivo());
			Utils.showOkMsg(info.toString());
		}
		else {
			setVisible(false);
			Utils.showErrorMsg("No se encontro al cliente");
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
