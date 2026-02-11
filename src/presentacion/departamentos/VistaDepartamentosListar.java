package presentacion.departamentos;

import java.awt.FlowLayout; 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import negocio.departamentos.TDepartamento;
import presentacion.controlador.Controlador;
import presentacion.controlador.EventosControlador;
import presentacion.controlador.IGUI;

public class VistaDepartamentosListar extends JFrame implements IGUI{
	
	ModeloTablaDepartamentos model;
	static boolean creado = false;
	
	public VistaDepartamentosListar() {
		super("Listar departamentos");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titulo = new JLabel("Listar departamentos");
		Font fuente = new Font("Dialog", Font.BOLD, 15);
		titulo.setFont(fuente);
		panelTitulo.add(titulo);
		panelTitulo.setBorder(new EmptyBorder(0,0,10,0));
		mainPanel.add(panelTitulo);
		
		model = new ModeloTablaDepartamentos();
		JTable table=new JTable(model);
		JScrollPane sp=new JScrollPane(table);
		mainPanel.add(sp);
		
		JPanel buttonPanelBack = new JPanel();
		buttonPanelBack.setLayout(new FlowLayout(FlowLayout.TRAILING));
				
		buttonPanelBack.add(crearButton("Atrás", EventosControlador.DEPARTAMENTOS_VISTA_INICIO));

		mainPanel.add(buttonPanelBack);
		
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				setVisible(false);
				Controlador.getInstancia().accion(EventosControlador.DEPARTAMENTOS_VISTA_INICIO, null);
			}
	});
	
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == EventosControlador.DEPARTAMENTOS_LISTAR_OK) {
			List<TDepartamento> lista = (List<TDepartamento>) datos;
			model.update(lista);
			pack();
			setVisible(true);
		}
	}
	
	private JButton crearButton(String title, int evento) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(evento, null);
				setVisible(false);
			}
		});
		return button;
	}

}
