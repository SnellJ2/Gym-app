import java.sql.*;

public class Queries {

	private Connection conn() throws SQLException
	{
		Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/jonassnell/Desktop/SQLite/usersdb.db");
		
		
		return conn;
	}
	
	public int newTable() throws SQLException
	{
		 String sql = "CREATE TABLE IF NOT EXISTS user_info" +
                 "(ID INT PRIMARY KEY NOT NULL," +
                 " HEIGHT INT,     " + 
                 " AGE INT," + 
                 " WEIGHT INT," + 
                 " GENDER INT);"; 
		Connection conn = this.conn();
		System.out.println("Database connection established");
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		conn.close();
		int checker = get_height();
		if(checker == 0) {
			return 1;
		}
		return 0;
	}


	public void insert(int height, int weight, int gender, int age) {
		String sql = "INSERT INTO user_info(ID, height,weight,gender,age) VALUES(1,?,?,?,?);";
		try (Connection conn = this.conn();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, height);
			pstmt.setInt(2, weight);
			pstmt.setInt(3, gender);
			pstmt.setInt(4, age);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public int get_height()
	{
		int height = 0;
		String sql = "SELECT height FROM user_info";
		try (Connection conn = this.conn();
				Statement stmt = conn.createStatement()){
				ResultSet rs = stmt.executeQuery(sql);
				height = rs.getInt("height");
				} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return height;
	}
	
	public int get_weight()
	{
		int weight = 0;
		String sql = "SELECT weight FROM user_info";
		try (Connection conn = this.conn();
				Statement stmt = conn.createStatement()){
				ResultSet rs = stmt.executeQuery(sql);
				weight = rs.getInt("weight");
				} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return weight;
	}
	
	public int get_age()
	{
		int age = 0;
		String sql = "SELECT age FROM user_info";
		try (Connection conn = this.conn();
				Statement stmt = conn.createStatement()){
				ResultSet rs = stmt.executeQuery(sql);
				age = rs.getInt("age");
				} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return age;
	}
	
	public int get_gender()
	{
		int gender = 0;
		String sql = "SELECT gender FROM user_info";
		try (Connection conn = this.conn();
				Statement stmt = conn.createStatement()){
				ResultSet rs = stmt.executeQuery(sql);
				gender = rs.getInt("gender");
				} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return gender;
	}
	
	public boolean tableExists() throws SQLException
	{
		/*String sql = "SELECT EXISTS (  "+
			  "  SELECT "+
		       " name "+
		 "   FROM " +
		  "      sqlite_schema "+
		   " WHERE " + 
		       " type='table' AND "+
		       " name='Customers' "+
		   " ); ";
		int bool;
	*/
		Connection c = this.conn();
				DatabaseMetaData dbm = c.getMetaData();
				// check if "employee" table is there
				ResultSet tables = dbm.getTables(null, null, "user_info", null);
				if (tables.next()) {
					return true;
				}
				else {
					return false;
				}
    }


}



