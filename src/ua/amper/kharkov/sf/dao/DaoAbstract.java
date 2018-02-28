package ua.amper.kharkov.sf.dao;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.util.LoadSqlExecuteUpdateFromFile;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static ua.amper.kharkov.sf.SFConstants.*;

public abstract class DaoAbstract {
    private static final Logger LOGGER = Logger.getLogger(DaoAbstract.class);
    protected String driver = null;  // драйвер JDBC
    protected String url = null;  // строка подключения
    protected Properties properties = null;  // свойства подключения объекта Connection

    public DaoAbstract(String driver) {
        this.driver = driver;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура регистрации драйвера JDBC
     */
    protected void RegisterDriverManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            LOGGER.error(MSG_DB_CONNECTINGDATABASE_ERROR + e.getMessage());

        } catch (IllegalAccessException e) {
            LOGGER.error(MSG_DB_CONNECTINGDATABASE_ERROR + e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(MSG_DB_CONNECTINGDATABASE_ERROR + e.getMessage());
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура определения строки подключения URL к серверу БД
     *
     * @param host     - имя компьютера
     * @param database - наименование БД (может быть пустой строкой)
     * @param port     - порт сервера
     */
    public abstract void setURL(String host, String database, int port);
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Функция получения объекта подключения
     *
     * @return Connection - объект подключения
     */
    public abstract Connection getConnection();
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура регистрации драйвера подключения к серверу СУБД JDBC и определения свойств
     *
     * @param login    - логин подключения
     * @param password - пароль подключения
     */
    public void Connect(String login, String password) {
        // Регистрация драйвера
        RegisterDriverManager();

        // Определение свойств подключения Connection
        properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("user", login);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура отключения от сервера БД
     *
     * @param connection объект подключения
     */
    public void Disconnect(Connection connection) {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            LOGGER.error(MSG_DB_DISCONNECTINGDATABASE_ERROR + e.getMessage());

        }
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Функция создания схемы
     *
     * @param schema наименование схемы
     * @return результат транзакции
     */
    public boolean createSchema(final String schema) {
        return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Функция удаления схемы
     *
     * @param schema наименование схемы
     * @return результат транзакции
     */
    public boolean dropSchema(final String schema) {
        return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Функция выполнения SQL-запроса
     *
     * @param sql текст запроса
     * @return результат выполнения запроса
     */
    public boolean execSQL(final String sql) {
        boolean result = false;
        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();
                statement.execute(sql);
                statement.close();
                statement = null;
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(SF_SQL_EXEC_SQL + "SQLException : code = " + String.valueOf(e.getErrorCode()) +
                    " - " + e.getMessage() + "\tSQL : " + sql);

        }
        return result;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}
