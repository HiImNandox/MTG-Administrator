package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import bdd.Conexion;

public class Bibliotecas {

	private String nombre;
	private String dueno;
	private String tipodebiblioteca;
	
	public Bibliotecas(String nombre, String dueno, String tipodebiblioteca) {
		setNombre(nombre);
		setDueno(dueno);
		setTipodebiblioteca(tipodebiblioteca);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDueno() {
		return dueno;
	}

	public String getTipodebiblioteca() {
		return tipodebiblioteca;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDueno(String dueno) {
		this.dueno = dueno;
	}

	public void setTipodebiblioteca(String tipodebiblioteca) {
		this.tipodebiblioteca = tipodebiblioteca;
	}
	
	public boolean anadirBiblioteca() {
		boolean resultado = true;

		if (!resultado) {
			Connection conexion = Conexion.open();
			String consulta = "INSERT INTO biblioteca (nombre, idUser, idTipoDeBiblioteca) VALUES (?,(SELECT idUser FROM usuarios where usuario = '"+getDueno()+"'),(SELECT idTipoDeBiblioteca FROM tipodebiblioteca where nombre = '"+getTipodebiblioteca()+"'));";
			try {
				PreparedStatement pst = conexion.prepareStatement(consulta);
				pst.setString(1, getNombre());
				
				if (pst.executeUpdate() == 1) {
					JOptionPane.showMessageDialog(null, "Tu biblioteca ha sido creado correctamente");
				}else {
					System.out.println("Ha habido un error");
					resultado = false;
				}
				
			} catch (Exception e) {
				System.out.println(e);
				resultado = false;
			}
		}else {
			
		}
		return resultado;
	}
}
