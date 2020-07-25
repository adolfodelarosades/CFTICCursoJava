package model;

import java.util.Date;

import util.Utilidades;

public class Dato {

	private String ccaa_iso;
	private Date fecha;
	private long num_casos;
	//Campos Auxiliares
	private String nombre_ccaa;
	private String nombre_ccaa_total;
	private double media;
	
	public Dato(String ccaa_iso, Date fecha, long num_casos) {
		super();
		this.ccaa_iso = ccaa_iso;
		this.fecha = fecha;
		this.num_casos = num_casos;
	}

	public Dato(Date fecha, double media) {
		super();
		this.fecha = fecha;
		this.media = media;
	}
	
	public Dato(String nombre_ccaa_total, long num_casos) {
		super();
		this.nombre_ccaa_total = nombre_ccaa_total;
		this.num_casos = num_casos;
	}

	public String getCcaa_iso() {
		return ccaa_iso;
	}

	public void setCcaa_iso(String ccaa_iso) {
		this.ccaa_iso = ccaa_iso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getNum_casos() {
		return num_casos;
	}

	public void setNum_casos(long num_casos) {
		this.num_casos = num_casos;
	}

	public String getNombre_ccaa() {
		return Utilidades.getNombreComunidad(ccaa_iso);
	}

	public String getNombre_ccaa_total() {
		return nombre_ccaa_total;
	}

	public void setNombre_ccaa_total(String nombre_ccaa_total) {
		this.nombre_ccaa_total = nombre_ccaa_total;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}	
}
