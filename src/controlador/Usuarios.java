package controlador;

import java.sql.*;

import javax.swing.JOptionPane;

import bdd.Conexion;

public class Usuarios {

	private String Usuario;
	private String Contrasena;
	private String correo;
	public String consulta;
	
	public Usuarios(String usuario, String contrasena, String correo) {
		setUsuario(usuario);
		setContrasena(contrasena);
		setCorreo(correo);
	}

	public String getUsuario() {
		return Usuario;
	}

	public String getContrasena() {
		return Contrasena;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public void registrarUsuarios() {
		consulta = "INSERT INTO usuarios (usuario,contrasena,correo) VALUES (?,?,?)";
		Connection conexion = Conexion.open();
		try {
			 PreparedStatement pst = conexion.prepareStatement(consulta);
			 pst.setString(1, getUsuario());
			 pst.setString(2, getContrasena());
			 pst.setString(3, getCorreo());
			 if (pst.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Tu usuario ha sido creado correctamente");
			}else {
				System.out.println("Ha habido un error");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int iniciarSesion() {
		int resultado = 0;
		consulta = "SELECT * from usuarios WHERE usuario = '"+getUsuario()+"' and contrasena = '"+getContrasena()+"';";
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet respuesta = pst.executeQuery();
			if (respuesta.next()) {
				JOptionPane.showMessageDialog(null, "Usuario correcto");
				resultado = respuesta.getInt("idUser");
			}else {
				System.out.println("no existe");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
		return resultado;
	}
}
