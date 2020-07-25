package util;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.json.simple.JSONObject;

import model.Dato;

public class Utilidades {
	
	public static Dato convertirADato(JSONObject ob) {
		
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			return new Dato( (String)ob.get("ccaa_iso"), 
					         sdt.parse((String)ob.get("fecha")), 
					         (long)ob.get("num_casos")      
					       );
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getNombreComunidad(String siglas) {
		
		HashMap<String, String> comunidades = new HashMap<>();

		comunidades.put("AN", "Andalucía");
		comunidades.put("AR", "Aragón");
		comunidades.put("AS", "Asturias");
		comunidades.put("CN", "Canarias");
		comunidades.put("CB", "Cantabria");
		comunidades.put("CM", "Castilla-La Mancha");
		comunidades.put("CL", "Castilla y León");
		comunidades.put("CT", "Cataluña");
		comunidades.put("EX", "Extremadura");
		comunidades.put("GA", "Galicia");
		comunidades.put("IB", "Islas Baleares");
		comunidades.put("RI", "La Rioja");
		comunidades.put("MD", "Madrid");
		comunidades.put("MC", "Murcia");
		comunidades.put("NC", "Navarra");
		comunidades.put("PV", "País Vasco");
		comunidades.put("VC", "Valenciana");
		comunidades.put("CE", "Ceuta");
		comunidades.put("ML", "Melilla");
	
		return comunidades.get(siglas);

	}
	
	public static String getFuenteDatos(int num) {
		
		HashMap<Integer, String> fuente = new HashMap<>();

		fuente.put(1, "FUENTE DE DATOS: Lee JSON con JSONSymple");
		fuente.put(2, "FUENTE DE DATOS: Lee JSON con GSON");
		fuente.put(3, "FUENTE DE DATOS: Archivo CVS");
	
		return fuente.get(num);

	}
}
