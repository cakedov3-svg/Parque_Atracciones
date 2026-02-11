package integracion.productos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.atracciones.TAtraccion;
import negocio.eventos.TEvento;
import negocio.productos.TProducto;

public class DAOProductoImp implements DAOProducto {
	
	private static final String PERSISTENCIA_PRODUCTOS = "resources/datosProductos.txt";
	private static final String PRODUCTOS_KEY = "productos";
	private static final String ID_KEY = "id";
	private static final String ID_ATRACCION_KEY = "idAtraccion";
	private static final String ACTIVO_KEY = "activo";
	private static final String NEXT_ID_KEY = "next_id";
	private static final String NOMBRE_KEY = "nombreProducto";
	private static final String TIPO_PRODUCTO_KEY = "tipoProducto";
	private static final String ID_EVENTO_KEY = "idEvento";


	
	private void crearFile() {
		try {
			Path filePath = Paths.get(PERSISTENCIA_PRODUCTOS);
			Files.createFile(filePath);
		} catch (IOException e1) {
		}
	}
	
	JSONObject leerJSON() {
		try {
			InputStream inFile = new FileInputStream(new File(PERSISTENCIA_PRODUCTOS));
			return new JSONObject(new JSONTokener(inFile));
		}catch(FileNotFoundException e) {
			crearFile();
			return new JSONObject();
		}
	}

	private void overwriteFile(JSONObject JO) {
		try {
			OutputStream outFile = new FileOutputStream(new File(PERSISTENCIA_PRODUCTOS));
			PrintStream p = new PrintStream(outFile);
			p.println(JO.toString());
		}catch(FileNotFoundException e){
			crearFile();
			overwriteFile(JO);
		}
	}
	
	private JSONObject loadData() throws IOException {
		File txt = new File(PERSISTENCIA_PRODUCTOS);
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
		FileOutputStream out;
		File txt = new File(PERSISTENCIA_PRODUCTOS);
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
	public int anyadirProducto(TProducto producto) {
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return -1; //TODO pendiente
		}
		JSONArray JA = new JSONArray();
		if(JO.has(PRODUCTOS_KEY)) JA = JO.getJSONArray(PRODUCTOS_KEY);
		JSONObject newObj = producto.getJSON();
		int next_id = 0;
		if(JO.has(NEXT_ID_KEY)) next_id = JO.getInt(NEXT_ID_KEY);
		newObj.put(ID_KEY, next_id);
		JA.put(newObj);
		JO.put(PRODUCTOS_KEY, JA);
		JO.put(NEXT_ID_KEY, next_id + 1);
		if(saveData(JO)) return newObj.getInt(ID_KEY);
		else return -1;
	}
	
	
	@Override
	public TProducto buscarProducto(int id) {
		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return null; //TODO o new TEvento()? Pendiente
		}
		JSONArray JA = JO.getJSONArray(PRODUCTOS_KEY); //TODO necesario comprobar si JO tiene JA?
		int i = 0;
		boolean found = false;
		while (i < JA.length() && !found) {
			if (id == JA.getJSONObject(i).getInt(ID_KEY)) found = true;
			else i++;
		}
		if (i < JA.length()) { //TODO && JA.getJSONObject(i).getBoolean(ACTIVO_KEY) ??? buscar dev no activos tmb ???
			return TProducto.getTransfer(JA.getJSONObject(i));
		}
		else return null;
	}


	@Override
	public List<TProducto> listarProductos() {
		ArrayList<TProducto> lista = new ArrayList<>();
		try {
			JSONObject JO = leerJSON();
			JSONArray productos = new JSONArray(); 

			if(JO.has(PRODUCTOS_KEY)) 
				productos = JO.getJSONArray(PRODUCTOS_KEY);
			
			for(int i = 0; i < productos.length(); i++) 
				if(productos.getJSONObject(i).getBoolean(ACTIVO_KEY)) lista.add(TProducto.getTransfer(productos.getJSONObject(i)));
			return lista;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<TProducto> productoAsociadoAtraccion(int idAtraccion) {
		try {
			JSONObject o = loadData();
			JSONArray a;
			if(o.has(PRODUCTOS_KEY)) a = o.getJSONArray(PRODUCTOS_KEY);
			else a = new JSONArray();
			List<TProducto> lista = new ArrayList<>();
			JSONObject jsonProducto;
			for(int i=0;i<a.length();++i) {
				jsonProducto = a.getJSONObject(i);
				if("paseAtraccion".equals(jsonProducto.getString(TIPO_PRODUCTO_KEY))) {
					if(jsonProducto.getBoolean(ACTIVO_KEY)&&jsonProducto.getInt(ID_ATRACCION_KEY)==idAtraccion) lista.add(TProducto.getTransfer(jsonProducto));
				}
			}
			return lista;
		}
		catch(IOException e) {
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<TProducto> productoAsociadoEvento(int idEvento) {
		try {
			JSONObject o = loadData();
			JSONArray a;
			if(o.has(PRODUCTOS_KEY)) a = o.getJSONArray(PRODUCTOS_KEY);
			else a = new JSONArray();
			List<TProducto> lista = new ArrayList<>();
			JSONObject jsonProducto;
			for(int i=0;i<a.length();++i) {
				jsonProducto = a.getJSONObject(i);
				if("paseEvento".equals(jsonProducto.getString(TIPO_PRODUCTO_KEY))) {
					if(jsonProducto.getBoolean(ACTIVO_KEY)&&jsonProducto.getInt(ID_EVENTO_KEY)==idEvento) lista.add(TProducto.getTransfer(jsonProducto));
				}
			}
			return lista;
		}
		catch(IOException e) {
			return new ArrayList<>();
		}
	}

	@Override
	public boolean buscarProductoPorIdAsoc(int id) {

	    JSONObject JO = leerJSON();
	
	    JSONArray productos = new JSONArray();
	
	    if(JO.has(PRODUCTOS_KEY))  productos = JO.getJSONArray(PRODUCTOS_KEY);
	    int i = 0; boolean found = false;
	
	    while(i<productos.length()) {
	                   if(productos.getJSONObject(i).getInt(NOMBRE_KEY) == id) found = true;
	                   i++;
	    }
	    return found;
	}

	@Override
	public boolean eliminarProducto(int id) {

		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return false; //TODO pendiente
		}
		JSONArray JA = JO.getJSONArray(PRODUCTOS_KEY); //TODO necesario comprobar si JO tiene JA?
		int i = 0;
		boolean found = false;
		while(i < JA.length() && !found) {
			if(id == JA.getJSONObject(i).getInt(ID_KEY)) found = true;
			else i++;
		}
		
		if(i < JA.length() && JA.getJSONObject(i).getBoolean(ACTIVO_KEY)) {
			TProducto t = TProducto.getTransfer(JA.getJSONObject(i));
			t.setActivo(false);
			JA.put(i, t.getJSON());
			return saveData(JO);
		}
		else return false;
	}


	@Override
	public boolean modificarProducto(TProducto producto) {

		JSONObject JO = new JSONObject();
		try {
			JO = loadData();
		} catch (IOException e) {
			return false; //TODO pendiente
		}
		JSONArray JA = JO.getJSONArray(PRODUCTOS_KEY); //TODO necesario comprobar si JO tiene JA?
		int i = 0;
		boolean found = false;
		while(i < JA.length() && !found) {
			if(producto.getId() == JA.getJSONObject(i).getInt(ID_KEY)) found = true;
			else i++;
		}
		
		if(i < JA.length()) {
			JA.put(i, producto.getJSON());
			return saveData(JO);
		}
		else return false;
	}


}
