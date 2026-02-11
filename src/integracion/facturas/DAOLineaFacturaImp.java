package integracion.facturas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.facturas.TLineaFactura;

public class DAOLineaFacturaImp implements DAOLineaFactura{
	private static String PERSISTENCIA_LINEAS_FACTURAS = "resources/datosLineasFactura.txt";
	private static String LINEAS_FACTURA_KEY = "lineas";
	private static final String ID_KEY = "id";
	private static final String ID_FACTURA_KEY = "idFactura";
	
	private JSONObject leerJSON() throws IOException{
		File txt = new File(PERSISTENCIA_LINEAS_FACTURAS);
		txt.createNewFile();
		FileInputStream in = new FileInputStream(txt);
		try {
			return new JSONObject(new JSONTokener(in));
		} catch(JSONException e) {
			return new JSONObject();
		} finally {
			in.close();
		}
	}
	
	private void guardarArchivo(JSONObject JO) throws IOException{
		File txt = new File(PERSISTENCIA_LINEAS_FACTURAS);
		txt.createNewFile();
		FileOutputStream out = new FileOutputStream(txt);
		try (PrintStream p = new PrintStream(out)) {
			p.print(JO.toString());
		}
	}
	
	@Override
	public int guardarLineaFactura(TLineaFactura newLinea) {
		try {
			JSONObject JO = leerJSON();
			JSONArray lineas = new JSONArray();
			if(JO.has(LINEAS_FACTURA_KEY)) 
				lineas = JO.getJSONArray(LINEAS_FACTURA_KEY);
			
			JSONObject aux = newLinea.getJSON();
			int newId = getNewIdLineaFactura(lineas);
			aux.put(ID_KEY, newId);
			lineas.put(aux);
			
			JO.put(LINEAS_FACTURA_KEY, lineas);

			guardarArchivo(JO);
			return newId;
		} catch (IOException e) {
			return -1;
		}
	}
	
	@Override
	public int[] guardarLineasFactura(List<TLineaFactura> newLineas) {
		int newIds[] = new int[newLineas.size()];
		
		int i = 0;
		for(TLineaFactura linea: newLineas) {
			newIds[i] = guardarLineaFactura(linea);
			i++;
		}
		return newIds;
	}
	
	@Override
	public List<TLineaFactura> getLineasFactura(int idFactura) {
		List<TLineaFactura> lineasFactura = new ArrayList<TLineaFactura>();
		try {
			JSONObject JO = leerJSON();
			JSONArray lineas = new JSONArray();
			if(JO.has(LINEAS_FACTURA_KEY)) 
				lineas = JO.getJSONArray(LINEAS_FACTURA_KEY);
			
			for(int i = 0; i < lineas.length(); ++i) {
				if(lineas.getJSONObject(i).getInt(ID_FACTURA_KEY) == idFactura)
					lineasFactura.add(TLineaFactura.getTransfer(lineas.getJSONObject(i)));
			}
			return lineasFactura;
		} catch (IOException e) {
			return lineasFactura;
		}
	}
	
	private int getNewIdLineaFactura(JSONArray JO) { //Si ya hemos leido el archivo
		return JO.length();
	}
	
}