package principal;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Dato;
import service.CasosService;
import util.Utilidades;

public class ConsultarCasos {

	public static void main(String[] args) {
		
		/**
		 * INFORMACIÓN PARA PROBAR CADA PUNTO
		 */
		
		//0 Determinar como lee el JSON o el CVS
		// 1. Lee JSON con JSONSymple
		// 2. Lee JSON con GSON
		// 3. Lee Archivo CVS
		int formaLeerDatos = 1;
		
		CasosService cs = new CasosService(formaLeerDatos);
		
		//1. fechas para un período de tiempo
		String fechaInicio = "21/06/2020";
		String fechaFin = "21/06/2020";
		
		//2. Fecha de un día determinado
		String fechaDia = "21/06/2020";
		
		//5. Total de positivos de una comunidad dada
		String comunidad = "Madrid";
		/********************************************************/
		
		DecimalFormat df = new DecimalFormat("###,###,##0.00");
		DecimalFormat dfsd = new DecimalFormat("###,###,##0");
		SimpleDateFormat formatoDateLeer = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoDatePresentar = new SimpleDateFormat("dd/MM/yyyy");
		
		if(cs.getDatos()!= null && cs.getDatos().size()>0) {
		try {
			
			System.out.println("\n***** " +  Utilidades.getFuenteDatos(formaLeerDatos) +  " *****");
			
			//1. Lista de casos producidos entre dos fechas dadas. Cada caso se caracteriza 
			//   por un nombre de comunidad, fecha y positivos (num_casos)
			System.out.println("\n***** 1. Lista de casos producidos entre dos fechas dadas. *****\n");
			System.out.format("Fecha Inicio: %-12s - Fecha Fin: %12s \r\n\n", formatoDatePresentar.format(formatoDateLeer.parse(fechaInicio)), formatoDatePresentar.format(formatoDateLeer.parse(fechaFin)));
			System.out.format("%-20s | %-10s | %-13s \r\n", "COMUNIDAD", "FECHA", "NO. CONTAGIOS");
			System.out.format("%-20s | %10s | %13s \r\n", "-".repeat(20), "-".repeat(10), "-".repeat(13));
			List<Dato> ld = cs.listaCasos(formatoDateLeer.parse(fechaInicio), formatoDateLeer.parse(fechaFin));
			ld.forEach(d-> System.out.format("%-20s | %s | %13s \r\n", d.getNombre_ccaa(), formatoDatePresentar.format(d.getFecha()), dfsd.format(d.getNum_casos())));
			
			//2. Total de positivos producidos en un determinado día
			System.out.println("\n***** 2. Total de positivos producidos en un determinado día. *****\n");
			System.out.println("Total del día " + formatoDatePresentar.format(formatoDateLeer.parse(fechaDia)) + ": " + cs.totalPositivosPorDia(formatoDateLeer.parse(fechaDia)));
			
			//3. Fecha en la que se produjo el pico de contagios
			System.out.println("\n***** 3. Fecha en la que se produjo el pico de contagios. *****\n");
			System.out.println("Fecha con más picos: " + formatoDatePresentar.format(cs.fechaPicos())  + " con " + dfsd.format(cs.totalPositivosPorDia(cs.fechaPicos())) + " casos" );
			
			//4. Media de positivos diarios
			System.out.println("\n***** 4. Media de positivos diarios. *****\n");
			System.out.format("%-12s | %8s \r\n", "Fecha", "Media");
			System.out.format("%-12s | %8s \r\n", "-".repeat(12), "-".repeat(8));
			List<Dato> mediaPositivosDiarios = cs.mediaPositivosDiarios();
			mediaPositivosDiarios.forEach(d-> System.out.format("%-12s | %8s \r\n", formatoDatePresentar.format(d.getFecha()), df.format(d.getMedia())));
			
			//5. Total de positivos de una comunidad dada
			System.out.println("\n***** 5. Total de positivos de una comunidad dada. *****\n");
			System.out.println("Total en la comunidad de " + comunidad + ": " + dfsd.format(cs.totalPositivosPorDia(comunidad)));
		
			//6. Tabla con listas de casos agrupados por comunidad
			System.out.println("\n***** 6. Tabla con listas de casos agrupados por comunidad. *****\n");
			System.out.format("%-20s | %13s \r\n", "COMUNIDAD", "No. CONTAGIOS");
			System.out.format("%-20s | %13s \r\n", "-".repeat(20), "-".repeat(13));
			List<Dato> datosPorComunidad = cs.datosPorComunidad();
			datosPorComunidad.forEach(d-> System.out.format("%-20s | %13s \r\n", d.getNombre_ccaa_total(), dfsd.format(d.getNum_casos())));

		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		}else {
			System.out.println("***** LO SIENTO, NO TENGO NINGÚN DATO *****");
		}
	}
}
