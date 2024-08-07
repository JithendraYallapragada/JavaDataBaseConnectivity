package JDBC;


//Before Every thing setup the database where your data should manipulate using the JDBC


//mysql> create database JDBC
//-> ;
//Query OK, 1 row affected (0.74 sec)
//
//mysql> show databases;
//+--------------------+
//| Database           |
//+--------------------+
//| JDBC               |
//| information_schema |
//| mysql              |
//| performance_schema |
//| sys                |
//+--------------------+
//5 rows in set (0.00 sec)
//
//mysql> use JDBC;
//Database changed
//mysql> show tables;
//Empty set (0.01 sec)
//
//mysql> create table studentData(id int(5),name varchar(20),marks int(5));
//Query OK, 0 rows affected, 2 warnings (1.18 sec)
//
//mysql> show tables;
//+----------------+
//| Tables_in_JDBC |
//+----------------+
//| studentData    |
//+----------------+
//1 row in set (0.00 sec)
//
//mysql> select *from studentData;
//Empty set (0.02 sec)
//
//mysql> insert into studentData values(0,Demo,00);
//ERROR 1054 (42S22): Unknown column 'Demo' in 'field list'
//mysql> insert into studentData values(0,'Demo',00);
//Query OK, 1 row affected (0.14 sec)
//
//mysql> select * from studentData;
//+------+------+-------+
//| id   | name | marks |
//+------+------+-------+
//|    0 | Demo |     0 |
//+------+------+-------+
//1 row in set (0.00 sec)
//
//mysql> exit;
//Bye

//Step 1:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
 
// Import the java.sql package it is in the **jdk not in the jre libraries

public class JavaDBLearn {

	public static void main(String[] args) throws Exception {
		//Step 2:->
		//2.a:
		//load the driver the driver is from the web download it and add in the external JRE's
		//where the jre is based on the db you use eg : myssql-connector/postgres-connector
		//step 2.b:
		//Register the Driver to register call the driver class where the driver class contains static method
		//hence the static method registers the driver..
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Step 3:
		//establish a connection:://using the Connection interface you can implement the execution and statements
		//the Connection interface object is returned from the DriveManager class contains method createConnection();
		String url="jdbc:mysql://localhost:3306/JDBC"; //first select Database 2.provide jdbc:mysql at first 3. give ip and port **if any
		String userName="admin";
		String password="2093";
		String query="select * from studentData";
		Connection con= DriverManager.getConnection(url,userName,password);
		//step 4:
		//pass the statements the statements are used to pass the query and execute and store the result data 
		//Brief explanation:::
			//   Connection class contains methods return Statement class
			//   so Statement class contains methods helps to execute the query and they returns ResultSet class/ others
			//   to Store result data..
		Statement st= con.createStatement();
		//Step 5&6: in brief explanation
		ResultSet rs=st.executeQuery(query);//fetching data
		//  Now the result set or the pointer in the result holds at the top so to reach the row use .next() method
		while(rs.next())//acts as both boolean and skips the pointer to column to data row
			{
			
			System.out.println("id is : " +rs.getInt(1)+" name is : " + rs.getString("name")+ " marks are : "+rs.getString(3));//you can write either column name or number
			//output :: id is : 0 name is : Demo marks are : 0
			}
			con.close();//close the connection...
	}
	

}
