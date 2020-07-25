package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import model.Dato;

public class LeerCSVoJSON {	

	public static List<Dato> LeerDatosConJSONSymple() {
		String rutaJSON = "datos_ccaas.json";
		List<Dato> datos = new ArrayList<>();
		try {
	        JSONParser parser = new JSONParser();
	        JSONArray array;
	        array = (JSONArray)parser.parse(new FileReader(rutaJSON));
	        
	        array.forEach(item ->{
	        	JSONObject obj = (JSONObject) item;
	       	    datos.add(Utilidades.convertirADato(obj));
	        });
	        
			return datos;
						
	    } catch (IOException | ParseException  e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static List<Dato> LeerDatosConGSon() {
		String rutaJSON = "datos_ccaas.json";
		try {	
			Gson gson = new Gson();
			
			Dato[] datos =gson.fromJson(new FileReader(rutaJSON), Dato[].class);
			return Arrays.asList(datos);
		} catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static List<Dato> LeerDatosDeCSV(){
		String rutaCVS = "datos_5_7_2020.csv";
		SimpleDateFormat formatoDate = new SimpleDateFormat("yyyy-MM-dd");
		List<Dato> datos = new ArrayList<>();
		try(BufferedReader bf = new BufferedReader(new FileReader(rutaCVS))){
			String dato;
			while((dato=bf.readLine())!=null) {
				String[] campos = dato.split(",");
				datos.add(new Dato(campos[0],  formatoDate.parse(campos[1]), Long.parseLong(campos[2])));
			}
			return datos;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
/*	
	public static Stream<Dato> LeerDatosConJSONSymple() {
		String rutaJSON = "datos_ccaas.json";
		try {
	        JSONParser parser = new JSONParser();
	        JSONArray array;
	        array = (JSONArray)parser.parse(new FileReader(rutaJSON));
			return ((Stream<JSONObject>)array.stream())
					.map(ob -> Utilidades.convertirADato(ob));	
	    } catch (IOException | ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}	
*/
/*	
	public static Stream<Dato> LeerDatosConGSon() {
		String rutaJSON = "datos_ccaas.json";
		try {	
			Gson gson = new Gson();
			
			//Pais pais = gson.fromJson(json, Pais.class);
			Dato[]datos =gson.fromJson(new FileReader(rutaJSON), Dato[].class);
			return Arrays.stream(datos);
		} catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
*/	
	
}
