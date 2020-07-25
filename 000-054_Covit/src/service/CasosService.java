package service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Dato;
import util.LeerCSVoJSON;

public class CasosService {
	
	int formaLeerDatos;
    List<Dato> datos;
    
	public CasosService(int formaLeerDatos) {
		super();
		this.formaLeerDatos = formaLeerDatos;
		
		if( formaLeerDatos == 1) {
		   datos = LeerCSVoJSON.LeerDatosConJSONSymple();
		}else if(formaLeerDatos == 2) {
		   datos = LeerCSVoJSON.LeerDatosConGSon();
		}else if(formaLeerDatos == 3) {
		   datos = LeerCSVoJSON.LeerDatosDeCSV();
		}
	}

	private Stream<Dato> obtenerDatos() {
		return datos.stream();
	}
	
	private Map<Date,List<Dato>> agrupaPorFecha(){
		return obtenerDatos()
			 .collect(Collectors.groupingBy(p -> p.getFecha()));	
	}
	
	private Map<String,List<Dato>> agrupaPorComunidad(){
		return obtenerDatos()
			 .collect(Collectors.groupingBy(p -> p.getNombre_ccaa()));	
	}
	
	public List<Dato> getDatos() {
		return datos;
	}
	
	//1. Lista de casos producidos entre dos fechas dadas. Cada caso se caracteriza 
	//   por un nombre de comunidad, fecha y positivos (num_casos)
	public List<Dato> listaCasos(Date fecha1, Date fecha2) {
		return obtenerDatos()
					.filter(d-> d.getFecha().getTime()>=fecha1.getTime() && d.getFecha().getTime()<=fecha2.getTime() )
					.collect(Collectors.toList());
	}
	
	//2. Total de positivos producidos en un determinado día
	public long totalPositivosPorDia(Date fecha) {
		return obtenerDatos()
					.filter(d-> d.getFecha().equals(fecha) )
					.mapToLong(d->d.getNum_casos())
					.sum();
	}
	
	//3. Fecha en la que se produjo el pico de contagios
	public Date fechaPicos() {
		Map<Date,List<Dato>> agrupacion = agrupaPorFecha();
		
		return agrupacion.keySet().stream() //Stream con las claves
		      .max((k1, k2) -> Long.compare(agrupacion.get(k1).stream().mapToLong(p->p.getNum_casos()).sum()
			                              , agrupacion.get(k2).stream().mapToLong(p->p.getNum_casos()).sum() ))
	          .get();
	}
	
	//4. Media de positivos diarios
	public List<Dato> mediaPositivosDiarios(){
		Map<Date,List<Dato>> agrupacion = agrupaPorFecha();
			
		return agrupacion.entrySet().stream()
		   .map(e-> new Dato(e.getKey(), e.getValue().stream().mapToDouble(p->p.getNum_casos()).average().getAsDouble()))
		   .sorted((d1, d2)-> d1.getFecha().compareTo(d2.getFecha()))
		   .collect(Collectors.toList());
	}
	
	//5. Total de positivos de una comunidad dada
	public long totalPositivosPorDia(String comunidad) {
		return obtenerDatos()
					.filter(d-> d.getNombre_ccaa().equals(comunidad) )
					.mapToLong(d->d.getNum_casos())
					.sum();
	}
	
	//6. Tabla con listas de casos agrupados por comunidad
	public List<Dato> datosPorComunidad(){
		Map<String,List<Dato>> agrupacion = agrupaPorComunidad();
		return 
		  agrupacion.entrySet().stream()
		   .map(e-> new Dato(e.getKey(), e.getValue().stream().mapToLong(p->p.getNum_casos()).sum()))   
		   .sorted((d1, d2)-> d1.getNombre_ccaa_total().compareTo(d2.getNombre_ccaa_total()))
		   .collect(Collectors.toList());
	}
	
}
