package ud3.practica2;

import java.io.Serializable;

public class PaqueteTCP implements Serializable {

	private int portoOrixe;
	private int portoDestino;
	private String mensaxe;

	public PaqueteTCP(int portoOrixe, int portoDestino, String mensaxe) {
		this.portoOrixe = portoOrixe;
		this.portoDestino = portoDestino;
		this.mensaxe = mensaxe;
	}

	public int getPortoOrixe() {
		return portoOrixe;
	}

	public int getPortoDestino() {
		return portoDestino;
	}

	public String getMensaxe() {
		return mensaxe;
	}
}
