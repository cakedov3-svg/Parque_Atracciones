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

public class VistaClienteAnyadir extends JFrame implements  IGUI{

	JTextField idTxt;
	JTextField nombreTxt;
	JTextField apellidoTxt;
	
	public VistaClienteAnyadir() {
		super("Añadir Cliente");
		initGUI();
	}
	private void initGUI() {
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS ));
		setContentPane(mainPanel);
		
		//título
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Añadir cliente");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		//Panel superior
		JPanel superior=new JPanel();
		
		superior.setLayout(new FlowLayout());
		
		JLabel nombre=new JLabel("Nombre: ");
		nombreTxt=new JTextField();
		nombreTxt.setEditable(true);
		nombreTxt.setPreferredSize(new Dimension(70, 30));
		superior.add(nombre);
		superior.add(nombreTxt);
		
		JLabel apellido=new JLabel("Apellido: ");
		apellidoTxt=new JTextField();
		apellidoTxt.setEditable(true);
		apellidoTxt.setPreferredSize(new Dimension(70, 30));
		superior.add(apellido);
		superior.add(apellidoTxt);
		
		mainPanel.add(superior);
		
		//Panel botones
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton okButton= crearButton("OK", EventosControlador.CLIENTE_CREAR);
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
		setVisible(false);
		
		if(evento==EventosControlador.CLIENTE_CREAR_OK) {
			StringBuilder info=new StringBuilder("Se ha creado al nuevo cliente correctamente\n");
			info.append("ID: ");
			int id=(int) datos;
			info.append(id);
			Utils.showOkMsg(info.toString());
			Controlador.getInstancia().accion(EventosControlador.CLIENTE_VISTA_INICIO, null);
		}
		else {
			Utils.showErrorMsg("No se ha podido crear al nuevo cliente");
			Controlador.getInstancia().accion(EventosControlador.CLIENTE_VISTA_INICIO, null);
		}
		
	}
	
	private JButton crearButton(String title, int evento) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TCliente c = new TCliente( nombreTxt.getText(), apellidoTxt.getText(), true);
				Controlador.getInstancia().accion(evento, c);
			}

			
		});
		return button;
	}

		
	}
	

