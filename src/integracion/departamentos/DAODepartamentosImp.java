package integracion.departamentos;

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

import negocio.departamentos.TDepartamento;

public class DAODepartamentosImp implements DAODepartamentos {
	
	private final static String FILE_NAME = "resources/departamentos.txt";
	private static final String DEPARTAMENTOS_KEY = "departamentos";
	private static final String ACTIVO_KEY = "activo";
	private static final String IDDEP_KEY = "idDep";
	private static final String NEXT_ID_KEY = "next_id";
	
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
	public List<TDepartamento> listarDepartamentos() {
		ArrayList<TDepartamento> departamentos = new ArrayList<>();
		try {
			JSONObject jsonobject = loadData();
			JSONArray json = jsonobject.getJSONArray(DEPARTAMENTOS_KEY);
			for(int i=0;i<json.length();i++) {
				if(json.getJSONObject(i).getBoolean(ACTIVO_KEY)) {
					departamentos.add(TDepartamento.getTransfer(json.getJSONObject(i)));
				}
			}
			return departamentos;
		}catch(IOException e) {
			return null;
		}
	}

	@Override
	public TDepartamento buscarDepartamento(int idDep) {
		try {
			JSONObject jsonobject = loadData();
			JSONArray json = new JSONArray();
			if(jsonobject.has(DEPARTAMENTOS_KEY)) json = jsonobject.getJSONArray(DEPARTAMENTOS_KEY);
			int i=0;
			for(i=0;i<json.length();i++) {//if found->break;
				if(idDep == (json.getJSONObject(i).getInt(IDDEP_KEY))) {
					break;
				}
			}
			if(i<json.length()) {
				return TDepartamento.getTransfer(json.getJSONObject(i));
			}
			else return null;
		}catch(IOException e) {
			return null;
		}
	}

	@Override
	public boolean modificarDepartamento(TDepartamento d) {
		JSONObject jsonobject;
		try {
			jsonobject=loadData();
		}catch(IOException e) {
			return false;
		}
		JSONArray json = jsonobject.getJSONArray(DEPARTAMENTOS_KEY);
		int i=0;
		for(i=0;i<json.length();i++) {
			if(d.getidDep() == json.getJSONObject(i).getInt(IDDEP_KEY)) {
				break;
			}
		}
		if(i<json.length()) {
			json.put(i,d.getJSON());
			jsonobject.put(DEPARTAMENTOS_KEY, json);
			return saveData(jsonobject);
		}
		else return false;
	}

	@Override
	public int anyadirDepartamento(TDepartamento d) {///XXX
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return -1;
		}
		JSONArray JA = new JSONArray();
		if(JO.has(DEPARTAMENTOS_KEY)) JA = JO.getJSONArray(DEPARTAMENTOS_KEY);
		JSONObject newObj = d.getJSON();
		int next_id = 0;
		if(JO.has(NEXT_ID_KEY)) next_id = JO.getInt(NEXT_ID_KEY);
		newObj.put(IDDEP_KEY, next_id);
		JA.put(newObj);
		JO.put(DEPARTAMENTOS_KEY, JA);
		JO.put(NEXT_ID_KEY, next_id + 1);
		if(saveData(JO)) return newObj.getInt(IDDEP_KEY);
		else return -1;
	}

	@Override
	public boolean eliminarDepartamento(int idDep) {
		try {
			JSONObject jsonobject = loadData();
			JSONArray json = jsonobject.getJSONArray(DEPARTAMENTOS_KEY);
			int i=0;
			for(i=0;i<json.length();i++) {//if found->break;
				if(idDep == (json.getJSONObject(i).getInt(IDDEP_KEY))) {
					break;
				}
			}
			TDepartamento d = TDepartamento.getTransfer(json.getJSONObject(i));
			d.setActivo(false);
			json.put(i, d.getJSON());
			jsonobject.put(DEPARTAMENTOS_KEY, json);
			return saveData(jsonobject);
		}catch (IOException e) {
			return false;
		}
	}
	
}
