package integracion.eventos;

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

import negocio.eventos.TEvento;

public class DAOEventoImp implements DAOEvento {
	
	private final static String FILE = "resources/eventos.txt";
	private final static String EVENTOS_KEY = "eventos";
	private final static String ID_KEY = "id";
	private final static String ACTIVO_KEY = "activo";
	private static final String NEXT_ID_KEY = "next_id";
	
	private JSONObject loadData() throws IOException {
		File txt = new File(FILE);
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
	
	private boolean saveData(JSONObject JO) {
		File txt = new File(FILE);
		try {
			txt.createNewFile();
			FileOutputStream out = new FileOutputStream(txt);
			try (PrintStream p = new PrintStream(out)) {
				p.println(JO.toString());
			}
		} catch (IOException e) {
			return false; 
		} 
		return true;
	}

	@Override
	public int crearEvento(TEvento t) {
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return -1; 
		}
		JSONArray JA = new JSONArray();
		if(JO.has(EVENTOS_KEY)) JA = JO.getJSONArray(EVENTOS_KEY);
		JSONObject newObj = t.getJSON();
		int next_id = 0;
		if(JO.has(NEXT_ID_KEY)) next_id = JO.getInt(NEXT_ID_KEY);
		newObj.put(ID_KEY, next_id);
		JA.put(newObj);
		JO.put(EVENTOS_KEY, JA);
		JO.put(NEXT_ID_KEY, next_id + 1);
		if(saveData(JO)) return newObj.getInt(ID_KEY);
		else return -1;
	}

	@Override
	public boolean eliminarEvento(int id) {
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return false; 
		}
		JSONArray JA = new JSONArray();
		if (JO.has(EVENTOS_KEY)) JA = JO.getJSONArray(EVENTOS_KEY);
		int i = 0;
		boolean found = false;
		while(i < JA.length() && !found) {
			if(id == JA.getJSONObject(i).getInt(ID_KEY)) found = true;
			else i++;
		}
		if(i < JA.length() && JA.getJSONObject(i).getBoolean(ACTIVO_KEY)) {
			TEvento t = TEvento.getTransfer(JA.getJSONObject(i));
			t.setActivo(false);
			JA.put(i, t.getJSON());
			return saveData(JO);
		}
		else return false;
	}

	@Override
	public TEvento buscarEvento(int id) {
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return null;
		}
		JSONArray JA = new JSONArray();
		if (JO.has(EVENTOS_KEY)) JA = JO.getJSONArray(EVENTOS_KEY);
		int i = 0;
		boolean found = false;
		while (i < JA.length() && !found) {
			if (id == JA.getJSONObject(i).getInt(ID_KEY)) found = true;
			else i++;
		}
		if (i < JA.length()) { //Devuelve activos y no activos
			return TEvento.getTransfer(JA.getJSONObject(i));
		}
		else return null;
	}

	@Override
	public List<TEvento> listarEventos() {
		ArrayList<TEvento> lista = new ArrayList<>();
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return null;
		}
		JSONArray JA = new JSONArray();
		if(JO.has(EVENTOS_KEY)) JA = JO.getJSONArray(EVENTOS_KEY);
		for(int i = 0; i < JA.length(); i++) { //Muestra solo activos
			if(JA.getJSONObject(i).getBoolean(ACTIVO_KEY)) lista.add(TEvento.getTransfer(JA.getJSONObject(i)));
		}
		return lista;
	}

	@Override
	public boolean modificarEvento(TEvento t) {
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return false; 
		}
		JSONArray JA = new JSONArray();
		if (JO.has(EVENTOS_KEY)) JA = JO.getJSONArray(EVENTOS_KEY);
		int i = 0;
		boolean found = false;
		while (i < JA.length() && !found) {
			if (t.getId() == JA.getJSONObject(i).getInt(ID_KEY)) found = true;
			else i++;
		}
		if (i < JA.length()) { 
			JA.put(i, t.getJSON()); 
			return saveData(JO);
		}
		else return false;
	}

}
