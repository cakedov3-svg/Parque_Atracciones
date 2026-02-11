package presentacion.facturas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import negocio.facturas.TDatosVenta;
import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;
import negocio.productos.TProducto;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaFacturaAnyadir extends JFrame implements IGUI{

	private static final int ROWS = 3;
	private static final int COLS = 2;
	private static final int GAP = 10;
	
	
	JTextField idVendedor;
	JTextField idCliente;
	JLabel precioT;
	JTable tablaLineas;
	ModeloTablaLineas modeloTablaLineas;
	JDialog dialogoLineas;
	
	JComboBox<String> prodBox;
	DefaultComboBoxModel<String> productosBoxModel;
	int prodSel;
	
	List<TProducto> productos;
	JSpinner cantSpinner;
	
	List<TLineaFactura> lineas;
	TDatosVenta datosVenta;
	
	
	public VistaFacturaAnyadir(){
		super("Comprar");
		initGUI();
	}
	
	void initGUI(){
		//Establecer el panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
		
		
		mainPanel.add(crearPanelInfoFactura());
		
		mainPanel.add(crearPanelTablaLineas());
		
		mainPanel.add(crearPanelAnyadirLinea());
		
		mainPanel.add(crearPanelCancelarOK());
		
		
		//Panel del botón de vuelta atrás:
		JPanel buttonPanelBack = new JPanel();
		buttonPanelBack.setLayout(new FlowLayout(FlowLayout.TRAILING));
		buttonPanelBack.add(crearButton("Atrás", EventosControlador.SISTEMA_PANTALLA_INICIO, false));

		mainPanel.add(buttonPanelBack);
		
		
		this.addWindowListener (new WindowAdapter() {
				public void windowClosing(WindowEvent e) { 
					setVisible(false);
					Controlador.getInstancia().accion(EventosControlador.FACTURAS_INICIO, null);
				}
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}


	private JPanel crearPanelCancelarOK() {
		JPanel cancelOKPanel = new JPanel();
		cancelOKPanel.setAlignmentX(CENTER_ALIGNMENT);
		cancelOKPanel.setLayout(new FlowLayout());
	
		JButton cancel = crearButton("Cancel", EventosControlador.FACTURAS_INICIO, false);
		cancelOKPanel.add(cancel);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idCl = -1;
				try {
					idCl = Integer.parseInt(idCliente.getText());
				}catch(NumberFormatException nfe) {
					Utils.showErrorMsg("El id del cliente debe ser un número entero. Vuelva a intenterlo");
					return;
				}
				String idV = idVendedor.getText();
				if(idV == null) {
					Utils.showErrorMsg("Debes introducir un id para el vendedor. Vuelva a intenterlo");
					return;
				}
				Double precio = Double.parseDouble(precioT.getText());
				if(precio == 0) {
					Utils.showErrorMsg("Debes comprar al menos un pase. Vuelva a intenterlo");
					return;
				}
				Calendar fAux = new GregorianCalendar();
				String fecha = fAux.get(Calendar.DAY_OF_MONTH) + "/" + (fAux.get(Calendar.MONTH) + 1) + "/" + fAux.get(Calendar.YEAR);
				
				TFactura tF = new TFactura(idCl ,idV ,precio, fecha);
				TDatosVenta tDV = new TDatosVenta(tF, lineas);

				Utils.getWindow(ok).setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.FACTURAS_ANYADIR, tDV);
			}
			
		});
		cancelOKPanel.add(ok);
		
	
		return cancelOKPanel;
	}

	private JPanel crearPanelInfoFactura() {
		JPanel infoFactura = new JPanel();
		infoFactura.setBorder(BorderFactory.createTitledBorder("Información de la Factura:"));
		infoFactura.setLayout(new GridLayout(ROWS, COLS, GAP ,GAP));
		
		infoFactura.add(new JLabel("ID Vendedor:"));
		idVendedor = new JTextField(10);
		idVendedor.setEditable(true);
		infoFactura.add(idVendedor);
		
		infoFactura.add(new JLabel("ID Cliente:"));
		idCliente = new JTextField(10);
		idCliente.setEditable(true);
		infoFactura.add(idCliente);
		
		infoFactura.add(new JLabel("Precio total acumulado (en €):"));
		precioT = new JLabel("0");
		infoFactura.add(precioT);
		
		return infoFactura;
	}

	private JScrollPane crearPanelTablaLineas() {
		modeloTablaLineas = new ModeloTablaLineas();
		JTable tabla = (new JTable(modeloTablaLineas));
		tabla.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane tablePanel = new JScrollPane(tabla);
		tablePanel.setBorder(BorderFactory.createTitledBorder("Lineas:"));
		return tablePanel;
	}
	

	private JPanel crearPanelAnyadirLinea() {
		JPanel anyadirLineaP = new JPanel();
		anyadirLineaP.setBorder(BorderFactory.createTitledBorder("Añadir Factura:"));
		anyadirLineaP.setLayout(new BoxLayout(anyadirLineaP, BoxLayout.Y_AXIS));
		
		JPanel supPanel = new JPanel();
		supPanel.setLayout(new FlowLayout());
		supPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		//Añadimos ComboBox de productos
		supPanel.add(new JLabel("Producto: "));
		prodSel = 0;
		productos = new ArrayList<TProducto>(); 
		productosBoxModel = new DefaultComboBoxModel<String>();
		prodBox = new JComboBox<String>(productosBoxModel);
		prodBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				prodSel = prodBox.getSelectedIndex();
			}
			
		});
		
		supPanel.add(prodBox);
		
		//Añadimos Spinner de cantidad
		supPanel.add(new JLabel("Cantidad:"));
		SpinnerNumberModel snm = new SpinnerNumberModel();
		snm.setValue(1); snm.setMinimum(0);
		cantSpinner = new JSpinner((snm));
		supPanel.add(cantSpinner);

		anyadirLineaP.add(supPanel);
		
		
		JPanel infPanel = new JPanel();
		infPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		
		lineas = new ArrayList<TLineaFactura>();
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TProducto tP = productos.get(prodSel);
				TLineaFactura tLF = new TLineaFactura();
				
				tLF.setNombreProducto(tP.getNombre());
				tLF.setIdProducto(tP.getId());
				tLF.setCantidad((int)snm.getValue());
				tLF.setPrecio(tP.getPrecio() * tLF.getCantidad());
				//tLF.setIdProducto(ABORT);
				
				boolean estaProd = false;
				for(TLineaFactura lf: lineas) {
					if(lf.getNombreProducto().equals(tLF.getNombreProducto())) {
						lf.setCantidad(lf.getCantidad()+tLF.getCantidad());
						lf.setPrecio(lf.getPrecio() + tLF.getPrecio());
						estaProd = true;
					}
				}
				if(!estaProd)lineas.add(tLF);
				
				modeloTablaLineas.actTabla(lineas);
				precioT.setText(Double.toString(Double.parseDouble(precioT.getText()) + tLF.getPrecio()));
			}
		});
		infPanel.add(okButton);
		anyadirLineaP.add(infPanel);
		
		return anyadirLineaP;
	}
	
	
	private JButton crearButton(String title, int evento, boolean visible) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utils.getWindow(button).setVisible(visible);
				Controlador.getInstancia().accion(evento, null);
			}
		});
		return button;
	}
	
	
	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.FACTURAS_ANYADIR_VISTA) {
			productos = (List<TProducto>) datos;
			for(TProducto tP: productos) {
				productosBoxModel.addElement(tP.getNombre());
			}
			return;
		}
		else if(evento == EventosControlador.FACTURAS_ANYADIR_OK) {
			Utils.showOkMsg("Se ha creado la factura con id " + (int)datos);
			Utils.getWindow(this).setVisible(false);
			Controlador.getInstancia().accion(EventosControlador.FACTURAS_INICIO, null);
		}
		else if(evento == EventosControlador.FACTURAS_ANYADIR_KO){
			Utils.getWindow(this).setVisible(false);
			Utils.showErrorMsg((String) datos);
			Controlador.getInstancia().accion(EventosControlador.FACTURAS_ANYADIR_VISTA, null);
		}
	}
}
