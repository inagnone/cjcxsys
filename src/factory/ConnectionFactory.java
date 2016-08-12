package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
    private static interface Singleton {
        final ConnectionFactory INSTANCE = new ConnectionFactory();
    }
 
    private final DataSource dataSource;
 
    private ConnectionFactory() {
 
        this.dataSource = new ComboPooledDataSource();
    }
 
    public static Connection getDatabaseConnection() throws SQLException {
        return new ComboPooledDataSource().getConnection();
    }
}
