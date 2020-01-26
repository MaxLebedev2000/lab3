package lab3.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCDatabase{

    private Connection connection;


    protected JDBCDatabase() {
        connect();
    }

    protected Connection connection(){
        return connection;
    }

    protected abstract String url();
    protected abstract String name();
    protected abstract String password();
    protected abstract void loadDriver() throws ClassNotFoundException;

    protected void connect(){
        try {
            loadDriver();
            this.connection = DriverManager.getConnection(url(), name(), password());
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }


}
