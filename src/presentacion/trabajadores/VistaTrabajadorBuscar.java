package presentacion.trabajadores;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.trabajadores.TEncargado;
import negocio.trabajadores.TJefe;
import negocio.trabajadores.TTrabajador;
import negocio.trabajadores.TVendedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;
import presentacion.utils.Utils;

public class VistaTrabajadorBuscar  extends JFrame implements IGUI{
	
	private JTextField textId;
	int status;
	
	public VistaTrabajadorBuscar(){
		super("Buscar un trabajador");
		initGUI();
	}
	
	void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//Introduce el id
		JPanel busqueda = new JPanel();
		busqueda.setLayout(new FlowLayout());
		JLabel lId = new JLabel("Id del trabajador: ");
		textId = new JTextField();
		textId.setPreferredSize(new Dimension(70, 30));
		busqueda.add(lId);
		busqueda.add(textId);
		mainPanel.add(busqueda);
		
		//Añadir botones ok y cancel
				JPanel botones = new JPanel();
				botones.setAlignmentX(CENTER_ALIGNMENT);
				botones.setLayout(new FlowLayout());
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						status = 0;
						setVisible(false);
						okOrCancel();
						Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_VISTA_INICIO, null);
					}
				});
				botones.add(cancelButton);
				
				botones.add(Box.createRigidArea(new Dimension(10,0)));
				
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						status = 1;
						setVisible(false);
						okOrCancel();
						Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_VISTA_INICIO, null);
					}
				});
				botones.add(okButton);
				
				mainPanel.add(botones);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_VISTA_INICIO, null);
			}
		});

		pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void okOrCancel() {
		if(status == 1) {
			Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_BUSCAR, textId.getText());
			Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_CREAR_OK, null);
		} 
		else 
			Controlador.getInstancia().accion(EventosControlador.TRABAJADOR_CREAR_CANCEL, null);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		TTrabajador t = (TTrabajador) datos;
		if(evento == EventosControlador.TRABAJADOR_BUSCAR_OK) {
			StringBuilder aux = new StringBuilder("Se encontro al trabajador con los siguientes datos");
			aux.append("\nId: " + t.getId());
			aux.append("\nNombre: " + t.getNombre());
			aux.append("\nApellido: " + t.getApellido());
			aux.append("\nidDep: " + t.getIdDep());
			aux.append("\nTipo: " + t.getTipo());
			if(t.getTipo() == "Vendedor") {
				TVendedor t1 = (TVendedor) t;
				aux.append("\nId del jefe: " + t1.getIdJefe());
				aux.append("\nNumero de Ventas: " + t1.getNumVentas());
			} else if(t.getTipo() == "Encargado") {
				TEncargado t1 = (TEncargado) t;
				aux.append("\nId del jefe: " + t1.getIdJefe());
			} else {
				TJefe t1 = (TJefe) t;
				aux.append("\nNumero de personas a su cargo: " + t1.getNumSub());
			}
			aux.append("\nActivo: " + t.getActivo());
			setVisible(false);
			Utils.showOkMsg(aux.toString());
		} else {
			setVisible(false);
			Utils.showErrorMsg("No se encontro al trabajador");
		}
	}

}
