package JDBC;
import java.sql.*;
//importing the data
class StudentData{//StudentData class for the data accessing
	int id;
	String name;
	int marks;
	StudentData(int id){
		this.id =id;
	}
	StudentData(String name){
		this.name=name;
	}
}

class DataAccessObject{
	Connection con;
	//Connection creation and the Driver Registration 
	public void connectTheDbAndRegisterTheDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver error :"+e.getMessage());
		}
		try {
			String url="jdbc:mysql://localhost:3306/JDBC";
			String userName="admin";
			String password ="2093";
			con=DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			System.out.println("connection error :"+e.getMessage());
		}
		
	}
		//updated marks based on the person
	  public int updateMarks(StudentData sd) {
		int rowsEffected=0;
		String query ="update studentData set marks = 0  where name=?";
		try(PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, sd.name);
			rowsEffected =pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Marks are not updated try again!! Enter the correct name");
		}
		return rowsEffected;
		
	}
	  //get marks from the id passes
	  public ResultSet getMarks(StudentData sd) {
			ResultSet rs=null;
			
			String query ="select marks from studentData where id=?";
			try {
				//pass the statement
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, sd.id);
			rs=pst.executeQuery();
			}
			catch(Exception e) {
				System.out.println("ID invalid try again!!");
			}
			return rs;
		}
	  
	  //connection closes
		public void closeDbConnection() {
			if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("connection closing error "+e.getMessage());
			}
		 }
		}

}
		
public class JdbcDaoLearn {

	public static void main(String[] args)throws Exception {
		DataAccessObject dao =new DataAccessObject();
		//establish the connection
		dao.connectTheDbAndRegisterTheDriver();
		//pass the id to get the marks
		ResultSet result=dao.getMarks(new StudentData(1));
		if(!result.next()) {
			//if no student found
			System.out.println("No Student found");
		}else {
			//print the marks
			System.out.println("you got : "+result.getInt(1)+" marks");
		}
		int rowsEffected= dao.updateMarks(new StudentData("demo"));
		System.out.println(rowsEffected +" row/s effected, so the marks updated");
		//close the connection
		dao.closeDbConnection();
		
	}

}
