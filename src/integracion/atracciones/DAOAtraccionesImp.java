package integracion.atracciones;

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

import negocio.atracciones.TAtraccion;

public class DAOAtraccionesImp implements DAOAtracciones{
	
	private static final String NOMBRE_ARCHIVO = "resources/atracciones.txt";
	private static final String ATRACCIONES_KEY="atracciones";
	private static final String ID_KEY = "id";
	private static final String ACTIVA_KEY = "activa";
	private static final String ID_ENCARGADO_KEY = "id_encargado";

	
	private JSONObject leerDatos() throws IOException {
		FileInputStream leer;
		File archivo = new File(NOMBRE_ARCHIVO);
		archivo.createNewFile();
		leer = new FileInputStream(archivo);
		try {
			return new JSONObject(new JSONTokener(leer));
		}
		catch(JSONException e) {
			return new JSONObject();
		}
		finally {
			leer.close();
		}
		
	}
	
	private boolean guardarDatos(JSONObject jo) {
		FileOutputStream guardar;
		File archivo = new File(NOMBRE_ARCHIVO);
		try {
			archivo.createNewFile();
			guardar = new FileOutputStream(archivo);
			try(PrintStream p = new PrintStream(guardar)){
				p.print(jo.toString());
			}
		}
		catch(IOException e) {
			return false;
		}
		return true;
	}
	
	private int generarId(JSONArray o) {
		return o.length();
	}

	@Override
	public int guardarAtraccion(TAtraccion atraccion) {
		JSONObject o;
		try {
			o=leerDatos();
		}
		catch(IOException e) {
			return -1;
		}
		JSONArray a;
		int id=-1;
		if(o==null) o=new JSONObject();
		if(!o.has(ATRACCIONES_KEY)) a= new JSONArray();
		else a = o.getJSONArray(ATRACCIONES_KEY);
		JSONObject jsonAtraccion = atraccion.getJSON();
		if(jsonAtraccion.has(ID_KEY)) {
			a.put(jsonAtraccion);
		}
		else {
			jsonAtraccion.put(ID_KEY, generarId(a));
			 id=jsonAtraccion.getInt(ID_KEY);
			a.put(jsonAtraccion);
			o.put(ATRACCIONES_KEY, a);
			if(guardarDatos(o)) return id;
			else return -1;
		}
		return id;
	}

	@Override
	public boolean eliminarAtraccion(int id) {
		try {
			JSONObject o = leerDatos();
			JSONArray a;
			if(o.has(ATRACCIONES_KEY)) a=o.getJSONArray(ATRACCIONES_KEY);
			else a = new JSONArray();
			int i=0;
			boolean encontrado=false;
			while(!encontrado&&i<a.length()) {
				if(id==a.getJSONObject(i).getInt(ID_KEY)) encontrado=true;
				else ++i;
			}
			if(encontrado) {
				TAtraccion atraccion = TAtraccion.getTransfer(a.getJSONObject(i));
				if(atraccion.getActiva()) {
					atraccion.setActiva(false);
					a.put(i, atraccion.getJSON());
					o.put(ATRACCIONES_KEY, a);
					return guardarDatos(o);
				}
				else return false;
			}
			else return false;
		}
		catch(IOException e) {
			return false;
		}
	}

	@Override
	public TAtraccion buscarAtraccion(int id) {
		try {
			JSONObject o = leerDatos();
			JSONArray a;
			if(o.has(ATRACCIONES_KEY)) a = o.getJSONArray(ATRACCIONES_KEY);
			else a = new JSONArray();
			int i=0;
			boolean encontrado = false;
			while(i<a.length()&&!encontrado) {
				if(a.getJSONObject(i).getInt(ID_KEY)==id) encontrado=true;
				else ++i;
			}
			if(encontrado) {
				return TAtraccion.getTransfer(a.getJSONObject(i));
			}
			return null;
		}
		catch(IOException e) {
			return null;
		}
	}

	@Override
	public List<TAtraccion> listarAtracciones() {
		try {
			JSONObject o = leerDatos();
			JSONArray a;
			if(o.has(ATRACCIONES_KEY)) a = o.getJSONArray(ATRACCIONES_KEY);
			else a = new JSONArray();
			List<TAtraccion> listaAtracciones = new ArrayList<>();
			JSONObject jsonAtraccion;
			for(int i=0;i<a.length();++i) {
				jsonAtraccion = a.getJSONObject(i);
				if(jsonAtraccion.getBoolean(ACTIVA_KEY)) listaAtracciones.add(TAtraccion.getTransfer(jsonAtraccion));
			}
			return listaAtracciones;
		}
		catch(IOException e){
			return new ArrayList<>();
		}
	}

	@Override
	public boolean modificarAtraccion(TAtraccion atraccion) {
		try {
			JSONObject o = leerDatos();
			JSONArray a;
			if(o.has(ATRACCIONES_KEY)) a = o.getJSONArray(ATRACCIONES_KEY);
			else a = new JSONArray();
			boolean encontrado = false;
			int i = 0;
			while(i<a.length()&&!encontrado) {
				if(a.getJSONObject(i).getInt(ID_KEY)==atraccion.getId()) encontrado=true;
				else ++i;
			}
			if(encontrado) {
				a.put(i, atraccion.getJSON());
				o.put(ATRACCIONES_KEY, a);
				return guardarDatos(o);
			}
			else return false;
		}
		catch(IOException e) {
			return false;
		}
	}
	
	@Override
	public List<TAtraccion> atraccionesEncargado(String idEncargado) {
		try {
			JSONObject o = leerDatos();
			JSONArray a;
			if(o.has(ATRACCIONES_KEY)) a = o.getJSONArray(ATRACCIONES_KEY);
			else a = new JSONArray();
			List<TAtraccion> listaAtracciones = new ArrayList<>();
			JSONObject jsonAtraccion;
			for(int i=0;i<a.length();++i) {
				jsonAtraccion = a.getJSONObject(i);
				if(jsonAtraccion.getBoolean(ACTIVA_KEY)&&jsonAtraccion.getString(ID_ENCARGADO_KEY).equals(idEncargado)) listaAtracciones.add(TAtraccion.getTransfer(jsonAtraccion));
			}
			return listaAtracciones;
		}
		catch(IOException e) {
			return new ArrayList<>();
		}
	}

}
