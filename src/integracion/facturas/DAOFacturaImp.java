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

import negocio.facturas.TFactura;

public class DAOFacturaImp implements DAOFactura{
	private static final String PERSISTENCIA_FACTURAS = "resources/datosFacturas.txt";
	private static final String FACTURAS_KEY = "facturas";
	private static final String ID_KEY = "id";
	
	
	JSONObject leerJSON() throws IOException{
		File txt = new File(PERSISTENCIA_FACTURAS);
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
		File txt = new File(PERSISTENCIA_FACTURAS);
		txt.createNewFile();
		FileOutputStream out = new FileOutputStream(txt);
		try (PrintStream p = new PrintStream(out)) {
			p.print(JO.toString());
		}
	}
	
	@Override
	public int guardarFactura(TFactura factura)   {
		try {
			JSONObject JO = leerJSON();
			JSONArray facturas = new JSONArray();
			if(JO.has(FACTURAS_KEY)) 
				facturas = JO.getJSONArray(FACTURAS_KEY);
			
			JSONObject aux = factura.getJSON();
			int newId = getNewIdFactura(facturas);
			aux.put(ID_KEY, newId);
			facturas.put(aux);
			
			JO.put(FACTURAS_KEY, facturas);
			guardarArchivo(JO);
			return newId;
		} catch (IOException e) {
			return -1;
		}
	}
	
	@Override
	public TFactura getFactura(int id) {
		try {
			JSONObject JO = leerJSON();
			JSONArray facturas = new JSONArray();
			TFactura tF = null;
			if(JO.has(FACTURAS_KEY)) 
				facturas = JO.getJSONArray(FACTURAS_KEY);
			
			int i = 0;
			while( i < facturas.length() && tF == null) {
				if(facturas.getJSONObject(i).getInt(ID_KEY) == id)
					tF =  TFactura.getTransfer(facturas.getJSONObject(i));
				i++;
			}
			return tF;
		} catch (IOException e) {
			return null;
		}
	}
	
	@Override
	public List<TFactura> getFacturas(){
		List<TFactura> lista = new ArrayList<TFactura>();
		try {
			JSONObject JO = leerJSON();
			JSONArray facturas = new JSONArray();
			if(JO.has(FACTURAS_KEY)) 
				facturas = JO.getJSONArray(FACTURAS_KEY);
			
			for(int i = 0; i<facturas.length(); ++i) 
				lista.add(TFactura.getTransfer(facturas.getJSONObject(i)));
				
			return lista;
		} catch (IOException e) {
			return lista;
		}
	}
	
	private int getNewIdFactura(JSONArray JO) { 
		return JO.length();
	}
	
}
