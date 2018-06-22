package fr.pizzeria.Utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;

public class JdbcConnector {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnector.class);

	static{
		try {
			Class.forName(ConfigReader.getPropertyFromConfig("db_driver"));
		} catch (ClassNotFoundException e) {
			LOGGER.error("Wrong Jdbc Driver type");
			e.printStackTrace();
		}
	}

	private String dbUrl, dbLogin, dbPassword;
	Connection myConnection;
	//Statement statement;


	public JdbcConnector(){

		myConnection = null;
		dbUrl = ConfigReader.getPropertyFromConfig("db_URL");
		dbLogin = ConfigReader.getPropertyFromConfig("db_login");
		dbPassword = ConfigReader.getPropertyFromConfig("db_password");

	}

	public void connectToDb() throws SQLException{

		if(myConnection != null && myConnection.isValid(0)){
			LOGGER.info("Connection still up and running.");
		}else{
			myConnection = DriverManager.getConnection(dbUrl,dbLogin,dbPassword);
			myConnection.setAutoCommit(false);
		}

		//statement = myConnection.createStatement();
	}

	public void insertIntoDb(String tableName,ArrayList<String> fields, ArrayList<Object> values) throws StockageException{

		Statement statement = null;
		PreparedStatement insertStatement= null;

		try {
			connectToDb();

			String preperadRequest = "INSERT INTO "+tableName+"(";
			for(String s : fields){
				preperadRequest += s+",";
			}

			//delete the last  comma that is not needed
			preperadRequest = preperadRequest.substring(0, preperadRequest.length() -1);
			preperadRequest += ") VALUES (";

			for(int i =0; i < values.size(); ++i){
				preperadRequest += "?,";
			}

			//delete the last  comma that is not needed
			preperadRequest = preperadRequest.substring(0, preperadRequest.length() -1);
			preperadRequest += ")";

			LOGGER.info(preperadRequest);

			//prepared statement String is ready to be used
			insertStatement = myConnection.prepareStatement(preperadRequest);

			simpeTypes objectType;
			int i = 1;

			for(Object o : values){

				objectType = simpeTypes.valueOf(o.getClass().getSimpleName());

				switch (objectType) {
				case Integer:
					insertStatement.setInt(i, (int)o);
					break;

				case String:
					insertStatement.setString(i, (String)o);
					break;

				case Double:
					insertStatement.setDouble(i, (double)o);
					break;

				default:
					throw new StockageException("Cannot parse type of value");
				}

				++i;
			}

			insertStatement.executeQuery();
			myConnection.commit();


		} catch (SQLException e) {
			LOGGER.error("Error while inserting data into table");
			e.printStackTrace();
		}finally{
			try {
				if (statement != null){
					statement.close();
				}
				if(insertStatement != null){
					insertStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error while closing db objects");
				e.printStackTrace();
				try {
					myConnection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public ArrayList<ArrayList<Object>> selectFromDb(String sqlRequest) throws StockageException{

		Statement statement = null;
		ResultSet result = null;
		ArrayList<ArrayList<Object>> returnArray = new ArrayList<>();
		int  i =0;

		try {
			//refresh to connection with the DB
			connectToDb();
			statement = myConnection.createStatement();
			result = statement.executeQuery(sqlRequest);

			ResultSetMetaData rsmd = result.getMetaData();

			//build the result array 
			for(i = 1; i <= rsmd.getColumnCount();++i){
				returnArray.add(new ArrayList<Object>());
			}

			//for each row of the select result
			while(result.next()){
				//for each column of the result set, insert the value in an array
				for(i = 1; i <= rsmd.getColumnCount();++i){

					Object obj = result.getObject(rsmd.getColumnLabel(i));
					returnArray.get(i-1).add(obj);

					//Before saving the value, cast it in int, double or String
					//Class classTemp = Class.forName(rsmd.getColumnClassName(i));
					//Object obj = classTemp.getConstructor().newInstance();
					//obj is an empty container of the current type of the value at the column i
					//get the enum value to use switch/case
					//objectType = simpeTypes.valueOf(obj.getClass().getSimpleName());

				}
			}


		} catch (SQLException | IllegalArgumentException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (statement != null){
					statement.close();
				}
				if(result != null){
					result.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error while closing db objects");
				e.printStackTrace();
				try {
					myConnection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}


		return returnArray;
	}

	public void deleteFromDb(String tablename, String conditions) throws DeletePizzaException{

		Statement statement = null;

		try {
			connectToDb();
			
			String deleteStatement = "DELETE FROM "+tablename+" WHERE "+conditions;
			
			statement = myConnection.createStatement();
			int result = statement.executeUpdate(deleteStatement);
			
			myConnection.commit();
			
			if(result == 0 ){
				throw new DeletePizzaException("No pizza hase been deleted.");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (statement != null){
					statement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error while closing db objects");
				e.printStackTrace();
				try {
					myConnection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void alterFromDb(String tableName,ArrayList<String> fields, ArrayList<Object> values, String conditions ) throws StockageException{
		
		Statement statement = null;
		PreparedStatement insertStatement= null;

		try {
			connectToDb();

			String preperadRequest = "UPDATE "+tableName+" SET ";
			for(String s : fields){
				preperadRequest += s+"= ?,";
			}

			//delete the last  comma that is not needed
			preperadRequest = preperadRequest.substring(0, preperadRequest.length() -1);
			preperadRequest += " WHERE "+conditions;

			LOGGER.info(preperadRequest);

			//prepared statement String is ready to be used
			insertStatement = myConnection.prepareStatement(preperadRequest);

			simpeTypes objectType;
			int i = 1;

			for(Object o : values){

				objectType = simpeTypes.valueOf(o.getClass().getSimpleName());

				switch (objectType) {
				case Integer:
					insertStatement.setInt(i, (int)o);
					break;

				case String:
					insertStatement.setString(i, (String)o);
					break;

				case Double:
					insertStatement.setDouble(i, (double)o);
					break;

				default:
					throw new UpdatePizzaException("Cannot parse type of value");
				}

				++i;
			}

			int result = insertStatement.executeUpdate();
			myConnection.commit();

			if(result == 0){
				throw new UpdatePizzaException("No lines have been updated.");
			}
			

		} catch (SQLException e) {
			LOGGER.error("Error while inserting data into table");
			e.printStackTrace();
		}finally{
			try {
				if (statement != null){
					statement.close();
				}
				if(insertStatement != null){
					insertStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error while closing db objects");
				e.printStackTrace();
				try {
					myConnection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	private enum simpeTypes{
		Integer,Double,String,BigDecimal;
	}


}
