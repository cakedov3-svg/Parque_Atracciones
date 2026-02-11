package integracion.clientes;

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

import negocio.clientes.TCliente;
import negocio.eventos.TEvento;
import negocio.trabajadores.TTrabajador;

public class DAOClientesImp implements DAOClientes {

	private final static String FILE_NAME = "resources/datosClientes.txt";
	
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
		File txt = new File(FILE_NAME);
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
	public TCliente getCliente(JSONObject j) {
		return TCliente.getTransfer(j);
	}

	@Override
	public int guardarCliente(TCliente c) {
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return -1; 
		}
		JSONArray JA = new JSONArray();
		if(JO.has("clientes")) JA = JO.getJSONArray("clientes");
		JSONObject newObj = c.getJSON();
		int next_id = 0;
		if(JO.has("next_id")) next_id = JO.getInt("next_id");
		newObj.put("id", next_id);
		JA.put(newObj);
		JO.put("clientes", JA);
		JO.put("next_id", next_id + 1);
		if(saveData(JO)) return newObj.getInt("id");
		else return -1;
	}

	@Override
	public boolean eliminarCliente(int id) {
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return false; 
		}
		JSONArray JA = JO.getJSONArray("clientes"); 
		int i = 0;
		boolean found = false;
		while(i < JA.length() && !found) {
			if(id == JA.getJSONObject(i).getInt("id")) found = true;
			else i++;
		}
		if(i < JA.length() && JA.getJSONObject(i).getBoolean("activo")) {
			TCliente t = TCliente.getTransfer(JA.getJSONObject(i));
			t.setActivo(false);
			JA.put(i, t.getJSON());
			return saveData(JO);
		}
		else return false;
	}

	@Override
	public List<TCliente> listarClientes() {
	List<TCliente> clientes=new ArrayList<>();
	JSONObject JO;
	try {
		JO = loadData();
		if(JO.has("clientes")) {
			JSONArray JA=JO.getJSONArray("clientes");
			int i=0;
			while(i<JA.length()) {
				if(JA.getJSONObject(i).getBoolean("activo")) {
					clientes.add(getCliente(JA.getJSONObject(i)));
				}
				i++;
			}
			return clientes;
		}
		else return null;
		
	} catch (IOException e) {
		return null;
	}
	
	
	}

	@Override
	public boolean modificarCliente(TCliente c) {
		JSONObject jsonobject;
		try {
			jsonobject=loadData();
		}catch(IOException e) {
			return false;
		}
		JSONArray json = jsonobject.getJSONArray("clientes");
		int i=0;
		for(i=0;i<json.length();i++) {
			if(c.getId() == json.getJSONObject(i).getInt("id")) {
				break;
			}
		}
		if(i<json.length()) {
			json.put(i,c.getJSON());
			jsonobject.put("clientes", json);
			return saveData(jsonobject);
		}
		else return false;
	}

	@Override
	public TCliente buscarCliente(int id) {
		try {
			JSONObject JO = loadData();
			JSONArray JA = new JSONArray();
			if(JO.has("clientes")) JA = JO.getJSONArray("clientes");
			int i = 0;
			boolean found = false;
			while(i < JA.length() && !found) {
				if(id == JA.getJSONObject(i).getInt("id")) found = true;
				else i++;
			}
			
			if(i < JA.length()) {
				return getCliente(JA.getJSONObject(i));
			}
			else return null;
		} catch (IOException e) {
			return null;
		}
	}
	
}
