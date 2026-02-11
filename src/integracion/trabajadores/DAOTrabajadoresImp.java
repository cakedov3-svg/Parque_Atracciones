package integracion.trabajadores;

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

import negocio.trabajadores.TTrabajador;

public class DAOTrabajadoresImp implements DAOTrabajadores{
	
	private final static String FILE_NAME = "resources/trabajadores.txt";
	
	private JSONObject loadData() throws IOException {
		FileInputStream in;
		File txt = new File(FILE_NAME);
		txt.createNewFile();
		in = new FileInputStream(txt);
		try {
			return new JSONObject(new JSONTokener(in));
		} catch(JSONException e) {
			return new JSONObject();
		} finally {
			in.close();
		}
	}
	
	private boolean saveData(JSONObject JO) {
		FileOutputStream out;
		File txt = new File(FILE_NAME);
		try {
			txt.createNewFile();
			out = new FileOutputStream(txt);
			try (PrintStream p = new PrintStream(out)) {
				p.print(JO.toString());
			}
		} catch(IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean guardarTrabajador(TTrabajador t) {
		JSONObject JO;
		try {
			JO = loadData();
		} catch (IOException e) {
			return false;
		}
		if(JO == null || !JO.has("trabajadores")) {
			JO = new JSONObject();
			JSONArray JA = new JSONArray();
			JA.put(t.getJSON());
			JO.put("trabajadores", JA);
		}
		else {
			JO.getJSONArray("trabajadores").put(t.getJSON());
		}
		return saveData(JO);
	}

	@Override
	public boolean eliminarTrabajador(String id) {
		try {
			JSONObject JO = loadData();
			JSONArray JA = new JSONArray();
			if(JO.has("trabajadores")) JA = JO.getJSONArray("trabajadores");
			int i = 0;
			boolean found = false;
			while(i < JA.length() && !found) {
				if(id.equals(JA.getJSONObject(i).get("id"))) found = true;
				else i++;
			}
			TTrabajador t = TTrabajador.getTransfer(JA.getJSONObject(i));
			t.setActivo(false);
			JA.put(i, t.getJSON());
			JO.put("trabajadores", JA);
			return saveData(JO);
		} catch (IOException e) {
			return false;
		}
		
	}

	@Override
	public TTrabajador buscarTrabajador(String id) {
		try {
			JSONObject JO = loadData();
			JSONArray JA = new JSONArray();
			if(JO.has("trabajadores")) JA = JO.getJSONArray("trabajadores");
			int i = 0;
			boolean found = false;
			while(i < JA.length() && !found) {
				if(id.equals(JA.getJSONObject(i).get("id"))) found = true;
				else i++;
			}
			if(i < JA.length()) {
				return TTrabajador.getTransfer(JA.getJSONObject(i));
			}
			else return null;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public List<TTrabajador> listarTrabajadores() {
		ArrayList<TTrabajador> trabajadores = new ArrayList<>();
		try {
			JSONObject JO = loadData();
			JSONArray JA = new JSONArray();
			if(JO.has("trabajadores")) JA = JO.getJSONArray("trabajadores");
			for(int i = 0; i < JA.length(); i++) 
				if(JA.getJSONObject(i).getBoolean("activo")) trabajadores.add(TTrabajador.getTransfer(JA.getJSONObject(i)));
			return trabajadores;
		} catch (IOException e) {
			return new ArrayList<>();
		}
	}

	@Override
	public boolean modificarTrabajador(TTrabajador t) {
		JSONObject JO;
		try {
			JO = loadData();
		} catch (IOException e) {
			return false;
		}
		JSONArray JA = JO.getJSONArray("trabajadores");
		boolean found = false;
		int i = 0;
		JSONObject aux = new JSONObject();
		while(i < JA.length() && !found) {
			 aux = JA.getJSONObject(i);
			if(t.getId().equals(aux.get("id"))) found = true;
			else i++;
		}
		if(i < JA.length()) {
			JA.put(i, t.getJSON());
			JO.put("trabajadores", JA);
			return saveData(JO);
		}
		else return false;
	}
	
	public List<TTrabajador> quedanTrabajadores(int idDep) {
		JSONObject JO;
		try {
			JO = loadData();
		} catch (IOException e) {
			return new ArrayList<>();
		}
		JSONArray JA;
		if(JO.has("trabajadores")) JA = JO.getJSONArray("trabajadores");
		else return new ArrayList<>();
		
		List<TTrabajador> lista = new ArrayList<>();
		int i = 0;
		while(i < JA.length()) {
			if(idDep == JA.getJSONObject(i).getInt("idDep") && JA.getJSONObject(i).getBoolean("activo")) 
				lista.add(TTrabajador.getTransfer(JA.getJSONObject(i)));
			i++;
		}
		return lista;
	}
	
	public TTrabajador buscarEncargado(String id) {
		TTrabajador t = buscarTrabajador(id);
		if(t == null) return null;
		if("Encargado".equals(t.getTipo())) return t;
		else return null;
	}
	
}
