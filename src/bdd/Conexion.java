package bdd;

import java.sql.*;
import javax.swing.JOptionPane;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {
	public static String bd;
	public static String login;
	public static String pass;
	public static String host;
	public static String url=null;
	
	public static Connection con=null;

	
	public static Connection open() {
		
		try {
			if ((con==null) || (con.isClosed())) {
				if (url==null) cargarPropiedades();
				url="jdbc:mysql://"+host+"/"+bd+"?useSSL=false";
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url, login, pass);
			}
		} catch (Exception e) {
			System.out.println("No se puede conectar!!!");
			System.out.println(e);
		}
		return con;
	}
	
	private static void cargarPropiedades() {
		Properties prop=new Properties();
		InputStream input=null;
		try {
			input=new FileInputStream("config.properties");
			prop.load(input);
			bd=		prop.getProperty("bbdd");
			login=	prop.getProperty("usuario");
			host=	prop.getProperty("host");
			pass=	prop.getProperty("password");
			
		} catch (Exception e) {
			System.out.println("Falta archivo de configuración!!");
		}
		
	}

	public static void close() {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			
		}
	}
	
	
}