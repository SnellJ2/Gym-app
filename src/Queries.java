import java.sql.*;

public class Queries {

	private Connection conn() throws SQLException
	{
		Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/jonassnell/Desktop/SQLite/usersdb.db");
		
		
		return conn;
	}
	
	public void newTable() throws SQLException
	{
		String sql ="CREATE TABLE IF NOT EXISTS warehouses (\n"
				                 + "	id integer PRIMARY KEY,\n"
				                 + "	name text NOT NULL, \n"
				                 + "	capacity real\n"
				                 + ");";
		Connection conn = this.conn();
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		if(conn != null)
		{
			conn.close();
		}
	}


	public void printAll()
	{
		String sql = "SELECT id, name, capacity FROM warehouses";
		System.out.println("test");
		try (Connection conn = this.conn();
			Statement stmt  = conn.createStatement();
			ResultSet rs    = stmt.executeQuery(sql)){
        
        // loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("id") +  "\t" + 
                rs.getString("name") + "\t" +
                rs.getDouble("capacity"));
			}
		} catch (SQLException e) {
        System.out.println(e.getMessage());
		}
	}
	public void insert(String name, double capacity) {
		String sql = "INSERT INTO warehouses(name,capacity), VALUES(?,?)";
		try (Connection conn = this.conn();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setDouble(2, capacity);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}

