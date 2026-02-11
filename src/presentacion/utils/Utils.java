package presentacion.utils;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Utils {

	/*
	 * return the frame to which 'c' belongs
	 */
	public static Frame getWindow(Component c) {
		Frame w = null;
		if (c != null) {
			if (c instanceof Frame)
				w = (Frame) c;
			else
				w = (Frame) SwingUtilities.getWindowAncestor(c);
		}
		return w;
	}

	public static void showErrorMsg(String msg) {
		showErrorMsg(null, msg);
	}

	public static void showErrorMsg(Component c, String msg) {
		JOptionPane.showMessageDialog(getWindow(c), msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showOkMsg(String msg) {
		JOptionPane.showMessageDialog(getWindow(null), msg, "OK", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void quit(Component c) {

		int n = JOptionPane.showOptionDialog(getWindow(c), "¿Seguro que deseas salir?", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == 0) {
			System.exit(0);
		}
	}
}