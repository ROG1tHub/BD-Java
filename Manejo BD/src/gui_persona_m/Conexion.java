package gui_persona_m;

import java.sql.*;

public class Conexion {
	private String bd;
	private String user;
	private String password;
	private String url;
	private String driverClassName;
	private Connection conn = null;
	private Statement stmt;

	public Conexion(String user, String password, String url, String driverClassName) {
		this.user = user;
		this.password = password;
		this.url = url;
		this.driverClassName = driverClassName;
	}

	public Conexion() {
		this.bd = "agenda"; // nombre de la base de datos
		this.user = "bingo"; // usuario de la base de datos
		this.password = "hola"; // password de la base de datos
		this.url = "jdbc:mysql://localhost:3307/" + bd + "?characterEncoding=utf8"; // servidor de la base de datos
		this.driverClassName = "com.mysql.jdbc.Driver"; // driver
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setUser(String user) throws SQLException {
		this.user = user;
	}

	@SuppressWarnings("deprecation")

	public void conectar() throws SQLException {
		try {
			Class.forName(this.driverClassName).newInstance();
			this.conn = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (Exception err) {
			System.out.println("Fallo al cargar el controlador JDBC/ODBC. Error " + err.getMessage());
		}
	}

	public ResultSet obtenerDatos(String sql) throws SQLException {
		this.stmt = conn.createStatement();
		return this.stmt.executeQuery(sql);
	}

	public void actualizar(String sql) throws SQLException {
		this.stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	}

	public void escribir(String sql) throws SQLException {
		this.stmt = conn.createStatement();
		stmt.execute(sql);
	}

	public ResultSet ExeGet(String Q) throws SQLException {
		Statement st = this.conn.createStatement();
		return (ResultSet) st.executeQuery(Q);
	}

	public int Exe(String Q) throws SQLException {
		Statement st = this.conn.createStatement();
		return st.executeUpdate(Q);
	}

	public void Off() throws SQLException {
		this.conn.close();
	}
}// class Conexion