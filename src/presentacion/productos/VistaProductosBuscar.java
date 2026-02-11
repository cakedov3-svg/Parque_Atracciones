package presentacion.productos;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

import negocio.eventos.TEvento;
import negocio.productos.TProducto;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaProductosBuscar extends JFrame implements IGUI {

	private static final String ENTRADA_INCORRECTA = "Los datos de entrada han sido introducidos de forma erronea. Vuelva a intentarlo";
	
	public VistaProductosBuscar() {
		super("Buscar Producto");
		initGui();
	}
	
	void initGui(){
		
		//Creamos el panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//Le damos forma y tamaño
		mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
		
		//Creamos texto de busqueda de productos
		JPanel auxPanel = new JPanel();
		auxPanel.setLayout(new FlowLayout());
		auxPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel buscar_prod = new JLabel("Introduce el ID del producto:");
		auxPanel.add(buscar_prod);
		
		//Creamos el buscador de productos
		JTextField escribir_prod = new JTextField();
		escribir_prod.setPreferredSize(new Dimension(80, 25));
		auxPanel.add(escribir_prod);
		auxPanel.setBorder(new EmptyBorder(0,0,15,0));
		mainPanel.add(auxPanel);
		
		//Botones OK y Cancel
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		botones.setAlignmentX(CENTER_ALIGNMENT);
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String aux = escribir_prod.getText();
				if(aux.equals("") || aux == null) Utils.showErrorMsg(ENTRADA_INCORRECTA);
				else{
					int id = Integer.parseInt(aux);
					Controlador.getInstancia().accion(EventosControlador.PRODUCTOS_BUSCAR, id);
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
		setVisible(false);
		if(evento == EventosControlador.PRODUCTOS_BUSCAR_OK) {
			TProducto transfer = (TProducto) datos;
			StringBuilder datosEvento = new StringBuilder("Se ha encontrado el siguiente producto con id " + transfer.getId());
			datosEvento.append("\nNombre: " + transfer.getNombre());
			datosEvento.append("\nTipo: " + String.valueOf(transfer.getTipo()));
			datosEvento.append("\nPrecio: " + transfer.getPrecio());
			if(transfer.isActivo()) datosEvento.append("\nEstado: " + "Activo");
			else if(!transfer.isActivo()) datosEvento.append("\nEstado: " + "No activo"); 
			Utils.showOkMsg(datosEvento.toString());
		}
		else Utils.showErrorMsg("No se ha encontrado ningun producto con el id introducido");
	}

}
