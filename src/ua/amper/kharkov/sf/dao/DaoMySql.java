package ua.amper.kharkov.sf.dao;

import java.sql.*;

import com.mysql.jdbc.Connection;
import org.apache.log4j.Logger;

import static ua.amper.kharkov.sf.SFConstants.SF_SQL_EXEC_SQL;

public class DaoMySql extends DaoAbstract {
    private static final Logger LOGGER = Logger.getLogger(DaoMySql.class);
    private com.mysql.jdbc.Connection connection = null;

    private final String DATABASE_CREATE = "CREATE DATABASE IF NOT EXISTS %s " +
            "CHARACTER SET utf8 " +
            "COLLATE utf8_general_ci ";
    private final String DROP_DATABASE = "DROP DATABASE %s";

    public ResultSet getResultSet() {
        return resultSet;
    }

    private void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    private ResultSet resultSet;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public DaoMySql(String driver) {
        super("com.mysql.jdbc.Driver");
    }
    public DaoMySql() {
        super("com.mysql.jdbc.Driver");
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void setURL(String host, String database, int port) {
        if (database.length() > 0)
            this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        else
            this.url = "jdbc:mysql://" + host + ":" + port;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     *     Функция, возвращающая количество строк в ResultSet.
     */
    private int resultSetCount(ResultSet resultSet) {
        try{
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            resultSet.beforeFirst();
            return i;
        } catch (Exception e){
            LOGGER.error(SF_SQL_EXEC_SQL );
        }
        return 0;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Функция выполнения SQL-запроса
     *
     * @param sql текст запроса
     * @return результат выполнения запроса
     */
    public boolean execSQLExecution(final String sql) {
        boolean result = false;
        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();
                setResultSet(statement.executeQuery(sql));
//                resultSet =statement.executeQuery(sql);
                int resultSetCount = resultSetCount(resultSet);

                if (resultSetCount != 0) {
                    result = true;
                }
//                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.error(SF_SQL_EXEC_SQL + "SQLException : code = " + String.valueOf(e.getErrorCode()) +
                    " - " + e.getMessage() + "\tSQL : " + sql);

        }

        return result;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public Connection getConnection() {
        return connection;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void Connect(String login, String password) {
        super.Connect(login, password);
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
//            System.out.println(e);
            connection = null;
        }
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public boolean createSchema(final String schema) {
        return execSQL(String.format(DATABASE_CREATE, schema));
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public boolean dropSchema(final String schema) {
        return execSQL(String.format(DROP_DATABASE, schema));
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
