package presentacion.productos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import negocio.atracciones.TAtraccion;
import negocio.eventos.TEvento;
import negocio.productos.TProducto;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.trabajadores.ModeloTabla;

public class VistaProductosListar extends JFrame implements IGUI{

	
	TablaProductos productos;
	static boolean creado = false;
	
	public VistaProductosListar(){
		super("Listar Productos");
		initGUI();
	}
	
	void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15,25,15,25));
		
		productos = new TablaProductos();
		JTable tabla = new JTable(productos);
		JScrollPane scroll = new JScrollPane(tabla);
		mainPanel.add(scroll);
		
		//Botones OK y Cancel
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout(FlowLayout.TRAILING));
		botones.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton cancelButton = new JButton("Atrás");
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
		if(evento == EventosControlador.PRODUCTOS_LISTAR_OK) {
			List<TProducto> lista = (List<TProducto>) datos;
			productos.update(lista);
			pack();
			setVisible(true);
		}
		else if (evento == EventosControlador.PRODUCTOS_LISTAR_ERROR) {
		}
	}

}
