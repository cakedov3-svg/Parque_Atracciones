package presentacion.productos;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import negocio.eventos.TEvento;
import negocio.productos.TPaseAtraccion;
import negocio.productos.TProducto;
import negocio.productos.TRepresentacionEvento;
import negocio.trabajadores.TEncargado;
import negocio.trabajadores.TJefe;
import negocio.trabajadores.TTrabajador;
import negocio.trabajadores.TVendedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaProductosCrear extends JFrame implements IGUI{
	
	private static final String ENTRADA_INCORRECTA = "Los datos de entrada han sido introducidos de forma erronea. Vuelva a intentarlo";
	
	JComboBox tiposBox;
	DefaultComboBoxModel tiposBoxModel;
	JTextField selectId;
	JTextField selectNombre;
	JTextField precio;
	private int status;

	public VistaProductosCrear() {
		super("Crear Producto");
		initGUI();
	}
	
	private void initGUI() {
		
		//Panel Principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15,25,15,25));
		
		//Primera fila de componentes
		JPanel supPanel = new JPanel();
		supPanel.setLayout(new FlowLayout());
		supPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel id = new JLabel("Introduce el ID del evento/atraccion: ");
		supPanel.add(id);
		
		selectId = new JTextField();
		selectId.setPreferredSize(new Dimension(100, 25));
		supPanel.add(selectId);
		
		JLabel nombre = new JLabel("Introduce el NOMBRE de la entrada: ");
		supPanel.add(nombre);
		
		selectNombre = new JTextField();
		selectNombre.setPreferredSize(new Dimension(100, 25));
		supPanel.add(selectNombre);
		
		JLabel tipoText = new JLabel("Elige su tipo:");
		supPanel.add(tipoText);
		List<String> listProd = TProducto.getProductos();
		tiposBoxModel = new DefaultComboBoxModel<>();
		tiposBoxModel.addAll(listProd);
		tiposBox= new JComboBox<String>(tiposBoxModel);
		supPanel.add(tiposBox);
		mainPanel.add(supPanel);
		
		//Segunda fila de componentes
		JPanel infPanel = new JPanel();
		infPanel.setLayout(new FlowLayout());
		infPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel precioText = new JLabel("Indica su precio: ");
		infPanel.add(precioText);
		precio = new JTextField();
		precio.setPreferredSize(new Dimension(200, 25));
		infPanel.add(precio);
		mainPanel.add(infPanel);
		
		//Botones OK y Cancel
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		botones.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				status = 1;
				setVisible(false);
				buttonsAction();
				Controlador.getInstancia().accion(EventosControlador.PRODUCTOS_VISTA_INICIO, null);
			}
		});
		botones.add(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				status = 0;
				setVisible(false);
				buttonsAction();
				Controlador.getInstancia().accion(EventosControlador.PRODUCTOS_VISTA_INICIO, null);
			}
		});
		botones.add(cancelButton);
		mainPanel.add(botones);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.PRODUCTOS_VISTA_INICIO, null);
			}
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	private void buttonsAction() {

		if(status == 1) {
			//try {
			String auxId = selectId.getText();
			String tipo = (String)tiposBoxModel.getSelectedItem();
			String auxPrecio = precio.getText();
			String auxNombre = selectNombre.getText();
			TProducto prod;
			if(auxPrecio == "" || auxPrecio == null || auxId == "" || auxId == null || auxNombre == null || auxNombre == "" ||tipo == null) Utils.showErrorMsg(ENTRADA_INCORRECTA);
		    else {
			    	Double price = Double.parseDouble(auxPrecio);
			    	int id = Integer.parseInt(auxId);//FIXME Gestionar numberFormatException
			    	String nombre = auxNombre;
			    
				if(id == -1 ||  price <= 0) Utils.showErrorMsg(ENTRADA_INCORRECTA);
				else {
					if (tipo.equals("paseAtraccion")) prod = new TPaseAtraccion(nombre, price, tipo, id);
					else  prod = new TRepresentacionEvento(nombre, price, tipo, id);
					//TProducto prod = new TProducto(nombre, price, tipo);
				Controlador.getInstancia().accion(EventosControlador.PRODUCTOS_CREAR, prod);
				setVisible(false);
				}
			}
		}
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		int id = (int) datos;
		setVisible(false);
		if(evento == EventosControlador.PRODUCTOS_CREAR_OK) Utils.showOkMsg("Se ha creado el producto con ID " + id);
		else if(evento == EventosControlador.PRODUCTOS_CREAR_ERROR) Utils.showErrorMsg("No se ha logrado crear el producto correctamente");
	}

}
