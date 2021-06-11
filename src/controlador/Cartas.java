package controlador;

import java.sql.*;

import javax.swing.JOptionPane;

import bdd.Conexion;

public class Cartas {

	private String nombre;
	private String coste;
	private String color;
	private int fuerza;
	private int resistencia;
	private String tipodecarta;
	private String subtipo;
	private String rareza;
	private String extension;
	private String textodelacarta;

	public Cartas(String nombre, String coste, String color, int fuerza, int resistencia, String tipodecarta, String subtipo, String rareza, String extension, String textodelacarta) {
		setNombre(nombre);
		setCoste(coste);
		setColor(color);
		setFuerza(fuerza);
		setResistencia(resistencia);
		setTipodecarta(tipodecarta);
		setSubtipo(subtipo);
		setRareza(rareza);
		setExtension(extension);
		setTextodelacarta(textodelacarta);
	}

	public String getNombre() {
		return nombre;
	}

	public String getCoste() {
		return coste;
	}
	
	public String getColor() {
		return color;
	}

	public int getFuerza() {
		return fuerza;
	}

	public int getResistencia() {
		return resistencia;
	}

	public String getTipodecarta() {
		return tipodecarta;
	}
	
	public String getSubtipo() {
		return subtipo;
	}

	public String getExtension() {
		return extension;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCoste(String coste) {
		this.coste = coste;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}


	public void setTipodecarta(String tipodecarta) {
		this.tipodecarta = tipodecarta;
	}

	public String getTextodelacarta() {
		return textodelacarta;
	}
	
	public String getRareza() {
		return rareza;
	}
	
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void setRareza(String rareza) {
		this.rareza = rareza;
	}

	public void setTextodelacarta(String textodelacarta) {
		this.textodelacarta = textodelacarta;
	}

	public boolean anadirCartas() {
		boolean resultado = comprobarNombre();
		String fuerzayresistencia = convertirString(getFuerza())+"/"+convertirString(getResistencia());
		if (getTipodecarta().equals("Encantamiento") || getTipodecarta().equals("Artefacto") || getTipodecarta().equals("Tierra")) {
			fuerzayresistencia = "";
		}
		if (getTipodecarta().equals("Planeswalker")) {
			fuerzayresistencia = ""+getResistencia();
		}
		
		if (!resultado) {
			Connection conexion = Conexion.open();
			String consulta = "INSERT INTO cartas (nombre, coste, fuerzairesistencia, textodelacarta, idColor, idTipoDeCarta, idRareza, idSubtipo, idExpansion) VALUES (?,?,?,?,(SELECT idColor FROM color where nombre = '"+getColor()+"'),(SELECT idTipoDeCarta FROM tipodecarta where nombre = '"+getTipodecarta()+"'),(SELECT idRareza FROM rareza where nombre = '"+getRareza()+"'),(SELECT idSubtipo FROM subtipo where nombre = '"+getSubtipo()+"'),(SELECT idExpansion FROM expansion where nombre = '"+getExtension()+"'));";
			try {
				PreparedStatement pst = conexion.prepareStatement(consulta);
				pst.setString(1, getNombre());
				pst.setString(2, getCoste());
				pst.setString(3, fuerzayresistencia);
				pst.setString(4, getTextodelacarta());
				
				if (pst.executeUpdate() == 1) {
					JOptionPane.showMessageDialog(null, "Tu carta ha sido creado correctamente");
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
	
	public boolean comprobarNombre() {
		Connection conexion = Conexion.open();
		String consulta = "SELECT * FROM cartas WHERE nombre = '"+getNombre()+"';";
		boolean result = true;
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet respuesta = pst.executeQuery();
			if (!respuesta.next()) {
				result = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public String convertirString(int numero) {
		String resultado = "";
		if (numero < 0) {
			resultado = "*";
		}else {
			resultado += numero;
		}
		return resultado;
	}
	
	public boolean editarCarta(int color, int tipodecarta, int rareza, int subtipo, int expansion, int idcarta) {
		boolean comprobar = true;
		String fuerzayresistencia = new String();
		if (getTipodecarta().equals("Encantamiento") || getTipodecarta().equals("Artefacto") || getTipodecarta().equals("Tierra")) {
			fuerzayresistencia = "";
		}else if (getTipodecarta().equals("Planeswalker")) {
			fuerzayresistencia = ""+getResistencia();
		} else {
			fuerzayresistencia = convertirString(getFuerza())+"/"+convertirString(getResistencia());
		}
		
			Connection conexion = Conexion.open();
			String consulta = "UPDATE cartas SET nombre = '"+getNombre()+"', coste = '"+getCoste()+"', fuerzairesistencia = '"+fuerzayresistencia+"', textodelacarta = '"+getTextodelacarta()+"', idColor = "+color+", idTipoDeCarta = "+tipodecarta+", idRareza = "+rareza+", idSubtipo = "+subtipo+", idExpansion = "+expansion+" where idCarta = "+idcarta;
			System.out.println(consulta);
			try {
				PreparedStatement pst = conexion.prepareStatement(consulta);
				
				if (pst.executeUpdate() == 1) {
					JOptionPane.showMessageDialog(null, "Tu carta ha sido editada correctamente");
				}else {
					System.out.println("Ha habido un error");
					comprobar = false;
				}
				
			} catch (Exception e) {
				System.out.println(e);
				comprobar = false;
			}
			
		return comprobar;
	}
}
