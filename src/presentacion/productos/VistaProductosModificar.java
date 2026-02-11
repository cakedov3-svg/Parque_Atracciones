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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import negocio.eventos.TEvento;
import negocio.productos.TProducto;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.eventos.Pair;
import presentacion.utils.Utils;

public class VistaProductosModificar extends JFrame implements IGUI{
	
	private static final String ENTRADA_INCORRECTA = "Los datos de entrada han sido introducidos de forma erronea. Vuelva a intentarlo";
	
	public VistaProductosModificar() {
		super("Modificar Producto");
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
		JLabel nombre = new JLabel("Introduce el ID del producto que quieras modificar: ");
		supPanel.add(nombre);
		JTextField selectId = new JTextField();
		selectId.setPreferredSize(new Dimension(200, 25));
		supPanel.add(selectId);
		
		JPanel activoP = new JPanel();
		activoP.setLayout(new FlowLayout());
		JRadioButton activoB = new JRadioButton("Activo");
		activoP.add(activoB);
		JRadioButton noActivoB = new JRadioButton("No activo");
		activoP.add(noActivoB);
		activoB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(( (JRadioButton) e.getSource()).isSelected()) {
					noActivoB.setSelected(false);
				}
			}
		});
		noActivoB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(( (JRadioButton) e.getSource()).isSelected()) {
					activoB.setSelected(false);
				}
			}
		});
		supPanel.add(activoP);
		mainPanel.add(supPanel);
		
		//Segunda fila de componentes
		JPanel infPanel = new JPanel();
		infPanel.setLayout(new FlowLayout());
		infPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel precioText = new JLabel("Indica su NUEVO precio: ");
		infPanel.add(precioText);
		JTextField precio = new JTextField();
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
				String auxId = selectId.getText();
				String auxPrecio = precio.getText(); 
				Boolean estado = null;
				if(activoB.isSelected()) estado = true;
				else if(noActivoB.isSelected()) estado = false;
				if(auxId.equals("") || auxId == null || auxPrecio.equals("") || auxPrecio == null) Utils.showErrorMsg(ENTRADA_INCORRECTA);
				else{
					int id = Integer.parseInt(auxId);
					int precio = Integer.parseInt(auxPrecio);
					Tuple<Integer, Integer, Boolean> par = new Tuple<Integer, Integer, Boolean>(id, precio, estado);
					Controlador.getInstancia().accion(EventosControlador.PRODUCTOS_MODIFICAR, par);
					setVisible(false);
				}
			}
		});
		botones.add(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.PRODUCTOS_VISTA_INICIO, null);
			}
		});
		botones.add(cancelButton);
		mainPanel.add(botones);
		
		//Boton x funcional
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.PRODUCTOS_VISTA_INICIO, null);
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
		TProducto transfer = (TProducto) datos;
		if(evento==EventosControlador.PRODUCTOS_MODIFICAR_OK) Utils.showOkMsg("El producto con id " + transfer.getId() + " ha sido modificado correctamente");
		else if(evento==EventosControlador.PRODUCTOS_MODIFICAR_ERROR) Utils.showErrorMsg("No se ha podido modificar el producto");
		setVisible(false);
	}

}
