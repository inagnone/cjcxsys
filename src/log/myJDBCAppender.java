package log;

import java.sql.Connection;
import java.sql.SQLException;

import util.TransactionManager;

public class myJDBCAppender extends  org.apache.log4j.jdbc.JDBCAppender{
	
	public myJDBCAppender(){
		super();
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return TransactionManager.getSource().getConnection();
	}
	
	@Override
	protected void closeConnection(Connection con) {
		// TODO Auto-generated method stub
		  try {
	            if (connection != null && !connection.isClosed())
	                connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
}
